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
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsResponseMapper newsResponseMapper;
    private final CourseRepository courseRepository;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createNews(NewsCreationDto newsCreationDto) {
        Course course = findCourseById(newsCreationDto.getCourseId());
        News news = newsMapper.toEntity(newsCreationDto);
        news.setCourse(course);
        news.setDelete(false);
        News savedNews = newsRepository.save(news);
        return savedNews.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateNews(UUID id, NewsUpdateDto newsUpdateDto) {
        News news = findNewsById(id);
        updateNewsEntity(news, newsUpdateDto);
        news.setDelete(false);
        news.setDateDelete(null);
        return news.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteNews(UUID id) {
        News news = findNewsById(id);
        news.setDelete(true);
        news.setDateDelete(LocalDateTime.now());
    }

    public NewsResponseDto getNews(UUID id) {
        News news = findNewsById(id);
        return newsResponseMapper.toDto(news);
    }

    public List<NewsResponseDto> getAllNews(DirectionEnum direction, UUID courseId) {
        List<News> newsList;
        if (direction != null && courseId != null) {
            Course course = findCourseById(courseId);
            newsList = newsRepository.findByDirectionAndCourse(direction, course);
        } else if (direction != null) {
            newsList = newsRepository.findByDirection(direction);
        } else if (courseId != null) {
            Course course = findCourseById(courseId);
            newsList = newsRepository.findByCourse(course);
        } else {
            newsList = newsRepository.findByDeleteFalse();
        }
        return newsList.stream()
                .map(newsResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    private void updateNewsEntity(News news, NewsUpdateDto newsUpdateDto) {
        Course course = findCourseById(newsUpdateDto.getCourseId());
        news.setCourse(course);
        news.setDirection(newsUpdateDto.getDirection());
        news.setImage(newsUpdateDto.getImage());
        news.setTitle(newsUpdateDto.getTitle());
        news.setDescription(newsUpdateDto.getDescription());
        news.setDatePublication(newsUpdateDto.getDatePublication());
    }

    private News findNewsById(UUID id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found with id: " + id));
    }

    private Course findCourseById(UUID courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
    }
}
