package com.atenea.talentmixer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TalentmixerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentmixerApplication.class, args);
	}

}
