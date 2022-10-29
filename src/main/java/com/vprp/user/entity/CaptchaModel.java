package com.vprp.user.entity;
import javax.persistence.*;

@Entity
@Table(name = "captcha", schema = "nrlm_security")
public class CaptchaModel {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "answer")
	private String answer;

	public CaptchaModel() {
		
	}

	public CaptchaModel(String answer) {
		this.answer = answer;
	}

	public Long getId() {
		return this.id;
	}

	public String getAnswer() {
		return this.answer;
	}
}