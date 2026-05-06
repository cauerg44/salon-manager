package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value =
            """
            SELECT * FROM clients c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    Page<Client> searchByName(String name, Pageable pageable);
}