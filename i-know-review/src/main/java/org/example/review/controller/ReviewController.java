package org.example.review.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@Slf4j
@Validated
public class ReviewController {

   private final ReviewServiceImpl reviewService;
   @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long reviewId) {
       return reviewService.getReviewById(reviewId);
   }

}
