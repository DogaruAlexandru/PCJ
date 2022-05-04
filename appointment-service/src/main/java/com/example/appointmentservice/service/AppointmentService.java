package com.example.appointmentservice.service;

import com.example.appointmentservice.entity.Appointment;
import com.example.appointmentservice.repository.AppointmentRepositoryImpl;
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
public class AppointmentService {

    private final AppointmentRepositoryImpl repository;
    private final RestTemplate template;
    @Value("${microservice.user-service.endpoints.endpoint.uri=http://USER-SERVICE/user/getUser/}")
    private String ENDPOINT_USER_URL;

    @Autowired
    @Lazy
    public AppointmentService(AppointmentRepositoryImpl repository, RestTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    public Appointment saveAppointment(Appointment appointment, String userId) {
        ResponseEntity<User> userResponse = template.getForEntity(ENDPOINT_USER_URL + userId, User.class);
        if (userResponse.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        appointment.setCarOwner(userResponse.getBody());
        return repository.save(appointment);
    }

    public Appointment getAppointmentById(String id) {
        return repository.getAppointmentById(id);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return repository.updateAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    public boolean deleteAppointment(String id) {
        return repository.deleteAppointment(id);
    }
}
