package br.com.beautycore.api.repository;

import br.com.beautycore.api.entity.JobItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobItemRepository extends JpaRepository<JobItem, Long> {
}
