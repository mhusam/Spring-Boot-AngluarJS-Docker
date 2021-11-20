package me.progresssoft.interview.repositry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import me.progresssoft.interview.model.Deal;
import me.progresssoft.interview.repository.DealRepository;
import me.progresssoft.interview.utils.CurrencyCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.test.annotation.Rollback;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DealRepositoryTests {
    
    private final static String DEAL_ID_FOR_TEST = "123-4567GGAAA-00029";   
    private @Autowired DealRepository dealRepository; 
    
    @Test
    @Rollback(false)
    @Order(1)
    public void testInsert() throws Exception {
        Deal record = Deal.builder()
                .dealId(DEAL_ID_FOR_TEST)
                .dateTime(LocalDateTime.now())
                .fromCurrency(CurrencyCode.CNY)
                .toCurrency(CurrencyCode.CNY)
                .amount(BigDecimal.ONE)
                .build();
        // when
        record = dealRepository.save(record);
        
        // then
        assertThat(record).isNotNull();
        assertThat(record.getUuid()).isNotNull();
    }
    
    @Test
    @Rollback(false)
    @Order(2)
    public void testUpdate() {
        BigDecimal newAmount = new BigDecimal(1000);
        
        Deal deal = dealRepository.findByDealId(DEAL_ID_FOR_TEST);
        deal.setAmount(newAmount);
        
        Deal updatedDeal = dealRepository.save(deal);
        assertThat(updatedDeal.getAmount()).isEqualTo(newAmount);
    }
    
    @Test
    @Rollback(false)
    @Order(3)
    public void testDelete() {
        boolean isPresentBeforeDelete = dealRepository.findByDealId(DEAL_ID_FOR_TEST) != null;
        dealRepository.deleteByDealId(DEAL_ID_FOR_TEST);
        boolean notPresentAfterDelete = dealRepository.findByDealId(DEAL_ID_FOR_TEST) != null;

        assertTrue(isPresentBeforeDelete);
        assertFalse(notPresentAfterDelete);
        
    }
}
