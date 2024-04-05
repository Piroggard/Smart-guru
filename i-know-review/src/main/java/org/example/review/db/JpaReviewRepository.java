package org.example.review.db;
;
import org.example.review.model.Review;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaReviewRepository extends PagingAndSortingRepository<Review, Integer> {

    Review getReviewById(Long reviewId);
}
