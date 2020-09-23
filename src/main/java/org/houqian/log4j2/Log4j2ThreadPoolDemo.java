package org.houqian.log4j2;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author houqian
 * @date 2020/9/10
 * @since 1.0
 */
//@Slf4j
public class Log4j2ThreadPoolDemo {

	private static Logger log = LogManager.getLogger(Log4j2Demo.class);


	public static void main(String[] args) {
		String traceId = IdUtil.fastUUID();
		MDC.put("traceId", traceId);
		log.info("gaga");


		final ExecutorService executorService = Executors.newFixedThreadPool(100);

		for (int i = 0; i < 10; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					log.info("hah");
				}
			});
		}
	}

}
