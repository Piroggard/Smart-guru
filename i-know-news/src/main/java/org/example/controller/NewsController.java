package org.example.controller;

import dto.NewsDto;

import dto.NewsDtoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.News;
import org.example.servise.NewServise;
import org.springframework.http.HttpStatus;
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
    public Long createNews (@Valid @RequestBody NewsDto newsDto) {
        log.info("Меттод get " + newsDto);
        return newServise.createNews (newsDto);
    }

    @GetMapping("/{courseId}")
    public List<NewsDtoResponse> getAllByCourseId(@PathVariable Long courseId,
                                                  @RequestParam(required = false, defaultValue = "0") int page,
                                                  @RequestParam(required = false, defaultValue = "0") int size) {
        log.info("Метод getAllByCourseId " + courseId);
        return newServise.getAllByCourseId(courseId, page, size);
    }

    @PatchMapping
    public NewsDtoResponse patchNews(@RequestBody News news) {
        log.info("Меттод patch " + news);
        return newServise.patchNews(news);
    }

    @DeleteMapping("/{newsId}/{courseId}")
    public void deleteNews(@PathVariable Long newsId, @PathVariable Long courseId) {
        log.info("Удаление новости " + newsId);
        newServise.deleteNews(newsId, courseId);
    }
}
