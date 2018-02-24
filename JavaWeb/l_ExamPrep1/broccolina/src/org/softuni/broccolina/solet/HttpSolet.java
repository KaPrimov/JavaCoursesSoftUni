package org.softuni.broccolina.solet;

public interface HttpSolet {
    void init();

    boolean isInitialized();

    SoletConfig getSoletConfig();

    void service(HttpSoletRequest request, HttpSoletResponse response);

    void doGet(HttpSoletRequest request, HttpSoletResponse response);

    void doPost(HttpSoletRequest request, HttpSoletResponse response);

    void doPut(HttpSoletRequest request, HttpSoletResponse response);

    void doDelete(HttpSoletRequest request, HttpSoletResponse response);
}
