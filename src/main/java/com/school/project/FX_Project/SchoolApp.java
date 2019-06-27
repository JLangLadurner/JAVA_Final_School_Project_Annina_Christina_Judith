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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class SchoolApp extends Application {

    private SchoolDataAccess dbAccess;
    private ListView<Student> studentListView;
    private ListView<Student> studentListViewGrade;
    private ObservableList<Student> dataStudent;
    private ListView<SchoolClass> schoolClassListView;
    private ObservableList<SchoolClass> dataSchoolClass;
    private TextField studIDTxtF = new TextField();
    private TextField studFirstNameTxtF = new TextField();
    private TextField studLastNameTxtF = new TextField();
    private TextField studOldClassTxtF = new TextField();
    private TextField schoolClassTxtF = new TextField();
    private Text actionStatusStud = new Text();
    private Text actionStatusGrade = new Text();
    private Scene welcomeScene, studToClassScene, gradesToStudScene;
    private TextField gradesStudNameTxtF = new TextField();
    private TextField bioTxtF = new TextField();
    private TextField mathTxtF = new TextField();
    private TextField drawTxtF = new TextField();
    private TextField gerTxtF = new TextField();
    private TextField surfTxtF = new TextField();
    private TextField musTxtF = new TextField();
    private TextField engTxtF = new TextField();

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
            // set fields for the selected student (ID, name, surname, oldClass)
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
            // set field for the selected schoolclass (classname)
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
            // data needed for saving
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

                actionStatusStud.setText("You have to choose a class");
                //schoolClassTxtF.requestFocus();
                //schoolClassTxtF.selectAll();
                return;
            }

            // to get the classID out from the classname
            int classID;
            for (int i = 0; i < schoolClassListView.getItems().size(); i++) {
                schoolClass = schoolClassListView.getSelectionModel().getSelectedItems().get(i);
                if (schoolClass.getClassName().equalsIgnoreCase(classNameNew)) {
                    classID = schoolClass.getClassID();
                    studentListView.getItems().get(studIndex).setStudNewClassID(classID);
                    student = studentListView.getSelectionModel().getSelectedItem();
                    studentListView.refresh();

                    // save the data in the database
                    try {
                        dbAccess.updateClass(student, schoolClass);
                    } catch (Exception e) {

                        displayException(e);
                    }
                    actionStatusStud.setText("Student is saved in his new class");
                }
                break;
            }
        }
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("SchoolApp");




        // Welcome Scene
        Label welcomeLbl= new Label("Welcome to SchoolApp");
        welcomeLbl.setStyle("-fx-font: 20 arial;");
        Text text = new Text("Please select task");

        Button button1 = new Button("Add students to classes");
        Button button2 = new Button("Add grades to students");
        button1.setOnAction(event -> primaryStage.setScene(studToClassScene));
        button2.setOnAction(event -> primaryStage.setScene(gradesToStudScene));

        VBox layoutBox = new VBox(20);
        layoutBox.setPadding(new Insets(70, 70, 70, 70));
        layoutBox.getChildren().addAll(welcomeLbl, text, button1, button2);
        layoutBox.setAlignment(Pos.TOP_CENTER);
        layoutBox.setMinSize(600, 400);
        welcomeScene= new Scene(layoutBox);




        // Student to Class Scene
        Label studToClassLbl = new Label("Select a student and the appropriate class for the coming school year");
        studToClassLbl.setLineSpacing(30);
        studToClassLbl.setStyle("-fx-font: 18 arial;");
        studToClassLbl.setAlignment(Pos.CENTER);

        studentListView = new ListView();
        studentListView.setPrefHeight(175);
        studentListView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerStudents());
        dataStudent = getDbDataStudents();
        studentListView.setItems(dataStudent);
        studentListView.setMaxHeight(270);
        studentListView.setPrefWidth(170);

        schoolClassListView = new ListView();
        schoolClassListView.setPrefHeight(175);
        schoolClassListView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerSchoolClasses());
        dataSchoolClass = getDbDataSchoolClasses();
        schoolClassListView.setItems(dataSchoolClass);
        schoolClassListView.setMaxHeight(270);
        schoolClassListView.setPrefWidth(80);


        Label studIDLbl = new Label("Student ID");
        Label studFirstNameLbl = new Label("First Name");
        Label studLastNameLbl = new Label("Last Name");
        Label studOldClassLbl = new Label("Last class passed");
        Label schoolClassIDLbl = new Label("Class this year");

        VBox lblBox = new VBox(studIDLbl, studFirstNameLbl, studLastNameLbl, studOldClassLbl, schoolClassIDLbl);
        lblBox.setSpacing(29);

        VBox txtFBox = new VBox(studIDTxtF, studFirstNameTxtF, studLastNameTxtF, studOldClassTxtF, schoolClassTxtF);
        txtFBox.setSpacing(20);

        HBox dataBox = new HBox(studentListView, lblBox, txtFBox, schoolClassListView);
        dataBox.setSpacing(30);

        Button saveStudentBtn = new Button("Save");
        saveStudentBtn.setOnAction(new SaveStudentButtonListener());
        saveStudentBtn.setStyle("-fx-background-color: lightgreen; ");
        saveStudentBtn.setPrefWidth(70);
        Button studToClassBackBtn = new Button("back");
        studToClassBackBtn.setOnAction(event -> primaryStage.setScene(welcomeScene));
        studToClassBackBtn.setStyle("-fx-background-color: lightblue;");
        studToClassBackBtn.setPrefWidth(70);
        HBox btnBoxStudent = new HBox(saveStudentBtn, studToClassBackBtn,actionStatusStud);
        btnBoxStudent.setAlignment(Pos.CENTER);
        btnBoxStudent.setSpacing(20);

        VBox allBox = new VBox( studToClassLbl, dataBox, btnBoxStudent);
        allBox.setPadding(new Insets(30,30,30,30));
        allBox.setSpacing(30);
        allBox.setAlignment(Pos.CENTER);
        studToClassScene = new Scene(allBox);





        // Grades to Student Scene
        Label gradAppLbl = new Label("Select a student to add the grades");
        gradAppLbl.setMinHeight(20);
        gradAppLbl.setWrapText(true);
        gradAppLbl.setStyle("-fx-font: 20 arial;");

        Label studLbl = new Label("Students:");
        Text studName = new Text ("Student");
        Text bioTxt = new Text ("Math");
        Text mathTxt = new Text ("German");
        Text drawTxt = new Text ("English");
        Text gerTxt = new Text ("Biology");
        Text surfTxt = new Text ("Music");
        Text phyTxt = new Text ("Drawing");
        Text geoTxt = new Text ("Surfing");

        studentListViewGrade = new ListView();
        studentListViewGrade.setPrefHeight(175);
        studentListViewGrade.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerStudents());
        dataStudent = getDbDataStudents();
        studentListViewGrade.setItems(dataStudent);
        studentListViewGrade.setPrefWidth(150);
        studentListViewGrade.setMaxHeight(410);

        Button saveGradesBtn = new Button("Save");
        saveGradesBtn.setStyle("-fx-background-color: lightgreen; ");
        saveGradesBtn.setMinWidth(70);
        saveGradesBtn.setOnAction(new SaveGradesButtonListener());
        Button gradesBackBtn = new Button("back");
        gradesBackBtn.setMinWidth(70);
        gradesBackBtn.setStyle("-fx-background-color: lightblue;");
        gradesBackBtn.setOnAction(event -> primaryStage.setScene(welcomeScene));

        VBox txtBox = new VBox(studName, bioTxt,mathTxt,drawTxt,gerTxt,surfTxt,phyTxt,geoTxt);
        txtBox.setSpacing(40);
        txtBox.setPadding(new Insets(0,10,20,50));

        HBox btnBoxGrades = new HBox(saveGradesBtn, gradesBackBtn);
        btnBoxGrades.setSpacing(20);

        VBox txtFieldBox = new VBox(gradesStudNameTxtF, mathTxtF, gerTxtF, engTxtF, bioTxtF, musTxtF, drawTxtF, surfTxtF, btnBoxGrades);
        txtFieldBox.setSpacing(30);

        HBox generalBox = new HBox(studentListViewGrade, txtBox, txtFieldBox);
        generalBox.setPadding(new Insets(30,50,20,50));

        actionStatusGrade.setWrappingWidth(350);
        actionStatusGrade.setTextAlignment(TextAlignment.CENTER);
        VBox showbox = new VBox(gradAppLbl, generalBox, actionStatusGrade);
        showbox.setPadding(new Insets(40, 5,5,5));
        showbox.setAlignment(Pos.TOP_CENTER);

        gradesToStudScene = new Scene(showbox, 580, 650);



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

            int ix = studentListViewGrade.getSelectionModel().getSelectedIndex();

            if (ix < 0) { // no student selected
                actionStatusGrade.setText("no student selected");
                return;
            }

            int studentID = studentListViewGrade.getSelectionModel().getSelectedItem().getStudentID();

            String mathGrade = mathTxtF.getText();
            String gerGrade = gerTxtF.getText();
            String engGrade = engTxtF.getText();
            String bioGrade = bioTxtF.getText();
            String musGrade = musTxtF.getText();
            String drawGrade = drawTxtF.getText();
            String surfGrade = surfTxtF.getText();

            // validate grade
            if (((bioGrade.length() > 1) || (mathGrade.length() > 1) || (gerGrade.length() > 1) || (musGrade.length() > 1)
                    || (engGrade.length() > 1)) ||
                    (!drawGrade.equalsIgnoreCase("very good") && !drawGrade.equalsIgnoreCase("well done")
                        && !drawGrade.equalsIgnoreCase("successful") && !drawGrade.equalsIgnoreCase("not successful"))
                || (!surfGrade.equalsIgnoreCase("very good") && !surfGrade.equalsIgnoreCase("well done")
                        && !surfGrade.equalsIgnoreCase("successful") && !surfGrade.equalsIgnoreCase("not successful"))) {

                    actionStatusGrade.setText("Please check the grades! Note: Drawing and Surfing are graded as follows:" +
                            " very good, well done, successful, not successful");
                    return;
            }

            // check if student already has grades
            student = dataStudent.get(ix);

            if (hasStudentAlreadyGrades(student)) {

                actionStatusGrade.setText("This student already has grades");
                return;
            }

            // save grades in the database
            try {
                dbAccess.insertGrades(studentID, mathGrade, gerGrade, engGrade, bioGrade, musGrade, drawGrade, surfGrade);
            } catch (Exception e) {

                displayException(e);
            }
            actionStatusGrade.setText("Grades are saved");
        }
    }

    // check if student
    private boolean hasStudentAlreadyGrades(Student student) {

        boolean bool = false;

        try {
            bool = dbAccess.gradesExists(student);
        }
        catch (Exception e) {

            displayException(e);
        }

        return bool;
    }
}

