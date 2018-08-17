package com.zzm.rabbitmq;

import com.zzm.rabbitmq.client.RabbitMQClient;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RabbitmqApplication {

	@Autowired
	private RabbitMQClient rabbitMQClient;

	@Bean
	public Queue tesetQueue(){
		return new Queue("james");
	}

	@PostConstruct
	public void init() {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		int threads = 10;

		ExecutorService executorService = Executors.newFixedThreadPool(threads);

		final CountDownLatch start = new CountDownLatch(1);

		final CountDownLatch end = new CountDownLatch(threads);

		for (int i = 0; i < threads; i++) {
			executorService.execute(() -> {
				try {
					start.await();
					for (int i1 = 0; i1 < 1000; i1++) {
						rabbitMQClient.send("发送消息----james-----");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					end.countDown();
				}
			});
		}
		start.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		stopWatch.stop();
		System.out.println("发送消息耗时：" + stopWatch.getTotalTimeMillis());
	}
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}
}
