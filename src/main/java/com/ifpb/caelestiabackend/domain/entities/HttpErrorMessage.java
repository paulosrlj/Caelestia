package com.ifpb.caelestiabackend.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorMessage {
    private HttpStatus status;
    private String message;
}
