package org.ecommerce.ecommerceuserservice.mapper;

import org.ecommerce.ecommerceuserservice.dto.UserRequestDTO;
import org.ecommerce.ecommerceuserservice.dto.UserResponseDTO;
import org.ecommerce.ecommerceuserservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(User user);
}
