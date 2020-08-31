package com.wallet.Service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.wallet.entity.WalletItem;

public interface WalletItemService {

	public WalletItem save(WalletItem w);

	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);
}
