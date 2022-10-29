package com.vprp.user.data.dto;
public class MessageBundleResponse {

	private char[] langCode = new char[2];
	private String messageBundleJSON;
	
	
	public MessageBundleResponse(char[] langCode, String messageBundleJSON) {
		super();
		this.langCode = langCode;
		this.messageBundleJSON = messageBundleJSON;
	}
	
	public char[] getLangCode() {
		return langCode;
	}
	public void setLangCode(char[] langCode) {	
		this.langCode = langCode;
	}
	public String getMessageBundleJSON() {
		return messageBundleJSON;
	}
	public void setMessageBundleJSON(String messageBundleJSON) {
		this.messageBundleJSON = messageBundleJSON;
	}
	
	
	
}
