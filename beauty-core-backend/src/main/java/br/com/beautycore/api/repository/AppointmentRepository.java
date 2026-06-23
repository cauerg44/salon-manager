package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM appointments ap
        WHERE ap.appointment_status = :appointmentStatus
        ORDER BY ap.created_at DESC
    """)
    Page<Appointment> findAllByStatusAndOrderByCreatedAtDesc(Pageable pageable, String appointmentStatus);

    @Query(nativeQuery = true, value = """
        SELECT * FROM appointments ap
        WHERE ap.appointment_status != 'CANCELED' AND ap.is_paid = false
        ORDER BY ap.created_at DESC
    """)
    Page<Appointment> findAllAppointmentsNotPaid(Pageable pageable);
}