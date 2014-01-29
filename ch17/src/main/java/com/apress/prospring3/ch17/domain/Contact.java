package com.apress.prospring3.ch17.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {

	private static final long serialVersionUID = -2732543103971915952L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION")
	private int version;

	@NotEmpty(message = "{validation.firstname.NotEmpty.message}")
	@Size(min = 3, max = 60, message = "{validation.firstname.Size.message}")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotEmpty(message = "{validation.lastname.NotEmpty.message}")
	@Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * JPA 제공자에게 이 칼럼이 대규모 객체 컬럼임을 알려준다.
	 * 사진 정보가 필요 없는 클래스를 로드 할 때는 성능에 영향을 주지 않게 끔 이 프로퍼티를 늦게 가져오도록 한다.
	 */
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "PHOTO")
	private byte[] photo;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * 비영속성 프로퍼티
	 * 이 프로퍼티는 프론트엔드에서 보여주는 용도로만 사용한다.
	 * @return
	 */
	@Transient
	public String getBirthDateString() {
		String birthDateString = "";

		if (birthDate != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			birthDateString = dateFormat.format(birthDate);
		}

		return birthDateString;
	}

	public String toString() {
		return "Contact [id=" + id + ", version=" + version + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", description=" + description + ", photo="
				+ Arrays.toString(photo) + "]";
	}

}
