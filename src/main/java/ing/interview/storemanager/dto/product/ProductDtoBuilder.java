package ing.interview.storemanager.dto.product;

public class ProductDtoBuilder {
    private final ProductDto dto;

    private ProductDtoBuilder() {
        this.dto = new ProductDto();
    }

    public static ProductDtoBuilder builder() {
        return new ProductDtoBuilder();
    }

    public ProductDtoBuilder withId(Long id) {
        this.dto.setId(id);
        return this;
    }

    public ProductDtoBuilder withName(String name) {
        this.dto.setName(name);
        return this;
    }

    public ProductDtoBuilder withDescription(String description) {
        this.dto.setDescription(description);
        return this;
    }

    public ProductDtoBuilder withPrice(Double price) {
        this.dto.setPrice(price);
        return this;
    }

    public ProductDtoBuilder withStock(Integer stock) {
        this.dto.setAvailableStock(stock);
        return this;
    }

    public ProductDto build() {
        return dto;
    }

}
