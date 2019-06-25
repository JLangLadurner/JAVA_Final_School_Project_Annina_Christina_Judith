package com.school.project.FX_Project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class SchoolApp extends Application {

    private SchoolDataAccess dbAccess;
    private ListView<Student> studentListView;
    private ObservableList<Student> dataStudent;
    private ListView<SchoolClass> schoolClassListView;
    private ObservableList<SchoolClass> dataSchoolClass;
    private TextField studIDTxtF = new TextField();
    private TextField studFirstNameTxtF = new TextField();
    private TextField studLastNameTxtF = new TextField();
    private TextField studOldClassTxtF = new TextField();
    private TextField schoolClassTxtF = new TextField();
    private Text actionStatus = new Text();


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

    private ObservableList<SchoolClass> getDbDataSchoolClasses() {
        List<SchoolClass> list = null;
        try {
            list = dbAccess.getAllRowsSchoolClasses();
        } catch (Exception e) {
            displayException(e);
        }
        ObservableList<SchoolClass> dbDataSchoolClass = FXCollections.observableList(list);
        return dbDataSchoolClass;
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
            String className = dbAccess.getSchoolClass(student);
            schoolClassTxtF.setText(className);
        }
    }

    private class ListSelectChangeListenerSchoolClasses implements ChangeListener<Number>{
        @Override
        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= dataSchoolClass.size())) {
                return; // invalid data
            }
            // set fields for the selected student (ID, name, surname, oldClass
            SchoolClass schoolClass = dataSchoolClass.get(new_val.intValue());
            schoolClassTxtF.setText(schoolClass.getClassName());
        }
    }

    private class SaveButtonListener implements EventHandler<ActionEvent> {
        Student student;
        SchoolClass schoolClass;

        @Override
        public void handle(ActionEvent ae) {

            String classNameNew = null;
            int classIndex = schoolClassListView.getSelectionModel().getSelectedIndex();
            int studIndex = studentListView.getSelectionModel().getSelectedIndex();
            if (classIndex != -1) {
                classNameNew = schoolClassTxtF.getText();
            }


            if (classIndex < 0) { // no data selected or no data
                return;
            }

            // validate class
            if (classNameNew.length() != 2) {

                actionStatus.setText("You have to choose a class");
                schoolClassTxtF.requestFocus();
                schoolClassTxtF.selectAll();
                return;
            }

            int classID;
            for (int i = 0; i < schoolClassListView.getItems().size(); i++) {
                schoolClass = schoolClassListView.getSelectionModel().getSelectedItems().get(i);
                if (schoolClass.getClassName().equalsIgnoreCase(classNameNew)) {
                    classID = schoolClass.getClassID();
                    studentListView.getItems().get(studIndex).setStudNewClassID(classID);
                    student = studentListView.getSelectionModel().getSelectedItem();
                    studentListView.refresh();

                    try {
                        dbAccess.updateClass(student, schoolClass);
                    } catch (Exception e) {

                        displayException(e);
                    }
                    actionStatus.setText("Student is saved in his new class");
                }
                break;
            }
        }

    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SchoolApp");

        Label studToClassLbl = new Label("Insert student to class");
        studToClassLbl.setLineSpacing(30);

        studentListView = new ListView();
        studentListView.setPrefHeight(175);
        studentListView.getSelectionModel().selectedIndexProperty().addListener(
                new SchoolApp.ListSelectChangeListenerStudents());
        dataStudent = getDbDataStudents();
        studentListView.setItems(dataStudent);

        schoolClassListView = new ListView();
        schoolClassListView.setPrefHeight(175);
        schoolClassListView.getSelectionModel().selectedIndexProperty().addListener(
                new SchoolApp.ListSelectChangeListenerSchoolClasses());
        dataSchoolClass = getDbDataSchoolClasses();
        schoolClassListView.setItems(dataSchoolClass);

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
        saveBtn.setOnAction(new SaveButtonListener());

        VBox dataVBox = new VBox(studToClassLbl, studIDHBox, studFirstNameHBox, studLastNameHBox,
                studOldClassHBox, schoolClassHBox, saveBtn, actionStatus);
        dataVBox.setSpacing(30);
        dataVBox.setPadding(new Insets (20,20,20,20));

        HBox allHBox = new HBox(studentListView, dataVBox, schoolClassListView);


        Scene generalScene = new Scene (allHBox);
        primaryStage.setScene(generalScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

