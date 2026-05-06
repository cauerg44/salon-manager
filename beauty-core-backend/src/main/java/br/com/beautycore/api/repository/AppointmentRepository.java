package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Page<Appointment> findAllByOrderByCreatedAtDesc(Pageable pageable);
}