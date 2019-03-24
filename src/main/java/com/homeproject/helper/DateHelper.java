package com.homeproject.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    // Приводим дату в формат dd-MM-yyyy
    public String formattingDate(LocalDate dateOfBirth){
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // Проверка високосный ли год
    public boolean checkLeapYear(int year,int month,int day){
        return LocalDate.of(year,month,day).isLeapYear();
    }
}
