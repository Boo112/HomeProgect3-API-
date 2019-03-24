package com.homeproject.processing;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.homeproject.helper.ParserFromJsonHelper;
import com.homeproject.helper.PathToFiles;
import com.homeproject.models.User;

import java.util.Random;

public class GenerateUserApi {

    private ParserFromJsonHelper parserFromJson=new ParserFromJsonHelper();
    private GenerateData generateData=new GenerateData();
    private PathToFiles fPath=new PathToFiles();
    private GenerateInn inn=new GenerateInn();
    private Random random = new Random();

    // Получение данных о пользователе из "https://randomuser.me/api/"
    public User getUserFromJSON(StringBuffer httpResponse, int count) {

        User user = new User();
        JsonElement jsonTree = new JsonParser().parse(httpResponse.toString());
        JsonElement results = jsonTree.getAsJsonObject().get("results");
        JsonElement name = parserFromJson.getArrayObject(results, "name");
        JsonElement firstName = parserFromJson.getValue(name, "first");
        JsonElement lastName = parserFromJson.getValue(name, "last");
        JsonElement gender = parserFromJson.getArrayObject(results, "gender");
        JsonElement nationality = parserFromJson.getArrayObject(results, "nat");
        JsonElement location = parserFromJson.getArrayObject(results, "location");
        JsonElement state = parserFromJson.getValue(location, "state");
        JsonElement city = parserFromJson.getValue(location, "city");
        JsonElement street = parserFromJson.getValue(location, "street");
        JsonElement index = parserFromJson.getValue(location, "postcode");
        JsonElement dob = parserFromJson.getArrayObject(results, "dob");
        JsonElement age = parserFromJson.getValue(dob, "age");
        JsonElement dateOfBirth = parserFromJson.getValue(dob, "date");

        user.setFirstName(parserFromJson.getFormatedData(firstName));
        user.setLastName(parserFromJson.getFormatedData(lastName));
        user.setPatronymic(parserFromJson.getPatronymic(gender,generateData,fPath,count));
        user.setGender(parserFromJson.genderToRus(gender));
        user.setCountry(parserFromJson.getCountry(nationality.toString().replace("\"", "")));
        user.setStreet(parserFromJson.getStreetWithoutHouse (street.toString().replace("\"", ""),
                parserFromJson.getHouseFromStreet(street.toString().replace("\"", ""))));
        user.setInn(String.valueOf(inn.getInn()));
        user.setHouse(parserFromJson.getHouseFromStreet(street.toString().replace("\"", "")));
        user.setFlat(String.valueOf(1 + random.nextInt(500)));
        user.setDateOfBorn(parserFromJson.getFormatedDate(parserFromJson.getFormatedData(dateOfBirth)));
        user.setAge(parserFromJson.getFormatedData(age));
        user.setIndex(parserFromJson.getFormatedData(index));
        user.setCity(parserFromJson.getFormatedData(city));
        user.setState(parserFromJson.getFormatedData(state));

        return user;
    }

}
