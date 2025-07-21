package ru.mts.media.platform.umc.domain.venue;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class VenueDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final VenueSot sot;
    private final VenueDomainServiceMapper mapper;

    public VenueSave save(FullExternalId id, SaveVenueInput input) {
        Optional<Venue> venueById = sot.getVenueById(id);
        VenueSave evt;
        if (venueById.isPresent()) {
            Venue venue = venueById.get();
            evt = new VenueSave(applyPatch(input).apply(venue));
        } else {
            Venue venue = mapper.toVenue(id, input);
            evt = new VenueSave(venue);
        }

        eventPublisher.publishEvent(evt);
        return evt;
    }

    private Function<Venue, Venue> applyPatch(SaveVenueInput updates) {
        return x -> mapper.patch(x, updates);
    }
}
