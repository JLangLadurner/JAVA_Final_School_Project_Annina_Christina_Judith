package com.school.project;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AppConfigurationEntry;

@SpringBootApplication
public class ProjectApplication extends Application {
    public void start(Stage primaryStage) throws Exception{

    }

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
