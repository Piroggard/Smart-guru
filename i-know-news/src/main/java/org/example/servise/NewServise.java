package org.example.servise;

import dto.NewsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.News;
import org.springframework.stereotype.Service;
import org.example.repository.NewsRepository;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@Service
public class NewServise {
    private final NewsRepository newsRepository;
    public News addNews (NewsDto newsDto){

        News news = News.builder()
                .courseId(newsDto.getCourseId())
                .publicationDate(LocalDateTime.now())
                .image(newsDto.getImage())
                .description(newsDto.getDescription())
                .heading(newsDto.getHeading())
                .direction(newsDto.getDirection())
                .build();
        log.info("Маппинг в model" + news);
        return newsRepository.save(news);
        /*try {
            return newsRepository.save(news);
        } catch (Exception e){
            log.error("Ошибка записи в базу - " + e.getMessage());
        }
        return null;*/
    }
}
