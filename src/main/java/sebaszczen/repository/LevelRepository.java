package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sebaszczen.model.Level;

public interface LevelRepository extends JpaRepository<Level,Long> {
}
