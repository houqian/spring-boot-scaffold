package org.houqian.springbootdemo.commons.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Jackson常用注解
 *
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/27
 */
@Slf4j
public class JacksonAnnotation {

  public class ExtendableBean {
    public String name;
    private Map<String, String> properties;

    {
      this.properties = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
      return properties;
    }

    public void add(String key, String value) {
      this.properties.put(key, value);
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  /**
   * <p>
   * 使用{@code @JsonAnyGetter}注解getXXX方法，来将对应的<br/>
   * map的键值对作为属性-属性值填充到所属对象。
   * </p>
   */
  public void testJsonAnyGetter() throws JsonProcessingException {
    ExtendableBean bean = new ExtendableBean();
    bean.setName("xiao ming");
    bean.add("sex", "male");
    bean.add("age", "18");

    this.toPrettyJSONString(bean);
  }


  // ====================================================================================
  public class MyBean {
    private int id;
    private String name;

    public MyBean(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public void setId(int id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getId() {
      return id;
    }

    @JsonGetter("name")
    public String getTheName() {
      return name;
    }
  }

  /**
   * <p>
   * 使用{@code @JsonGetter}注解解决get方法与属性名不一致的问题。<br/>
   * </p>
   */
  public void testJsonGetter() {
    MyBean x1 = new MyBean(1, "x1");
    this.toPrettyJSONString(x1);
  }

  // ====================================================================================
  @JsonPropertyOrder({"name", "id"})
  public class MyBean2 {
    private int id;
    private String name;

    public MyBean2(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public void setId(int id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }

  public void testJsonPropertyOrder() {
    MyBean2 bean = new MyBean2(1, "zs");
    this.toPrettyJSONString(bean);
  }


  // ====================================================================================
  public class RawBean {
    private String id;

    @JsonRawValue
    private String json;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getJson() {
      return json;
    }

    public void setJson(String json) {
      this.json = json;
    }
  }

  public void testJsonRawValue() {
    RawBean bean = new RawBean();
    bean.setId(UUID.randomUUID().toString());
    bean.setJson("{\"name\" : \"xiao ming\",\"sex\" : \"male\",\"age\" : \"18\"}");
    this.toPrettyJSONString(bean);
  }

  // ====================================================================================
  public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    TypeEnumWithValue(Integer id, String name) {
      this.id = id;
      this.name = name;
    }

    @JsonValue
    public String getName() {
      return name;
    }
  }

  public void testJsonValue() {
    this.toPrettyJSONString(TypeEnumWithValue.TYPE1);
  }

  // ====================================================================================
  @JsonRootName(value = "user")
  public class UserWithRoot {
    private int id;
    private String name;

    public UserWithRoot(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public void testJsonRootName() {
    UserWithRoot user = new UserWithRoot(1, "ls");
    this.toPrettyJSONStringWithRootName(user);
  }



  // ====================================================================================
  private ObjectMapper mapper = new ObjectMapper();

  private <T> String toPrettyJSONString(T t) {
    try {
      String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
      log.info(result);
      return result;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private <T> String toPrettyJSONStringWithRootName(T t) {
    try {
      String result = mapper.enable(SerializationFeature.WRAP_ROOT_VALUE).writerWithDefaultPrettyPrinter().writeValueAsString(t);
      log.info(result);
      return result;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
