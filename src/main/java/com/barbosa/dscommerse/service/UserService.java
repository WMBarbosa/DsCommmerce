package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.UserDetailsDTO;
import com.barbosa.dscommerse.entities.Role;
import com.barbosa.dscommerse.entities.User;
import com.barbosa.dscommerse.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsDTO> result = repository.searchUserAndRolesByEmail(username);
		if (result.isEmpty()) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		User user = new User();
		user.setEmail(result.getFirst().username());
		user.setPassword(result.getFirst().password());
		for (UserDetailsDTO userDetailsDTO : result) {
			user.addRoles(new Role(userDetailsDTO.roleId(), userDetailsDTO.authority()));
		}
		
		return user;
	}
}
