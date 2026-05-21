package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM appointments ap
        WHERE ap.appointment_status = :appointmentStatus
        ORDER BY ap.created_at DESC;
    """)
    Page<Appointment> findAllByStatusAndOrderByCreatedAtDesc(Pageable pageable, String appointmentStatus);
}