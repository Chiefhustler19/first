package gui;

import model.User;
import dao.JobDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {

    private JobDAO jobDAO = new JobDAO();
    private JTextArea area;

    public DashboardFrame(User user) {

        setTitle("Dashboard - " + user.getRole());
        setSize(500, 400);
        setLayout(new BorderLayout());

        area = new JTextArea();
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        // ✅ VIEW JOBS BUTTON
        JButton viewJobs = new JButton("View Jobs");

        viewJobs.addActionListener(e -> {
            area.setText("");

            ArrayList<String> jobs = jobDAO.getJobs();

            if (jobs.isEmpty()) {
                area.setText("No jobs available.");
            } else {
                for (String job : jobs) {
                    area.append(job + "\n\n");
                }
            }
        });

        panel.add(viewJobs);

        // 🔄 REFRESH BUTTON
        JButton refresh = new JButton("Refresh");

        refresh.addActionListener(e -> viewJobs.doClick());

        panel.add(refresh);

        // 💼 EMPLOYER FEATURE
        if (user.getRole().trim().equalsIgnoreCase("employer")) {

            JButton postJob = new JButton("Post Job");

            postJob.addActionListener(e -> {

                String title = JOptionPane.showInputDialog("Job Title:");
                String desc = JOptionPane.showInputDialog("Description:");

                jobDAO.addJob(title, desc, 1); // temp ID

                JOptionPane.showMessageDialog(this, "Job posted!");
            });

            panel.add(postJob);
        }

        add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}