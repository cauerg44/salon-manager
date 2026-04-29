package br.com.barberflow.api.entity;

import br.com.barberflow.api.entity.enums.AttendanceStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus attendanceStatus;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal grossAmount;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(name = "tb_attendance_procedure",
            joinColumns = @JoinColumn(name = "attendance_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    private Set<Procedure> procedures = new HashSet<>();

    @OneToOne(mappedBy = "attendance", cascade = CascadeType.ALL)
    private Payment payment;

    public Attendance() {
    }

    public Attendance(Long id, Barber barber, Client client) {
        this.id = id;
        this.barber = barber;
        this.client = client;
        this.createdAt = LocalDateTime.now();
        this.finishedAt = null;
        this.attendanceStatus = AttendanceStatus.WAITING;
        this.grossAmount = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal calculateGrossAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Procedure procedure : procedures) {
            sum = sum.add(procedure.getBasePrice());
        }
        return sum;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void addProcedure(Procedure procedure) {
        this.procedures.add(procedure);
        this.grossAmount = calculateGrossAmount();
    }

    public void removeProcedure(Procedure procedure) {
        this.procedures.remove(procedure);
        this.grossAmount = calculateGrossAmount();
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment != null) {
            payment.setAttendance(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}