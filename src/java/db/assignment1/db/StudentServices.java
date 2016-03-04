
package db.assignment1.db;

import db.assignment1.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public class StudentServices {

    // List Student name and id by department
    public List<Student> getStudentsByDepartment(String deparment) {
        List<Student> students = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            if (con != null) {
            //System.out.println("Connection Successfull");

                //String sql="SELECT * FROM probabilistic_relation";
                String sql = "SELECT * FROM students WHERE dept= ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, deparment);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    // String course=rs.getString("course_code");
                    //String expression=rs.getString("expression");
                    //System.out.println(name+" "+course+" "+" "+expression);
                    students.add(new Student(id, name));
                }
                rs.close();
            }
        } catch (ClassNotFoundException ce) {

            System.out.println("Error in loading database class:" + ce.getMessage());
            return null;
        } catch (SQLException se) {
            System.out.println("Error in SQL:" + se.getMessage());
            return null;
        }

        //System.out.println(table);
        return students;
    }

    // List Student name and id by department
    public List<Student> getCourseTakenByStudent(String studentName) {
        List<Student> students = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            if (con != null) {
            //System.out.println("Connection Successfull");

                //String sql="SELECT * FROM probabilistic_relation";
                String sql = "SELECT courses.cNo, courses.title "
                        + " FROM (courses JOIN transcripts USING(cNo)) JOIN students USING(id)"
                        + " WHERE students.name= ? and transcripts.grade in ('A','B')";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, studentName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cno = rs.getInt("cNo");
                    String title = rs.getString("title");
                    // String course=rs.getString("course_code");
                    //String expression=rs.getString("expression");
                    //System.out.println(name+" "+course+" "+" "+expression);
                    Student st = new Student();
                    st.setCno(cno);
                    st.setTitle(title);
                    students.add(st);
                }
                rs.close();
            }
        } catch (ClassNotFoundException ce) {

            System.out.println("Error in loading database class:" + ce.getMessage());
            return null;
        } catch (SQLException se) {
            System.out.println("Error in SQL:" + se.getMessage());
            return null;
        }

        //System.out.println(table);
        return students;
    }

    // List Student name and id by department
    public List<Student> getStudentWithTotalCreditByDepartment(String department) {
        List<Student> students = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            if (con != null) {
            //System.out.println("Connection Successfull");

                //String sql="SELECT * FROM probabilistic_relation";
                String sql = "SELECT students.id,students.name, "
                        + " sum(courses.credit) AS total_credits "
                        + " FROM (courses JOIN transcripts USING(cNo)) "
                        + " JOIN students USING(id) "
                        + " WHERE students.dept= ? "
                        + " GROUP BY students.id ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, department);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int totalCredit=rs.getInt("total_credits");
                    // String course=rs.getString("course_code");
                    //String expression=rs.getString("expression");
                    //System.out.println(name+" "+course+" "+" "+expression);
                    students.add(new Student(id, name,totalCredit));
                }
                rs.close();
            }
        } catch (ClassNotFoundException ce) {

            System.out.println("Error in loading database class:" + ce.getMessage());
            return null;
        } catch (SQLException se) {
            System.out.println("Error in SQL:" + se.getMessage());
            return null;
        }

        //System.out.println(table);
        return students;
    }

}
