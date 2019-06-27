package com.school.project.FX_Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDataAccess {

    private Connection conn;
    private static final String studentTable = "student";
    private static final String classTable = "teacherclasskey";

    public SchoolDataAccess()
            throws SQLException, ClassNotFoundException {

        //Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolTest" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // connection to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }
    
    public List<Student> getAllRowsStudents()  throws SQLException {

        String sql = "SELECT student_id, first_name, last_name, old_class, fk_new_class_id  FROM " + studentTable + " ORDER BY last_name";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            int studentID = rs.getInt("student_id");
            String studFirstName = rs.getString("first_name");
            String studLastName = rs.getString("last_name");
            String studOldClass = rs.getString("old_class");
            int studNewClassID = rs.getInt("fk_new_class_id");
            list.add(new Student(studentID, studFirstName, studLastName, studOldClass, studNewClassID));
        }
        pstmnt.close(); // also closes related result set
        return list;
    }

    public List<SchoolClass> getAllRowsSchoolClasses() throws SQLException {
        String sql = "SELECT class_ID, class_name FROM schoolclass ORDER BY class_name";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<SchoolClass> list = new ArrayList<>();

        while(rs.next()){
            int classID = rs.getInt("class_ID");
            String className = rs.getString("class_name");
            list.add(new SchoolClass(classID,className));
        }
        pstmnt.close();
        return list;
    }

    public void updateClass(Student student, SchoolClass schoolClass)
            throws SQLException {

        String dml = "UPDATE `student` SET `fk_new_class_id`= ? WHERE student_id = ?";
        PreparedStatement pstmnt = conn.prepareStatement(dml, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmnt.setInt(1, schoolClass.getClassID());
        pstmnt.setInt(2, student.getStudentID());
        pstmnt.executeUpdate();
        pstmnt.close();
    }

    public String getSchoolClass(Student student) throws Exception{
        String sql = "SELECT schoolclass.class_name FROM schoolclass JOIN student ON student.fk_new_class_id = schoolclass.class_ID WHERE student.student_Id = ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1,student.getStudentID());
        ResultSet rs = pstmnt.executeQuery();
        String className = "";
        while(rs.next()){
            className = rs.getString("class_name");
        }
        pstmnt.close();
        return className;
    }

    public void insertGrades(int studID, String mathGrade, String gerGrade, String engGrade, String bioGrade, String musGrade, String drawGrade,
                             String surfGrade) throws Exception{
        String sql = "INSERT INTO grade VALUES (?, ?, ?)";
        PreparedStatement pstmnt = conn.prepareStatement(sql);

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 1);
        pstmnt.setString(3, mathGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 2);
        pstmnt.setString(3, gerGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2,3);
        pstmnt.setString(3, engGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 4);
        pstmnt.setString(3, bioGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 5);
        pstmnt.setString(3, musGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 6);
        pstmnt.setString(3, drawGrade);
        pstmnt.executeUpdate();

        pstmnt.setInt(1, studID);
        pstmnt.setInt(2, 7);
        pstmnt.setString(3, surfGrade);
        pstmnt.executeUpdate();

        pstmnt.close();
    }

    public boolean studentIdExists(Student student) throws SQLException {

        String sql = "SELECT COUNT(student_id) FROM grade WHERE student_id = ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1, student.getStudentID());
        ResultSet rs = pstmnt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        pstmnt.close();
        if (count > 0) {

            return true;
        }

        return false;
    }
}
