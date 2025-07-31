package ing.interview.storemanager.controller;

import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestBody ProductDto productDto) {
        productService.deleteProduct(productDto.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<ProductDto> updateProductPrice(@PathVariable long id, @RequestBody Double productPrice) {
        return ResponseEntity.ok(productService.changePrice(id, productPrice));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDto> updateProductStock(@PathVariable long id, @RequestBody Integer stock) {
        return ResponseEntity.ok(productService.updateStock(id, stock));
    }

    @PutMapping("/{id}/stock/add")
    public ResponseEntity<ProductDto> addProductStock(@PathVariable long id, @RequestBody Integer amount) {
        return ResponseEntity.ok(productService.addStock(id, amount));
    }


}
