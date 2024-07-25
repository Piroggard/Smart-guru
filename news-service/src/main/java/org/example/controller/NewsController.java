package org.example.controller;

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
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public UUID createNews(@RequestBody NewsCreationDto newsCreationDto) {
        log.info("Received request to create news with title: '{}'", newsCreationDto.getTitle());
        return newsService.createNews(newsCreationDto);
    }

    @PatchMapping
    public UUID updateNews(@RequestBody NewsUpdateDto newsUpdateDto) {
        log.info("Received request to update news with ID: '{}'", newsUpdateDto.getId());
        return newsService.updateNews(newsUpdateDto);
    }

    @DeleteMapping("/{newsId}")
    public void deleteNews(@PathVariable UUID newsId) {
        log.info("Received request to delete news with ID: '{}'", newsId);
        newsService.deleteNews(newsId);
    }

    @GetMapping("/{newsId}")
    public NewsResponseDto getNews(@PathVariable UUID newsId) {
        log.info("Received request to get details for news with ID: '{}'", newsId);
        return newsService.getNews(newsId);
    }

    @GetMapping
    public List<NewsResponseDto> getAllNews() {
        log.info("Received request to get all news");
        return newsService.getAllNews();
    }

    @GetMapping("/direction/{direction}")
    public List<NewsResponseDto> getNewsByDirection(@PathVariable DirectionEnum direction) {
        log.info("Received request to get news by direction: '{}'", direction);
        return newsService.getNewsByDirection(direction);
    }

    @GetMapping("/course/{courseId}")
    public List<NewsResponseDto> getNewsByCourse(@PathVariable UUID courseId) {
        log.info("Received request to get news by course with ID: '{}'", courseId);
        return newsService.getNewsByCourse(courseId);
    }
}
