package rate.dto;

import rate.model.Course;

public class CourseRegistrationStatus extends Course{
    private Boolean isUserRegistered;
    public CourseRegistrationStatus(Course course){
        super(course.getId(),course.getName(),course.getAverageRates(),course.getProfessor(),
                course.getTotalHours(), course.getWeekDay(),course.getStartTime(),
                course.getEndTime(),course.getRegisteredStudents(), course.getNoOfFeedbacks());
        this.isUserRegistered=false;
    }
    public Integer courseId(){
        return super.getId();
    }

    public Boolean getUserRegistered() {
        return isUserRegistered;
    }

    public void setUserRegistered(Boolean userRegistered) {
        isUserRegistered = userRegistered;
    }
}
