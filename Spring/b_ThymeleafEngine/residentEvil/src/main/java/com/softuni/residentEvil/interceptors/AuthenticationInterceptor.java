package com.softuni.residentEvil.interceptors;

import com.softuni.residentEvil.annotations.PreAuthenticate;
import com.softuni.residentEvil.entities.User;
import com.softuni.residentEvil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;

    @Autowired
    public AuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if(handlerMethod.hasMethodAnnotation(PreAuthenticate.class)) {
                PreAuthenticate methodAnnotation = handlerMethod.getMethodAnnotation(PreAuthenticate.class);
                if (methodAnnotation.loggedIn()) {
                    Object username = request.getSession().getAttribute("username");
                    if (username == null) {
                        return false;
                    }
                    User user = this.userService.findByUsername(username.toString());
                    if (user.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(methodAnnotation.inRole().name()))) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
