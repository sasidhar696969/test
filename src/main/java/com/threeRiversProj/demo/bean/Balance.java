package com.threeRiversProj.demo.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Balance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	@ApiModelProperty(required = true)
	public String accountNumber;
	@ApiModelProperty(required = true)
	public Date lastUpdateTimestamp;
	@ApiModelProperty(required = true)
	public Double balance;
	
}
