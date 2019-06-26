package com.school.project.FX_Project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Scene welcomeScene, studToClassScene, gradesToStudScene;
    private TextField gradesStudNameTxtF = new TextField();
    private TextField bioTxtF = new TextField();
    private TextField mathTxtF = new TextField();
    private TextField drawTxtF = new TextField();
    private TextField gerTxtF = new TextField();
    private TextField surfTxtF = new TextField();
    private TextField phyTxtF = new TextField();
    private TextField geoTxtF = new TextField();

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
            gradesStudNameTxtF.setText(student.getStudFirstName() + " " + student.getStudLastName());
            studLastNameTxtF.setText(student.getStudLastName());
            studOldClassTxtF.setText(student.getStudOldClass());
            String className = "";
            try {
                className = dbAccess.getSchoolClass(student);
            } catch (Exception e) {

                displayException(e);
            }
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

    private class SaveStudentButtonListener implements EventHandler<ActionEvent> {
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

        // Welcome Scene
        Label label1= new Label("Welcome to SchoolApp");
        Text text = new Text("Please select task");
        Button button1 = new Button("Add students to classes");
        Button button2 = new Button("Add grades to students");
        button1.setOnAction(event -> primaryStage.setScene(studToClassScene));
        button2.setOnAction(event -> primaryStage.setScene(gradesToStudScene));
        VBox layout1 = new VBox(20);
        layout1.setPadding(new Insets(30, 30, 30, 30));
        layout1.getChildren().addAll(label1, text, button1, button2);
        welcomeScene= new Scene(layout1, 300, 250);


        // Student to Class Scene
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

        Button saveStudentBtn = new Button("Save");
        saveStudentBtn.setOnAction(new SaveStudentButtonListener());
        Button studToClassBackBtn = new Button("back");
        studToClassBackBtn.setOnAction(event -> primaryStage.setScene(welcomeScene));
        HBox btnBoxStudent = new HBox(saveStudentBtn, actionStatus, studToClassBackBtn);

        VBox dataVBox = new VBox(studToClassLbl, studIDHBox, studFirstNameHBox, studLastNameHBox,
                studOldClassHBox, schoolClassHBox, btnBoxStudent);
        dataVBox.setSpacing(30);
        dataVBox.setPadding(new Insets (20,20,20,20));

        HBox allHBox = new HBox(studentListView, dataVBox, schoolClassListView);
        studToClassScene = new Scene(allHBox);





        // Grades to Student Scene
        Label gradAppLbl = new Label("Select a student to add the grades");
        gradAppLbl.setMinHeight(20);
        gradAppLbl.setWrapText(true);
        gradAppLbl.setStyle("-fx-font: 20 arial;");
        Label studLbl = new Label("Students:");
        Text studName = new Text ("Student");
        Text bioTxt = new Text ("Biology");
        Text mathTxt = new Text ("Math");
        Text drawTxt = new Text ("Drawing");
        Text gerTxt = new Text ("German");
        Text surfTxt = new Text ("Surfing");
        Text phyTxt = new Text ("Physics");
        Text geoTxt = new Text ("Geography");

        studentListView = new ListView();
        studentListView.setPrefHeight(175);
        studentListView.getSelectionModel().selectedIndexProperty().addListener(
                new SchoolApp.ListSelectChangeListenerStudents());
        dataStudent = getDbDataStudents();
        studentListView.setItems(dataStudent);
        studentListView.setPrefWidth(150);
        studentListView.setMaxHeight(410);

        Button saveGradesBtn = new Button("Save");
        saveGradesBtn.setOnAction(new SaveGradesButtonListener());
        Button gradesBackBtn = new Button("back");
        gradesBackBtn.setOnAction(event -> primaryStage.setScene(welcomeScene));

        HBox studNameBox = new HBox(gradesStudNameTxtF);
        studNameBox.setSpacing(5);
        studNameBox.setPrefWidth(40);

        VBox txtBox = new VBox(studName, bioTxt,mathTxt,drawTxt,gerTxt,surfTxt,phyTxt,geoTxt);
        txtBox.setSpacing(40);
        txtBox.setPadding(new Insets(0,10,20,40));

        HBox btnBoxGrades = new HBox(saveGradesBtn, gradesBackBtn);
        btnBoxGrades.setSpacing(50);

        VBox txtFieldBox = new VBox(studNameBox, bioTxtF, mathTxtF, drawTxtF, gerTxtF, surfTxtF, phyTxtF, geoTxtF, btnBoxGrades, actionStatus);
        txtFieldBox.setSpacing(30);

        HBox generalBox = new HBox(studentListView, txtBox, txtFieldBox);
        //generalBox.setSpacing(20);
        generalBox.setPadding(new Insets(30,50,50,50));

        VBox showbox = new VBox(gradAppLbl, generalBox);
        showbox.setPadding(new Insets(40, 5,5,5));
        showbox.setAlignment(Pos.TOP_CENTER);

        gradesToStudScene = new Scene(showbox);



        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        Application.launch(args);
    }
    private class SaveGradesButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent ae) {
            Student student;

            int ix = studentListView.getSelectionModel().getSelectedIndex();

            if (ix < 0) { // no student selected
                actionStatus.setText("no student selected");
                return;
            }

            int studentID = studentListView.getSelectionModel().getSelectedItem().getStudentID();

            String bioGrade = bioTxtF.getText();
            String mathGrade = mathTxtF.getText();
            String drawGrade = drawTxtF.getText();
            String gerGrade = gerTxtF.getText();
            String surfGrade = surfTxtF.getText();
            String phyGrade = phyTxtF.getText();
            String geoGrade = geoTxtF.getText();

            // validate grade
            if ((bioGrade.length() > 1) || (mathGrade.length() > 1) || (drawGrade.length() < 9) || (gerGrade.length() > 1) || (surfGrade.length() < 9) || (phyGrade.length() > 1) || (geoGrade.length() > 1)) {

                actionStatus.setText("Please check the grades");
                /*nametxt.requestFocus();
                nametxt.selectAll();*/
                return;
            }

            // check if student already has grades
            student = dataStudent.get(ix);

            if (isStudentAlreadyInDb(student)) {

                actionStatus.setText("This student already has grades");
                return;
            }

            try {
                dbAccess.insertGrades(studentID, bioGrade, mathGrade, drawGrade, gerGrade, surfGrade, phyGrade, geoGrade);
            } catch (Exception e) {

                displayException(e);
            }
            actionStatus.setText("Grades are saved");
        }
    }

    private boolean isStudentAlreadyInDb(Student student) {

        boolean bool = false;

        try {
            bool = dbAccess.studentIdExists(student);
        }
        catch (Exception e) {

            displayException(e);
        }

        return bool;
    }
}

