package org.softuni.broccolina.util;

import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.broccolina.solet.HttpSoletResponse;
import org.softuni.javache.http.*;

import java.util.UUID;

public final class SessionManager {
    private static final String SESSION_TOKEN = "Javache";

    private HttpSessionStorage sessionStorage;

    public SessionManager() {
        this.sessionStorage = new HttpSessionStorageImpl();
    }

    public void initializeSession(HttpSoletRequest request) throws IllegalAccessException {
        HttpSession serverSession = null;

        if (request.getCookies().containsKey(SESSION_TOKEN)
                && this.sessionStorage.getSession(request
                .getCookies()
                .get(SESSION_TOKEN)
                .getValue()) != null) {

            String sessionId = request
                    .getCookies()
                    .get(SESSION_TOKEN)
                    .getValue();

            serverSession = this.sessionStorage.getSession(sessionId);

        } else {
            serverSession = new HttpSessionImpl(UUID.randomUUID().toString());

            this.sessionStorage.setSession(serverSession.getId(), serverSession);
        }

        request.setSession(serverSession);
    }

    public void attachSession(HttpSoletRequest request, HttpSoletResponse response) {
        HttpSession newSession = request.getSession();

        if (!newSession.isValid()) {
            response.addCookie(new HttpCookieImpl(SESSION_TOKEN, "token=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT"));

            return;
        }


        this.sessionStorage.setSession(newSession.getId(), newSession);
        response.addCookie(new HttpCookieImpl(SESSION_TOKEN, newSession.getId()));
    }

    public void refreshSessions() {
        this.sessionStorage.refreshSessions();
    }
}
