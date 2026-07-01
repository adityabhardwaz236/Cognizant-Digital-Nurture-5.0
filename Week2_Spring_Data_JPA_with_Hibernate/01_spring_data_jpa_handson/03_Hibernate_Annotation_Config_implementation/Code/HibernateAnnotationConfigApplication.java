package com.cognizant.week2.jpa.handson03;

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
public class HibernateAnnotationConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateAnnotationConfigApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(RegionService regionService) {
        return args -> {
            regionService.addSampleRegions();
            regionService.listRegions().forEach(region ->
                System.out.println(region.getRegionCode() + " -> " + region.getRegionName()));
        };
    }
}

@Entity
@Table(name = "region")
class Region {

    @Id
    private String regionCode;
    private String regionName;

    Region() {
    }

    Region(String regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getRegionName() {
        return regionName;
    }
}

interface RegionRepository extends JpaRepository<Region, String> {
}

@org.springframework.stereotype.Service
class RegionService {

    private final RegionRepository regionRepository;

    RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    void addSampleRegions() {
        regionRepository.saveAll(List.of(
            new Region("NA", "North America"),
            new Region("EU", "Europe")
        ));
    }

    List<Region> listRegions() {
        return regionRepository.findAll();
    }
}
