package org.example.controller;

import dto.NewsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.News;
import org.example.servise.NewServise;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/news")
@Slf4j
@AllArgsConstructor
@RestController
public class NewsController {

    NewServise newServise;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News addNews (@RequestBody NewsDto newsDto){

        return newServise.addNews(newsDto);
    }



}
