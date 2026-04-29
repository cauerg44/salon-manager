package br.com.barberflow.api.services;

import br.com.barberflow.api.dto.request.BarberRequestDTO;
import br.com.barberflow.api.dto.response.BarberResponseDTO;
import br.com.barberflow.api.entity.Barber;
import br.com.barberflow.api.repository.BarberRepository;
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
    public BarberResponseDTO insert(BarberRequestDTO dto) {
        Barber entity = new Barber();

        entity.setName(dto.name());
        entity.activate();

        entity = repository.save(entity);
        return new BarberResponseDTO(entity);
    }
}