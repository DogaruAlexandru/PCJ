package com.example.appointmentservice.repository;

import com.example.appointmentservice.entity.AppointmentSummary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppointmentSummaryRepositoryImpl implements AppointmentSummaryRepository {

    private final List<AppointmentSummary> appointmentSummaries;

    public AppointmentSummaryRepositoryImpl(List<AppointmentSummary> appointmentSummaries) {
        this.appointmentSummaries = appointmentSummaries;
    }

    @Override
    public AppointmentSummary save(AppointmentSummary appointmentSummary) {
        findAppointmentSummaryById(appointmentSummary.getId()).ifPresent(i -> {
            throw new RuntimeException("AppointmentSummary with id " + i.getId() + " already exists!");
        });
        if (!appointmentSummary.getMechanic().getUserType().equals("MECHANIC")) {
            throw new RuntimeException("AppointmentSummary user is not a mechanic!");
        }
        this.appointmentSummaries.add(appointmentSummary);
        return appointmentSummary;
    }

    @Override
    public Optional<AppointmentSummary> findAppointmentSummaryById(String id) {
        return appointmentSummaries.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public AppointmentSummary getAppointmentSummaryById(String id) {
        return findAppointmentSummaryById(id).orElseThrow(
                () -> new RuntimeException("AppointmentSummary with id " + id + " not found!"));
    }

    @Override
    public List<AppointmentSummary> getAllAppointmentSummaries() {
        return this.appointmentSummaries;
    }

    @Override
    public AppointmentSummary updateAppointmentSummary(AppointmentSummary appointmentSummary) {
        AppointmentSummary existingAppointmentSummary = findAppointmentSummaryById(appointmentSummary.getId()).orElseThrow(
                () -> new RuntimeException("AppointmentSummary with id " + appointmentSummary.getId() + " not found!"));
        appointmentSummary.setAppointment(existingAppointmentSummary.getAppointment());
        appointmentSummary.setMechanic(existingAppointmentSummary.getMechanic());
        int index = appointmentSummaries.indexOf(existingAppointmentSummary);
        appointmentSummaries.remove(index);
        appointmentSummaries.add(index, appointmentSummary);

        return appointmentSummary;
    }

    @Override
    public boolean deleteAppointmentSummary(String id) {
        AppointmentSummary existingAppointmentSummary = findAppointmentSummaryById(id).orElseThrow(
                () -> new RuntimeException("AppointmentSummary with id " + id + " not found!"));
        return appointmentSummaries.remove(existingAppointmentSummary);
    }
}
