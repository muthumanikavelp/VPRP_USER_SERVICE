package com.vprp.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message_bundle", schema="vprp_cache")
public class MessageBundle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lang_code")
	private char[] langCode = new char[2];
	@Column(name = "message_bundle_json")
	private String messageBundleJSON;
	
	
	public MessageBundle(char[] langCode, String messageBundleJSON) {
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

	public MessageBundle() {
		super();
	}
	
	
	
	
	
}
