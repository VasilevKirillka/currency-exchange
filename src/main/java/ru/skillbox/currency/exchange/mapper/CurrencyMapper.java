package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;


@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);
    @Mapping(target = "value", expression = "java(convertValue(currencyDto.getValue()))")
    Currency convertToEntity(CurrencyDto currencyDto);

    default Double convertValue(String value){
        String str= value.replace(',','.');
        return Double.parseDouble(str);
    }

    default void updateEntityValue(CurrencyRepository repository, CurrencyDto currencyDto) {
        Currency currency = repository.findByIsoNumCode(currencyDto.getIsoNumCode());
        if(currency != null){
          currency.setValue(convertValue(currencyDto.getValue()));
          currency.setIsoCharCode(currencyDto.getIsoCharCode());
          repository.save(currency);
        }
    }
}
