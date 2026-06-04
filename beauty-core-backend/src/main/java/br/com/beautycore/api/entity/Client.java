package br.com.beautycore.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal credit;
    private Boolean inAppointment;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}