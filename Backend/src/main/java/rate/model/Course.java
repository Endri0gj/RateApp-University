package rate.model;

import jakarta.persistence.*;

@Entity
@Table (name = "course")
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name = "average_rates")
    private Double averageRates;

    private String professor;
    @Column(name = "total_hours")
    private Double totalHours;
    @Column(name = "week_day")
    private String weekDay;

    @Column(name="start_time")
    private String startTime;

    @Column(name="end_time")
    private String endTime;

    @Column (name = "registered_students")
    private Integer registeredStudents;

    @Column (name="noOfFeedbacks")
    private Integer noOfFeedbacks;

    public int compareTo(Course otherCourse){
        return Double.compare(otherCourse.averageRates,this.averageRates);
    }

    public Course() {
    }

    public Course(Integer id, String name, Double averageRates, String professor, Double totalHours, String weekDay, String startTime, String endTime, Integer registeredStudents, Integer noOfFeedbacks) {
        this.id = id;
        this.name = name;
        this.averageRates = averageRates;
        this.professor = professor;
        this.totalHours = totalHours;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registeredStudents = registeredStudents;
        this.noOfFeedbacks = this.noOfFeedbacks;
    }

    public Integer getNoOfFeedbacks() {
        return noOfFeedbacks;
    }

    public void setNoOfFeedbacks(Integer noOfFeedbacks) {
        this.noOfFeedbacks = noOfFeedbacks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAverageRates() {
        return averageRates;
    }

    public void setAverageRates(Double averageRates) {
        this.averageRates = averageRates;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Integer registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
