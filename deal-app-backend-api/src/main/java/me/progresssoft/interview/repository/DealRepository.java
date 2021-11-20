package me.progresssoft.interview.repository;

import java.util.List;
import me.progresssoft.interview.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@Repository
public interface DealRepository extends JpaRepository<Deal, String> {
    
    @Query("SELECT CASE WHEN COUNT(1) > 0 THEN 'true' ELSE 'false' END FROM Deal d WHERE d.dealId = :dealId")
    Boolean isRecordExists(@Param("dealId") String dealId);
    
    @Query("SELECT d FROM Deal d WHERE d.dealId = :dealId")
    Deal findByDealId(@Param("dealId") String dealId);
    
    void deleteByDealId(String dealId);
    
    List<Deal> findByDealIdContaining(String dealId);
}
