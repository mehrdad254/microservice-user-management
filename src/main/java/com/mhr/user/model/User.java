package com.mhr.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements Serializable,UserDetails{

	private static final long serialVersionUID = 4940181420373603881L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String emaile;
	
	@JsonIgnore
	private String password;
	
	private Boolean enabled = true;
	
	private String name;
	
	@Column(name = "created_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Roles> roles;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Long id, String emaile, String password, Boolean enabled, String name, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<Roles> roles) {
		super();
		this.id = id;
		this.emaile = emaile;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmaile() {
		return emaile;
	}



	public void setEmaile(String emaile) {
		this.emaile = emaile;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	public List<Roles> getRoles() {
		return roles;
	}



	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emaile;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Roles roles : roles)
			authorities.addAll(roles.getAuthorities());
		
		return authorities;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", emaile=" + emaile + ", password=" + password + ", enabled=" + enabled + ", name="
				+ name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", roles=" + roles + "]";
	}

	
}
