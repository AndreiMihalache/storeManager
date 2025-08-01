package ing.interview.storemanager.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Auto generated ID")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull
    @Min(value = 0, message = "Price shouldn't be negative")
    private Double price;


    private Integer availableStock;
}
