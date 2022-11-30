package com.NeedInDeed.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	@NotBlank(message = "Product Name is Mandatory...")
	@Pattern(regexp = "^[A-Za-z]+$")
	private String productName;

	private String description;

	@NotNull(message = "stock value can not be null")
	@Min(value = 0, message = "stock value must be greater than equal to 0")
	private Integer stock;

	@NotNull
	@Min(value = 1, message = "stock value must be greater than equal to 1")
	private Double cost;

}
