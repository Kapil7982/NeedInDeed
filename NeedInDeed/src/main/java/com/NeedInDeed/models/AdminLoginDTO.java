package com.NeedInDeed.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdminLoginDTO {

	private String password;
	private Integer adminId;

}
