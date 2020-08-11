package org.houqian.springbootdemo.log4j2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * 测试Log4j2的inheritable特性，可以直接通过设置将MDC内的数据传递到线程池中的线程中去
 *
 * @author houqian
 * @date 2020/8/11
 * @since 1.0
 */
@Slf4j
public class TestInheritableLog4j2 {

	public static void main(String[] args) {
		MDC.put("traceId", UUID.randomUUID().toString());
		Msg msg = new Msg("测试Log4j2的线程私有变量传递功能", "我的content");
		log.info("我是主线程，开始测试:{}", msg);
		new Daemon(msg).start();
	}


	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class Msg {
		private String title;
		private String content;
	}


	@Slf4j
	static class Daemon extends Thread {

		private Msg msg;

		public Daemon(Msg msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			log.info("daemon log:{}", msg);
		}
	}

}
