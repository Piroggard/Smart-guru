package org.example.course.controller.servise;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.controller.error.ApiError;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CourseServise {

    private final AddressRepository addressRepository;
    private final PhotosRepository photosRepository;
    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;

    public Course addCourse (CourseDto courseDto){


        List <Photos> idPhotos = new ArrayList<>();
        List <Review> idReview = new ArrayList<>();

        try {
           Address addressq = addressRepository.save(Address.builder()
                    .house(courseDto.getAddress().getHouse())
                    .city(courseDto.getAddress().getCity())
                    .street(courseDto.getAddress().getStreet())
                    .build());
           idAddress = address.getId();
        } catch (Exception e){
            System.out.printf("Ошибка Адрес " + e.getMessage());
        }
        List<PhotosDto>photosDtoList = courseDto.getPhotosCourse();
        for (PhotosDto photosDto : photosDtoList) {
            Photos photos = Photos.builder()
                    .photos(photosDto.getPhotos()).build();
            idPhotos.add(photos);
        }


       /* List<ReviewDto> reviewDtoList = courseDto.getReviews();
        for (ReviewDto reviewDto : reviewDtoList) {
            Review review = Review.builder()
                    .name(reviewDto.getName())
                    .description(reviewDto.getDescription()).build();
            idReview.add(review);
        }*/


        /*try {
            List<PhotosDto>photosDtoList = courseDto.getPhotosCourse();
            for (PhotosDto photosDto : photosDtoList) {
               Photos photos = photosRepository.save(Photos.builder()
                        .photos(photosDto.getPhotos()).build());
               idPhotos.add(photos);
            }
        } catch (Exception e){
            System.out.printf("Ошибка Фото " + e.getMessage());
        }*/

        try {
            List<ReviewDto> reviewDtoList = courseDto.getReviews();
            for (ReviewDto reviewDto : reviewDtoList) {
                Review review = reviewRepository.save(Review.builder()
                        .name(reviewDto.getName())
                        .description(reviewDto.getDescription()).build());
                idReview.add(review);
            }
        } catch (Exception e){
            System.out.printf("Ошибка Ревью " + e.getMessage());
        }
        try {










            courseRepository.save(Course.builder()
                    .name(courseDto.getName())
                    .url(courseDto.getUrl())
                    .type(courseDto.getType())
                    .numberSeats(courseDto.getNumberSeats())
                    .price(courseDto.getPrice())
                    .photo(courseDto.getPhoto())
                            .address(addressRepository.save(Address.builder()
                                    .house(courseDto.getAddress().getHouse())
                                    .city(courseDto.getAddress().getCity())
                                    .street(courseDto.getAddress().getStreet())
                                    .build()))





                    .build());
        } catch (Exception e){
            System.out.printf("Ошибка курса - " + e.getMessage());
        }



        return null;

    }
}
