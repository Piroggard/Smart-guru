package smartguru.organizerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.model.Organizer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizerMapper {

    Organizer toEntity(OrganizerDto dto);

    OrganizerDto toDto(Organizer entity);
}
