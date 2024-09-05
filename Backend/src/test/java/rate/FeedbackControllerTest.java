package rate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rate.controller.FeedbackController;
import rate.dto.FeedbackDto;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FeedbackControllerTest {
    @Autowired
    private FeedbackController feedbackController;

    @Test
    public void feedbacksForCourseWithId1AreSorted(){
        List<FeedbackDto> feedbacks=feedbackController.showFeedbacksByCourseId(1);
        List<FeedbackDto> sortedFeedbacks=feedbacks;
        Collections.sort(sortedFeedbacks);
        Assertions.assertTrue(sortedFeedbacks.equals(feedbacks));
    }
    @Test
    public void noFeedbacksForCourseWithId10000(){
        FeedbackDto feedbackDto=new FeedbackDto(1,"test","test","test@test.com",4,"Super",new Date());
        ResponseEntity response=feedbackController.saveFeedback(feedbackDto,10000);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test void noFeedbacksForUserWithId10000(){
        FeedbackDto feedbackDto=new FeedbackDto(10000,"test","test","test@test.com",4,"Super",new Date());
        ResponseEntity response=feedbackController.saveFeedback(feedbackDto,1);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    @Test void noFailureForInvalidUserId(){
        FeedbackDto feedbackDto=new FeedbackDto(-20,"test","test","test@test.com",4,"Super",new Date());
        ResponseEntity response=feedbackController.saveFeedback(feedbackDto,1);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
}
