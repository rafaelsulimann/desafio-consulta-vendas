package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(name = "minDate") String minDate, @RequestParam(name = "maxDate") String maxDate, @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.searchReport(minDate, maxDate, name, pageable));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSumaryDTO>> getSummary(@RequestParam(name = "minDate") String minDate, @RequestParam(name = "maxDate") String maxDate) {
		return ResponseEntity.status(HttpStatus.OK).body(service.searchSumary(minDate, maxDate));
	}
}
