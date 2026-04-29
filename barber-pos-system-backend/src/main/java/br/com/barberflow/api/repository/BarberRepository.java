package br.com.barberflow.api.repository;

import br.com.barberflow.api.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberRepository extends JpaRepository<Barber, Long> {

    List<Barber> findAllByOrderByIsActiveDesc();
}