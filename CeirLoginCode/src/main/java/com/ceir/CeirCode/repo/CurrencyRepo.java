package com.ceir.CeirCode.repo;

import java.time.YearMonth;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ceir.CeirCode.model.Currency;
import com.ceir.CeirCode.model.PortAddress;

public interface CurrencyRepo extends JpaRepository<Currency, Long>,JpaSpecificationExecutor<Currency>{


	public Currency save(Currency currency);
	public Currency findById(long id);
    public Currency findTopByMonthDateOrderByIdDesc(Date  date);
    boolean existsByMonthDateAndCurrency(String date,Integer currency);   

	
}
