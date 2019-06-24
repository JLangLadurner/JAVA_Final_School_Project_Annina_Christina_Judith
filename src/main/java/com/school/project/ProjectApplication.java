package com.school.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AppConfigurationEntry;

@SpringBootApplication
public class ProjectApplication extends Application {
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SchoolApp");

        Label studIDLbl = new Label("Student ID");
        TextField studIDTxtF = new TextField();
        Label schoolClassIDLbl = new Label("School class");
        TextField schoolClassTxtF = new TextField();

        Label studToClassLbl = new Label("Insert student to class");
        studToClassLbl.setLineSpacing(30);
        HBox studHBox = new HBox(studIDLbl, studIDTxtF);
        studHBox.setSpacing(50);
        studHBox.setPadding(new Insets(0, 20, 0, 20));
        HBox schoolClassHBox = new HBox(schoolClassIDLbl, schoolClassTxtF);
        schoolClassHBox.setSpacing(50);
        schoolClassHBox.setPadding(new Insets(0,20,50,20));
        VBox allVBox = new VBox(studToClassLbl, studHBox, schoolClassHBox);
        allVBox.setSpacing(30);
        allVBox.setPadding(new Insets (20,20,20,20));


        Scene generalScene = new Scene (allVBox);
        primaryStage.setScene(generalScene);
        primaryStage.show();
    }

	public static void main(String[] args) {
        Application.launch(args);
        SpringApplication.run(ProjectApplication.class, args);
	}

}
