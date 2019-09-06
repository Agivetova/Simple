package kg.gulnaz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Stock {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    @JsonProperty(value = "IPO")
    private String ipo;
    @Getter
    @Setter
    @JsonFormat(pattern = "#0.00")
    private BigDecimal price;

    public Stock() {
    }

    public Stock(String ipo, BigDecimal price) {
        this(0, ipo, price);
    }

    public Stock(long id, String ipo, BigDecimal price) {
        this.id = id;
        this.ipo = ipo;
        this.price = price;
    }
}
