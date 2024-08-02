package org.example.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.db.ReviewRepository;
import org.example.review.dto.ReviewDto;
import org.example.review.dto.ReviewMapper;
import org.example.review.exception.BadRequestException;
import org.example.review.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewServiceInterface {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewDto getReviewById(UUID reviewId) {
        log.info("Получаем отзыв по ID: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow();
        return reviewMapper.toDto(review);
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        log.info("Добавляем отзыв: {}", reviewDto);
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Transactional
    public ReviewDto updateReview(ReviewDto reviewDto, UUID reviewId) {
        log.info("Обновляем отзыв с ID: {}", reviewId);
        if(reviewRepository.findById(reviewId).isEmpty()) {
            log.error("Отзыва с ID {} не существует", reviewId);
            throw new BadRequestException(HttpStatus.NOT_FOUND, "Такого отзыва не существует" + reviewId);
        }
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Transactional
    public void deleteReview(UUID reviewId) {
        log.info("Удаляем отзыв с ID: {}", reviewId);
        if(reviewRepository.findById(reviewId).isEmpty()) {
            log.error("Отзыва с ID {} не существует", reviewId);
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        } else {
            reviewRepository.findById(reviewId);
        }
    }
}