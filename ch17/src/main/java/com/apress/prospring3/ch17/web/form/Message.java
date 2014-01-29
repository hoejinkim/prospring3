package com.apress.prospring3.ch17.web.form;

/**
 * MessageSource에서 가져온 메시지 및 뷰가 메시지 영역에 보여줄 메시지 타입을 저장하는
 * 커스텀 클래스
 * @author hjkim
 *
 */
public class Message {

	private String type;

	private String message;

	public Message() {
	}

	public Message(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
