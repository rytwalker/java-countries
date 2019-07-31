package com.lambdaschool.countries;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/names")
public class NameController {
    @GetMapping(value = "/all", produces = {"application/json"} )
    public ResponseEntity<?> getAllCountriesAlphabetically() {
        ArrayList<Country> sortedCountries = CountriesApplication.ourCountryList.countryList;
        sortedCountries.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return new ResponseEntity<>(sortedCountries, HttpStatus.OK);
    }

    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesWithFirstLetter(@PathVariable char letter) {
        ArrayList<Country> sortedCountries = CountriesApplication.ourCountryList.findCountries(c -> (c.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter)));
        sortedCountries.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return new ResponseEntity<>(sortedCountries, HttpStatus.OK);
    }

    @GetMapping(value = "/size/{number}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesWithNameSize(@PathVariable int number) {
        ArrayList<Country> sortedCountries = CountriesApplication.ourCountryList.findCountries(c -> (c.getName().length() >= number));
        sortedCountries.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return new ResponseEntity<>(sortedCountries, HttpStatus.OK);
    }
}
