package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.AppointmentServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentServiceRepository extends JpaRepository<AppointmentServiceEntity, Long> {
}
