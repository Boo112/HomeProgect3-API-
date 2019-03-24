package com.homeproject.helper;

import com.google.gson.JsonElement;
import com.homeproject.processing.GenerateData;

import java.time.LocalDate;

public class ParserFromJsonHelper {

    // Преобразуем строковую дату рождения из Json в в LocalDate
    public LocalDate getFormatedDate(String dateOfBirth) {
        int day;
        int year;
        int month;
        int index = dateOfBirth.indexOf('T');

        dateOfBirth = dateOfBirth.substring(0, index);
        year = Integer.parseInt(dateOfBirth.substring(0, 4));
        day = Integer.parseInt(dateOfBirth.substring(8, index));
        month = Integer.parseInt(dateOfBirth.substring(5, 7));

        return LocalDate.of(year, month, day);
    }

    //Получаем номер дома из названия улицы
    public String getHouseFromStreet(String street) {
        return street.replaceAll("\\D+", "");
    }

    // Получаем страну из национальности
    public String getCountry(String nationality) {
        String country;

        //AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IR, NO, NL, NZ, TR, US
        switch (nationality) {
            case "BR":
                country = "Brazil";
                break;
            case "US":
                country = "United States";
                break;
            case "FR":
                country = "France";
                break;
            case "CA":
                country = "Canada";
                break;
            case "AU":
                country = "Australia";
                break;
            case "CH":
                country = "China";
                break;
            case "DE":
                country = "Germany";
                break;
            case "DK":
                country = "Denmark";
                break;
            case "ES":
                country = "Spain";
                break;
            case "FI":
                country = "Finland";
                break;
            case "GB":
                country = "Great Britain";
                break;
            case "IE":
                country = "Norway";
                break;
            case "NO":
                country = "France";
                break;
            case "NL":
                country = "France";
                break;
            case "IR":
                country = "Turkey";
                break;
            case "NZ":
                country = "New Zealand";
                break;
            case "TR":
                country = "France";
                break;
            default:
                country = "Unknown Country";
                break;
        }
        return country;
    }

    // Преобразование в нормальную отформатированную строку данных из Json
    public String getFormatedData(JsonElement jsonElement) {
        return jsonElement.toString().replace("\"", "");
    }

    // Проеобазуем male в "М"
    public String genderToRus(JsonElement jsonGender){
        String gender;

        if(getFormatedData(jsonGender).equals("male")) {
            gender="M";
        }else {
            gender="Ж";
        }
        return gender;
    }

    // получаем отчество
    public String getPatronymic(JsonElement jsonGender, GenerateData generateData, PathToFiles fPath, int count){
        String patronymic;

        if(getFormatedData(jsonGender).equals("male")) {
            patronymic = generateData.getNameFromFile(fPath.filePatronymicMan)[count];
        }else{
            patronymic = generateData.getNameFromFile(fPath.filePatronymicWoman)[count];
        }
        return patronymic;
    }

    public String getStreetWithoutHouse (String street,String house){
        return street.replaceAll(house,"");
    }

}
