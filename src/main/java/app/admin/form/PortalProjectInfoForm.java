package app.admin.form;

import app.config.anotation.Description;
import app.config.anotation.HtmlSelect;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PortalProjectInfoForm {
  private Long id;
  @Description("项目名称")
  @Column
  private String projectName;
  @Description("整体项目背景情况")
  @Column
  private String background;
  @Description("业务模块")
  @Column
  private String businessModule;
  @Description("访问类型")
  @Column
  @HtmlSelect({
      "公网",
      "私有化部署",
      "内网和公网"
  })
  private String accessType;
  @Description("所属业务线")
  @Column
  @HtmlSelect({
      "智慧线",
      "智慧法治研究与应用部",
      "企业法务事业中心",
      "智慧执法线",
      "法宝线",
      "对外项目",
      "法律科技中心",
      "法务",
      "学堂",
      "公司",
      "智慧司法线",
  })
  private String businessLine;
  @Description("项目类型")
  @HtmlSelect({"流程类", "检索系统", "小程序", "MQ消息服务", "转发服务", "标注系统", "辅助量刑审讯系统", "模型服务", "管理系统", "内容类", "检索类", "对外项目", "内部项目", "其它"})
  private String projectType;
  //前后分离
  @Description("前后分离")
  @Column
  @HtmlSelect({"是","否"})
  private String frontendBackendSeparation;
  //架构方式
  @Description("架构方式")
  @Column
  @HtmlSelect({
      "前后分离",
      "单体",
      "微服务",
      "webform",
      "jboss",
      "分布式服务"
  })
  private String frameworkType;
  //项目技术选型
  @Description("项目技术选型")
  @Column
  private String technologies;
  //目前的部署情况
  @Description("目前的部署情况")
  @Column
  private String deploySituation;
  //环境
  @Description("环境")
  @Column
  private String situations;
  //CI/CD
  @Description("CI/CD自动化部署")
  @Column
  @HtmlSelect({"有","无"})
  private String cicdFlag;
  //日志情况
  @Description("日志情况")
  @Column
  @HtmlSelect({"本地日志","ELK","云端日志"})
  private String logSituation;
  //开发环境地址
  @Description("开发环境地址")
  @Column
  private String accessAddressDev;
  //测试环境地址
  @Description("测试环境地址")
  @Column
  private String accessAddressTest;
  //演示环境地址
  @Description("演示环境地址")
  @Column
  private String accessAddressUat;
  //正式环境地址
  @Description("正式环境地址")
  @Column
  private String accessAddressProd;
  //部署服务器和程序位置
  @Description("部署服务器和程序位置")
  @Column
  private String deployDetail;
  //关系数据库(如有集群需写全）
  @Description("关系数据库")
  @Column
  private String relationalDatabase;
  //NoSql(如有集群需写全）
  @Description("NoSql")
  @Column
  private String nosqlDatabase;
  //消息队列(如有集群需写全）
  @Description("消息队列")
  @Column
  private String messageQueue;
  //大数据存储(如有集群需写全）
  @Description("大数据存储")
  @Column
  private String bigdataStore;
  //gitlab地址
  @Description("gitlab地址")
  @Column
  private String codeAddress;
  //git权限人员
  @Description("git权限人员")
  @Column
  private String gitOwners;
  //项目复用情况
  @Description("项目复用情况")
  @Column
  private String projectRepeatUseCase;
  //项目开发人员
  @Description("项目开发人员")
  @Column
  private String projectDevelopers;
  //技术文档confluence地址
  @Description("技术文档confluence地址")
  @Column
  private String confluenceTecAddress;
}
