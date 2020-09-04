package com.wallet.Service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wallet.Service.WalletItemService;
import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.util.enums.TypeEnum;

@Service
public class WalletItemServiceImp implements WalletItemService{


	@Autowired
	private WalletItemRepository repository;
	
	@Value("${pagination.items_per_page}")
	private int itemsPerPage;

	@Override
	@CacheEvict(value = "findByWalletIdAndType", allEntries = true)
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
	@Cacheable(value = "findByWalletIdAndType")
	public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
		return repository.findByWalletIdAndType(wallet, type);
	}

	@Override
	public BigDecimal sumByWalletId(Long wallet) {
		return repository.sumByWalletId(wallet);
	}

	@Override
	public Optional<WalletItem> findById(Long wi) {
		return repository.findById(wi);
	}

	@Override
	@CacheEvict(value = "findByWalletIdAndType", allEntries = true)
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	

}
