package com.example.appointmentservice.repository;

import com.example.appointmentservice.entity.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final List<Appointment> appointments;

    public AppointmentRepositoryImpl(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public Appointment save(Appointment appointment) {
        findAppointmentById(appointment.getId()).ifPresent(i -> {
            throw new RuntimeException("Appointment with id " + i.getId() + " already exists!");
        });
        if (!appointment.getCarOwner().getUserType().equals("CAR_OWNER")) {
            throw new RuntimeException("Appointment user is not a car owner!");
        }
        if (appointment.getStartDate().isAfter(appointment.getEndDate())) {
            throw new RuntimeException("Appointment start date can not be after end date");
        }
        findAppointmentBetweenDates(appointment.getStartDate()).ifPresent(i -> {
            throw new RuntimeException("Appointment with start date " + i.getStartDate() + " is already occupied!");
        });

        this.appointments.add(appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findAppointmentById(String id) {
        return appointments.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public Appointment getAppointmentById(String id) {
        return findAppointmentById(id).orElseThrow(
                () -> new RuntimeException("Appointment with id " + id + " not found!"));
    }

    @Override
    public Optional<Appointment> findAppointmentBetweenDates(LocalDateTime startDate) {
        return appointments.stream().filter(i -> i.getStartDate().isBefore(startDate)
                && i.getEndDate().isAfter(startDate)).findFirst();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return this.appointments;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = findAppointmentById(appointment.getId()).orElseThrow(
                () -> new RuntimeException("Appointment with id " + appointment.getId() + " not found!"));
        appointment.setCarOwner(existingAppointment.getCarOwner());
        int index = appointments.indexOf(existingAppointment);
        appointments.remove(index);
        appointments.add(index, appointment);

        return appointment;
    }

    @Override
    public boolean deleteAppointment(String id) {
        Appointment existingAppointment = findAppointmentById(id).orElseThrow(
                () -> new RuntimeException("Appointment with id " + id + " not found!"));
        return appointments.remove(existingAppointment);
    }
}

