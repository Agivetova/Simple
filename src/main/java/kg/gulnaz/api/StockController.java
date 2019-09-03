package kg.gulnaz.api;

import kg.gulnaz.model.Stock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "/api/stocks")
public class StockController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Stock> getAll() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("APPL.M", BigDecimal.valueOf(100.0)));
        stocks.add(new Stock("IBM.L", BigDecimal.valueOf(93.34)));
        stocks.add(new Stock("MAC.L", BigDecimal.valueOf(94.90)));

        return stocks;
    }
}
