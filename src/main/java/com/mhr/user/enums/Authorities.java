package com.mhr.user.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority {

	OP_ACCESS_ADMIN,
	OP_ACCESS_USER,
	OP_NEW_USER,
	OP_EDIT_USER,
	OP_DELETE_USER;

	@Override
	public String getAuthority() {
		return this.name();
	}
}
