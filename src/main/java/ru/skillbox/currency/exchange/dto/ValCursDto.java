package ru.skillbox.currency.exchange.dto;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@ToString
@XmlRootElement(name = "ValCurs")
public class ValCursDto {
    private String date;

    private List<CurrencyDto> valutes;

    public ValCursDto() {
    }

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "Valute")
    public List<CurrencyDto> getValutes() {
        return valutes;
    }

    public void setValutes(List<CurrencyDto> valutes) {
        this.valutes = valutes;
    }

}
