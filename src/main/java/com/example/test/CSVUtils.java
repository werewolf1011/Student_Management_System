package com.example.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVUtils {

    public static List<studentData> readCSV(String[] filePaths) {
        List<studentData> users = new ArrayList<>();

        for (String filePath : filePaths) {
            try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
                String[] headers = csvReader.readNext(); // Read headers
                String[] nextLine;

                while ((nextLine = csvReader.readNext()) != null) {
                    String username = null;
                    String password = null;
                    String designation = null;

                    // Extract data based on the number of columns
                    if (nextLine.length <= 13) {
                        username = nextLine[6]; // Username
                        password = nextLine[7]; // Password
                        designation = nextLine[8]; // Designation
                    }

                    // Add user if data is valid
                    if (username != null && password != null && designation != null) {
                        users.add(new studentData(username, password, designation));
                    }
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public static List<studentData> readCSV(String csvFile) throws IOException, CsvException {
        List<studentData> dataList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = csvReader.readNext(); // Skip headers
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                // Assuming CSV format matches studentData fields order
                studentData student = new studentData(
                        line[0], line[1], line[2], line[3], line[4], line[5], line[6],
                        line[7], line[8], line[9], line[10], line[11], line[12]
                );
                dataList.add(student);
            }
        }
        return dataList;
    }

    public Map<String, String[]> readResultCSV(String filePath, int keyIndex) throws IOException {
        Map<String, String[]> dataMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read and ignore the header line
            if ((line = br.readLine()) != null) {
                // Read data lines
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length > keyIndex) {
                        String key = values[keyIndex];
                        dataMap.put(key, values);
                    }
                }
            }
        }
        return dataMap;
    }





}
