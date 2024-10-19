package com.example.backend.controllers;

import com.example.backend.services.LoadDataService;
import com.example.backend.services.SearchDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final SearchDataService searchDataService;
    private final LoadDataService loadDataService;

    @GetMapping("/findAll")
    public List<Map<String, Object>> findAllProducts()  {
        return searchDataService.findAll();
    }

    @GetMapping("/findBy/{search}")
    public List<Map<String, Object>> findByNameOrDescription(@PathVariable String search) {
        return searchDataService.findByNameOrDescription(search);
    }

    @GetMapping("/addData")
    public void AddDataToIndex() {
        loadDataService.loadDataFromDbToIndex();
    }



}
