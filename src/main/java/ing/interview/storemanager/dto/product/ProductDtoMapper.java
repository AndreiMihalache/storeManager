package ing.interview.storemanager.dto.product;

import ing.interview.storemanager.model.store.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    /**
    *Mapper class for product to product dto mapping, could have also used MapStruct
    */

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailableStock(productDto.getAvailableStock());
        return product;
    }

    public ProductDto toDto(Product product) {
        return ProductDtoBuilder.builder()
                .withId(product.getId())
                .withName(product.getName())
                .withDescription(product.getDescription())
                .withPrice(product.getPrice())
                .withStock(product.getAvailableStock())
                .build();
    }

}
