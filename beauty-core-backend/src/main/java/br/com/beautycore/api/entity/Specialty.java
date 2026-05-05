package br.com.beautycore.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "specializations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialty_seq")
    @SequenceGenerator(name = "specialty_seq", sequenceName = "sq_specializations", allocationSize = 1)
    private Long id;

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}