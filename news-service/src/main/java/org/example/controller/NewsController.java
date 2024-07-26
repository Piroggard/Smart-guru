package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.NewsCreationDto;
import org.example.dto.NewsResponseDto;
import org.example.dto.NewsUpdateDto;
import org.example.enam.DirectionEnum;
import org.example.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public UUID createNews(@Valid @RequestBody NewsCreationDto newsCreationDto) {
        log.info("Received request to create news with title: '{}'", newsCreationDto.getTitle());
        return newsService.createNews(newsCreationDto);
    }

    @PutMapping("/{id}")
    public UUID updateNews(@PathVariable UUID id, @Valid @RequestBody NewsUpdateDto newsUpdateDto) {
        log.info("Received request to update news with ID: '{}'", id);
        return newsService.updateNews(id, newsUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable UUID id) {
        log.info("Received request to delete news with ID: '{}'", id);
        newsService.deleteNews(id);
    }

    @GetMapping("/{id}")
    public NewsResponseDto getNews(@PathVariable UUID id) {
        log.info("Received request to get details for news with ID: '{}'", id);
        return newsService.getNews(id);
    }

    @GetMapping
    public List<NewsResponseDto> getAllNews(@RequestParam(required = false) DirectionEnum direction,
                                            @RequestParam(required = false) UUID courseId) {
        log.info("Received request to get news with direction: '{}' and courseId: '{}'", direction, courseId);
        return newsService.getAllNews(direction, courseId);
    }
}
