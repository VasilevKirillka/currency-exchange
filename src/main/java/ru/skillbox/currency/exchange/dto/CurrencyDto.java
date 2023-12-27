package ru.skillbox.currency.exchange.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;

@ToString
public class CurrencyDto {

    private Long nominal;
    private String name;
    private String value;
    private Long isoNumCode;
    private String isoCharCode;

    public CurrencyDto() {
    }

    @XmlElement(name = "NumCode")
    public Long getIsoNumCode() {
        return isoNumCode;
    }

    public void setIsoNumCode(Long isoNumCode) {
        this.isoNumCode = isoNumCode;
    }

    @XmlElement(name = "CharCode")
    public String getIsoCharCode() {
        return isoCharCode;
    }

    public void setIsoCharCode(String isoCharCode) {
        this.isoCharCode = isoCharCode;
    }

    @XmlElement(name = "Nominal")
    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}