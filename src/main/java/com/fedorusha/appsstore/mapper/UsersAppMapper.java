package com.fedorusha.appsstore.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersAppMapper {
    UsersAppMapper INSTANCE = Mappers.getMapper(UsersAppMapper.class);


}
