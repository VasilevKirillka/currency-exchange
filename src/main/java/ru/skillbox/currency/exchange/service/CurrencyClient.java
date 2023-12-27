package ru.skillbox.currency.exchange.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.client.JsonHttpClient;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.ValCursDto;
import ru.skillbox.currency.exchange.parser.CurrencyXmlParser;

import java.util.List;


@Slf4j
@Service
public class CurrencyClient {

    private final JsonHttpClient client;
    private final CurrencyXmlParser parser;

    @Autowired
    public CurrencyClient(JsonHttpClient client, CurrencyXmlParser parser) {
        this.client = client;
        this.parser = parser;
    }

    public List<CurrencyDto> getCurrency() {
        String xml = client.getCurrencies();
        ValCursDto cursDto = parser.toObject(xml);
        return cursDto.getValutes();
    }
}
