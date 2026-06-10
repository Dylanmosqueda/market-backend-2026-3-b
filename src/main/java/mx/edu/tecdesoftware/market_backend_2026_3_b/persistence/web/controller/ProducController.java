package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/products")
public class ProducController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
}
