package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPgRepository extends JpaRepository<EventPgEntity, Long> {

    @Query("SELECT e FROM EventPgEntity e JOIN e.venues v WHERE v.referenceId = :referenceId")
    List<EventPgEntity> findAllByVenueReferenceId(String referenceId);
}
