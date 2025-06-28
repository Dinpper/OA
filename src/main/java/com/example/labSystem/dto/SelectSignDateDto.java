package com.example.labSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class SelectSignDateDto {
    String account;
    LocalDate date;
}
