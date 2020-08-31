package com.wallet.Service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wallet.Service.WalletItemService;
import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.util.enums.TypeEnum;

@Service
public class WalletServiceImp implements WalletItemService{

	@Autowired
	private WalletItemRepository repository;
	
	@Value("${pagination.items_per_page}")
	private int itemsPerPage;

	@Override
	public WalletItem save(WalletItem wi) {
		return repository.save(wi);
	}

	@Override
	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
	
		@SuppressWarnings("deprecation")
		PageRequest pg = new PageRequest(page,itemsPerPage);

		
		return repository.findAllByWalletIdAndDataGreaterThanEqualAndDataLessThanEqual(wallet,start, end, pg);
	}

	@Override
	public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
		return repository.findByWalletIdAndType(wallet, type);
	}

	@Override
	public BigDecimal sumByWalletId(Long wallet) {
		return repository.sumByWalletId(wallet);
	}
	

	

}
