package com.ecommerce.motoecom.repositories;

import com.ecommerce.motoecom.Model.AppRole;
import com.ecommerce.motoecom.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
