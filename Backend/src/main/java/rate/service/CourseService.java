package rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rate.dto.CourseRegistrationStatus;
import rate.model.Course;
import rate.repository.CourseRepo;

import java.util.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    public List<Course> findAll(){
        List<Course> allCourses=courseRepo.findAll();
        Collections.sort(allCourses);
        return allCourses;
    }


    //ALDINI
    public List<CourseRegistrationStatus> findCourseRegistrationStatus(int userId){
        List<CourseRegistrationStatus> coursesPerUser=new ArrayList<>();

        HashMap<Integer,CourseRegistrationStatus> courseHash=new HashMap<>();
        List<Course> allCourse=findAll();
        for(Course course : allCourse){
            courseHash.put(course.getId(),new CourseRegistrationStatus(course));
        }

        List<Object[]> courses=courseRepo.getCoursesByUserId(userId);
        for(Object[] course : courses){
            Integer courseId=(Integer) course[0];
            CourseRegistrationStatus changeRegistrationStatus=courseHash.get(courseId);
            changeRegistrationStatus.setUserRegistered(true);
            courseHash.put(courseId,changeRegistrationStatus);
        }

        for(Course course : allCourse){
            Integer courseId=course.getId();
            coursesPerUser.add(courseHash.get(courseId));
        }
        return coursesPerUser;
    }

    public void deleteUserCourseRelate(Integer courseId,Integer userId){
        courseRepo.deleteUserCourseRelate(courseId,userId);
    }

    public void insertUserCourseRelate(Integer courseId,Integer userId){
        courseRepo.insertUserCourseRelate(courseId,userId);
    }

    public Course findById(Integer id){
        return courseRepo.findById(id).orElse(null);
    }
    public void updateCourse(Integer courseId,int operation){
        Course course=findById(courseId);
        if(course==null)
            throw new RuntimeException();
        course.setId(courseId);
        int noOfStudents=course.getRegisteredStudents();
        noOfStudents=noOfStudents+operation;
        course.setRegisteredStudents(noOfStudents);
        courseRepo.save(course);
    }


    //Calendar
    public List<Course> [] dataForCalendar(Integer userId){
        List<CourseRegistrationStatus> courses=findCourseRegistrationStatus(userId);
        List<Course> [] courseInCalendar=new List[7];
        for (int i = 0; i < 7; i++)
            courseInCalendar[i] = new ArrayList<>();

        for(CourseRegistrationStatus course : courses){
            if(course.getUserRegistered()){
                switch (course.getWeekDay()){
                    case "Sunday": courseInCalendar[0].add(course); break;
                    case "Monday": courseInCalendar[1].add(course); break;
                    case "Tuesday": courseInCalendar[2].add(course); break;
                    case "Wednesday": courseInCalendar[3].add(course); break;
                    case "Thursday": courseInCalendar[4].add(course); break;
                    case "Friday": courseInCalendar[5].add(course); break;
                    case "Saturday": courseInCalendar[6].add(course); break;
                }
            }
            //else : User is not registred in that course, skip it!
        }


        return courseInCalendar;
    }

    //Ndryshojme vleresimin mesatar  kur vjene nje feedback i ri
    public boolean updateAverageRating(int newRate,Integer courseId){
        Course course=courseRepo.findById(courseId).orElse(null);
        if(course==null) return false;
        int noOfFeedback=course.getNoOfFeedbacks();
        Double averageRate=course.getAverageRates();

        Double totalRate=averageRate*noOfFeedback;
        noOfFeedback++;
        course.setNoOfFeedbacks(noOfFeedback);
        averageRate=(totalRate+newRate)/noOfFeedback;
        course.setId(courseId);
        course.setAverageRates(averageRate);
        courseRepo.save(course);
        return true;
    }
}
