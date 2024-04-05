package org.example.review.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@Slf4j
@Validated
public class ReviewController {

   private final ReviewServiceImpl reviewService;
   @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long reviewId) {
      log.info("Getting review by id " + reviewId);
      return reviewService.getReviewById(reviewId);
   }
    @GetMapping("/{id}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable Long userId) {
        log.info("Getting all reviews from user " + userId);
        return reviewService.getAllReviewsByUserId(userId);
    }


   @PostMapping
    public ReviewDto addReview(@RequestBody @Valid ReviewDto reviewDto) {
      log.info("Adding review " + reviewDto.getTitle());
      return reviewService.addReview(reviewDto);
   }

   @PatchMapping("/{id}")
    public ReviewDto updateReview(@RequestBody @Valid ReviewDto reviewDto, @PathVariable Long reviewId) {
        log.info("Updating review " + reviewId);
        return reviewService.updateReview(reviewDto, reviewId);
   }

   @DeleteMapping("/reviewId")
    public void deleteReview(@PathVariable Long reviewId) {
       log.info("Deleting review " + reviewId);
       reviewService.deleteReview(reviewId);
   }



}
