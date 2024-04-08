package org.example.servise;

import dto.NewsDto;
import dto.NewsDtoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.News;
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

        News news = News.builder()
                .courseId(newsDto.getCourseId())
                .publicationDate(LocalDateTime.now())
                .image(newsDto.getImage())
                .description(newsDto.getDescription())
                .heading(newsDto.getHeading())
                .direction(newsDto.getDirection())
                .build();
        log.info("Маппинг в model" + news);

        try {
            return newsRepository.save(news);
        } catch (Exception e) {
            log.error("Ошибка записи в базу - " + e.getMessage());
        }
        return null;
    }

    public List<News> getAllByCourseId(Long courseId) {
        return newsRepository.findByCourseId(courseId);
        //return mappingNewsToNewsDtoResponse(listNews);

    }

    public List<NewsDtoResponse> mappingNewsToNewsDtoResponse(List<News> newsList) {
        List<NewsDtoResponse> newsDtoResponses = new ArrayList<>();
        for (News news : newsList) {
            newsDtoResponses.add(NewsDtoResponse.builder()
                    .courseId(news.getCourseId())
                    .direction(news.getDirection())
                    .image(news.getImage())
                    .heading(news.getHeading())
                    .description(news.getDescription())
                    .publicationDate(news.getPublicationDate())
                    .build());
        }
        return newsDtoResponses;
    }

    public News patchNews (News news){
        return newsRepository.save(news);
    }

    public List<News> deleteNews (Long newsId, Long courseId){
        newsRepository.deleteById(newsId);
        log.info("Удаляем - " + newsId + "из курса " + courseId);
        return newsRepository.findByCourseId(courseId);

    }
}
