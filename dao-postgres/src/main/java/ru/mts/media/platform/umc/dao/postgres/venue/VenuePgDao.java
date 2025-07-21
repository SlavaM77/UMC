package ru.mts.media.platform.umc.dao.postgres.venue;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSave;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class VenuePgDao implements VenueSot {

    private final VenuePgRepository repository;
    private final VenuePgMapper mapper;
    private final EventSot eventSot;

    @Override
    public Optional<Venue> getVenueByReferenceId(String id) {
        return Optional.of(id)
                .map(repository::findByReferenceId)
                .map(mapper::asModel)
                .map(venue -> {
                    venue.setEvents(eventSot.getEventsByVenueId(venue.getId()));
                    return venue;
                });
    }

    @Override
    public Optional<Venue> getVenueById(FullExternalId externalId) {
        return Optional.of(externalId)
                .map(mapper::asPk)
                .flatMap(repository::findById)
                .map(mapper::asModel)
                .map(venue -> {
                    venue.setEvents(eventSot.getEventsByVenueId(venue.getId()));
                    return venue;
                });
    }

    @EventListener
    public void handleVenueCreatedEvent(VenueSave evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }
}
