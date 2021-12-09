package com.atos.userdirectory.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Required;

import com.atos.userdirectory.validation.Country;
import com.atos.userdirectory.validation.Username;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	/**
	 * User Entity
	 */

	// Customized annotation for username constraint validation @Username
	@Id
	@Username
	@Column(name = "username")
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private GenderType gender;

	@NotNull
	@Past
	@Column(name = "birth_date")
	private LocalDate birthDate;

	// Customized annotation for country constraint validation @Country
	@Country
	@Column(name = "country")
	private String country;

	// Pattern to accept only 10 numeric characters
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(name = "phone")
	private String phone;

	// Constructors

	public User() {
	}

	public User(String username, GenderType gender, LocalDate birthDate, String country, String phone) {
		this.username = username;
		this.gender = gender;
		this.birthDate = birthDate;
		this.country = country;
		this.phone = phone;
	}

	// Getters/Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// Override toString() method

	@Override
	public String toString() {
		return "User [username=" + username + ", gender=" + gender + ", birthDate=" + birthDate + ", country=" + country
				+ ", phone=" + phone + "]";
	}

}
