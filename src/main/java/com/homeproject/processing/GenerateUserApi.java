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
    public User getUserFromJSON(StringBuffer temp, int count) {

        User user = new User();
        JsonElement jsonTree = new JsonParser().parse(temp.toString());
        JsonElement results = jsonTree.getAsJsonObject().get("results");
        JsonElement name = getArrayObject(results, "name");
        JsonElement firstName = getValue(name, "first");
        JsonElement lastName = getValue(name, "last");
        JsonElement gender = getArrayObject(results, "gender");
        JsonElement nationality = getArrayObject(results, "nat");
        JsonElement location = getArrayObject(results, "location");
        JsonElement state = getValue(location, "state");
        JsonElement city = getValue(location, "city");
        JsonElement street = getValue(location, "street");
        JsonElement index = getValue(location, "postcode");
        JsonElement dob = getArrayObject(results, "dob");
        JsonElement age = getValue(dob, "age");
        JsonElement dateOfBirth = getValue(dob, "date");

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

    private JsonElement getArrayObject(JsonElement array, String objectName) {
        return array.getAsJsonArray().get(0).getAsJsonObject().get(objectName);
    }

    private JsonElement getValue(JsonElement object, String objectName) {
        if (object.isJsonObject()) {
            return object.getAsJsonObject().get(objectName);
        }
        return null;
    }
}
