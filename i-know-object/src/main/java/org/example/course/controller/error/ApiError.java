package org.example.course.controller.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    long errorCode;
    String errorCause;
}
