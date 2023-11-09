package com.btssio.models.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DATE_FORMAT);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return DATE_FORMAT.format(v);
    }
}
