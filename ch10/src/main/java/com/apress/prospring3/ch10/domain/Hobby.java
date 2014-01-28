package com.apress.prospring3.ch10.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby implements Serializable {

	private static final long serialVersionUID = 8049074477165040371L;

	@Id
	@Column(name = "HOBBY_ID")
	private String hobbyId;

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "HOBBY_ID"), inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
	private Set<Contact> contacts;

	public String getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public String toString() {
		return "Hobby [hobbyId=" + hobbyId + "]";
	}

}
