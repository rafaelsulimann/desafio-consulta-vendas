package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSumaryDTO> searchSumary(String minDate, String maxDate){
		LocalDate now = LocalDate.now(ZoneId.of("UTC"));
		LocalDate min = null;
		LocalDate max = null;

		if(minDate != null){
			min = minDate.equals("") ? now.minusDays(365) : LocalDate.parse(minDate);
		}else{
			min = now.minusDays(365);
		}

		if(maxDate != null){
			max = maxDate.equals("") ? now : LocalDate.parse(maxDate);
		}else{
			max = now;
		}

		return repository.searchSumary(min, max).stream().map(x -> new SaleSumaryDTO(x)).toList();
	}

	public Page<SaleMinDTO> searchReport(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate now = LocalDate.now(ZoneId.of("UTC"));
		LocalDate min = null;
		LocalDate max = null;

		if(minDate != null){
			min = minDate.equals("") ? now.minusDays(365) : LocalDate.parse(minDate);
		}else{
			min = now.minusDays(365);
		}

		if(maxDate != null){
			max = maxDate.equals("") ? now : LocalDate.parse(maxDate);
		}else{
			max = now;
		}

		Page<SaleMinDTO> result = repository.searchReport(min, max, name, pageable);

		return result;
	}
}
