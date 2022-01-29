package app.front.controller;

import app.admin.form.KeyValueDescription;
import app.admin.form.PortalProjectInfoForm;
import app.common.HTMLTemplateUtils;
import app.common.IpUtils;
import app.common.service.eshop.api.PortalProjectInfoService;
import app.common.service.eshop.api.ProductService;
import app.config.anotation.FrontController;
import app.front.controller.parent.FrontAbstractController;
import app.persistence.entity.eshop.PortalProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Butta
 */

@FrontController
public class ShopFrontController extends FrontAbstractController {

  private final ProductService productService;
  @Autowired
  PortalProjectInfoService portalProjectInfoService;

  @Autowired
  public ShopFrontController(ProductService productService) {
    this.productService = productService;
  }

  @RequestMapping(value = "/ip")
  public ResponseEntity<String> list(HttpServletRequest request) {
    return ResponseEntity.ok(IpUtils.getAllIpAddr(request));
  }
  @RequestMapping(value = "/products")
  public String list(Model model) {
    List<PortalProjectInfo> products = portalProjectInfoService.findAll();
    model.addAttribute("products", products);
    return "front/products";
  }

  @RequestMapping(value = "/detail")
  public String detail(@RequestParam Long id, Model model) {
    PortalProjectInfo portalProjectInfo = portalProjectInfoService.findById(id).orElse(null);
    List<KeyValueDescription> keyValueDescriptions = new ArrayList<>();

    for (String field : HTMLTemplateUtils.getFiledName(portalProjectInfo)) {
      keyValueDescriptions.add(KeyValueDescription.builder()
          .key(field)
          .description(HTMLTemplateUtils.getFieldDescByName(field, PortalProjectInfoForm.class))
          .value(HTMLTemplateUtils.getFieldValueByName(field, portalProjectInfo))
          .build());
    }
    model.addAttribute("detail", keyValueDescriptions);
    return "front/productDetail";
  }
}
