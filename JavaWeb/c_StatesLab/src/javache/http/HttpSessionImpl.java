package javache.http;

import java.util.HashMap;
import java.util.Map;

public class HttpSessionImpl implements HttpSession {
	
	Map<String, Map<String, Object>> allSessions;
	
	
	
	public HttpSessionImpl() {
		this.allSessions = new HashMap<>();
	}

	@Override
	public void setSessionData(String sessionId, Map<String, Object> data) {
		if (!this.allSessions.containsKey(sessionId)) {
			this.allSessions.put(sessionId, data);
		} else {
			for (Map.Entry<String, Object> kvp : data.entrySet()) {
				this.allSessions.get(sessionId).put(kvp.getKey(), kvp.getValue());				
			}
		}		
	}

	@Override
	public Map<String, Object> getSessionData(String sessionId) {
		return this.allSessions.get(sessionId);
	}

}
