package com.henrique.majesty.controller.product;

import com.henrique.majesty.dto.product.ProductDto;
import com.henrique.majesty.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto product = productService.save(productDto);
        return ResponseEntity.status(201).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> list(@PageableDefault Pageable pageable) {
        Page<ProductDto> products = productService.list(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto product = productService.get(id);
        return ResponseEntity.ok(product);
    }
}
