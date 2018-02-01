package org.softuni.main.javache.http;


public interface HttpSessionStorage {

    void setSessionData(String sessionId, HttpSession session);

    HttpSession getSessionData(String sessionId);  
    
    void removeSession(String sessionId);
}
