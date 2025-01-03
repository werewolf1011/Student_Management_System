//package com.example.test;
//
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Arrays;
//
//public class CSVDataLoader {
//
//
//    public static ObservableList<studentData> loadUserData(String[] csvFiles) throws IOException {
//        ObservableList<studentData> userDataList = FXCollections.observableArrayList();
//
//        for (String filePath : csvFiles) {
//            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//                String[] headers = reader.readNext(); // Read headers (skip for now)
//
//                String[] line;
//                while ((line = reader.readNext()) != null) {
//                    try {
//                        studentData userData = createStudentDataFromCsvLine(line);
//                        System.out.println(userDataList);
//                        if (userData != null) {
//                            userDataList.add(userData);
//                        }
//                    } catch (ParseException e) {
//                        System.err.println("Error parsing date: " + e.getMessage());
//                    }
//                }
//            } catch (CsvValidationException e) {
//                System.err.println("CSV validation error: " + e.getMessage());
//            }
//        }
//        return userDataList;
//    }
//
//    private static studentData createStudentDataFromCsvLine(String[] line) throws ParseException {
//
//
//        String userNum = line[0];
//        String year = line[9];
//        String faculty =  line[12];
//        String firstName = line[1];
//        String lastName = line[2];
//        String gender = line[3];
//        String birthDate = line[4];
//        String designation = line[8];
//
//        return new studentData(userNum, year, faculty, firstName, lastName, gender, birthDate, designation);
//    }
//}
