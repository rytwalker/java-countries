package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController {
    @GetMapping(value = "/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesWithPopulation(@PathVariable long people) {
        ArrayList<Country> countriesWithPopulation = CountriesApplication.ourCountryList.findCountries(c -> (c.getPopulation() >= people));
        return new ResponseEntity<>(countriesWithPopulation, HttpStatus.OK);
    }

    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithSmallestPopulation() {
        ArrayList<Country> sortCountries = CountriesApplication.ourCountryList.countryList;
        sortCountries.sort((o1, o2) -> (int)(o1.getPopulation() - o2.getPopulation()));
        Country returnCountry = sortCountries.get(0);
        return new ResponseEntity<>(returnCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithLargestPopulation() {
        ArrayList<Country> sortCountries = CountriesApplication.ourCountryList.countryList;
        sortCountries.sort((o1, o2) -> (int)(o2.getPopulation() - o1.getPopulation()));
        Country returnCountry = sortCountries.get(0);
        return new ResponseEntity<>(returnCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/median", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMedianPopulation() {
        ArrayList<Country> sortCountries = CountriesApplication.ourCountryList.countryList;
        sortCountries.sort((o1, o2) -> (int)(o2.getPopulation() - o1.getPopulation()));
        int medianIndex = sortCountries.size() / 2 - 1;
        Country returnCountry = sortCountries.get(medianIndex);
        return new ResponseEntity<>(returnCountry, HttpStatus.OK);
    }
}
