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

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

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

    /**
     * Get all db records
     * @return
     * @throws SQLException
     */
    public List<Student> getAllRowsStudents()  throws SQLException {

        String sql = "SELECT studentId, stud_firstName, stud_lastName, stud_old_class, fk_stud_new_classID  FROM " + studentTable + " ORDER BY stud_lastName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            int studentID = rs.getInt("studentId");
            String studFirstName = rs.getString("stud_firstName");
            String studLastName = rs.getString("stud_lastName");
            String studOldClass = rs.getString("stud_old_class");
            int studNewClassID = rs.getInt("fk_stud_new_classID");
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

        String dml = "UPDATE `student` SET `fk_stud_new_classID`= ? WHERE studentId = ?";
        PreparedStatement pstmnt = conn.prepareStatement(dml, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmnt.setInt(1, schoolClass.getClassID());
        pstmnt.setInt(2, student.getStudentID());
        pstmnt.executeUpdate();
        pstmnt.close();
    }

    public String getSchoolClass(Student student) throws Exception{
        String sql = "SELECT schoolclass.class_name FROM schoolclass JOIN student ON student.fk_stud_new_classID = schoolclass.class_ID WHERE student.studentId = ?";
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

    public void insertGrades(int studID, String bioGrade, String mathGrade, String drawGrade, String gerGrade,
                             String surfGrade, String phyGrade, String geoGrade) throws Exception{
        String sql = "INSERT INTO studgrade VALUES (?, ?, ?)";
        PreparedStatement pstmnt = conn.prepareStatement(sql);

        pstmnt.setString(1, bioGrade);
        pstmnt.setInt(2, 1);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, mathGrade);
        pstmnt.setInt(2, 2);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, drawGrade);
        pstmnt.setInt(2, 3);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, gerGrade);
        pstmnt.setInt(2, 4);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, surfGrade);
        pstmnt.setInt(2, 5);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, phyGrade);
        pstmnt.setInt(2, 6);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.setString(1, geoGrade);
        pstmnt.setInt(2, 7);
        pstmnt.setInt(3, studID);
        pstmnt.executeUpdate();

        pstmnt.close();
    }

    public boolean studentIdExists(Student student) throws SQLException {

        String sql = "SELECT COUNT(fk_studentId) FROM studgrade WHERE fk_studentId = ?";
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
