package org.example.servise;
import dto.NewsDtoResponse;
import org.example.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dto.NewsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.NewsMapper;
import org.example.model.News;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class NewServise {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public Long createNews (NewsDto newsDto) {
        News news = newsMapper.toEntity(newsDto);
        news.setPublicationDate(LocalDateTime.now());
        log.info("Маппинг в model" + news);
        return newsRepository.save(news).getNewsId();
    }

    public List<NewsDtoResponse> getAllByCourseId(Long courseId, int page, int size) {
        List<News> newsList = newsRepository.findByCourseId(courseId, PageRequest.of(page, size));
        List<NewsDtoResponse> newsDtoResponses = new ArrayList<>();
        for (News news : newsList) {
            NewsDtoResponse newsDtoResponse = newsMapper.toDTO(news);
            newsDtoResponses.add(newsDtoResponse);
        }
        return newsDtoResponses;
    }

    public NewsDtoResponse patchNews(News news) {
        return newsMapper.toDTO(newsRepository.save(news));

    }

    public void deleteNews(Long newsId, Long courseId) {
        newsRepository.deleteById(newsId);
        log.info("Удаляем - " + newsId + "из курса " + courseId);
    }
}
