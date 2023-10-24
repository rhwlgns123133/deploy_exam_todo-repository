package com.example.springtest.controller.formatter;

import lombok.extern.log4j.Log4j2;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Log4j2
public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException{
        log.info(text);
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
