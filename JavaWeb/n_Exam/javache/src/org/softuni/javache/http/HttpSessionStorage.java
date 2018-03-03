package org.softuni.javache.http;

public interface HttpSessionStorage {
    void setSession(String sessionId, HttpSession session);

    HttpSession getSession(String sessionId);

    void removeSession(String sessionId);

    void refreshSessions();
}
