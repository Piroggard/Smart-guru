package org.example.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignUserInterceptor implements RequestInterceptor {

    private final UserContext userContext;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (userContext.getUserId() != null) {
            requestTemplate.header("x-user-id", String.valueOf(userContext.getUserId()));
        }
    }
}
