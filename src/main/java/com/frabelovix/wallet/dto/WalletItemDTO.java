package com.frabelovix.wallet.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frabelovix.wallet.entitty.Wallet;

import lombok.Data;

@Data
public class WalletItemDTO {

	private Long id;
	@NotNull(message = "Insira uma carteira válida")
	private Wallet wallet;
	@NotNull(message = "Insira uma data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date date;
	@NotNull(message = "Insira um tipo")
	@Pattern(regexp="^(ENTRADA|SAÍDA)$", message="Para o tipo somente são aceitos os valores ENTRADA ou SAÍDA")
	private String type;
	@NotNull(message = "Insira uma descrição")
	@Length(min= 5, message = "A descrição deve ter no mínimo 5 caracteres")
	private String description;
	@NotNull(message = "Insira um valor")
	private BigDecimal value;
	

}
