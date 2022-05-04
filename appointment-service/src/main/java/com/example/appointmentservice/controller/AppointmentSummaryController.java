package com.example.appointmentservice.controller;

import com.example.appointmentservice.entity.AppointmentSummary;
import com.example.appointmentservice.service.AppointmentSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointmentSummary")
public class AppointmentSummaryController {

    private final AppointmentSummaryService service;

    @Autowired
    public AppointmentSummaryController(AppointmentSummaryService service) {
        this.service = service;
    }

    @GetMapping("/appointmentSummaries")
    public ResponseEntity<List<AppointmentSummary>> getAllAppointmentSummaries() {
        return ResponseEntity.ok(service.getAllAppointmentSummaries());
    }

    @PostMapping("saveAppointmentSummary/{appointmentId}/{userId}")
    public ResponseEntity<?> saveAppointmentSummary(@RequestBody AppointmentSummary appointmentSummary, @PathVariable String appointmentId, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(service.saveAppointmentSummary(appointmentSummary, appointmentId, userId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PutMapping("/updateAppointmentSummary")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentSummary appointmentSummary) {
        try {
            return ResponseEntity.ok(service.updateAppointmentSummary(appointmentSummary));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleteAppointmentSummary/{appointmentSummaryId}")
    public ResponseEntity<?> deleteAppointmentSummary(@PathVariable String appointmentSummaryId) {
        try {
            return ResponseEntity.ok(service.deleteAppointmentSummary(appointmentSummaryId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/getAppointmentSummary/{appointmentSummaryId}")
    public ResponseEntity<?> getAppointment(@PathVariable String appointmentSummaryId) {
        try {
            return ResponseEntity.ok(service.getAppointmentSummaryById(appointmentSummaryId));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }


}
