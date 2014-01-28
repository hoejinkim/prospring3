package com.apress.prospring3.ch10.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "contact_tel_detail")
public class ContactTelDetail implements Serializable {

	private static final long serialVersionUID = 8047402277912604292L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@Column(name = "TEL_TYPE")
	private String telType;

	@Column(name = "TEL_NUMBER")
	private String telNumber;

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	private Contact contact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTelType() {
		return telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String toString() {
		return "ContactTelDetail [id=" + id + ", version=" + version
				+ ", telType=" + telType + ", telNumber=" + telNumber + "]";
	}

}
