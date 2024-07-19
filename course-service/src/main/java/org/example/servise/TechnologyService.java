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

@Slf4j
@AllArgsConstructor
@Service
public class TechnologyService {

    private final TechnologyMapper technologyMapper;
    private final TechnologyRepository technologyRepository;
    private final TechnologyResponseMapper technologyResponseMapper;

    public void createTechnology (TechnologyRequestDto technologyRequestDto, Course course){
        Technology technology = technologyMapper.toTechnology(technologyRequestDto);
        technology.setCourse(course);
        technologyRepository.save(technology);
    }

    public void updateTechnology (TechnologyUpdateRequestDto technologyUpdateRequestDto, Course course){
        Technology technology = Technology.builder()
                .id(technologyUpdateRequestDto.getId())
                .course(course)
                .name(technologyUpdateRequestDto.getName())
                .photo(technologyUpdateRequestDto.getPhoto())
                .build();
        technologyRepository.save(technology);
    }

    public TechnologyResponseDto getTechnology (Course course){
        return (technologyResponseMapper.toDto(technologyRepository.findTechnologiesByCourse(course)));
    }
}
