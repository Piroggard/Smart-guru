package org.example.dto;

import lombok.Data;
import org.example.model.Address;
import org.example.model.Review;

import java.util.List;
@Data
public class CourseDto {

    String name; //
    String url;
    String type;
    Address address;
    long numberSeats;
    long price;
    String photo;
    List<String> photosCourse;
    List <Review> reviews;

}
