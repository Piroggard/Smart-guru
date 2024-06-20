package org.example.review.db;

import org.example.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long> {

    Review getReviewById(Long reviewId);

    void deleteReviewById(Long reviewId);

    List<Review> getAllReviewsByUserId(Long userId);
}
