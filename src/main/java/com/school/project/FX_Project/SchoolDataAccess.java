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

        String sql = "SELECT studentId, stud_firstName, stud_lastName, stud_class FROM " + studentTable + " ORDER BY stud_lastName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            int studentID = rs.getInt("studentId");
            String studFirstName = rs.getString("stud_firstName");
            String studLastName = rs.getString("stud_lastName");
            String studOldClass = rs.getString("stud_class");
            list.add(new Student(studentID, studFirstName, studLastName, studOldClass));
        }
        pstmnt.close(); // also closes related result set
        return list;
    }
    public List<SchoolClass> getAllRowsSchoolClasses() throws SQLException {
        String sql = "SELECT DISTINCT classname FROM teacherclasskey ORDER BY classname";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<SchoolClass> list = new ArrayList<>();

        while(rs.next()){
            String className = rs.getString("classname");
            list.add(new SchoolClass(className));
        }
        pstmnt.close();
        return list;
    }
}
