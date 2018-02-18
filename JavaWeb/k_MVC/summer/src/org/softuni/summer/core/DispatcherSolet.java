package org.softuni.summer.core;

import org.softuni.broccolina.solet.*;
import org.softuni.javache.http.HttpStatus;

@WebSolet(route = "/*")
public class DispatcherSolet extends BaseHttpSolet {

    protected DispatcherSolet(SoletConfig soletConfig) {
        super(soletConfig);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void service(HttpSoletRequest request, HttpSoletResponse response) {
        response.setStatusCode(HttpStatus.OK);
        response.addHeader("Content-Type", "text/html");
        response.setContent("<h1>Hello World</h1>".getBytes());
//        super.service(request, response);
    }
}
