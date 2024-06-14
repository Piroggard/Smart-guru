package org.example.review.db;

import org.example.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long> {

    Review getReviewById(Long reviewId);

    void deleteReviewById(Long reviewId);

    List<Review> getAllReviewsByUserId(Long userId);
}