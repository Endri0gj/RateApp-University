package rate.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "course_id")
    private Integer courseId;

    private Integer rating;
    private String comment;
    @Column(name = "publish_date")
    private Date date;

    public Feedback(Integer id, Integer userId, Integer courseId, Integer rating, String comment, Date date) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Feedback() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
