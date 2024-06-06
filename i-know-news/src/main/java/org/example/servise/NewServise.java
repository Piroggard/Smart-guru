package org.example.servise;

import dto.NewsDto;
import dto.NewsDtoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mappers.NewsMapper;
import org.example.model.News;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.example.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class NewServise {
    private final NewsRepository newsRepository;

    public News addNews(NewsDto newsDto) {
        News news = NewsMapper.INSTANCE.toNews(newsDto);
        news.setPublicationDate(LocalDateTime.now());
        log.info("Маппинг в model" + news);
        try {
            return newsRepository.save(news);
        } catch (Exception e) {
            log.error("Ошибка записи в базу - " + e.getMessage());
        }
        return null;
    }

    public List<News> getAllByCourseId(Long courseId, int page, int size) {
        return newsRepository.findByCourseId(courseId, PageRequest.of(page, size));

    }

    public News patchNews(News news) {
        return newsRepository.save(news);
    }

    public List<News> deleteNews(Long newsId, Long courseId) {
        newsRepository.deleteById(newsId);
        log.info("Удаляем - " + newsId + "из курса " + courseId);
        return newsRepository.findByCourseId(courseId);
    }
}
