package ing.interview.storemanager.controller;

import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/description")
    public ResponseEntity<ProductDto> updateProductDescription( @PathVariable long id, @RequestBody String description) {
        return ResponseEntity.ok(productService.updateDescription(id,description));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/price")
    public ResponseEntity<ProductDto> updateProductPrice(@PathVariable long id, @RequestBody Double productPrice) {
        return ResponseEntity.ok(productService.changePrice(id, productPrice));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDto> updateProductStock(@PathVariable long id, @RequestBody Integer stock) {
        return ResponseEntity.ok(productService.updateStock(id, stock));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/stock/add")
    public ResponseEntity<ProductDto> addProductStock(@PathVariable long id, @RequestBody Integer amount) {
        return ResponseEntity.ok(productService.addStock(id, amount));
    }


}
