package com.example.test;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriterUtil {
    public static void updateCourse(studentData updatedCourse) throws IOException {
        String filePath = "Courses.csv";

        // Read all lines from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Update the course count in memory
        boolean isUpdated = false;
        if (!lines.isEmpty()) {
            String headerLine = lines.get(0);
            List<String> updatedLines = new ArrayList<>();
            updatedLines.add(headerLine); // Write header to updated lines

            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                if (fields.length >= 3) {
                    String courseName = fields[0].trim();
                    if (courseName.equals(updatedCourse.getCourseName())) {
                        // Update the course count
                        int currentCount = Integer.parseInt(fields[2].trim());
                        fields[2] = String.valueOf(currentCount + 1);
                        isUpdated = true;
                    }
                    updatedLines.add(String.join(",", fields));
                } else {
                    updatedLines.add(lines.get(i)); // Add line unchanged if it doesn't match format
                }
            }

            // If the course was not found, add it
            if (!isUpdated) {
                updatedLines.add(String.format("%s,%s,%d",
                        updatedCourse.getCourseName(),
                        updatedCourse.getCourseDescription(),
                        updatedCourse.getCourseEnrolled()));
            }

            // Write all lines back to the original file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }
        }
    }



        // Method to write course details to "Courses.csv"
    public static void writeCourse(studentData course) throws IOException {
        String csvFilePath = "Courses.csv"; // Path to your Courses.csv file

        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFilePath, true))
                .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            String[] courseRecord = {
                    course.getCourseName(),           // Course Name
                    course.getCourseDescription() ,    // Course Description
                    String.valueOf(course.getCourseEnrolled())
            };
            writer.writeNext(courseRecord);
        }
    }

    public static void writeUser(studentData user) throws IOException {
        String csvFilePath = getCsvFilePath(user.getDesignation());

        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFilePath, true))
                .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            String[] userRecord = {
                    String.valueOf(user.getUserNumber()), // 1. User#
                    user.getFirstName(),                   // 2. First Name
                    user.getLastName(),                    // 3. Last Name
                    user.getGender(),                      // 4. Gender
                    user.getBirthDate(),                   // 5. Birth Date
                    user.getEmail(),                       // 6. Email
                    user.getUsername(),                    // 7. Username
                    user.getPassword(),                    // 8. Password
                    user.getDesignation(),                 // 9. Designation
                    user.getYear(),                         // 10. Year
                    user.getImageUrl(),                     // 11. image url
                    user.getPhone(),                        // 12. phone
                    user.getFaculty()                       // 13. Faculty
            };
            writer.writeNext(userRecord);
        }
    }

    private static String getCsvFilePath(String designation) {
        switch (designation) {
            case "Admin":
                return "Admin.csv";
            case "Teacher":
                return "Teacher.csv";
            case "Librarian":
                return "Librarian.csv";
            case "AdmissionOfficer":
                return "AdmissionOfficer.csv";
            case "Student":
                return "StudentData.csv";
            default:
                throw new IllegalArgumentException("Unknown designation: " + designation);
        }
    }


    public static void writeSportDetail(studentData participants) throws IOException {
        String csvFilePath = "SportParticipants.csv";
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFilePath, true))
                .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            String[] ParticipantsRecord = {
                    participants.getFullName(),
                    participants.getFaculty() ,
                    participants.getStudentId(),
                    participants.getInterestedSport()
            };
            writer.writeNext(ParticipantsRecord);
        }
    }

    public static void writeProblem(studentData newProblem) throws IOException {
        String csvFilePath = "Problem.csv";
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFilePath, true))
                .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            String[] ProblemRecord = {
                    newProblem.getSCId(),
                    newProblem.getFullName(),
                    newProblem.getProblemId(),
                    newProblem.getStudentId(),
                    newProblem.getAddress(),
                    newProblem.getFaculty() ,
                    newProblem.getGender(),
                    newProblem.getEmail(),
                    newProblem.getSubject(),
                    newProblem.getDescription()

            };
            writer.writeNext(ProblemRecord);
        }
    }


    public static void writeSurveyDetailsl(studentData newSurvey) throws IOException {
        String csvFilePath = "Survey.csv";
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFilePath, true))
                .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            String[] SurveyRecord = {
                    newSurvey.getFullName(),
                    newSurvey.getStudentId(),
                    newSurvey.getFaculty() ,
                    newSurvey.getQ1(),
                    newSurvey.getA1(),
                    newSurvey.getQ2(),
                    newSurvey.getA2(),
                    newSurvey.getQ3(),
                    newSurvey.getA3(),

            };
            writer.writeNext(SurveyRecord);
        }
    }
}
