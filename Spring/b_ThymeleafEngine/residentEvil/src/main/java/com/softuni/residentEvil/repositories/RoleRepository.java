package com.softuni.residentEvil.repositories;

import com.softuni.residentEvil.entities.Role;
import com.softuni.residentEvil.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleEnum role);
}
