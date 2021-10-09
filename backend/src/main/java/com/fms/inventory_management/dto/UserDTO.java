package com.fms.inventory_management.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fms.inventory_management.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String email;
	private String office;
	private String password;

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		office = entity.getOffice();
		password = entity.getPassword();
	}

}
