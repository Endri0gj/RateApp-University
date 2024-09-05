package rate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rate.dto.FeedbackDto;
import rate.exception.RateAppException;
import rate.model.Course;
import rate.model.Feedback;
import rate.model.User;
import rate.repository.CourseRepo;
import rate.repository.FeedbackRepo;
import rate.repository.UserRepo;

import java.util.*;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CourseRepo courseRepo;
    public List<FeedbackDto> getFeedbacksByCourseId(Integer courseId){
        List<Object[]> feedbacksData=feedbackRepo.getFeedbacksByCourseId(courseId);
        List<FeedbackDto> feedbacks=new ArrayList<>();
        for(Object[] data : feedbacksData){
            Integer feedbackId=(Integer) data[0];
            String comment=(String) data[1];
            Integer userId=(Integer) data[2];
            Integer rating=(Integer) data[4];
            Date publishedDate=(Date) data[5];

            User user=userRepo.findById(userId).orElse(null);
            if(user==null)
                continue;
            FeedbackDto feedback=new FeedbackDto(userId,user.getName(),user.getSurname(),
                    user.getEmail(),rating,comment,publishedDate);
            feedbacks.add(feedback);
        }
        Collections.sort(feedbacks);
        return feedbacks;
    }

    public ResponseEntity<String> saveFeedback(FeedbackDto feedback,Integer courseId){
        User user=userRepo.findById(feedback.getUserID()).orElse(null);
        if(user==null){
            String message= RateAppException.noSuchElement("userId",feedback.getUserID());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        Course course=courseRepo.findById(courseId).orElse(null);
        if(course==null){
            String message= "There is no course with id: "+courseId;
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }

        Feedback fullFeedback=feedback.dtoToFeedback(courseId);
        feedbackRepo.save(fullFeedback);

        return  new ResponseEntity<>("Feedback successfully saved!",HttpStatus.OK);
    }
}
