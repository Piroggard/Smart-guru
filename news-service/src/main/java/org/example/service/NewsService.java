package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.NewsCreationDto;
import org.example.dto.NewsResponseDto;
import org.example.dto.NewsUpdateDto;
import org.example.enam.DirectionEnum;
import org.example.mapper.NewsMapper;
import org.example.mapper.NewsResponseMapper;
import org.example.model.Course;
import org.example.model.News;
import org.example.repository.CourseRepository;
import org.example.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional (readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsResponseMapper newsResponseMapper;
    private final CourseRepository courseRepository;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createNews(NewsCreationDto newsCreationDto) {
        Course course = courseRepository.findById(newsCreationDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + newsCreationDto.getCourseId()));
        News news = newsMapper.toEntity(newsCreationDto);
        news.setCourse(course);
        news.setDelete(false);
        News savedNews = newsRepository.save(news);
        return savedNews.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateNews(NewsUpdateDto newsUpdateDto) {
        News news = findNewsById(newsUpdateDto.getId());
        updateNewsEntity(news, newsUpdateDto);
        news.setDelete(false);
        news.setDateDelete(null);
        newsRepository.save(news);
        return news.getId();
    }

    private void updateNewsEntity(News news, NewsUpdateDto newsUpdateDto) {
        Course course = courseRepository.findById(newsUpdateDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + newsUpdateDto.getCourseId()));
        news.setCourse(course);
        news.setDirection(newsUpdateDto.getDirection());
        news.setImage(newsUpdateDto.getImage());
        news.setTitle(newsUpdateDto.getTitle());
        news.setDescription(newsUpdateDto.getDescription());
        news.setDatePublication(newsUpdateDto.getDatePublication());
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteNews(UUID newsId) {
        News news = findNewsById(newsId);
        news.setDelete(true);
        news.setDateDelete(LocalDateTime.now());
        newsRepository.save(news);
    }

    public NewsResponseDto getNews(UUID newsId) {
        News news = findNewsById(newsId);
        return newsResponseMapper.toDto(news);
    }

    private News findNewsById(UUID id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found with id: " + id));
    }

    public List<NewsResponseDto> getAllNews() {
        List<News> newsList = newsRepository.findByDeleteFalse();
        return newsList.stream()
                .map(newsResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<NewsResponseDto> getNewsByDirection(DirectionEnum direction) {
        List<News> newsList = newsRepository.findByDirection(direction);
        return newsList.stream()
                .map(newsResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<NewsResponseDto> getNewsByCourse(UUID courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        List<News> newsList = newsRepository.findByCourse(course);
        return newsList.stream()
                .map(newsResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
