package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("/age")
public class AgeController {
    @GetMapping(value = "/{age}", produces = {"application/json"})
    ResponseEntity<?> getCountriesWithMedianAge(@PathVariable int age) {
        ArrayList<Country> returnCountries = CountriesApplication.ourCountryList.findCountries(c -> (c.getAvgAge() >= age));
        return new ResponseEntity<>(returnCountries, HttpStatus.OK);
    }

    @GetMapping(value = "/min", produces = {"application/json"})
    ResponseEntity<?> getCountryWithMinMedianAge() {
        ArrayList<Country> sortCountries = CountriesApplication.ourCountryList.countryList;
        sortCountries.sort(Comparator.comparingInt(Country::getAvgAge));
        Country returnCountry = sortCountries.get(0);
        return new ResponseEntity<>(returnCountry, HttpStatus.OK);

    }

    @GetMapping(value = "/max", produces = {"application/json"})
    ResponseEntity<?> getCountryWithMaxMedianAge() {
        ArrayList<Country> sortCountries = CountriesApplication.ourCountryList.countryList;
        sortCountries.sort((o1, o2) -> (o2.getAvgAge() - o1.getAvgAge()));
        Country returnCountry = sortCountries.get(0);
        return new ResponseEntity<>(returnCountry, HttpStatus.OK);

    }
}
