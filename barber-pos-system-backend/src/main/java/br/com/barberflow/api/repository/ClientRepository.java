package br.com.barberflow.api.repository;

import br.com.barberflow.api.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM tb_client c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            ORDER BY c.name
            """)
    Page<Client> searchByClientName(String name, Pageable pageable);
}
