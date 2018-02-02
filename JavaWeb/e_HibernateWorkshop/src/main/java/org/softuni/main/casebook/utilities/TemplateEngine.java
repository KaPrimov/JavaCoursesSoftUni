package org.softuni.main.casebook.utilities;

import java.util.Map;

public final class TemplateEngine {
	public TemplateEngine() {}
	
	public String renderHtml(String html, Map<String, String> viewData) {
		for (Map.Entry<String, String> data : viewData.entrySet()) {
			html = html.replace(String.format("${%s}", data.getKey()), data.getValue());
		}
		
		return html;
	}
}
