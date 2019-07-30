package com.lambdaschool.countries;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NameController {
    @GetMapping(value = "/all", produces = {"application/json"} )
    public ResponseEntity<?> getAllCountriesAlphabetically() {
        ArrayList<Country> sortedCountries = CountriesApplication.ourCountryList.countryList;
        sortedCountries.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return new ResponseEntity<>(sortedCountries, HttpStatus.OK);
    }
}
