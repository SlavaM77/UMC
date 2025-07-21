package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDomainService {

    private final ApplicationEventPublisher eventPublisher;
    private final VenueSot venueSot;
    private final EventDomainServiceMapper mapper;

    public EventSave save(String venueReferenceId, SaveEventInput input) {
        Venue venue = venueSot.getVenueByReferenceId(venueReferenceId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Venue with referenceId: '%s' not found", venueReferenceId)));

        Event event = mapper.toEvent(input);

        if (event.getVenues() != null) {
            event.getVenues().add(venue);
        } else {
            event.setVenues(List.of(venue));
        }

        EventSave evt = new EventSave(event);
        eventPublisher.publishEvent(evt);
        return evt;
    }
}
