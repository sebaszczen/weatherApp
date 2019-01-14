package sebaszczen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sebaszczen.domain.SynopticStation;

@Repository
public interface ImgwApiRepository extends JpaRepository<SynopticStation,Long> {

}
