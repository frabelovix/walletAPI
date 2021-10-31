package com.frabelovix.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.frabelovix.wallet.entitty.Wallet;

import lombok.Data;

@Data
public class WalletItemDTO {

	private Long id;
	@NotNull(message = "Insira uma carteira válida")
	private Wallet wallet;
	@NotNull(message = "Insira uma data")
	private Date date;
	@NotNull(message = "Insira um tipo")
	private String type;
	@NotNull(message = "Insira uma descrição")
	@Length(min= 5, message = "A descrição deve ter no mínimo 5 caracteres")
	private String description;
	@NotNull(message = "Insira um valor")
	private BigDecimal value;
	

}
