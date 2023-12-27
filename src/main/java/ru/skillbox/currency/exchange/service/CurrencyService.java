package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;
    private final CurrencyClient client;

    public List<Currency> getAll() {
        log.info("Запуск метода получения данных о всех валютах - getAll");
        return repository.findAllByOrderByNameAsc();
    }

    public CurrencyDto getById(Long id) {
        log.info("Запуск метода получения валюты по Id - getById");
        Currency currency = repository.findById(id).orElse(null);
        if (currency != null) {
            return mapper.convertToDto(currency);
        } else {
            log.info("Не найдена валюта с id: " + id);
            return null;
        }
    }

    public Double convertValue(Long value, Long numCode) {
        log.info("Запуск метода конвертации валюты - convertValue");
        Currency currency = repository.findByIsoNumCode(numCode);
        if (currency != null) {
            log.info("Конвертация валюты " + currency.getName() + " в количестве " + value);
            return value * currency.getValue();
        } else {
            log.info("Данного числового кода ISO не сушествует. Конвертация невозможна");
            return null;
        }
    }

    public CurrencyDto create(CurrencyDto dto) {
        log.info("Запуск метода create");
        Currency currency = mapper.convertToEntity(dto);
        if (!existNumCode(dto)) {
            repository.save(currency);
        } else {
            log.info("Данная валюта уже есть");
        }
        return mapper.convertToDto(currency);
    }

    public void update() {
        log.info("Запуск метода обновления валют - update");
        List<CurrencyDto> currencyDtos = client.getCurrency();
        currencyDtos
                .forEach(dto -> {
                    if (!existNumCode(dto)) {
                        repository.save(mapper.convertToEntity(dto));
                    }
                    mapper.updateEntityValue(repository, dto);
                });
    }

    private boolean existNumCode(CurrencyDto dto) {
        return repository.existsByIsoNumCode(dto.getIsoNumCode());
    }
}
