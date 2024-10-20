package com.example.backend.controllers;

import com.example.backend.services.LoadDataService;
import com.example.backend.services.SearchDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin(value = "*")
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
