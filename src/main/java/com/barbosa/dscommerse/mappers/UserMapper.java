package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.UserDTO;
import com.barbosa.dscommerse.entities.Role;
import com.barbosa.dscommerse.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToStrings")
    UserDTO toDto(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);

    @Named("mapRolesToStrings")
    default List<String> mapRolesToStrings(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of();
        }
        return roles.stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList());
    }

}
