package com.homeproject.processing;

import java.util.Random;

public class GenerateInn {

    private Random random = new Random();
    private int[] coefficientN2 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private int[] coefficientN1 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    // Генерация валидного ИНН
    public String getInn() {
        StringBuilder inn=new StringBuilder();
        //ИНН= Код субьекта(1-2)-Номер налоговой инспеции(2 цифры)-4 -
        // 10 - номер налоговой записи налогоплательщика-11 и 12 - контрольные цифры
        // Код региона Москва
        inn.append(77);

        // Налоговая инспецция в Москве их 51
        int numberTaxInspection = 1 + random.nextInt(51);
        if (numberTaxInspection < 10) {
            numberTaxInspection = '0' + numberTaxInspection;
        }
        inn.append(numberTaxInspection);

        // Номер налоговой записи генерируются в произвольном порядке от 000000 до 999999
        for (int i = 0; i < 6; i++) {
            int k = random.nextInt(9);
            inn.append(k);
        }
        // К промежуточному ИНН добавляем предпоследнее число
        inn.append(getDigit(inn.toString(),coefficientN2));
        // получаем полный валидный ИНН, добавляя последнее число
        inn.append(getDigit(inn.toString(),coefficientN1));

        return inn.toString();
    }

    //Получение последних цифр ИНН
    private int getDigit(String inn,int[] coefficient ){

        int lastDigit;
        int sumOfProductDigit = 0;
        int digitsInn;
        int innLength=inn.length();

        for (int i = 0; i < innLength; i++) {
            digitsInn = Character.digit(inn.toCharArray()[i], 10);
            sumOfProductDigit = sumOfProductDigit + digitsInn * coefficient[i];
        }
        lastDigit = sumOfProductDigit % 11;

        if (lastDigit == 10) {
            lastDigit = 0;
        }
        return lastDigit;
    }
}
