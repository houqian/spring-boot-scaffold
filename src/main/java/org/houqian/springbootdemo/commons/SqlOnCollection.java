package org.houqian.springbootdemo.commons;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.query.parser.sql.SQLParser;
import com.googlecode.cqengine.resultset.ResultSet;
import org.houqian.springbootdemo.commons.sqlwithcollection.Car;
import org.houqian.springbootdemo.commons.sqlwithcollection.CarFactory;

import static com.googlecode.cqengine.codegen.AttributeBytecodeGenerator.createAttributes;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/20
 */
public class SqlOnCollection {

  public static void main(String[] args) {
    SQLParser<Car> parser = SQLParser.forPojoWithAttributes(Car.class, createAttributes(Car.class));
    IndexedCollection<Car> cars = new ConcurrentIndexedCollection<Car>();
    cars.addAll(CarFactory.createCollectionOfCars(10));

    ResultSet<Car> results = parser.retrieve(cars,
            "SELECT * FROM cars WHERE (" +
                    "(manufacturer = 'Ford' OR manufacturer = 'Honda') " +
                    "AND price <= 5000.0 " +
                    "AND color NOT IN ('GREEN', 'WHITE')) " +
                    "ORDER BY manufacturer DESC, price ASC ");

    for (Car car : results) {
      // Prints: Honda Accord, Ford Fusion, Ford Focus
      System.out.println(car);
    }
  }

}
