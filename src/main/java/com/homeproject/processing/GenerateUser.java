package com.homeproject.processing;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.homeproject.helper.ParserFromJsonHelper;
import com.homeproject.models.User;
import com.homeproject.helper.PathToFiles;
import java.time.LocalDate;
import java.util.Random;

public class GenerateUser {

    private GenerateData generateData =new GenerateData();
    private PathToFiles fPath=new PathToFiles();
    private GenerateInn inn=new GenerateInn();
    private Random random = new Random();


    // Получаем пользователей из локальной базы
    public User getUserFromLocalDatabase(int count) {

        User user = new User();
        String firstName;
        String lastName;
        String patronymic;
        String gender;
        LocalDate dateBirth = generateData.getDataBirth();

        if(count % 2 == 0){
            firstName=generateData.getNameFromFile(fPath.fileNameMan)[count];
        }else {
            firstName=generateData.getNameFromFile(fPath.fileNameWoman)[count];
        }
        // Тут получаем остальные данные в зависимости от пола юзера
        if (generateData.getGender(firstName)) {
            lastName = generateData.getNameFromFile(fPath.fileSecondNameMan)[count];
            patronymic = generateData.getNameFromFile(fPath.filePatronymicMan)[count];
            gender = "М";
        } else {
            lastName = generateData.getNameFromFile(fPath.fileSecondNameWoman)[count];
            patronymic = generateData.getNameFromFile(fPath.filePatronymicWoman)[count];
            gender = "Ж";
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic);
        user.setGender(gender);
        user.setCountry(generateData.getNameFromFile(fPath.fileCountries)[count]);
        user.setStreet(generateData.getNameFromFile(fPath.fileStreets)[count]);
        user.setInn(String.valueOf(inn.getInn()));
        user.setHouse(String.valueOf(1 + random.nextInt(200)));
        user.setFlat(String.valueOf(1 + random.nextInt(500)));
        user.setDateOfBorn(dateBirth);
        user.setAge(String.valueOf(generateData.getAge(dateBirth)));
        user.setIndex(String.valueOf(generateData.getIndex()));
        user.setCity(generateData.getNameFromFile(fPath.fileCity)[count]);
        user.setState(generateData.getNameFromFile(fPath.fileOblast)[count]);

        return user;
    }

}
