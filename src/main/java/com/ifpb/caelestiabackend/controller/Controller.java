package com.ifpb.caelestiabackend.controller;

import org.springframework.http.ResponseEntity;

public interface Controller<T> {
    ResponseEntity<T> add(T t);
}
