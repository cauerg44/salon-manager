package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.BarberInsertRequestDTO;
import br.com.barberflow.api.dto.request.BarberPatchRequestDTO;
import br.com.barberflow.api.dto.response.BarberResponseDTO;
import br.com.barberflow.api.entity.Barber;
import br.com.barberflow.api.repository.BarberRepository;
import br.com.barberflow.api.services.exception.DomainException;
import br.com.barberflow.api.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberService {

    @Autowired
    private BarberRepository repository;

    @Transactional(readOnly = true)
    public List<BarberResponseDTO> findAll() {
        List<Barber> list = repository.findAllByOrderByIsActiveDesc();
        return list.stream().map(barber -> new BarberResponseDTO(barber)).collect(Collectors.toList());
    }

    @Transactional
    public BarberResponseDTO insert(BarberInsertRequestDTO dto) {
        Barber entity = new Barber();

        entity.setName(dto.name());
        entity.activate();

        entity = repository.save(entity);
        return new BarberResponseDTO(entity);
    }

    @Transactional
    public BarberResponseDTO patch(Long id, BarberPatchRequestDTO dto) {
        try {
            Barber entity = repository.getReferenceById(id);
            entity.setName(dto.name());
            entity = repository.save(entity);
            return new BarberResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Barbeiro não encontrado com id: " + id);
        }
    }

    @Transactional
    public BarberResponseDTO deactivate(Long id) {
        Barber barber = repository.findById(id).orElseThrow(() -> (
                new ResourceNotFoundException("Barbeiro não encontrado")));

        if (barber.getActive() == false) {
            throw new DomainException("Barbeiro já inativo.");
        }

        barber.deactivate();
        barber = repository.save(barber);
        return new BarberResponseDTO(barber);
    }

    @Transactional
    public BarberResponseDTO activate(Long id) {
        Barber barber = repository.findById(id).orElseThrow(() -> (
                new ResourceNotFoundException("Barbeiro não encontrado")));

        if (barber.getActive() == true) {
            throw new DomainException("Barbeiro já ativo.");
        }

        barber.activate();
        barber = repository.save(barber);
        return new BarberResponseDTO(barber);
    }
}