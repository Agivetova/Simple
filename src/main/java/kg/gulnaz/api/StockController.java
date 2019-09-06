package kg.gulnaz.api;

import kg.gulnaz.model.Stock;
import kg.gulnaz.service.StockService;
import kg.gulnaz.service.StockWithIPOExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Stock> getAll() {
        return stockService.getAll();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Stock newStock(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Stock update(@PathVariable(value = "id") long id, @RequestBody Stock stock) {
        stock.setId(id);
        return stockService.save(stock);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteStock(@PathVariable(value = "id") long id) {
        stockService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Stock getStock(@PathVariable(value = "id") long id) {
        return stockService.findById(id);
    }

    @ExceptionHandler(StockWithIPOExistsException.class)
    public ResponseEntity<ErrorResponse> handle(StockWithIPOExistsException ex) {
        ErrorResponse body = new ErrorResponse();
        body.setError("CONFLICTING");
        body.setMessage(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
