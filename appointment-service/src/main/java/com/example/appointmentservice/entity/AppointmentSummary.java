package com.example.appointmentservice.entity;

import com.example.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSummary {
    private String id;
    private Appointment appointment;
    private User mechanic;
    private String comment;
    private double totalCost;

}
