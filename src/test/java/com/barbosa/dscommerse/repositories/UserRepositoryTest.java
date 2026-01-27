package com.barbosa.dscommerse.repositories;

import com.barbosa.dscommerse.dtos.UserDetailsDTO;
import com.barbosa.dscommerse.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private String existingEmail;
    private String nonExistingEmail;

    @BeforeEach
    void setUp() {
        existingEmail = "maria@gmail.com";
        nonExistingEmail = "naoexiste@email.com";
    }

    @Test
    void findByEmailShouldReturnUserWhenEmailExists() {
        Optional<User> result = userRepository.findByEmail(existingEmail);
        assertTrue(result.isPresent());
        assertEquals(existingEmail, result.get().getEmail());
    }

    @Test
    void findByEmailShouldReturnEmptyWhenEmailDoesNotExist() {
        Optional<User> result = userRepository.findByEmail(nonExistingEmail);
        assertTrue(result.isEmpty());
    }

    @Test
    void searchUserAndRolesByEmailShouldReturnNonEmptyListWhenEmailExists() {
        List<UserDetailsDTO> result = userRepository.searchUserAndRolesByEmail(existingEmail);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(existingEmail, result.get(0).username());
    }

    @Test
    void searchUserAndRolesByEmailShouldReturnEmptyListWhenEmailDoesNotExist() {
        List<UserDetailsDTO> result = userRepository.searchUserAndRolesByEmail(nonExistingEmail);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllShouldReturnPageOfUsers() {
        Page<User> result = userRepository.findAll(PageRequest.of(0, 10));
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
    }
}
