package com.wallet.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.Service.WalletItemService;
import com.wallet.Service.WalletService;
import com.wallet.dto.WalletItemDTO;
import com.wallet.entity.WalletItem;
import com.wallet.response.Response;
import com.wallet.util.enums.TypeEnum;

@RestController
@RequestMapping("/wallet-item")
public class WalletItemController {
	
	
	@Autowired
	public WalletItemService service;
	
	@Autowired
	public WalletService walletService;

	
	@PostMapping
	public ResponseEntity<Response<WalletItemDTO>> create(@Valid @RequestBody WalletItemDTO walletItemDTO, BindingResult result){
		Response<WalletItemDTO> response = new Response<WalletItemDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		WalletItem walletItem = service.save(this.convertDTOToEntity(walletItemDTO));
		response.setData(this.convertEntityToDTO(walletItem));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping(value = "/{wallet}")
	public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(@PathVariable("wallet") Long wallet,
			@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
			@RequestParam(name = "page", defaultValue = "0") int page){
		
		Response<Page<WalletItemDTO>> response = new Response<Page<WalletItemDTO>>();
		Page<WalletItem> items = service.findBetweenDates(wallet, startDate, endDate, page);
		Page<WalletItemDTO> dto = items.map(i-> this.convertEntityToDTO(i));
		response.setData(dto);
		return ResponseEntity.ok().body(response);	
	}
	
	
	@GetMapping(value = "/type/{wallet}")
	public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletIdAndType(@PathVariable("wallet") Long wallet,
			@RequestParam("type") String type){

		Response<List<WalletItemDTO>> response = new Response<List<WalletItemDTO>>();
		List<WalletItem> list = service.findByWalletAndType(wallet, TypeEnum.getEnum(type));
		List<WalletItemDTO> dto = new ArrayList<WalletItemDTO>();
		list.forEach(i -> dto.add(this.convertEntityToDTO(i)));
		response.setData(dto);
		return ResponseEntity.ok().body(response);	
	}
	
	@GetMapping(value = "/total/{wallet}")
	public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet){
		Response<BigDecimal> response = new Response<BigDecimal>();
		BigDecimal value = service.sumByWalletId(wallet);
		response.setData((value==null)?BigDecimal.ZERO:value);
		return ResponseEntity.ok().body(response);	
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------
	private WalletItem convertDTOToEntity(WalletItemDTO wid) {
		return null;
	}
	
	private WalletItemDTO convertEntityToDTO(WalletItem wi) {
		return new WalletItemDTO(wi.getId(), wi.getType().toString(), wi.getData(), wi.getDescription(), wi.getValue(), wi.getWallet().getId());
	}
}
