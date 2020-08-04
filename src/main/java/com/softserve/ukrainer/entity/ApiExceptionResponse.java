package com.softserve.ukrainer.entity;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class ApiExceptionResponse implements Serializable {
    private int status;
    private String message;
    private Instant timestamp;
}
