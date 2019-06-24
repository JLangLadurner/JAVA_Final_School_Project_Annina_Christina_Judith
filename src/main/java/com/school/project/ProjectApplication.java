package com.school.project;

import com.school.project.FXclasses.SchoolDataAccess;
import com.school.project.FXclasses.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.List;
import java.util.Observable;

@SpringBootApplication
public class ProjectApplication extends Application {
    private SchoolDataAccess dbAccess;
    private ListView<Student> studentListView;
    private ObservableList<Student> dataStudent;
    private TextField studIDTxtF = new TextField();
    private TextField studFirstNameTxtF = new TextField();
    private TextField studLastNameTxtF = new TextField();
    private TextField studOldClassTxtF = new TextField();
    private TextField schoolClassTxtF = new TextField();


    @Override
    public void init() {
        try {
            dbAccess = new SchoolDataAccess();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void stop() {
        try {
            dbAccess.closeDb();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    private ObservableList<Student> getDbDataStudents() {

        List<Student> list = null;
        try {
            list = dbAccess.getAllRowsStudents();
        }
        catch (Exception e) {

            displayException(e);
        }
        ObservableList<Student> dbDataStudent = FXCollections.observableList(list);
        return dbDataStudent;
    }
    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
    private class ListSelectChangeListenerStudents implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= dataStudent.size())) {
                return; // invalid data
            }
            // set fields for the selected student (ID, name, surname, oldClass
            Student student = dataStudent.get(new_val.intValue());
            studIDTxtF.setText(Integer.toString(student.getStudentID()));
            studFirstNameTxtF.setText(student.getStudFirstName());
            studLastNameTxtF.setText(student.getStudLastName());
            studOldClassTxtF.setText(student.getStudOldClass());
        }
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SchoolApp");

        Label studToClassLbl = new Label("Insert student to class");
        studToClassLbl.setLineSpacing(30);

        studentListView = new ListView();
        studentListView.setPrefHeight(175);
        studentListView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerStudents());
        dataStudent = getDbDataStudents();
        studentListView.setItems(dataStudent);

        Label studIDLbl = new Label("Student ID");
        Label studFirstNameLbl = new Label("First Name");
        Label studLastNameLbl = new Label("Last Name");
        Label studOldClassLbl = new Label("Last class passed");
        Label schoolClassIDLbl = new Label("Class this year");

        studIDTxtF.setEditable(false);
        HBox studIDHBox = new HBox(studIDLbl, studIDTxtF);
        studIDHBox.setSpacing(50);
        studIDHBox.setPadding(new Insets(0, 20, 0, 20));

        studFirstNameTxtF.setEditable(false);
        HBox studFirstNameHBox = new HBox(studFirstNameLbl, studFirstNameTxtF);
        studFirstNameHBox.setSpacing(50);
        studFirstNameHBox.setPadding(new Insets(0, 20, 0, 20));

        studLastNameTxtF.setEditable(false);
        HBox studLastNameHBox = new HBox(studLastNameLbl, studLastNameTxtF);
        studLastNameHBox.setSpacing(50);
        studLastNameHBox.setPadding(new Insets(0, 20, 0, 20));

        studOldClassTxtF.setEditable(false);
        HBox studOldClassHBox = new HBox(studOldClassLbl, studOldClassTxtF);
        studOldClassHBox.setSpacing(50);
        studOldClassHBox.setPadding(new Insets(0, 20, 0, 20));

        HBox schoolClassHBox = new HBox(schoolClassIDLbl, schoolClassTxtF);
        schoolClassHBox.setSpacing(50);
        schoolClassHBox.setPadding(new Insets(0,20,50,20));

        Button saveBtn = new Button("Save");
        VBox dataVBox = new VBox(studToClassLbl, studIDHBox, studFirstNameHBox, studLastNameHBox, studOldClassHBox, schoolClassHBox, saveBtn);
        dataVBox.setSpacing(30);
        dataVBox.setPadding(new Insets (20,20,20,20));

        HBox allHBox = new HBox(studentListView, dataVBox);


        Scene generalScene = new Scene (allHBox);
        primaryStage.setScene(generalScene);
        primaryStage.show();
    }

	public static void main(String[] args) {
        Application.launch(args);
        SpringApplication.run(ProjectApplication.class, args);
	}

}
