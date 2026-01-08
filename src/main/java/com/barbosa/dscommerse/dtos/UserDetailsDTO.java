package com.barbosa.dscommerse.dtos;

public record UserDetailsDTO(String username, String password, Long roleId, String authority) {
}
