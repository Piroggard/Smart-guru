package org.example.review.dto;

import org.example.course.db.CourseStorage;
import org.example.course.model.Course;
import org.example.review.model.Review;
import org.example.user.db.UserStorage;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewMapper {
    @Autowired
    private static CourseStorage courseStorage;
    @Autowired
    private static UserStorage userStorage;
    public static ReviewDto toDto(Review review) {
        if (review == null) {
            return null;
        }
        return ReviewDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .postDate(review.getPostDate())
                .courseId(review.getCourse() != null ? review.getCourse().getId() : null)
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .build();
    }

    public static Review toEntity(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }
        Course course = courseStorage.getCourseById(reviewDto.getCourseId());
        User user = userStorage.getUserById(reviewDto.getUserId());


        return Review.builder()
                .id(reviewDto.getId())
                .title(reviewDto.getTitle())
                .description(reviewDto.getDescription())
                .postDate(reviewDto.getPostDate())
                .course(course)
                .user(user)
                .build();
    }
}
