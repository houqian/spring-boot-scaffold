package org.houqian.springbootdemo.kafka.muti_thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import one.util.streamex.StreamEx;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-05-17
 */
@Configuration
public class KafkaConfig {

  public static void main(String[] args) throws IOException {
    Object[]       data         = {1, "1", "3", "1.1", "1.2"};
    List<String[]> t1           = Stream.of(data).map(v -> String.valueOf(v).split("\\.")).collect(Collectors.toList());
    ObjectMapper   objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(t1));

    List<String> t2 = Stream.of(data)
            .map(v -> String.valueOf(v))
            .flatMap(v -> Stream.of(v.split("\\.")))
            .collect(Collectors.toList());
    System.out.println(objectMapper.writeValueAsString(t2));

    //Stream.of(data).reduce((v1, v2) -> Integer.parseInt(String.valueOf(v1)) + Integer.parseInt(String.valueOf(v2))).get();
    Integer t3 = Stream.of(data)
            .map(String::valueOf)
            .flatMap(v -> {
              if (v.contains(".")) return Stream.of(v.split("."));
              return Stream.of(v);
            })
            .map(Integer::parseInt)
            .reduce(Integer::sum)
            .get();
    System.out.println(t3);

    Integer t4 = StreamEx.of(data)
            .map(String::valueOf)
            .flatMap(v -> {
              if (v.contains(".")) return Stream.of(v.split("."));
              return Stream.of(v);
            })
            .map(Integer::parseInt)
            .foldLeft((v1, v2) -> v1 + v2)
            .get();
    System.out.println(t4);

    //new BufferedReader(new FileReader("filepath")).lines();


    String filePath = "/Users/houqian/repo/github/spring-boot-demo/src/main/java";
    //Files.list(Paths.get(filePath)).forEach(System.out::println);
    /*
    Files.walkFileTree(Paths.get(filePath), new FileVisitor<Path>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println(Thread.currentThread().getName() + "-访问文件之前-dir: " + dir + ", attrs: [create time]" + attrs.creationTime());
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(Thread.currentThread().getName() + "-访问文件中-file: " + file.getFileName() + ", attrs:[create time] " + attrs.creationTime());
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println(Thread.currentThread().getName() + "-访问文件失败");
        return FileVisitResult.SKIP_SUBTREE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println(Thread.currentThread().getName() + "-访问文件之后-dir: " + dir);
        return FileVisitResult.CONTINUE;
      }
    });
     */

    Files.walkFileTree(Paths.get(filePath), new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(Thread.currentThread().getName() + "-访问文件中-dir: " + file.getParent().toAbsolutePath() + ",file: " + file.getFileName() + ", attrs:[create time] " + attrs.creationTime());

        return FileVisitResult.CONTINUE;
      }

    });

    new ConcurrentHashMap<String, String>();
  }

}
