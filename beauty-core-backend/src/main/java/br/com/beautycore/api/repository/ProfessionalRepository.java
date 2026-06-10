package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Professional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    @Query(nativeQuery = true, value =
            """
            SELECT * FROM professionals prof
            WHERE LOWER(prof.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    Page<Professional> searchByName(String name, Pageable pageable);

    Optional<Professional> findByEmail(String email);

    List<Professional> findAllByIsActive(Boolean isActive);
}