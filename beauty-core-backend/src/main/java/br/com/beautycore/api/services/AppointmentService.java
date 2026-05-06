package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.response.AppointmentResponseDTO;
import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findAll(Pageable pageable) {
        Page<Appointment> list = repository.findAll(pageable);
        return list.map(appointment -> new AppointmentResponseDTO(appointment));
    }
}