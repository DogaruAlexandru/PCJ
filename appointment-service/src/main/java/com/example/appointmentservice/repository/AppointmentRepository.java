package com.example.appointmentservice.repository;

import com.example.appointmentservice.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Optional<Appointment> findAppointmentById(String id);

    Appointment getAppointmentById(String id);

    Optional<Appointment> findAppointmentBetweenDates(LocalDateTime startDate);

    List<Appointment> getAllAppointments();

    Appointment updateAppointment(Appointment appointment);

    boolean deleteAppointment(String id);
}
