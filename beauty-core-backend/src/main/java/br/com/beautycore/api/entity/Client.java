package br.com.beautycore.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Client extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "phone", length = 11, nullable = false, unique = true)
    private String phone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "credit", nullable = false, precision = 5, scale = 2)
    private BigDecimal credit;

    @Column(name = "in_appointment", columnDefinition = "DEFAULT FALSE NOT NULL")
    private Boolean inAppointment;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments = new ArrayList<>();
}