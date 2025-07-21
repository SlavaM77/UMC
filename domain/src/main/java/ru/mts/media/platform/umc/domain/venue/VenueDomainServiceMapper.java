package ru.mts.media.platform.umc.domain.venue;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface VenueDomainServiceMapper {

    @Mapping(target = "id", source = "id.externalId")
    @Mapping(target = "externalId", source = "id")
    Venue toVenue(FullExternalId id, SaveVenueInput input);

    Venue patch(@MappingTarget Venue src, SaveVenueInput updates);
}
