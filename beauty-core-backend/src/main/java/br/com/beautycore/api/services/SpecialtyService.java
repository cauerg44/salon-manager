package br.com.beautycore.api.services;

import br.com.beautycore.api.dto.response.SpecialtyResponseDTO;
import br.com.beautycore.api.entity.Specialty;
import br.com.beautycore.api.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository repository;

    @Transactional(readOnly = true)
    public List<SpecialtyResponseDTO> findAll() {
        List<Specialty> list = repository.findAll();
        return list.stream()
                .map(item -> new SpecialtyResponseDTO(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }
}
