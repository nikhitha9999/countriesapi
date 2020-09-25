package com.countries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.countries.dao.CountryRepository;
import com.countries.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepo;

	@Override
	public Country addCountry(Country country) {
		System.out.println("Before Add " + country);
		return countryRepo.save(country);
	}

	@Override
	public List<Country> getAllCountries() {
		return StreamSupport.stream(countryRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<Country> getAllCountriesByName(String countryName) {
		return countryRepo.findAllByNameContainingIgnoreCase(countryName);
	}

	@Override
	public List<Country> getAllCountriesByPartialName(String countryName) {
		return countryRepo.findAllByNameContainingIgnoreCase(countryName);
	}

	@Override
	public List<Country> getAllCountriesByAltName(String countryName) {
		List<Country> countries = new ArrayList<>();
		getAllCountries().stream().forEach(c -> {
			c.getAltSpellings().stream().forEach(country -> {
				if (country.equalsIgnoreCase(countryName)) {
					countries.add(c);
				}
			});
		});
		return countries;

	}

	@Override
	public List<Country> getCountries(String fullText, String countryName) {
		List<Country> existingCountries = new ArrayList<>();
		if (fullText.equals("true")) {
			existingCountries = getAllCountriesByName(countryName);
		} else {
			existingCountries = getAllCountriesByAltName(countryName);
		}
		if (existingCountries.size() == 0) {
			existingCountries = getAllCountriesByPartialName(countryName);
		}
		return existingCountries;
	}

	@Override
	public List<Country> getCountriesByCode(String code) {
		return countryRepo.findAllByAlpha3CodeContainingIgnoreCase(code);
	}

	@Override
	public List<Country> getCountriesByMultipleCode(String code) {
		List<Country> existingCountries = new ArrayList<>();
		String[] codes = code.split(";");
		for (String c : codes) {
			List<Country> countriesbyAlpha3Code = countryRepo.findAllByAlpha3CodeIgnoreCase(c);
			List<Country> countriesbyAlpha2Code = countryRepo.findAllByAlpha2CodeIgnoreCase(c);
			if (countriesbyAlpha3Code != null) {
				existingCountries.addAll(countriesbyAlpha3Code);
			}
			if (countriesbyAlpha2Code != null) {
				existingCountries.addAll(countriesbyAlpha2Code);
			}
		}
		return existingCountries;
	}

	@Override
	public List<Country> getAllCountriesByCurrency(String currency) {
		List<Country> countries = new ArrayList<>();
		getAllCountries().stream().forEach(c -> {
			c.getCurrency().stream().forEach(country -> {
				if (country.equalsIgnoreCase(currency)) {
					countries.add(c);
				}
			});
		});
		return countries;

	}

	@Override
	public List<Country> getAllCountriesByLanguage(String language) {
		List<Country> countries = new ArrayList<>();
		getAllCountries().stream().forEach(c -> {
			if (language.equalsIgnoreCase(c.getLanguages().get("iso639_1"))
					|| language.equalsIgnoreCase(c.getLanguages().get("iso639_2"))) {
				countries.add(c);
			}
		});
		return countries;
	}

	@Override
	public List<Country> getAllCountriesByCapital(String capital) {
		return countryRepo.findAllByCapitalIgnoreCase(capital);
	}

	@Override
	public List<Country> getAllCountriesByRegion(String region) {
		return countryRepo.findAllByRegionIgnoreCase(region);
	}

	@Override
	public List<Country> getAllCountriesByCallingCode(String callingcode) {
		List<Country> countries = new ArrayList<>();
		getAllCountries().stream().forEach(c -> {
			c.getCallingCodes().stream().forEach(country -> {
				if (country.equalsIgnoreCase(callingcode)) {
					countries.add(c);
				}
			});
		});
		return countries;

	}
}
