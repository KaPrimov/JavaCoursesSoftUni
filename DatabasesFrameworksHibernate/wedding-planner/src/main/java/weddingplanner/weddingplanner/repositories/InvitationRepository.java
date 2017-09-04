package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weddingplanner.weddingplanner.entities.Invitation;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findAllByWeddingId(Long wedding_id);

}
