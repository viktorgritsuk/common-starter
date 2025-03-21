package com.common.starter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.starter.model.response.VersionResponse;


@RestController
@RequestMapping("/")
public class VersionController {

    @Value("${spring.application.version}")
    private String version;

    @GetMapping
    public ResponseEntity<VersionResponse> versionGet() {
        return ResponseEntity.ok(new VersionResponse(version));
    }

}
