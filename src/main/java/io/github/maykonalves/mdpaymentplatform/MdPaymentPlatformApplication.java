package io.github.maykonalves.mdpaymentplatform;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger"))
public class MdPaymentPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdPaymentPlatformApplication.class, args);
	}

}
