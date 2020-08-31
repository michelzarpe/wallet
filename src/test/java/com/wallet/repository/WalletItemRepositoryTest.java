package com.wallet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	
	/*@Test
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
	*/
	
	@Test
	public void testDeletWalletItem() {
		Optional<Wallet> ow = walletRepository.findById(saveWalletId);
		WalletItem wi = new WalletItem(null,TYPE,DATE,DESCRIPTION, VAlUE,ow.get());
		wi = repository.save(wi);
		repository.deleteById(wi.getId());
		Optional<WalletItem> response = repository.findById(saveWalletItemId);
		assertFalse(response.isPresent());
	}
	
	@Test
	public void testFindBetweenDates() {
		Optional<Wallet> w = walletRepository.findById(saveWalletId);
		LocalDateTime localDateTime = DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Date currentDatePlusFiveDays=Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
		Date currentDatePlusSevemDays=Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());	
		repository.save(new WalletItem(null, TYPE, currentDatePlusFiveDays, DESCRIPTION, VAlUE, w.get()));
		repository.save(new WalletItem(null, TYPE, currentDatePlusSevemDays, DESCRIPTION, VAlUE, w.get()));
		PageRequest pg = new PageRequest(0, 10);
		Page<WalletItem> response = repository.findAllByWalletIdAndDataGreaterThanEqualAndDataLessThanEqual(saveWalletId,DATE,currentDatePlusFiveDays,pg);
		assertEquals(response.getContent().size(), 2);
		assertEquals(response.getTotalElements(), 2);
		assertEquals(response.getContent().get(0).getWallet().getId(), saveWalletId);
	}
	
	
	@Test
	public void testFindByType() {
		List<WalletItem> response = repository.findByWalletIdAndType(saveWalletId,TYPE);
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(),TYPE);
	}
	
	@Test
	public void testFindByTypeSd() {
		Optional<Wallet> w = walletRepository.findById(saveWalletId);
		repository.save(new WalletItem(null, TypeEnum.SD, DATE, DESCRIPTION, VAlUE, w.get()));
		List<WalletItem> response = repository.findByWalletIdAndType(saveWalletId,TYPE.SD);
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(),TYPE.SD);
	}
	
	@Test
	public void testSumByWallet() {
		Optional<Wallet> w = walletRepository.findById(saveWalletId);
		repository.save(new WalletItem(null, TypeEnum.SD, DATE, DESCRIPTION, BigDecimal.valueOf(150.80), w.get()));
		BigDecimal response = repository.sumByWalletId(saveWalletId);
		assertEquals(response.compareTo(BigDecimal.valueOf(215.8)), 0);
		
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem(null, null, DATE, DESCRIPTION, null, null);
		wi=repository.save(wi);
	}
	
}
