package rate;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rate.controller.CourseController;
import rate.dto.CourseRegistrationStatus;
import rate.model.Course;

import java.util.List;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CourseControllerTest {
    @Autowired
    CourseController courseController;

    @Test
    @Order(1)
    public void insertUserCourseRelate(){
        courseController.insert(1,1);
    }

    @Test
    @Order(2)
    public void userWithId1IsRegistredToCourseWithId1(){
        List<CourseRegistrationStatus> courses=courseController.getCourseWithRegistrationStatus(1);
        CourseRegistrationStatus courseWithId1=null;
        for(int i=0;i<courses.size();i++)
            if(courses.get(i).courseId()==1){
                courseWithId1=courses.get(i);
                break;
            }
        System.out.println(courseWithId1.getId());
        System.out.println(courseWithId1.getUserRegistered());
        Assertions.assertTrue(courseWithId1!=null);
        Assertions.assertTrue(courseWithId1.getUserRegistered()==true);

    }
    @Test
    @Order(3)
    public void getCoursesPerWeekDay(){
        List<Course>[] courses=courseController.dataForCalendar(1);
        Assertions.assertTrue(courses.length==7);
    }

    @Test
    @Order(4)
    public void deleteUserCourseRelate(){
        courseController.delete(1,1);
    }
}
