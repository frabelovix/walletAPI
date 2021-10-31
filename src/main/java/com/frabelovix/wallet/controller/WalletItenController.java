package com.frabelovix.wallet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frabelovix.wallet.dto.WalletItemDTO;
import com.frabelovix.wallet.entitty.WalletItem;
import com.frabelovix.wallet.response.Response;
import com.frabelovix.wallet.service.WalletItemService;
import com.frabelovix.wallet.util.enums.TypeEnum;

@RestController
@RequestMapping("wallet-item")
public class WalletItenController {

	@Autowired
	WalletItemService service;

	@PostMapping
	public ResponseEntity<Response<WalletItemDTO>> create(@Validated @RequestBody WalletItemDTO dto,
			BindingResult result) {

		Response<WalletItemDTO> response = new Response<WalletItemDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		WalletItem wi = service.save(this.convertDtoToEntity(dto));

		response.setData(this.convertEntityToDto(wi));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping(value = "/{wallet}")
	public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(@PathVariable("wallet") Long wallet,
			@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		Response<Page<WalletItemDTO>> response = new Response<Page<WalletItemDTO>>();

		Page<WalletItem> items = service.FindBetweenDates(wallet, startDate, endDate, page);
		Page<WalletItemDTO> dto = items.map(i -> this.convertEntityToDto(i));
		response.setData(dto);

		return ResponseEntity.ok().body(response);

	}

	@GetMapping(value = "type/{wallet}")
	public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletAndType(@PathVariable("wallet") long wallet,
			@RequestParam("type") String type) {

		Response<List<WalletItemDTO>> response = new Response<List<WalletItemDTO>>();

		List<WalletItem> list = service.findByWalletAndType(wallet, TypeEnum.getEnum(type));

		List<WalletItemDTO> dto = new ArrayList<>();
		list.forEach(i -> dto.add(this.convertEntityToDto(i)));
		response.setData(dto);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping(value = "/total/{wallet}")
	public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet) {
		Response<BigDecimal> response = new Response<BigDecimal>();
		BigDecimal value = service.sumByWalletId(wallet);
		response.setData(value == null ? BigDecimal.ZERO : value);

		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result) {

		Response<WalletItemDTO> response = new Response<WalletItemDTO>();

		Optional<WalletItem> wi = service.findById(dto.getId());

		if (!wi.isPresent()) {
			result.addError(new ObjectError("WalletItem", "WalletItem não encontrado"));
		} else {
			if (wi.get().getWallet().getId() != dto.getWallet().getId()) {
				result.addError(new ObjectError("WalletItem", "Você não pode alterar a carteira"));
			}
		}

		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		WalletItem saved = service.save(this.convertDtoToEntity(dto));

		response.setData(this.convertEntityToDto(saved));

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping(value = "/walletItemId")
	public ResponseEntity<Response<String>> delete(@PathVariable("wallet") Long walletItemId) {
		Response<String> response = new Response<String>();

		Optional<WalletItem> wi = service.findById(walletItemId);

		if (!wi.isPresent()) {
			response.getErrors().add("Carteira de id " + walletItemId + " não encontrada");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		service.deleteById(walletItemId);
		response.setData("Carteira de id " + walletItemId + " excluída com sucesso");
		return ResponseEntity.ok().body(response);

	}

	private WalletItem convertDtoToEntity(WalletItemDTO dto) {
		WalletItem wi = new WalletItem();
		wi.setDate(dto.getDate());
		wi.setDescription(dto.getDescription());
		wi.setId(dto.getId());
		// wi.setType(dto.getType());
		wi.setValue(dto.getValue());
		wi.setWallet(dto.getWallet());

		return wi;
	}

	private WalletItemDTO convertEntityToDto(WalletItem w) {
		WalletItemDTO dto = new WalletItemDTO();
		dto.setDate(w.getDate());
		dto.setDescription(w.getDescription());
		dto.setId(w.getId());
		// dto.setType(w.getType());
		dto.setValue(w.getValue());
		dto.setWallet(w.getWallet());

		return dto;
	}

}
