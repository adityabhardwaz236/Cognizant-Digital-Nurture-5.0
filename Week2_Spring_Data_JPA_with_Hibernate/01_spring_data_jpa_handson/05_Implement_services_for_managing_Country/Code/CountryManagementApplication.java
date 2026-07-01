package com.cognizant.week2.jpa.handson05;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class CountryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CountryService countryService) {
        return args -> {
            countryService.addCountry(new Country("IN", "India"));
            countryService.addCountry(new Country("US", "United States"));
            countryService.updateCountryName("US", "USA");
            countryService.listCountries().forEach(country ->
                System.out.println(country.getCountryCode() + " -> " + country.getCountryName()));
        };
    }
}

@Entity
@Table(name = "country")
class Country {

    @Id
    private String countryCode;
    private String countryName;

    Country() {
    }

    Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

interface CountryRepository extends JpaRepository<Country, String> {
}

@org.springframework.stereotype.Service
class CountryService {

    private final CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    List<Country> listCountries() {
        return countryRepository.findAll();
    }

    Country updateCountryName(String countryCode, String countryName) {
        Country country = countryRepository.findById(countryCode).orElseThrow();
        country.setCountryName(countryName);
        return countryRepository.save(country);
    }
}
