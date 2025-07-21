package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {

    private final EventSot sot;

    @DgsQuery
    public List<Event> eventsByVenueId(@InputArgument String venueId) {
        return sot.getEventsByVenueId(venueId);
    }

    @DgsQuery
    public List<Event> events(@InputArgument Integer limit) {
        return sot.getEvents(limit);
    }
}
