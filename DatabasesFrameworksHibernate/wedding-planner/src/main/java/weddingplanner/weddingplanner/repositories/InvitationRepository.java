package weddingplanner.weddingplanner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import weddingplanner.weddingplanner.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
