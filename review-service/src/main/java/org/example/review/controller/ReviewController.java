package org.example.review.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.service.ReviewServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
@Slf4j
@Validated
public class ReviewController {

   private final ReviewServiceImpl reviewService;

   @GetMapping("{id}")
    public ReviewDto getReviewById(@PathVariable("id") UUID id) {
      log.info("Getting review by id " + id);
      return reviewService.getReviewById(id);
   }

   @PostMapping
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
      log.info("Adding review " + reviewDto.getTitle());
      return reviewService.addReview(reviewDto);
   }

   @PatchMapping("{id}")
    public ReviewDto updateReview(@RequestBody ReviewDto reviewDto, @PathVariable("id") UUID reviewId) {
        log.info("Updating review " + reviewId);
        return reviewService.updateReview(reviewDto, reviewId);
   }

   @DeleteMapping("{id}")
    public void deleteReview(@PathVariable UUID id) {
       log.info("Deleting review " + id);
       reviewService.deleteReview(id);
   }
}
