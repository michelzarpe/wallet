package com.wallet.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	public WalletItem save(WalletItem w);

	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);
	
	public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type);
	
	public BigDecimal sumByWalletId(Long wallet);
	
	public Optional<WalletItem> findById(Long wi);
	
	public void deleteById(Long id);
}
