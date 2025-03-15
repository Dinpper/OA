package com.example.labSystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuestionDto implements Serializable {
    String[] prompt;
    String question;
}
