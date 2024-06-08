package servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Status;
import model.StatusEnum;
import org.springframework.stereotype.Service;
import repository.RepositoryStatus;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CurseServise {
    private final RepositoryStatus repositoryStatus;
    public UUID addStatus (Status statusEnum){
       repositoryStatus.save(statusEnum);
        return null;
    }
}
