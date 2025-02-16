package myproject_springjdbc.controller;

import myproject_springjdbc.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    // Возвращаем список продуктов
    @GetMapping("/products/fetch-product")
    public List<String> fetchProduct(@RequestParam String name) {
        return productRepository.getProductByName(name);
    }
}
