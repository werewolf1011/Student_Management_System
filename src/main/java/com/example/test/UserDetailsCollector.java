package com.example.test;

import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsCollector {

    private Map<String, String[]> studentData;
    private Map<String, String[]> sportParticipants;
    private Map<String, String[]> surveyData;
    private Map<String, String[]> problemData;

    public UserDetailsCollector() throws IOException {
        CSVUtils csvReader = new CSVUtils();
        studentData = csvReader.readResultCSV("StudentData.csv",0);
        sportParticipants = csvReader.readResultCSV("SportParticipants.csv",2);
        surveyData = csvReader.readResultCSV("Survey.csv",2);
        problemData = csvReader.readResultCSV("Problem.csv",3);
    }

    public Map<String, UserDetails> getAllUserDetails() {
        Map<String, UserDetails> userDetailsMap = new HashMap<>();

        for (String studentId : studentData.keySet()) {
            UserDetails userDetails = getUserDetails(studentId);
            if (userDetails != null) {
                userDetailsMap.put(studentId, userDetails);
            }
        }

        return userDetailsMap;
    }

    private UserDetails getUserDetails(String studentId) {
        String[] studentInfo = studentData.get(studentId);
        if (studentInfo == null) return null;

        String fullName = studentInfo[1] + " " + studentInfo[2];
        String username = studentInfo[6];
        String email = studentInfo[5];
        String faculty = studentInfo[12];

        String[] sportInfo = sportParticipants.get(studentId);
        String interestedSport = sportInfo != null ? sportInfo[3] : "N/A";

        String[] problemInfo = problemData.get(studentId);
        String scId = problemInfo != null ? problemInfo[0] : "N/A";
        String problemSubject = problemInfo != null ? problemInfo[8] : "N/A";

        int surveyCount = 0;
        for (String[] survey : surveyData.values()) {
            if (survey[2].equals(studentId)) {
                surveyCount++;
            }
        }

        String enrolledCourses = readEnrolledCourses(username);

        return new UserDetails(
                studentId,
                fullName,
                username,
                email,
                faculty,
                interestedSport,
                scId,
                problemSubject,
                surveyCount,
                enrolledCourses
        );
    }

    private String readEnrolledCourses(String username) {
        String fileName = username + "_enrolledCourses.txt";
        StringBuilder courses = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                courses.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses.toString();
    }
}
