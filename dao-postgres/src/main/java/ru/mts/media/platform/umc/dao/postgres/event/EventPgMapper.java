package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = VenuePgMapper.class)
public interface EventPgMapper {

    @Mapping(target = "startTime", source = "startTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "dd-MM-yyyy HH:mm")
    Event asModel(EventPgEntity eventPg);

    List<Event> asModels(Collection<EventPgEntity> eventPgs);

    @Mapping(target = "startTime", source = "startTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "dd-MM-yyyy HH:mm")
    EventPgEntity asEntity(Event event);
}
