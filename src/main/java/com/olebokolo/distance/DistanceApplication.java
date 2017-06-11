package com.olebokolo.distance;

import com.olebokolo.distance.reading.CommandLineController;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Log4j
@SpringBootApplication
public class DistanceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DistanceApplication.class, args)
				.getBean(CommandLineController.class)
				.resolveParams(args);
	}
}
