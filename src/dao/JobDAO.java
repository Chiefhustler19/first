package dao;

import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class JobDAO {

    public void addJob(String title, String desc, int employerId) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO jobs(title,description,employer_id) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setInt(3, employerId);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getJobs() {
        ArrayList<String> jobs = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM jobs";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jobs.add(rs.getString("title") + " → " + rs.getString("description"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return jobs;
    }
}
