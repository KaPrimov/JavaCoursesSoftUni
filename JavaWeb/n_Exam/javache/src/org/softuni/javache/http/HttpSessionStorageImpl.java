package org.softuni.javache.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpSessionStorageImpl implements HttpSessionStorage {

    private Map<String, HttpSession> allSessions;

    public HttpSessionStorageImpl() {
        this.allSessions = new HashMap<>();
    }

    @Override
    public void setSession(String sessionId,
                               HttpSession session) {
        if (!this.allSessions.containsKey(sessionId)) {
            this.allSessions.put(sessionId, session);
        } else {
            for(Map.Entry<String, Object> attribute
                    : session
                    .getAttributes()
                    .entrySet()) {
                this.getSession(sessionId)
                        .addAttribute(
                        attribute.getKey()
                        , attribute.getValue());
            }
        }
    }

    @Override
    public HttpSession getSession(String sessionId) {
        if(!this.allSessions.containsKey(sessionId)) {
            return null;
        }

        return this.allSessions.get(sessionId);
    }

    @Override
    public void removeSession(String sessionId) {
        this.allSessions.remove(sessionId);
    }

    @Override
    public void refreshSessions() {
        List<String> sessionIdsToRemove = new ArrayList<String>();

        for (Map.Entry<String,HttpSession> sessionEntry : this.allSessions.entrySet()) {
            if(!sessionEntry.getValue().isValid()) {
                sessionIdsToRemove.add(sessionEntry.getValue().getId());
            }
        }

        for (String id : sessionIdsToRemove) {
            this.removeSession(id);
        }
    }
}
