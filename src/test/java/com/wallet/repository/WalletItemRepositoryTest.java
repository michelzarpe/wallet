package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {
	 private static final Long ID = 1L;
	 private static final Date DATE = new Date();
	 private static final String TYPE = "EN";
	 private static final String DESCRIPTION = "Conta de luz";
	 private static final BigDecimal VAlUE = BigDecimal.valueOf(65);
	 
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	
	@Test
	public void testSave() {
		Wallet w = new Wallet(null,"Carteira 1",BigDecimal.valueOf(615.85));
		w = walletRepository.save(w);
		
		WalletItem wi = new WalletItem(ID,TYPE,DATE,DESCRIPTION, VAlUE,w);
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getData(), DATE);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getValue(), VAlUE);
		assertEquals(response.getWallet().getId(),w.getId());
	}
	
}
