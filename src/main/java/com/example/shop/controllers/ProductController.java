package com.example.shop.controllers;

import com.example.shop.services.ProductService;
import com.example.shop.dtos.request.ProductRequest;
import com.example.shop.dtos.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    // add new product - CREATE
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    // show all products - READ
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    // show product by id
    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    // edit product - UPDATE
    @PutMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity editProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest){
        productService.updateProduct(id,productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    // delete product - DELETE
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // buy product - DELETE
    @DeleteMapping("/buy/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity buyProduct(@PathVariable Integer id, @RequestParam Integer amount) {
        productService.buyProduct(id,amount);
        return new ResponseEntity(HttpStatus.OK);
    }

}
