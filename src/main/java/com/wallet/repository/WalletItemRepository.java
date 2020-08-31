package com.wallet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {
	
	Page<WalletItem> findAllByWalletIdAndDataGreaterThanEqualAndDataLessThanEqual(Long wallet,Date init, Date end, Pageable pg);
	                 
	List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);
}
