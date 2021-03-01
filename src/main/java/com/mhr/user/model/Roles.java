package com.mhr.user.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mhr.user.enums.Authorities;

@Entity
@Table(name = "roles" )
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ElementCollection(targetClass = Authorities.class , fetch = FetchType.EAGER)
	@Column(name = "authorities")
	private List<Authorities> authorities;

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(Long id, String name, List<Authorities> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + ", authorities=" + authorities + "]";
	}
	
	
}
