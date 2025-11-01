import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentID = request.getParameter("studentID");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schooldb", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Attendance (StudentID, Date, Status) VALUES (?, ?, ?)");
            ps.setString(1, studentID);
            ps.setString(2, date);
            ps.setString(3, status);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h3>Attendance Recorded Successfully!</h3>");
            } else {
                out.println("<h3>Error recording attendance.</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
