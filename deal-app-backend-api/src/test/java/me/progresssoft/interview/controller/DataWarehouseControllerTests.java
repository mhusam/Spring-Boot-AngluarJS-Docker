package me.progresssoft.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import me.progresssoft.interview.model.Deal;
import me.progresssoft.interview.utils.CurrencyCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class DataWarehouseControllerTests {

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;
    
    @Test
    public void testPassedDeal() throws Exception {
        String dealId = "123-4567GGAAA-ccc";
        Deal record = Deal.builder()
                .amount(new BigDecimal(500))
                .dealId(dealId)
                .dateTime(LocalDateTime.now())
                .fromCurrency(CurrencyCode.CNY)
                .toCurrency(CurrencyCode.AED)
                .build();
        
        // when
        this.mockMvc.perform(post("/api/v1/deal").
                             content(mapper.writeValueAsString(record)).
                             contentType(MediaType.APPLICATION_JSON).
                             accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
        
        // then
        this.mockMvc.perform(delete("/api/v1/deal/by-deal-id/" + dealId).
                             contentType(MediaType.APPLICATION_JSON).
                             accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void testTwiceRecordDeal() throws Exception {
        String dealId = "123-4567GGAAA-ccc";
        Deal record = Deal.builder()
                .amount(new BigDecimal(500))
                .dealId(dealId)
                .dateTime(LocalDateTime.now())
                .fromCurrency(CurrencyCode.CNY)
                .toCurrency(CurrencyCode.AED)
                .build();
        
        // when
        this.mockMvc.perform(post("/api/v1/deal").
                             content(mapper.writeValueAsString(record)).
                             contentType(MediaType.APPLICATION_JSON).
                             accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
        
        this.mockMvc.perform(post("/api/v1/deal").
                             content(mapper.writeValueAsString(record)).
                             contentType(MediaType.APPLICATION_JSON).
                             accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isConflict());
        
        // then
        this.mockMvc.perform(delete("/api/v1/deal/by-deal-id/" + dealId).
                             contentType(MediaType.APPLICATION_JSON).
                             accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}
