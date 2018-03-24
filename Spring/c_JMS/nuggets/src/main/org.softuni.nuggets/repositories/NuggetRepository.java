package org.softuni.nuggets.repositories;

import org.softuni.nuggets.entities.Nugget;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NuggetRepository extends JpaRepository<Nugget, String> {

    @Query("SELECT n FROM Nugget AS n " +
            "WHERE lower(n.name) LIKE :pattern")
    Slice<Nugget> findIfNuggetIsFound(@Param("pattern") String pattern, Pageable pageable);
}
