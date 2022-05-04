package com.example.appointmentservice.entity;

import com.example.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private String id;
    private String car;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User carOwner;

}
