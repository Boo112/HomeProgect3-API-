package com.homeproject.worker;

import com.homeproject.helper.DateHelper;
import com.homeproject.models.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelWorker {

    private DateHelper dateHelper =new DateHelper();

    public void createExcelFile(String filename) {

        File excelFile = new File(filename);
        try(FileOutputStream fileOut = new FileOutputStream(excelFile);
            HSSFWorkbook workbook = new HSSFWorkbook()) {

            HSSFSheet sheet = workbook.createSheet("Данные пользователей");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Имя");
            rowhead.createCell(1).setCellValue("Фамилия");
            rowhead.createCell(2).setCellValue("Отчество");
            rowhead.createCell(3).setCellValue("Возраст");
            rowhead.createCell(4).setCellValue("Пол");
            rowhead.createCell(5).setCellValue("Дата рождения");
            rowhead.createCell(6).setCellValue("ИНН");
            rowhead.createCell(7).setCellValue("Почтовый индекс");
            rowhead.createCell(8).setCellValue("Страна");
            rowhead.createCell(9).setCellValue("Область");
            rowhead.createCell(10).setCellValue("Город");
            rowhead.createCell(11).setCellValue("Улица");
            rowhead.createCell(12).setCellValue("Дом");
            rowhead.createCell(13).setCellValue("Квартира");

            workbook.write(fileOut);

            System.out.println("\n" + "Файл создан. " + "Путь: " + excelFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void reWriteExcel(String fileName, User user) {

        File excelFile = new File(fileName);
        try (FileInputStream inputStream = new FileInputStream(excelFile);
             HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
             FileOutputStream fileOut = new FileOutputStream(excelFile)){

            HSSFSheet sheet = workbook.getSheetAt(0);

            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            //Дописываем данные
            row.createCell(0).setCellValue(user.getFirstName());
            row.createCell(1).setCellValue(user.getLastName());
            row.createCell(2).setCellValue(user.getPatronymic());
            row.createCell(4).setCellValue(user.getGender());
            row.createCell(3).setCellValue(user.getAge());
            row.createCell(5).setCellValue(dateHelper.formattingDate(user.getDateOfBorn()));
            row.createCell(6).setCellValue(user.getInn());
            row.createCell(7).setCellValue(user.getIndex());
            row.createCell(8).setCellValue(user.getCountry());
            row.createCell(9).setCellValue(user.getState());
            row.createCell(10).setCellValue(user.getCity());
            row.createCell(11).setCellValue(user.getStreet());
            row.createCell(12).setCellValue(user.getHouse());
            row.createCell(13).setCellValue(user.getFlat());

            int lastCellNum=row.getLastCellNum()-1;
            for (int i = 0; i < lastCellNum; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOut);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
