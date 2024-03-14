package org.example.course.controller.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.controller.error.ApiError;
import org.example.dto.AddressDto;
import org.example.dto.CourseDto;
import org.example.dto.PhotosDto;
import org.example.dto.ReviewDto;
import org.example.model.Address;
import org.example.model.Course;
import org.example.model.Photos;
import org.example.model.Review;
import org.example.repository.AddressRepository;
import org.example.repository.CourseRepository;
import org.example.repository.PhotosRepository;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CourseServise {
    private final AddressRepository addressRepository;
    private final PhotosRepository photosRepository;
    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;

    public ApiError addCourse(CourseDto courseDto) {
        try {
            Address address = addressRepository.save(Address.builder()
                    .house(courseDto.getAddress().getHouse())
                    .city(courseDto.getAddress().getCity())
                    .street(courseDto.getAddress().getStreet())
                    .build());
            Course course = courseRepository.save(Course.builder()
                    .name(courseDto.getName())
                    .url(courseDto.getUrl())
                    .type(courseDto.getType())
                    .numberSeats(courseDto.getNumberSeats())
                    .price(courseDto.getPrice())
                    .photo(courseDto.getPhoto())
                    .address(address)
                    .build());
            List<Review> reviews = new ArrayList<>();
            List<ReviewDto> reviewDtoList = courseDto.getReviews();
            for (ReviewDto reviewDto : reviewDtoList) {
                Review review = Review.builder()
                        .name(reviewDto.getName())
                        .course(course)
                        .description(reviewDto.getDescription()).build();
                reviews.add(review);
            }
            reviewRepository.saveAll(reviews);

            List<Photos> photos = new ArrayList<>();
            List<PhotosDto> photosDtoList = courseDto.getPhotosCourse();

            for (PhotosDto photosDto : photosDtoList) {
                Photos photo = Photos.builder()
                        .photos(photosDto.getPhotos())
                        .course(course)
                        .build();
                photos.add(photo);
            }
            photosRepository.saveAll(photos);
        } catch (Exception e) {
            log.error("Ошибка записи в базу - " + e.getMessage());
            return ApiError.builder()
                    .errorCode(2001)
                    .errorCause("Ошибка записи в БД")
                    .build();
        }
        return ApiError.builder()
                .errorCode(0)
                .body("Данные сохранены")
                .build();

    }

    public CourseDto get(Long courseId) {
        Course course = courseRepository.getReferenceById(courseId);
        List<Photos> photos = photosRepository.findByCourse_Id(courseId);
        List<Review> reviews = reviewRepository.findByCourse_Id(courseId);
        List<PhotosDto> photosDtoList = new ArrayList<>();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for (Photos photo : photos) {
            photosDtoList.add(PhotosDto.builder()
                    .photos(photo.getPhotos())
                    .build());
        }
        for (Review review : reviews) {
            reviewDtoList.add(ReviewDto.builder()
                    .name(review.getName())
                    .description(review.getDescription())
                    .build());
        }


        return CourseDto.builder()
                .name(course.getName())
                .url(course.getUrl())
                .type(course.getType())
                .address(AddressDto.builder().house(course.getAddress().getHouse())
                        .street(course.getAddress().getStreet())
                        .city(course.getAddress().getCity()).build())
                .numberSeats(course.getNumberSeats())
                .price(course.getPrice())
                .photo(course.getPhoto())
                .photosCourse(photosDtoList)
                .reviews(reviewDtoList)
                .build();


    }
}

