package org.example.servise;
import org.example.mappers.NewsMapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import dto.NewsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mappers.NewsMapper;
import org.example.model.News;
import org.springframework.data.domain.PageRequest;
import org.example.repository.NewsRepository;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class NewServise {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 30, rollbackFor = Exception.class)
    public Long addNews(NewsDto newsDto) {
        News news = newsMapper.toDto(newsDto);
        news.setPublicationDate(LocalDateTime.now());
        log.info("Маппинг в model" + news);
        return newsRepository.save(news).getNewsId();

    }

    public List<News> getAllByCourseId(Long courseId, int page, int size) {
        return newsRepository.findByCourseId(courseId, PageRequest.of(page, size));
    }

    public News patchNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long newsId, Long courseId) {
        newsRepository.deleteById(newsId);
        log.info("Удаляем - " + newsId + "из курса " + courseId);
        newsRepository.findByCourseId(courseId);
    }
}
