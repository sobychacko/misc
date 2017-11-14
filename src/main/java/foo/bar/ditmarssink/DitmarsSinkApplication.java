package foo.bar.ditmarssink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@SpringBootApplication
public class DitmarsSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DitmarsSinkApplication.class, args);
	}

	@EnableBinding(Sink.class)
	public static class MySink {

		@StreamListener(Sink.INPUT)
		public void foo(String foo) {
			throw new RuntimeException("have fun");
		}

		@ServiceActivator(inputChannel="errorChannel")
		public void error(Throwable value) {
			System.out.println("Message: " + value);
		}
	}
}
