package kg.gulnaz.service;

import kg.gulnaz.entity.StockEntity;
import kg.gulnaz.model.Stock;
import kg.gulnaz.model.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

//*
@RequiredArgsConstructor

public class StockService {
    private StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAll() {
        Iterable<StockEntity> iterator = stockRepository.findAll();
        List<Stock> list = new ArrayList<>();
        for (StockEntity  e: iterator) {
            list.add(new Stock(e.getId(), e.getIpo(), asMoney(e.getPrice())));
        }
        return list;
    }

    @Transactional
    public Stock save(Stock stock) {
        try {
            return saveOrUpdate(stock);
        } catch (DataIntegrityViolationException ex) {
            throw new StockWithIPOExistsException(String.format("Stock with %s already exists", stock.getIpo()));
        }
    }

    private Stock saveOrUpdate(Stock stock) {
        boolean isNew = stock.getId() == 0;
        if (isNew) {
            StockEntity newEntity = stockRepository.save(new StockEntity(0, stock.getIpo(), toCoins(stock.getPrice())));
            stock.setId(newEntity.getId());
            return stock;

        }
        // if exisiting entity
        StockEntity existing = stockRepository.findById(stock.getId()).orElse(null);
        if (existing == null) {
            throw new EntityNotFoundException(String.format("Entity with ID %d not found", stock.getId()));
        }

        existing.setIpo(stock.getIpo());
        existing.setPrice(toCoins(stock.getPrice()));
        stockRepository.save(existing);
        return stock;
    }

    @Transactional
    public void delete(long id) {
        Optional<StockEntity> found = stockRepository.findById(id);
        if (!found.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity with ID %d not found", id));
        }
        stockRepository.delete(found.get());
    }

    public Stock findById(long id) {
        Optional<StockEntity> found = stockRepository.findById(id);
        if (!found.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity with ID %d not found", id));
        }
        StockEntity entity = found.get();
        return new Stock(entity.getId(), entity.getIpo(), asMoney(entity.getPrice()));
    }

    private BigDecimal asMoney(long coins) {
        if (coins == 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(coins / 100);
    }

    private long toCoins(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        return price.multiply(BigDecimal.valueOf(100)).longValue();
    }

}
