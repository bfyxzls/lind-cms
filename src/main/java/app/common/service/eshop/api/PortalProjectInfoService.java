package app.common.service.eshop.api;

import app.persistence.entity.eshop.PortalProjectInfo;
import app.persistence.entity.eshop.Product;

import java.util.List;
import java.util.Optional;

public interface PortalProjectInfoService {
  List<PortalProjectInfo> findAll();
  Optional<PortalProjectInfo> findById(Long id);
}
