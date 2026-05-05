package br.com.beautycore.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professionals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professional_seq")
    @SequenceGenerator(name = "professional_seq", sequenceName = "sq_professionals", allocationSize = 1)
    private Long id;

    private String name;
    private Boolean isActive;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "professional_specialty",
            joinColumns = @JoinColumn(name = "professional_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specializations = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    public void addSpecialty(Specialty specialty) {
        this.specializations.add(specialty);
    }

    public void removeSpecialty(Specialty specialty) {
        this.specializations.remove(specialty);
    }
}