package org.houqian.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-06-14
 */
public class RequestBaiduCommand extends HystrixCommand<String> {

  private RestTemplate restTemplate = new RestTemplate();

  protected RequestBaiduCommand(String name) {
    super(
            Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("RequestBaidu"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RequestThreadPool"))
    );


    RestTemplateBuilder templateBuilder = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(2000))
            .setReadTimeout(Duration.ofMillis(5000));

    int timeout = 500;
    RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout)
            .build();
    CloseableHttpClient client = HttpClientBuilder
            .create()
            .setDefaultRequestConfig(config)
            .build();
    new HttpComponentsClientHttpRequestFactory();
  }

  @Override
  protected String run() throws Exception {
    Thread.sleep(TimeUnit.HOURS.toMillis(8));
    return null;
  }

  @Override
  protected String getFallback() {
    return super.getFallback();
  }

  public static void main(String[] args) {
    String max = IntStream.range(0, 999)
            .filter(i -> i % 2 == 0)
            .filter(i -> i % 3 == 0)
            .filter(i -> {
              return String.valueOf(i).contains("2");
            })
            .mapToObj(i -> String.valueOf(i))
            .sorted()
            .max(Comparator.reverseOrder())
            .get();
    System.out.println(max);
    long seconds = TimeUnit.HOURS.toMillis(8);
    System.out.println(seconds);
  }
}
