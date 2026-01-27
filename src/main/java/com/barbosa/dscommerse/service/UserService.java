package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.UserDTO;
import com.barbosa.dscommerse.dtos.UserDetailsDTO;
import com.barbosa.dscommerse.entities.Role;
import com.barbosa.dscommerse.entities.User;
import com.barbosa.dscommerse.mappers.UserMapper;
import com.barbosa.dscommerse.repositories.UserRepository;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsDTO> result = repository.searchUserAndRolesByEmail(username);
		if (result.isEmpty()) {
			throw new UsernameNotFoundException("Email not found");
		}
		
		User user = new User();
		user.setEmail(result.getFirst().username());
		user.setPassword(result.getFirst().password());
        result.stream()
                .map(dto -> new Role(dto.roleId(), dto.authority()))
                .forEach(user::addRoles);
		
		return user;
	}

    protected User authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();

        String username = switch (principal) {
            case Jwt jwt -> jwt.getClaimAsString("username");
            case UserDetails userDetails -> userDetails.getUsername();
            case String str -> str;
            case null, default -> throw new IllegalStateException("Tipo de principal não suportado");
        };

        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        User user = authenticate();
        return mapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable){
        return repository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
