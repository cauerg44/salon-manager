package br.com.beautycore.api.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isActive;
    private Boolean isWorking;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "professional_specialty",
            joinColumns = @JoinColumn(name = "professional_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specializations = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addSpecialty(Specialty specialty) {
        this.specializations.add(specialty);
    }

    public void removeSpecialty(Specialty specialty) {
        this.specializations.remove(specialty);
    }
}