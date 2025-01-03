package com.example.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserDetails {
    private SimpleStringProperty userNumber;
    private SimpleStringProperty fullName;
    private SimpleStringProperty username;
    private SimpleStringProperty email;
    private SimpleStringProperty faculty;
    private SimpleStringProperty interestedSport;
    private SimpleStringProperty scId;
    private SimpleStringProperty problemSubject;
    private SimpleIntegerProperty surveyCount;
    private SimpleStringProperty enrolledCourses;

    public UserDetails(String userNumber, String fullName, String username, String email,
                       String faculty, String interestedSport, String scId, String problemSubject,
                       int surveyCount, String enrolledCourses) {
        this.userNumber = new SimpleStringProperty(userNumber);
        this.fullName = new SimpleStringProperty(fullName);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.faculty = new SimpleStringProperty(faculty);
        this.interestedSport = new SimpleStringProperty(interestedSport);
        this.scId = new SimpleStringProperty(scId);
        this.problemSubject = new SimpleStringProperty(problemSubject);
        this.surveyCount = new SimpleIntegerProperty(surveyCount);
        this.enrolledCourses = new SimpleStringProperty(enrolledCourses);
    }

    // Getters for SimpleProperties
    public String getUserNumber() { return userNumber.get(); }
    public String getFullName() { return fullName.get(); }
    public String getUsername() { return username.get(); }
    public String getEmail() { return email.get(); }
    public String getFaculty() { return faculty.get(); }
    public String getInterestedSport() { return interestedSport.get(); }
    public String getScId() { return scId.get(); }
    public String getProblemSubject() { return problemSubject.get(); }
    public int getSurveyCount() { return surveyCount.get(); }
    public String getEnrolledCourses() { return enrolledCourses.get(); }

    // Property getters for JavaFX bindings
    public SimpleStringProperty userNumberProperty() { return userNumber; }
    public SimpleStringProperty fullNameProperty() { return fullName; }
    public SimpleStringProperty usernameProperty() { return username; }
    public SimpleStringProperty emailProperty() { return email; }
    public SimpleStringProperty facultyProperty() { return faculty; }
    public SimpleStringProperty interestedSportProperty() { return interestedSport; }
    public SimpleStringProperty scIdProperty() { return scId; }
    public SimpleStringProperty problemSubjectProperty() { return problemSubject; }
    public SimpleIntegerProperty surveyCountProperty() { return surveyCount; }
    public SimpleStringProperty enrolledCoursesProperty() { return enrolledCourses; }
}
