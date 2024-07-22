package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.TechnologyRequestDto;
import org.example.dto.TechnologyResponseDto;
import org.example.dto.TechnologyUpdateRequestDto;
import org.example.mapper.TechnologyMapper;
import org.example.mapper.TechnologyResponseMapper;
import org.example.model.Course;
import org.example.model.Technology;
import org.example.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class TechnologyService {

    private final TechnologyMapper technologyMapper;
    private final TechnologyRepository technologyRepository;
    private final TechnologyResponseMapper technologyResponseMapper;
    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void createTechnology (TechnologyRequestDto technologyRequestDto, Course course){
        Technology technology = technologyMapper.toTechnology(technologyRequestDto);
        technology.setCourse(course);
        technologyRepository.save(technology);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void updateTechnology (TechnologyUpdateRequestDto technologyUpdateRequestDto, UUID courseId){
        Technology technology = technologyRepository.findTechnologiesByCourseId(courseId);
        technology.setName(technologyUpdateRequestDto.getName());
        technology.setPhoto(technologyUpdateRequestDto.getPhoto());
    }
    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public TechnologyResponseDto getTechnology (Course course){
        return (technologyResponseMapper.toDto(technologyRepository.findTechnologiesByCourseId(course.getId())));
    }
}
