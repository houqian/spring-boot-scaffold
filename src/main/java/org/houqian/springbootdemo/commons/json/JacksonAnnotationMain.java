package org.houqian.springbootdemo.commons.json;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/27
 */
public class JacksonAnnotationMain {
  public static void main(String[] args) throws Exception {
    JacksonAnnotation jacksonAnnotation = new JacksonAnnotation();

    // map键值对合并到所属对象属性-属性值
    jacksonAnnotation.testJsonAnyGetter();
/*
    // GET方法重命名
    jacksonAnnotation.testJsonGetter();

    // 属性排序
    jacksonAnnotation.testJsonPropertyOrder();

    // 属性值原样保留JSON串
    jacksonAnnotation.testJsonRawValue();

    // 用于指定enum类型的序列化值
    jacksonAnnotation.testJsonValue();

    // 修改RootName
    jacksonAnnotation.testJsonRootName();*/


  }
}
