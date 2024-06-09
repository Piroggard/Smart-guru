package servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Status;
import org.springframework.stereotype.Service;
import repository.RepositoryStatus;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {
    private final RepositoryStatus repositoryStatus;
    public List<Status> getStatus (){
       return repositoryStatus.findAll();
    }
}
