package org.softuni.summer.core;

import org.softuni.broccolina.solet.BaseHttpSolet;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.broccolina.solet.HttpSoletResponse;
import org.softuni.broccolina.solet.WebSolet;
import org.softuni.javache.http.HttpStatus;

@WebSolet(route = "/*")
public class DispatcherSolet extends BaseHttpSolet {

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
