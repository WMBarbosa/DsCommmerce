package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.dtos.UserDTO;
import com.barbosa.dscommerse.dtos.UserDetailsDTO;
import com.barbosa.dscommerse.entities.User;
import com.barbosa.dscommerse.mappers.UserMapper;
import com.barbosa.dscommerse.repositories.UserRepository;
import com.barbosa.dscommerse.service.serviceException.ResourceNotFoundException;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService userService;

    private Long existingId;
    private Long nonExistingId;
    private User user;
    private UserDTO userDTO;
    private String existingEmail;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 9999L;
        user = Factory.createUser();
        userDTO = Factory.createUserDTO();
        existingEmail = "maria@gmail.com";
    }

    @Test
    void loadUserByUsernameShouldReturnUserDetailsWhenEmailExists() {
        List<UserDetailsDTO> dtos = List.of(
                new UserDetailsDTO(existingEmail, user.getPassword(), 1L, "ROLE_CLIENT")
        );
        when(repository.searchUserAndRolesByEmail(existingEmail)).thenReturn(dtos);

        UserDetails result = userService.loadUserByUsername(existingEmail);

        assertNotNull(result);
        assertEquals(existingEmail, result.getUsername());
        assertEquals(1, result.getAuthorities().size());
        verify(repository).searchUserAndRolesByEmail(existingEmail);
    }

    @Test
    void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenEmailDoesNotExist() {
        when(repository.searchUserAndRolesByEmail(anyString())).thenReturn(List.of());

        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("naoexiste@email.com"));
        verify(repository).searchUserAndRolesByEmail("naoexiste@email.com");
    }

    @Test
    void findByIdShouldReturnUserDTOWhenIdExists() {
        when(repository.findById(existingId)).thenReturn(Optional.of(user));
        when(mapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.findById(existingId);

        assertNotNull(result);
        assertEquals(userDTO.getEmail(), result.getEmail());
        verify(repository).findById(existingId);
        verify(mapper).toDto(user);
    }

    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(nonExistingId));
        verify(repository).findById(nonExistingId);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void findAllShouldReturnPageOfUserDTO() {
        Page<User> userPage = new PageImpl<>(List.of(user));
        when(repository.findAll(any(PageRequest.class))).thenReturn(userPage);
        when(mapper.toDto(any(User.class))).thenReturn(userDTO);

        Page<UserDTO> result = userService.findAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(repository).findAll(any(PageRequest.class));
        verify(mapper).toDto(user);
    }
}
