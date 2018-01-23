package javache.http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javache.WebConstants;

public class HttpResponseImpl implements HttpResponse {

	private Map<String, String> headers;
	private byte[] content;
	private int statusCode;	
	private Map<Integer, String> statusCodes;
	private Map<String, String> supportedContentTypes;
	
	public HttpResponseImpl() {
		this.headers = new HashMap<>();
		this.seedStatusCodes();
		this.supportedContentTypes = new HashMap<>();
	}

	private void seedStatusCodes() {
		this.statusCodes = new HashMap<>();
		this.statusCodes.put(WebConstants.OK_STATUS_CODE, WebConstants.OK_STATUS);
		this.statusCodes.put(WebConstants.FOUND_STATUS_CODE, WebConstants.FOUND_STATUS);
		this.statusCodes.put(WebConstants.NOT_MODIFIED_STATUS_CODE, WebConstants.NOT_MODIFIED_STATUS);
		this.statusCodes.put(WebConstants.BAD_REQUEST_STATUS_CODE, WebConstants.BAD_REQUEST_STATUS);
		this.statusCodes.put(WebConstants.UNAUTHORIZED_STATUS_CODE , WebConstants.UNAUTHORIZED_STATUS);
		this.statusCodes.put(WebConstants.NOT_FOUND_CODE , WebConstants.NOT_FOUND_STATUS);
		this.statusCodes.put(WebConstants.INTERNAL_SERVER_ERROR_STATUS_CODE, WebConstants.INTERNAL_SERVER_ERROR_STATUS);
		
	}

	@Override
	public Map<String, String> getHeaders() {
		return this.headers;
	}

	@Override
	public int getStatusCode() {
		return this.statusCode;
	}

	@Override
	public byte[] getContent() {
		return this.content;
	}

	@Override
	public byte[] getBytes() {
		StringBuilder response = new StringBuilder();
		this.generateResponseLine(response);
		this.generateResponseHeaders(response);
		byte[] responseData = response.toString().getBytes();
		byte[] totalData = new byte[responseData.length + this.content.length];
		System.arraycopy(responseData, 0, totalData, 0, responseData.length);
		System.arraycopy(this.content, 0, totalData, responseData.length, this.content.length);
		return totalData;
	}

	private void generateResponseHeaders(StringBuilder response) {
		response.append("Server: Javacje/0.0.1").append(System.lineSeparator())
		.append("Date: ").append(new Date()).append(System.lineSeparator())
		.append("Accept-Ranges: ").append("bytes").append(System.lineSeparator())
		.append(this.appendHeaders())
		.append(System.lineSeparator());
	}

	private String appendHeaders() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> header : this.headers.entrySet()) {
			sb.append(header.getKey()).append(": ").append(header.getValue()).append(System.lineSeparator());
		}
		
		return sb.toString();
	}
		
//	private String generateExtensionType() {
//		if (this.extension.equals("jpeg") || this.extension.equals("jpg")) {
//			return "image/jpeg";
//		} else if (this.extension.equals("png")) {
//			return "image/png";
//		}
//		return "text/html";
//	}

	private void generateResponseLine(StringBuilder response) {
		response.append("HTTP/1.1")
			.append(this.statusCodes.containsKey(this.statusCode) ? this.statusCode : WebConstants.BAD_REQUEST_STATUS_CODE)
			.append(this.statusCodes.getOrDefault(this.statusCode, WebConstants.BAD_REQUEST_STATUS))
			.append(System.lineSeparator());
			
	}

	@Override
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		
	}

	@Override
	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public void addHeader(String header, String value) {
		this.headers.putIfAbsent(header, value);		
	}
}
