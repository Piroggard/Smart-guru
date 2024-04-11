package org.example.review.db;
;
import org.example.review.model.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface JpaReviewRepository extends PagingAndSortingRepository<Review, Integer> {
    Set<Review> getAllReviewsByUserId(Long userId);
    Set<Review> getAllReviewsByCourseId(Long courseId);
    Set<Review> getAllReviewsByCourseType(String courseType);

}
