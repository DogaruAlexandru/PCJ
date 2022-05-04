package com.example.appointmentservice.service;

import com.example.appointmentservice.entity.Appointment;
import com.example.appointmentservice.entity.AppointmentSummary;
import com.example.appointmentservice.repository.AppointmentSummaryRepositoryImpl;
import com.example.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RefreshScope
public class AppointmentSummaryService {

    private final AppointmentSummaryRepositoryImpl repository;
    private final RestTemplate template;
    @Value("${microservice.user-service.endpoints.endpoint.uri=http://USER-SERVICE/user/getUser/}")
    private String ENDPOINT_USER_URL;
    @Value("${microservice.appointment-service.endpoints.endpoint.uri=http://APPOINTMENT-SERVICE/appointment/getAppointment/}")
    private String ENDPOINT_APPOINTMENT_URL;

    @Autowired
    @Lazy
    public AppointmentSummaryService(AppointmentSummaryRepositoryImpl repository, RestTemplate template) {
        this.repository = repository;
        this.template = template;
    }


    public AppointmentSummary saveAppointmentSummary(AppointmentSummary appointmentSummary, String appointmentId, String userId) {
        ResponseEntity<User> userResponse = template.getForEntity("http://USER-SERVICE/user/getUser/" + userId, User.class);
        ResponseEntity<Appointment> appointmentResponse = template.getForEntity("http://APPOINTMENT-SERVICE/appointment/getAppointment/" + appointmentId, Appointment.class);
        if (userResponse.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        if (appointmentResponse.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            throw new RuntimeException("Appointment with id " + appointmentId + " not found");
        }
        appointmentSummary.setMechanic(userResponse.getBody());
        appointmentSummary.setAppointment(appointmentResponse.getBody());
        return repository.save(appointmentSummary);
    }

    public AppointmentSummary getAppointmentSummaryById(String id) {
        return repository.getAppointmentSummaryById(id);
    }

    public AppointmentSummary updateAppointmentSummary(AppointmentSummary appointmentSummary) {
        return repository.updateAppointmentSummary(appointmentSummary);
    }

    public List<AppointmentSummary> getAllAppointmentSummaries() {
        return repository.getAllAppointmentSummaries();
    }

    public boolean deleteAppointmentSummary(String id) {
        return repository.deleteAppointmentSummary(id);
    }
}
