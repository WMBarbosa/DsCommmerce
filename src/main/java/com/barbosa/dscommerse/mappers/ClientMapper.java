
package com.barbosa.dscommerse.mappers;

import com.barbosa.dscommerse.dtos.ClientDTO;
import com.barbosa.dscommerse.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDto(User client);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(ClientDTO clientDTO);
}