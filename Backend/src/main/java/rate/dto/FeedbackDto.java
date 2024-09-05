package rate.dto;

import rate.model.Feedback;

import java.util.Date;

public class FeedbackDto implements Comparable<FeedbackDto>{
    private Integer userID;
    private String name;
    private String surname;
    private String email;
    private Integer rating;
    private String comment;
    private Date date;
    public int compareTo(FeedbackDto feedbackDto){
        int result=feedbackDto.date.compareTo(date); //E rendisim sipas dates ne rend zbrites
        return result;
    }
    public Feedback dtoToFeedback(Integer courseId){
        Feedback feedback= new Feedback();
        feedback.setUserId(userID);
        feedback.setCourseId(courseId);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setDate(date);
        return feedback;
    }

    public FeedbackDto(Integer userID, String name, String surname, String email, Integer rating, String comment, Date date) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
