package com.example.test;

public class studentData {
    // Fields from the first class (User)
    private String username;
    private String password;
    private String designation;
    private String userNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private String email;
    private String faculty;
    private String year;
    private String phone;
    private String imageUrl;
    private String courseName;
    private String courseDescription;
    private Integer courseEnrolled;
    private String FullName;
    private String StudentId;
    private String InterestedSport;
    private String Q1;
    private String A1;
    private String Q2;
    private String A2;
    private String Q3;
    private String A3;
    private String SCId;
    private String Address;
    private String ProblemId;
    private String Subject;
    private String Description;

    public studentData() {}


    public studentData(String username, String password, String designation) {
        this.username = username;
        this.password = password;
        this.designation = designation;
    }

    public studentData(String userNumber, String firstName, String lastName, String gender,
                String birthDate, String email, String faculty, String year,
                String username, String password, String phone, String designation, String imageUrl) {
        this.userNumber = userNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.faculty = faculty;
        this.year = year;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.designation = designation;
        this.imageUrl = imageUrl;
    }

    public studentData(String userNumber, String year, String faculty, String firstName,
                String lastName, String gender, String birthDate, String designation) {
        this.userNumber = userNumber;
        this.year = year;
        this.faculty = faculty;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.designation = designation;
    }

    public studentData(String coursename, String courseDescription, Integer courseEnrolled) {
        this.courseName = coursename;
        this.courseDescription = courseDescription;
        this.courseEnrolled = courseEnrolled;
    }

    public studentData(String FullName, String Faculty, String StudentId, String InterestedSport){
        this.FullName = FullName;
        this.faculty = Faculty;
        this.StudentId = StudentId;
        this.InterestedSport = InterestedSport;
    }

    public studentData(String FullName, String Faculty, String StudentId, String Q1, String A1, String Q2, String A2, String Q3, String A3){
        this.FullName = FullName;
        this.StudentId = StudentId;
        this.faculty = Faculty;
        this.Q1 = Q1;
        this.A1 = A1;
        this.Q2 = Q2;
        this.A2 = A2;
        this.Q3 = Q3;
        this.A3 = A3;

    }

    public studentData(String SCId, String FullName,String ProblemId,String StudentId,String Address,String Faculty,String Gender,String Email,String Subject,String Description){
        this.SCId = SCId;
        this.FullName = FullName;
        this.ProblemId = ProblemId;
        this.StudentId = StudentId;
        this.Address = Address;
        this.faculty = Faculty;
        this.gender = Gender;
        this.email = Email;
        this.Subject = Subject;
        this.Description = Description;
    }
    // Getters and Setters for all fields


    public String getSCId() {
        return SCId;
    }

    public void setSCId(String SCId) {
        this.SCId = SCId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProblemId() {
        return ProblemId;
    }

    public void setProblemId(String problemId) {
        ProblemId = problemId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String q2) {
        Q2 = q2;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String q3) {
        Q3 = q3;
    }

    public String getA3() {
        return A3;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getInterestedSport() {
        return InterestedSport;
    }

    public void setInterestedSport(String interestedSport) {
        InterestedSport = interestedSport;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Integer getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(Integer courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getUserNumber() { return userNumber; }
    public void setUserNumber(String userNumber) { this.userNumber = userNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}
