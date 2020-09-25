package com.countries.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.countries.model.Country;

public interface CountryRepository extends CrudRepository<Country, String> {

	List<Country> findAllByNativeNameIgnoreCase(String countryName);

	List<Country> findAllByNameContainingIgnoreCase(String countryName);

	List<Country> findAllByCapitalIgnoreCase(String capital);

	List<Country> findAllByRegionIgnoreCase(String region);
	
	List<Country> findAllByAlpha3CodeIgnoreCase(String code);

	List<Country> findAllByAlpha2CodeIgnoreCase(String code);

	List<Country> findAllByAlpha3CodeContainingIgnoreCase(String code);

}
