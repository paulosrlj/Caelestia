package com.ifpb.caelestiabackend.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorMessage {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public HttpErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
