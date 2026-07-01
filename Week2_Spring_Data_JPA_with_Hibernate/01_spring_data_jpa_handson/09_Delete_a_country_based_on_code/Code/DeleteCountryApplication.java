package com.cognizant.week2.jpa.handson09;

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
public class DeleteCountryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeleteCountryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(CountryService countryService) {
        return args -> {
            countryService.seedCountries();
            countryService.deleteByCode("US");
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
}

@org.springframework.stereotype.Service
class CountryService {

    private final CountryRepository countryRepository;

    CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    void seedCountries() {
        countryRepository.saveAll(List.of(
            new Country("IN", "India"),
            new Country("US", "United States")
        ));
    }

    void deleteByCode(String countryCode) {
        countryRepository.deleteById(countryCode);
    }

    List<Country> listCountries() {
        return countryRepository.findAll();
    }
}
