package ing.interview.storemanager.service;

import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.dto.product.ProductDtoMapper;
import ing.interview.storemanager.exception.ProductInfoError;
import ing.interview.storemanager.model.store.Product;
import ing.interview.storemanager.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;
    ProductDtoMapper productDtoMapper;


    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(value -> productDtoMapper.toDto(value)).orElse(null);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productDtoMapper.toEntity(productDto);
        validateProduct(product);
        productRepository.saveAndFlush(product);
        return productDtoMapper.toDto(product);
    }



    @Transactional
    public ProductDto changePrice(Long productId, Double price) {
        if(price.compareTo(BigDecimal.ZERO.doubleValue()) < 0){
            throw new ProductInfoError("Price cannot be less than zero");
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setPrice(price);
            productRepository.saveAndFlush(product);
            return productDtoMapper.toDto(product);
        }
        else throw new ProductInfoError("Product not found");
    }

    @Transactional
    public ProductDto updateStock(Long productId, Integer quantity) {

        if(quantity < 0){
            throw new ProductInfoError("Quantity cannot be less than zero");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setAvailableStock(quantity);
            productRepository.saveAndFlush(product);
            return productDtoMapper.toDto(product);
        }
        else throw new ProductInfoError("Product not found");

    }

    @Transactional
    public ProductDto addStock(Long productId, Integer quantity) {
        if(quantity < 0){
            throw new ProductInfoError("Cannot add stock less than zero");
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setAvailableStock(product.getAvailableStock()+quantity);
            productRepository.saveAndFlush(product);
            return productDtoMapper.toDto(product);
        }
        else throw new ProductInfoError("Product not found");
    }

    @Transactional
    public ProductDto updateDescription(Long productId, String description) {
        if(description == null || description.isEmpty()){
            throw new ProductInfoError("Description cannot be empty");
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setDescription(description);
            productRepository.saveAndFlush(product);
            return productDtoMapper.toDto(product);
        }
        else throw new ProductInfoError("Product not found");

    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAllProducts() {
        List<Product> productList =  productRepository.findAll();
        return productList.stream().map(product -> productDtoMapper.toDto(product)).toList();
    }

    private void validateProduct(Product product) {
        if(product.getPrice()==null || product.getPrice()<0){
            throw new ProductInfoError("Price cannot be less than 0");
        }
    }

}
