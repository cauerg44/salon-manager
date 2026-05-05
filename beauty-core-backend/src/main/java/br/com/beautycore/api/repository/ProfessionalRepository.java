package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}