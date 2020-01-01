package org.houqian.jpmml;

import org.jpmml.evaluator.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-31
 */
public class Execute {
  public static void main(String[] args) throws JAXBException, SAXException, IOException {
    // 从pmml文件实例化模型执行引擎
    Evaluator evaluator = new LoadingModelEvaluatorBuilder()
            .setLocatable(false)
            .setVisitors(new DefaultVisitorBattery())
            //.setOutputFilter(OutputFilters.KEEP_FINAL_RESULTS)
            .load(new File("src/main/resources/pipeline.pmml.xml"))
            .build();

    // 校验模型合法性
    evaluator.verify();

    // 输入变量
    List<? extends InputField> inputFields = evaluator.getInputFields();
    System.out.println("Input fields: " + inputFields);

    // 标签
    List<? extends TargetField> targetFields = evaluator.getTargetFields();
    System.out.println("Target field(s): " + targetFields);

    // 输出变量
    List<? extends OutputField> outputFields = evaluator.getOutputFields();
    System.out.println("Output fields: " + outputFields);


    // 释放引用，优化GC
    evaluator = null;
  }
}
