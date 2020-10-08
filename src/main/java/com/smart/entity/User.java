package com.smart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Name;
	@Column(unique = true)
	private String Email;
	private String imageUrl;
	private String password;
	@Column(length = 500)
	private String about;
	private String role;
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contact = new ArrayList<>();
	public User() {
		super();
			}
	public User(int id, String name, String email, String imageUrl, String password, String about, String role,
			boolean enabled) {
		super();
		this.id = id;
		Name = name;
		Email = email;
		this.imageUrl = imageUrl;
		this.password = password;
		this.about = about;
		this.role = role;
		this.enabled = enabled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", Name=" + Name + ", Email=" + Email + ", imageUrl=" + imageUrl + ", password="
				+ password + ", about=" + about + ", role=" + role + ", enabled=" + enabled + ", contact=" + contact
				+ "]";
	}
	
}
