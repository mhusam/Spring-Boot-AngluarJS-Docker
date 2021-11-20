package me.progresssoft.interview.model;

import me.progresssoft.interview.utils.CurrencyCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.progresssoft.interview.utils.DateTimeUtil;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@Entity
@Table(name = "DEAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"dealId"}, callSuper = false)
@Builder
public class Deal extends BaseModel implements Serializable {
    
    @NotBlank(message = "Deal Id must not be empty or null")
    @Column(name = "DEAL_ID", nullable = false, unique = true)
    private String dealId;

    @NotNull(message = "From Currency must not be null")
    @Column(name = "FROM_CURRENCY")
    @Enumerated(EnumType.STRING)
    private CurrencyCode fromCurrency;

    @NotNull(message = "To Currency must not be null") 
    @Column(name = "TO_CURRENCY")
    @Enumerated(EnumType.STRING)
    private CurrencyCode toCurrency;

    @NotNull(message = "Date Time must not be null") 
    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @NotNull(message = "Amount must not be null") 
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }
    
    public @Override String toString() {
        return String.format("{uuid:'%s',dealId:'%s',fromCurrency:'%s',toCurrency:'%s',dateTime:'%s',amount:'%s'}", 
                getUuid(), dealId, fromCurrency, toCurrency, DateTimeUtil.formatDateTime(dateTime), NumberFormat.getCurrencyInstance().format(amount));
    }
}
