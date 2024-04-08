package org.example.controller;

import dto.NewsDto;
import dto.NewsDtoResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.News;
import org.example.servise.NewServise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("/news")
@Slf4j
@AllArgsConstructor
@RestController
public class NewsController {

    NewServise newServise;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News addNews(@Valid @RequestBody NewsDto newsDto) {
        log.info("Меттод get " + newsDto);
        return newServise.addNews(newsDto);
    }

    @GetMapping("{courseId}")
    public List<News> getAllByCourseId(@PathVariable Long courseId) {
        log.info("Меттод getAllByCourseId " + courseId);
        return newServise.getAllByCourseId(courseId);
    }

    @PatchMapping
    public News patchNews(@RequestBody News news) {
        log.info("Меттод patch " + news);
        return newServise.patchNews(news);
    }

    @DeleteMapping("{newsId}/{courseId}")
    public List<News> deleteNews(@PathVariable Long newsId, @PathVariable Long courseId) {
        return newServise.deleteNews(newsId, courseId);
    }


    }
