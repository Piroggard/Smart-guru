package org.example.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserContextFilter implements Filter {

    private final UserContext userContext;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String userId = req.getHeader("x-user-id");
        if (userId != null) {
            userContext.setUserId(UUID.fromString(userId));
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            userContext.clear();
        }
    }
}
