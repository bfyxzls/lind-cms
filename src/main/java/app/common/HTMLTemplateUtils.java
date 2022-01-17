package app.common;

import app.config.anotation.Description;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * HTML模板渲染工具类
 */
public class HTMLTemplateUtils {

  private final static TemplateEngine templateEngine = new TemplateEngine();

  /**
   * 使用 Thymeleaf 渲染 HTML
   *
   * @param template HTML模板
   * @param params   参数
   * @return 渲染后的HTML
   */
  public static String render(String template, Map<String, Object> params) {
    Context context = new Context();
    context.setVariables(params);
    return templateEngine.process(template, context);
  }

  /**
   * 获取属性名数组
   */
  public static List<String> getFiledName(Object o) {
    return Arrays.stream(o.getClass().getDeclaredFields()).map(i -> i.getName()).collect(Collectors.toList());
  }

  public static List<String> getFiledNameByType(Class<?> type) {
    return Arrays.stream(type.getDeclaredFields()).map(i -> i.getName()).collect(Collectors.toList());
  }

  /**
   * 获取类型所有字段的描述
   *
   * @param type
   * @return
   */
  public static List<String> getFiledDescByType(Class<?> type) {
    return Arrays.stream(type.getDeclaredFields())
        .map(o -> o.isAnnotationPresent(Description.class)
            ? o.getAnnotation(Description.class).value()
            : o.getName())
        .collect(Collectors.toList());
  }

  /**
   * 获取某个类型某个字段的描述
   *
   * @param fieldName
   * @return
   */
  public static String getFieldDescByName(String fieldName, Class<?> type) {
    Field field = Arrays.stream(type.getDeclaredFields()).filter(i -> i.getName().equals(fieldName)).findFirst().orElse(null);
    if (field != null) {
      return field.isAnnotationPresent(Description.class)
          ? field.getAnnotation(Description.class).value()
          : field.getName();
    }
    return null;
  }

  /**
   * 根据属性名获取属性值
   */
  public static Object getFieldValueByName(String fieldName, Object o) {
    try {
      String firstLetter = fieldName.substring(0, 1).toUpperCase();
      String getter = "get" + firstLetter + fieldName.substring(1);
      Method method = o.getClass().getMethod(getter, new Class[]{});
      Object value = method.invoke(o, new Object[]{});
      return value;
    } catch (Exception e) {
      return null;
    }
  }


}