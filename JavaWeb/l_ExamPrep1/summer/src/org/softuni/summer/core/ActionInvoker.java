package org.softuni.summer.core;

import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.broccolina.solet.HttpSoletResponse;
import org.softuni.javache.http.HttpResponse;
import org.softuni.summer.api.Model;
import org.softuni.summer.api.PathVariable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;

public final class ActionInvoker {
    private boolean isPrimitiveOrWrapperOrString(Class<?> clazz) {
        return clazz.isPrimitive()
                || clazz.getSimpleName().equals(Integer.class.getSimpleName())
                || clazz.getSimpleName().equals(Long.class.getSimpleName())
                || clazz.getSimpleName().equals(Double.class.getSimpleName())
                || clazz.getSimpleName().equals(Boolean.class.getSimpleName())
                || clazz.getSimpleName().equals(String.class.getSimpleName());
    }

    private Class<?> getWrapper(Class<?> clazz) {
        if(!clazz.isPrimitive()) return clazz;
        if(clazz.getSimpleName().equals(int.class.getSimpleName())) return Integer.class;
        if(clazz.getSimpleName().equals(double.class.getSimpleName())) return Double.class;
        if(clazz.getSimpleName().equals(boolean.class.getSimpleName())) return Boolean.class;
        if(clazz.getSimpleName().equals(long.class.getSimpleName())) return Long.class;
        return String.class;
    }

    private boolean isPathVariable(Parameter parameter) {
        return Arrays.stream(parameter
                .getAnnotations())
                .anyMatch(x -> x.annotationType().getSimpleName().equals(
                        PathVariable.class.getSimpleName()));
    }

    private boolean isModel(Parameter parameter) {
        return parameter.getType().getSimpleName()
                .equals(Model.class.getSimpleName());
    }

    private boolean isRequest(Parameter parameter) {
        return Arrays.stream(parameter.getType().getInterfaces())
                .anyMatch(y -> y.getSimpleName()
                        .equals(HttpSoletRequest.class.getSimpleName()))
                || parameter.getType().getSimpleName().equals(HttpSoletRequest.class.getSimpleName());
    }

    private boolean isResponse(Parameter parameter) {
        return Arrays.stream(parameter.getType().getInterfaces())
                .anyMatch(y -> y.getSimpleName()
                        .equals(HttpSoletResponse.class.getSimpleName()))
                || parameter.getType().getSimpleName().equals(HttpSoletResponse.class.getSimpleName());
    }

    private Object[] getActionArguments(ControllerActionPair cap, Model templateModel
    , HttpSoletRequest request, HttpSoletResponse response, Object bindingModel) {
        Object[] actionArguments = new Object[cap.getAction().getParameterCount()];

        Iterator parametersIterator = cap.getParameters().iterator();

        for (int i = 0; i < cap.getAction().getParameterCount(); i++) {
            Parameter parameter = cap.getAction().getParameters()[i];

            if(this.isPathVariable(parameter) && parametersIterator.hasNext()) {
                actionArguments[i] = parametersIterator.next();
            } else if(this.isModel(parameter)) {
                actionArguments[i] = templateModel;
            } else if(this.isRequest(parameter)) {
                actionArguments[i] = request;
            } else if(this.isResponse(parameter)) {
                actionArguments[i] = response;
            } else {
                actionArguments[i] = bindingModel;
            }
        }
        
        return actionArguments;
    }

    public String invokeAction(ControllerActionPair cap, Model templateModel, HttpSoletRequest request, HttpSoletResponse response) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Parameter bindingModelParameter =
                Arrays.stream(cap
                        .getAction()
                        .getParameters())
                .filter(x -> !this.isPrimitiveOrWrapperOrString(x.getType()))
                .filter(x -> !this.isModel(x))
                .filter(x -> !this.isRequest(x))
                .filter(x -> !this.isResponse(x))
                .findFirst()
                .orElse(null);

        if(bindingModelParameter != null) {
            Object bindingModelInstance =
                    bindingModelParameter
                    .getType()
                    .getConstructor()
                    .newInstance();

            Arrays.stream(bindingModelParameter
                    .getType()
                    .getDeclaredFields())
                    .filter(x -> this.isPrimitiveOrWrapperOrString(x.getType()))
                    .forEach(x -> {
                        if(!request.getBodyParameters().containsKey(x.getName())) {
                            throw new IllegalArgumentException();
                        }

                        x.setAccessible(true);

                        String bodyParameterValue = request
                                .getBodyParameters().get(x.getName());

                        try {
                            Object fieldValue =
                                    this.getWrapper(x.getType())
                                            .getConstructor(String.class)
                                            .newInstance(bodyParameterValue);

                            x.set(bindingModelInstance, fieldValue);
                        } catch (InstantiationException
                                | IllegalAccessException
                                | NoSuchMethodException
                                | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

            Object[] actionArguments =
                    this.getActionArguments(cap, templateModel, request, response, bindingModelInstance);

            return cap
                    .getAction()
                    .invoke(cap.getController(), actionArguments)
                    .toString();
        } else {
            Object[] actionArguments =
                    this.getActionArguments(cap, templateModel, request, response, null);

            return cap
                    .getAction()
                    .invoke(cap.getController(), actionArguments)
                    .toString();
        }
    }
}
