package com.tburakdemir.kaskodegerlistesi.repository;

import com.tburakdemir.kaskodegerlistesi.entity.Currency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>  {
    @Query("SELECT c.year, c.month, c.usdTry, c.xauTryg, c.minWageTry FROM Currency c WHERE c.year = ?1 AND c.month = ?2")
    List<Object[]> findByYearAndMonth(int year, int month);
}
