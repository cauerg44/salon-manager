package br.com.beautycore.api.converter;

import br.com.beautycore.api.entity.Appointment;
import br.com.beautycore.api.entity.Client;
import br.com.beautycore.api.entity.Professional;
import br.com.beautycore.api.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentConverter {

    public static Appointment createDtoToEntityConverter(Professional professional, Client client) {

        Appointment entity = new Appointment();

        entity.setProfessional(professional);

        client.setInAppointment(false);
        entity.setClient(client);

        entity.setAppointmentStatus(AppointmentStatus.WAITING);

        entity.setIsPaid(false);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setFinishedAt(null);

        return entity;
    }
}