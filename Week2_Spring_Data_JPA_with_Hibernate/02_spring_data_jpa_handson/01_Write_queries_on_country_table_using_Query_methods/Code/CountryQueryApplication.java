package com.cognizant.week2.jpa3.ex01;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class CountryQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CountryService countryService) {
        return args -> {
            countryService.seedCountries();
            System.out.println(countryService.findCountryByCode("IN").getCountryName());
            System.out.println(countryService.findCountriesContaining("United").size());
            countryService.listCountryNames().forEach(System.out::println);
        };
    }
}

@Entity
@Table(name = "country")
class Country {

    @Id
    private String countryCode;
    private String countryName;
    private String capital;

    Country() {
    }

    Country(String countryCode, String countryName, String capital) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.capital = capital;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }
}

interface CountryRepository extends JpaRepository<Country, String> {

    Country findByCountryCode(String countryCode);

    List<Country> findByCountryNameContainingIgnoreCase(String keyword);

    @Query("select c.countryName from Country c order by c.countryName")
    List<String> fetchCountryNames();
}

@org.springframework.stereotype.Service
class CountryService {

    private final CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    void seedCountries() {
        countryRepository.saveAll(List.of(
            new Country("IN", "India", "New Delhi"),
            new Country("US", "United States", "Washington"),
            new Country("UK", "United Kingdom", "London")
        ));
    }

    Country findCountryByCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }

    List<Country> findCountriesContaining(String keyword) {
        return countryRepository.findByCountryNameContainingIgnoreCase(keyword);
    }

    List<String> listCountryNames() {
        return countryRepository.fetchCountryNames();
    }
}
