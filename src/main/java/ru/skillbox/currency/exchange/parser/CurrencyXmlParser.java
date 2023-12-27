package ru.skillbox.currency.exchange.parser;

import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.dto.ValCursDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;

@Service
public class CurrencyXmlParser {
    public ValCursDto toObject(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(ValCursDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(new StringReader(xml));
            return valCursDto;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
