package br.com.beautycore.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class JobItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "service_seq", sequenceName = "sq_services", allocationSize = 1)
    private Long id;

    private String name;
    private BigDecimal basePrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}