package com.fedorusha.appsstore.mapper;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class );
    UserDto toDTO(User user);
    User fromDTO(UserDto userDto);
}
