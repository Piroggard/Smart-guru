package repository;

import model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStatus extends JpaRepository<Status, Enum> {
}
