package ing.interview.storemanager;

import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.dto.product.ProductDtoBuilder;
import ing.interview.storemanager.dto.product.ProductDtoMapper;
import ing.interview.storemanager.exception.ProductInfoError;
import ing.interview.storemanager.model.store.Product;
import ing.interview.storemanager.repository.ProductRepository;
import ing.interview.storemanager.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Mock
    ProductDtoMapper productDtoMapper;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        productDto = ProductDtoBuilder.builder()
                .withId(1L)
                .withName("Product Name")
                .withDescription("description")
                .withPrice(99.99)
                .withStock(100)
                .build();

        product = new Product();
        product.setId(1L);
        product.setDescription("description");
        product.setPrice(99.99);
        product.setAvailableStock(100);
    }

    @Test
    void addProduct_returnDto() {
        when(productDtoMapper.toEntity(productDto)).thenReturn(product);
        when(productDtoMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.saveAndFlush(product)).thenReturn(product);

        ProductDto result = productService.addProduct(productDto);
        assertEquals(productDto,result);
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void addProduct_shouldThrow_whenPriceNegative() {

        product.setPrice((double) -3);
        when(productDtoMapper.toEntity(productDto)).thenReturn(product);

        assertThatThrownBy(() ->  productService.addProduct(productDto))
                .isInstanceOf(ProductInfoError.class)
                .hasMessage("Price cannot be less than 0");
    }

    @Test
    void changePrice_shouldUpdatePrice_whenValid() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.saveAndFlush(product)).thenReturn(product);
        when(productDtoMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.changePrice(1L, 10.0);

        assertThat(result.getPrice()).isEqualTo(99.99);
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void changePrice_shouldThrow_whenPriceNegative() {
        assertThatThrownBy(() -> productService.changePrice(1L, -1.0))
                .isInstanceOf(ProductInfoError.class)
                .hasMessage("Price cannot be less than zero");
    }

    @Test
    void changePrice_shouldThrow_whenProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.changePrice(1L, 15.0))
                .isInstanceOf(ProductInfoError.class)
                .hasMessage("Product not found");
    }

    @Test
    void updateStock_shouldUpdate_whenValid() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productDtoMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.updateStock(1L, 20);

        assertThat(result).isEqualTo(productDto);
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void updateStock_shouldThrow_whenNegativeQuantity() {
        assertThatThrownBy(() -> productService.updateStock(1L, -10))
                .isInstanceOf(ProductInfoError.class)
                .hasMessage("Quantity cannot be less than zero");
    }

    @Test
    void addStock_shouldAddQuantity_whenValid() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productDtoMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.addStock(1L, 10);

        assertThat(result).isEqualTo(productDto);
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void updateDescription_shouldThrow_whenEmpty() {
        assertThatThrownBy(() -> productService.updateDescription(1L, ""))
                .isInstanceOf(ProductInfoError.class)
                .hasMessage("Description cannot be empty");
    }

    @Test
    void findById_shouldReturnProductDto_whenFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productDtoMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.findById(1L);

        assertThat(result).isEqualTo(productDto);
    }

    @Test
    void findAllProducts_shouldReturnList() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productDtoMapper.toDto(product)).thenReturn(productDto);

        List<ProductDto> result = productService.findAllProducts();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(productDto);
    }
}
