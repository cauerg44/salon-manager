package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
