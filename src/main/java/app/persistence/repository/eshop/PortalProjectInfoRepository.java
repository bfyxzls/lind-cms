package app.persistence.repository.eshop;

import app.persistence.entity.eshop.PortalProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalProjectInfoRepository extends JpaRepository<PortalProjectInfo, Long> {
  Optional<PortalProjectInfo> getById(Long id);
}
