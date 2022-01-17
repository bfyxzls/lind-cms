package app.admin.controller;


import app.admin.controller.parent.AdminAbstractController;
import app.admin.form.KeyValueDescription;
import app.admin.form.PortalProjectInfoForm;
import app.common.HTMLTemplateUtils;
import app.common.service.eshop.api.PortalProjectInfoService;
import app.config.anotation.AdminController;
import app.persistence.entity.eshop.PortalProjectInfo;
import app.persistence.repository.eshop.PortalProjectInfoRepository;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 项目管理
 */

@AdminController
@RequestMapping("/admin/product")
public class ProductController extends AdminAbstractController {
  @Autowired
  PortalProjectInfoRepository portalProjectInfoRepository;
  @Autowired
  private PortalProjectInfoService portalProjectInfoService;

  @RequestMapping("/all")
  public String all(Model model) {
    model.addAttribute("products", portalProjectInfoService.findAll());

    return "admin/product/all";
  }


  @RequestMapping("/addProduct")
  public String addProduct(@RequestParam(required = false, defaultValue = "0") Long productId, Model model) {
    PortalProjectInfoForm portalProjectInfoForm = new PortalProjectInfoForm();
    if (productId > 0) {
      Optional<PortalProjectInfo> productOpt = portalProjectInfoService.findById(productId);
      productOpt.ifPresent(o -> {
        BeanUtil.copyProperties(o, portalProjectInfoForm, "id");
      });
    }
    List<KeyValueDescription> keyValueDescriptions = new ArrayList<>();
    for (String field : HTMLTemplateUtils.getFiledName(portalProjectInfoForm)) {
      if (field.equals("id"))
        continue;
      keyValueDescriptions.add(KeyValueDescription.builder()
          .key(field)
          .description(HTMLTemplateUtils.getFieldDescByName(field, PortalProjectInfoForm.class))
          .value(HTMLTemplateUtils.getFieldValueByName(field, portalProjectInfoForm))
          .build());
    }
    model.addAttribute("detail", keyValueDescriptions);
    model.addAttribute("id", productId);
    return "admin/addProduct";
  }


  @PostMapping("/addProduct")
  public String productForm(@RequestParam(required = false) Long productId, @Valid PortalProjectInfoForm portalProjectInfoForm, BindingResult bindingResult) {
    PortalProjectInfo productOpt = new PortalProjectInfo();
    if (productId > 0) {
      productOpt = portalProjectInfoService.findById(productId).orElse(null);
    }
    if (!bindingResult.hasErrors()) {
      BeanUtil.copyProperties(portalProjectInfoForm, productOpt, "id");
      portalProjectInfoRepository.save(productOpt);

    }

    return redirect("/admin/product/all");
  }

  @RequestMapping("/delProduct")
  public String delProduct(@RequestParam Long productId) {
    portalProjectInfoRepository.deleteById(productId);
    return redirect("/admin/product/all");
  }
}
