package rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import rate.model.Course;
import rate.model.User;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Integer> {
    @Query(value = "SELECT * FROM user_course_relate u WHERE u.user_id = :userId ", nativeQuery = true)
    List<Object[]> getCoursesByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value ="DELETE FROM user_course_relate u WHERE u.course_id=:courseId AND u.user_id=:userId ",nativeQuery = true)
    public void deleteUserCourseRelate(@Param("courseId") Integer courseId,@Param("userId") Integer userId );

    @Transactional
    @Modifying
    @Query(value="INSERT INTO user_course_relate (course_id,user_id) values(:courseId,:userId)",nativeQuery = true)
    public void insertUserCourseRelate(@Param("courseId") Integer courseId,@Param("userId") Integer userId );


}
