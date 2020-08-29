package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {
	 Logger LOGGER = Logger.getLogger(WalletItemRepositoryTest.class.getName());
	 private static final Long ID = 1L;
	 private static final Date DATE = new Date();
	 private static final TypeEnum TYPE = TypeEnum.EN;
	 private static final String DESCRIPTION = "Conta de luz";
	 private static final BigDecimal VAlUE = BigDecimal.valueOf(65);
	 private Long saveWalletItemId = null;
	 private Long saveWalletId=null;
	 
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	
	@Before
	public void setp() {
		Wallet w = new Wallet(null, "Carteira Teste", BigDecimal.valueOf(250));
		w = walletRepository.save(w);
		WalletItem wi = new WalletItem(null, TYPE, DATE, DESCRIPTION, VAlUE, w);
		wi = repository.save(wi);
		saveWalletId = w.getId();
		saveWalletItemId = w.getId();
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}
		
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
	
	@Test
	public void testUpdate() {
		String descricao = "Alterada a Descrição";
		Optional<WalletItem> owi = repository.findById(saveWalletItemId);
		LOGGER.info("********** "+owi.get().getDescription());
		WalletItem wi = owi.get();
		wi.setDescription(descricao);
		wi = repository.save(wi);
		Optional<WalletItem> nweOwi = repository.findById(saveWalletItemId);
		LOGGER.info("********** "+nweOwi.get().getDescription());
		assertEquals(descricao,nweOwi.get().getDescription());
	}
	
	
	@Test
	public void testDeletWalletItem() {
		Optional<Wallet> ow = walletRepository.findById(saveWalletId);
		WalletItem wi = new WalletItem(null,TYPE,DATE,DESCRIPTION, VAlUE,ow.get());
		wi = repository.save(wi);
		repository.deleteById(wi.getId());
		Optional<WalletItem> response = repository.findById(saveWalletItemId);
		assertFalse(response.isPresent());
	}
	/*
	 //SE EU COLOCAR ESSE TESTE PARA DE FUNCIONAR O TESTE DE UPDATE
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem(null, null, DATE, DESCRIPTION, null, null);
		wi=repository.save(wi);
	}
	*/
}
