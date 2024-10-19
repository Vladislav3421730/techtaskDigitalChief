package com.example.backend.controllers;

import com.example.backend.services.CreateIndexService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final CreateIndexService createIndexService;



}
