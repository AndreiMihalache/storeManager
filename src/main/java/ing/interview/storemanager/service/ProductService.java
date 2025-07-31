package ing.interview.storemanager.service;

import ing.interview.storemanager.dao.ProductDao;
import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.dto.product.ProductDtoMapper;
import ing.interview.storemanager.exceptions.ProductInfoError;
import ing.interview.storemanager.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    ProductDao productDao;
    ProductDtoMapper productDtoMapper;


    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {

        return productDtoMapper.toDto(productDao.findById(id));
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productDao.deleteById(productId);
    }

    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productDtoMapper.toEntity(productDto);
        productDao.save(product);
        return productDtoMapper.toDto(product);
    }

    @Transactional
    public ProductDto changePrice(Long productId, Double price) {
        if(price.compareTo(BigDecimal.ZERO.doubleValue()) < 0){
            throw new ProductInfoError("Price cannot be less than zero");
        }
        Product product = productDao.findById(productId);
        product.setPrice(price);
        productDao.update(product);
        return productDtoMapper.toDto(product);
    }

    @Transactional
    public ProductDto updateStock(Long productId, Integer quantity) {

        if(quantity < 0){
            throw new ProductInfoError("Quantity cannot be less than zero");
        }

        Product product = productDao.findById(productId);
        product.setAvailableStock(quantity);
        productDao.update(product);
        return productDtoMapper.toDto(product);

    }

    @Transactional
    public ProductDto addStock(Long productId, Integer quantity) {
        if(quantity < 0){
            throw new ProductInfoError("Cannot add stock less than zero");
        }
        Product product = productDao.findById(productId);
        product.setAvailableStock(product.getAvailableStock()+quantity);
        productDao.update(product);
        return productDtoMapper.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAllProducts() {
        List<Product> productList =  productDao.findAll();
        return productList.stream().map(product -> productDtoMapper.toDto(product)).toList();
    }

}
