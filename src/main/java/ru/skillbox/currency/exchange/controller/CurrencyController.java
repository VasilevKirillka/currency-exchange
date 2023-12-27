package ru.skillbox.currency.exchange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.service.CurrencyService;

import java.util.List;


@RestController
@EnableScheduling
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping
    ResponseEntity<List<Currency>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id) {
        CurrencyDto result = service.getById(id);
        return result == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Не найдена валюта с id: " + id)
                : ResponseEntity.ok(result);
    }

    @GetMapping(value = "/convert")
    ResponseEntity<?> convertValue(@RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
        Double result = service.convertValue(value, numCode);
        return result == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Данного числового кода ISO не сушествует. Конвертация невозможна")
                : ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Scheduled(cron = "${cron.scheduler}")
    @PostMapping("/update")
    public void update() {
        service.update();
    }
}
