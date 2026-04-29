package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.AttendanceInsertRequestDTO;
import br.com.barberflow.api.dto.response.AttendanceResponseDTO;
import br.com.barberflow.api.entity.Attendance;
import br.com.barberflow.api.entity.Barber;
import br.com.barberflow.api.entity.Client;
import br.com.barberflow.api.entity.Procedure;
import br.com.barberflow.api.entity.enums.AttendanceStatus;
import br.com.barberflow.api.repository.AttendanceRepository;
import br.com.barberflow.api.repository.BarberRepository;
import br.com.barberflow.api.repository.ClientRepository;
import br.com.barberflow.api.repository.ProcedureRepository;
import br.com.barberflow.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProcedureRepository procedureRepository;

    @Transactional
    public AttendanceResponseDTO create(AttendanceInsertRequestDTO dto) {
        Attendance entity = new Attendance();
        createDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new AttendanceResponseDTO(entity);
    }

    private void createDtoToEntity(Attendance entity, AttendanceInsertRequestDTO dto) {
        try {
            Barber barber = barberRepository.getReferenceById(dto.barberId());
            Client client = clientRepository.getReferenceById(dto.clientId());

            entity.setCreatedAt(LocalDateTime.now());
            entity.setFinishedAt(null);
            entity.setAttendanceStatus(AttendanceStatus.WAITING);

            entity.getProcedures().clear();

            for (long procedureId : dto.proceduresIds()) {
                Procedure procedure = procedureRepository.getReferenceById(procedureId);
                entity.addProcedure(procedure);
            }

            entity.setPayment(null);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Barbeiro ou cliente não encontrados.");
        }
    }
}