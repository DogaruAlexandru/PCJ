package com.example.appointmentservice.repository;

import com.example.appointmentservice.entity.AppointmentSummary;

import java.util.List;
import java.util.Optional;

public interface AppointmentSummaryRepository {
    AppointmentSummary save(AppointmentSummary appointmentSummary);

    Optional<AppointmentSummary> findAppointmentSummaryById(String id);

    AppointmentSummary getAppointmentSummaryById(String id);

    List<AppointmentSummary> getAllAppointmentSummaries();

    AppointmentSummary updateAppointmentSummary(AppointmentSummary appointmentSummary);

    boolean deleteAppointmentSummary(String id);
}
