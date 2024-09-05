package rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rate.model.Feedback;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback,Integer> {
    @Query(value = "SELECT * FROM comments c WHERE c.course_id = :courseId ", nativeQuery = true)
    List<Object[]> getFeedbacksByCourseId(@Param("courseId") Integer courseId);

}
