package kg.gulnaz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Stock {
    @Getter
    @Setter
    @JsonProperty(value = "IPO")
    private String ipo;
    @Getter
    @Setter
    @JsonFormat(pattern = "#0.00")
    private BigDecimal price;

    public Stock(String ipo, BigDecimal price) {
        this.ipo = ipo;
        this.price = price;
    }
}
