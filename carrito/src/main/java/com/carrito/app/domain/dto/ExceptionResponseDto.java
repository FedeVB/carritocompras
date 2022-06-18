package com.carrito.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {
    private LocalDate date;
    private String message;
    private List<String> details;

    public ExceptionResponseDto(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }
}

