package ru.mts.media.platform.umc.dao.postgres.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.domain.event.EventSave;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventPgDao implements EventSot {

    private final EventPgRepository repository;
    private final EventPgMapper mapper;

    @Override
    public List<Event> getEventsByVenueId(String id) {
        List<EventPgEntity> entities = repository.findAllByVenueReferenceId(id);
        return mapper.asModels(entities);
    }

    @Override
    public List<Event> getEvents(Integer limit) {
        if (limit != null) {
            Pageable pageable = PageRequest.of(0, limit);
            Page<EventPgEntity> entities = repository.findAll(pageable);
            return mapper.asModels(entities.getContent());
        } else {
            List<EventPgEntity> entities = repository.findAll();
            return mapper.asModels(entities);
        }
    }

    @EventListener
    public void handleEventSaveEvent(EventSave evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }
}
