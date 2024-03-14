package org.example.repository;

import org.example.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository <News, Long> {
}
