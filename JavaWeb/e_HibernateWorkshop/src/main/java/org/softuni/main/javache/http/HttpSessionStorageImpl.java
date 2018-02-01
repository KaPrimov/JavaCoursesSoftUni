package org.softuni.main.javache.http;

import java.util.HashMap;
import java.util.Map;

public class HttpSessionStorageImpl implements HttpSessionStorage {

    private Map<String, HttpSession> allSessions;

    public HttpSessionStorageImpl() {
        this.allSessions = new HashMap<>();
    }

    @Override
    public void setSessionData(String sessionId,
                               HttpSession session) {
        if (!this.allSessions.containsKey(sessionId)) {
            this.allSessions.put(sessionId, session);
        } else {
            for (Map.Entry<String, Object> kvp : session.getAttributes().entrySet()) {
                this.getSessionData(sessionId).addAttribute(kvp.getKey(), kvp.getValue());
            }
        }
    }

    @Override
    public HttpSession getSessionData(String sessionId) {
        return this.allSessions.get(sessionId);
    }
    
    @Override
    public void removeSession(String sessionId) {
        this.allSessions.remove(sessionId);
    }
}
