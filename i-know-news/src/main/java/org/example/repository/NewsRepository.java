package org.example.repository;

import org.example.model.News;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository <News, Long> {
    List<News> findByCourseId (Long id, PageRequest courseId);
    List<News> findByCourseId (Long id);

}
