package org.example.course.controller.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    long errorCode;
    String errorCause;
    Object body;

    public ApiError(long errorCode, String errorCause) {
        this.errorCode = errorCode;
        this.errorCause = errorCause;
    }

    public ApiError(long errorCode, Object body) {
        this.errorCode = errorCode;
        this.body = body;
    }

}
