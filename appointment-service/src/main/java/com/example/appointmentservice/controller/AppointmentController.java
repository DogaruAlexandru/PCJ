package com.example.appointmentservice.controller;

import com.example.appointmentservice.entity.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @PostMapping("/saveAppointment/{userId}")
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(service.saveAppointment(appointment, userId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PutMapping("/updateAppointment")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
        try {
            return ResponseEntity.ok(service.updateAppointment(appointment));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String appointmentId) {
        try {
            return ResponseEntity.ok(service.deleteAppointment(appointmentId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/getAppointment/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable String appointmentId) {
        try {
            return ResponseEntity.ok(service.getAppointmentById(appointmentId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
