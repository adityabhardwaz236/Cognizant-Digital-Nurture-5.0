package com.cognizant.week2.jpa3.ex02;

import java.math.BigDecimal;
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
public class StockQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(StockService stockService) {
        return args -> {
            stockService.seedStocks();
            System.out.println(stockService.findBySector("Technology").size());
            System.out.println(stockService.findStocksAbovePrice(new BigDecimal("500")).size());
            stockService.listSymbols().forEach(System.out::println);
        };
    }
}

@Entity
@Table(name = "stock")
class Stock {

    @Id
    private String symbol;
    private String companyName;
    private String sector;
    private BigDecimal price;

    Stock() {
    }

    Stock(String symbol, String companyName, String sector, BigDecimal price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSector() {
        return sector;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

interface StockRepository extends JpaRepository<Stock, String> {

    List<Stock> findBySectorIgnoreCase(String sector);

    List<Stock> findByPriceGreaterThan(BigDecimal price);

    @Query("select s.symbol from Stock s order by s.symbol")
    List<String> fetchSymbols();
}

@org.springframework.stereotype.Service
class StockService {

    private final StockRepository stockRepository;

    StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    void seedStocks() {
        stockRepository.saveAll(List.of(
            new Stock("INFY", "Infosys", "Technology", new BigDecimal("1500")),
            new Stock("TCS", "Tata Consultancy Services", "Technology", new BigDecimal("3600")),
            new Stock("HDFCBANK", "HDFC Bank", "Banking", new BigDecimal("1600"))
        ));
    }

    List<Stock> findBySector(String sector) {
        return stockRepository.findBySectorIgnoreCase(sector);
    }

    List<Stock> findStocksAbovePrice(BigDecimal price) {
        return stockRepository.findByPriceGreaterThan(price);
    }

    List<String> listSymbols() {
        return stockRepository.fetchSymbols();
    }
}
