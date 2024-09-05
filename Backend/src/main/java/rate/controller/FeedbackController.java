package rate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rate.dto.FeedbackDto;
import rate.service.CourseService;
import rate.service.FeedbackService;

import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CourseService courseService;
    @CrossOrigin("*")
    @GetMapping("/feedbacks/{courseId}")
    public List<FeedbackDto> showFeedbacksByCourseId(@PathVariable Integer courseId){
        return feedbackService.getFeedbacksByCourseId(courseId);
    }
    @CrossOrigin("*")
    @PostMapping("/feedback/{courseId}")
    public ResponseEntity<?> saveFeedback(@RequestBody FeedbackDto feedbackDto,@PathVariable Integer courseId){
        courseService.updateAverageRating(feedbackDto.getRating(),courseId);
        return feedbackService.saveFeedback(feedbackDto,courseId);
    }
}
