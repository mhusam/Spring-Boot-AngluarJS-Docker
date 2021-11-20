package me.progresssoft.interview.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import me.progresssoft.interview.exeption.ApiException;
import me.progresssoft.interview.exeption.TwiceRecordException;
import me.progresssoft.interview.model.Deal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.progresssoft.interview.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/deal")
public class DataWarehouseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private @Autowired DealService dealService;

    @PostMapping 
    public ResponseEntity<Deal> save(@Valid @RequestBody Deal deal) throws ApiException {
        LOGGER.debug("Invoked: DataWarehouseController::save");
        LOGGER.debug("Parameters: " + deal);

        try {
            deal = dealService.saveDeal(deal);
        } catch (ApiException apie) {
            LOGGER.debug("Exit: API exception! ".concat(apie.getMessage()));
            throw apie;
        } catch (TwiceRecordException tu) {
            LOGGER.debug("Exit: Twice record exception! ".concat(tu.getMessage()));
            throw tu;
        }

        LOGGER.debug("Exit: Record has been saved successfully!");
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Deal>> findAll(@RequestParam(required = false) String dealId) {
        try {
            List<Deal> deals = new ArrayList<>();

            dealService.findAll(dealId).forEach(deals::add);

            if (deals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(deals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable("id") String id) {
        Optional<Deal> dealData = dealService.findById(id);

        if (dealData.isPresent()) {
            return new ResponseEntity<>(dealData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable("id") String id, @RequestBody Deal deal) throws ApiException {
        Optional<Deal> dealData = dealService.findById(id);

        if (dealData.isPresent()) {
            Deal _deal = dealData.get();
            _deal.setDateTime(deal.getDateTime());
            _deal.setAmount(deal.getAmount());
            _deal.setFromCurrency(deal.getFromCurrency());
            _deal.setToCurrency(deal.getToCurrency());
            
            return new ResponseEntity<>(dealService.updateDeal(_deal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDeal(@PathVariable("id") String id) {
        try {
            dealService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/by-deal-id/{dealId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable("dealId") String dealId) throws ApiException {
        try {
            dealService.deleteByDealId(dealId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllDeals() {
        try {
            dealService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
