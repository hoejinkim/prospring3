package com.apress.prospring3.ch10.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	private static final long serialVersionUID = -9192868903306291216L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	/**
	 * mappedBy 속성은 ContactTelDetail 클래스에서 관계를 제공하는 프로퍼티를 나타낸다. 즉,
	 * CONTACT_TEL_DETAIL 테이블의 외래키 정의와 연결된다.
	 * 
	 * cascade 속성은 업데이트 작업이 자식까지 전달할지 지정한다.
	 * 
	 * orphanRemoval 속성은 연락처 전화번호 상세를 업데이트 후 Set에 남아 있지 말아야 할 엔트리를 데이터베이스에서 삭제할지
	 * 여부를 지정한다.
	 */
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContactTelDetail> contactTelDetails;

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", joinColumns = @JoinColumn(name = "CONTACT_ID"), inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	private Set<Hobby> hobbies;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Set<ContactTelDetail> getContactTelDetails() {
		return contactTelDetails;
	}

	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public String toString() {
		return "Contact [id=" + id + ", version=" + version + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + "]";
	}

}
