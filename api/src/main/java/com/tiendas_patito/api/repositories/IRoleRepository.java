package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}