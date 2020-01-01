package org.houqian.jpmml;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dmg.pmml.PMML;
import org.jpmml.model.PMMLUtil;
import org.jpmml.model.visitors.LocatorNullifier;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.*;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-31
 */
public class Load {

  public static void main(String[] args) throws FileNotFoundException, JAXBException, SAXException, JsonProcessingException {
    FileInputStream srcModelIs = new FileInputStream(new File("src/main/resources/pipeline.pmml.xml"));
    PMML            pmml       = PMMLUtil.unmarshal(srcModelIs);

    optimize(pmml);

    FileOutputStream newModelOs = new FileOutputStream(new File("src/main/resources/new-pipeline.pmml.xml"));
    store(pmml, newModelOs);
  }

  public static void optimize(PMML pmml) {
    LocatorNullifier nullifier = new LocatorNullifier();
    nullifier.applyTo(pmml);
  }

  public static void store(PMML pmml, OutputStream os) throws JAXBException {
    org.jpmml.model.PMMLUtil.marshal(pmml, os);
  }
}
