package com.cognizant.week2.jpa.handson01;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class SpringDataJpaQuickExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaQuickExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CountryService countryService) {
        return args -> {
            countryService.saveSampleData();
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
}

interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findByCountryNameContainingIgnoreCase(String countryName);
}

@org.springframework.stereotype.Service
class CountryService {

    private final CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    void saveSampleData() {
        countryRepository.saveAll(List.of(
            new Country("IN", "India"),
            new Country("US", "United States"),
            new Country("UK", "United Kingdom")
        ));
    }

    List<Country> listCountries() {
        return countryRepository.findAll();
    }
}
