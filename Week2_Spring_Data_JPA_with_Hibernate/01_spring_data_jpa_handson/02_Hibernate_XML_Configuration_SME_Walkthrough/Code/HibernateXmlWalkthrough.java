package com.cognizant.week2.jpa.handson02;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateXmlWalkthrough {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            CountryDao countryDao = context.getBean(CountryDao.class);
            countryDao.save(new Country("IN", "India"));
            countryDao.save(new Country("JP", "Japan"));
            countryDao.findAll().forEach(country ->
                System.out.println(country.getCountryCode() + " -> " + country.getCountryName()));
        }
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

class CountryDao {

    private final java.util.Map<String, Country> storage = new java.util.LinkedHashMap<>();

    public void save(Country country) {
        storage.put(country.getCountryCode(), country);
    }

    public List<Country> findAll() {
        return List.copyOf(storage.values());
    }
}
