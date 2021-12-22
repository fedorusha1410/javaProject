package com.fedorusha.appsstore.mapper;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.dto.InsertingAppDto;
import com.fedorusha.appsstore.model.Apps;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AppMapper {

    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    abstract ApplicationDto toDTO(Apps app);

    Apps fromDTO(ApplicationDto appDto);

    Apps fromDTO2(InsertingAppDto insertingAppDto);

}
