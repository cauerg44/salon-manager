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
import br.com.barberflow.api.services.exception.DomainException;
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

    @Transactional
    public AttendanceResponseDTO start(Long id) {
        Attendance attendance = repository.findById(id)
                .orElseThrow(() -> (new ResourceNotFoundException("Atendimento não encontrado.")));

        if (attendance.getAttendanceStatus() == AttendanceStatus.WAITING) {
            attendance.setAttendanceStatus(AttendanceStatus.IN_PROGRESS);
            attendance = repository.save(attendance);

            return new AttendanceResponseDTO(attendance);
        }
        else throw new DomainException("É possível iniciar atendimento apenas os clientes que estão em espera");
    }

    private void createDtoToEntity(Attendance entity, AttendanceInsertRequestDTO dto) {
        Barber barber = barberRepository.findById(dto.barberId())
                .orElseThrow(() -> (new ResourceNotFoundException("Procedimento ou serviço não encontrado.")));

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> (new ResourceNotFoundException("Procedimento ou serviço não encontrado.")));

        entity.setCreatedAt(LocalDateTime.now());
        entity.setFinishedAt(null);
        entity.setAttendanceStatus(AttendanceStatus.WAITING);

        entity.setBarber(barber);
        entity.setClient(client);

        entity.getProcedures().clear();
        for (long procedureId : dto.proceduresIds()) {
            Procedure procedure = procedureRepository.findById(procedureId)
                    .orElseThrow(() -> (new ResourceNotFoundException("Procedimento ou serviço não encontrado.")));
            entity.addProcedure(procedure);
        }

        entity.setPayment(null);
    }
}