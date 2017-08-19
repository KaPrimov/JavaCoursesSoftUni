package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByName(String name);
}
