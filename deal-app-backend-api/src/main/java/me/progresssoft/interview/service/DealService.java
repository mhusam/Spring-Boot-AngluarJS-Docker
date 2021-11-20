package me.progresssoft.interview.service;

import java.util.List;
import java.util.Optional;
import me.progresssoft.interview.exeption.ApiException;
import me.progresssoft.interview.exeption.TwiceRecordException;
import me.progresssoft.interview.model.Deal;
import me.progresssoft.interview.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
public interface DealService {
    
    List<Deal> findAll(String dealId);
    Optional<Deal> findById(String id);
    
    Deal saveDeal(Deal deal) throws ApiException;
    Deal updateDeal(Deal deal) throws ApiException;
    
    void deleteByDealId(String dealId);
    void deleteById(String id);
    void deleteAll();
}

@Service
@Transactional(readOnly = true)
@Configurable
class DealServiceImpl implements DealService {
    
    private @Autowired DealRepository repository;
    
    @Override
    @Transactional(readOnly = false)
    public Deal saveDeal(Deal deal) throws ApiException {
        if(deal == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Deal must not be null!");
        }
        if(repository.isRecordExists(deal.getDealId())) {
            throw new TwiceRecordException(deal.getDealId().concat(" deal id is already exists!"));
        }
        
        return repository.save(deal);
    }
    
    @Override
    @Transactional(readOnly = false)
    public Deal updateDeal(Deal deal) throws ApiException {
        if(deal == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Deal must not be null!");
        }
        return repository.save(deal);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteByDealId(String dealId) {
        repository.deleteByDealId(dealId);
    }

    @Override
    public List<Deal> findAll(String dealId) {
        if(dealId == null || dealId.trim().isBlank()) {
            return repository.findAll();
        } else {
            return repository.findByDealIdContaining(dealId);
        }
    }

    @Override
    public Optional<Deal> findById(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}