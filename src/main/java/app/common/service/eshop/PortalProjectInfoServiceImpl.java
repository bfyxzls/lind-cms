package app.common.service.eshop;

import app.common.service.eshop.api.PortalProjectInfoService;
import app.persistence.entity.eshop.PortalProjectInfo;
import app.persistence.repository.eshop.PortalProjectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortalProjectInfoServiceImpl implements PortalProjectInfoService {
  @Autowired
  private PortalProjectInfoRepository portalProjectInfoRepository;

  @Override
  public List<PortalProjectInfo> findAll() {
    return portalProjectInfoRepository.findAll();
  }

  @Override
  public Optional<PortalProjectInfo> findById(Long id) {
    return portalProjectInfoRepository.findById(id);
  }
}
