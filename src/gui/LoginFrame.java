package gui;

import services.AuthService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private AuthService auth = new AuthService();

    public LoginFrame() {

        setTitle("YouthLink Login");
        setSize(300, 200);
        setLayout(new GridLayout(4,2));

        JTextField email = new JTextField();
        JPasswordField password = new JPasswordField();

        add(new JLabel("Email"));
        add(email);

        add(new JLabel("Password"));
        add(password);

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        add(login);
        add(register);

        login.addActionListener(e -> {
            User user = auth.login(email.getText(), new String(password.getPassword()));

            if (user != null) {
                new DashboardFrame(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
            }
        });

        register.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Name:");
            String role = JOptionPane.showInputDialog("Role (student/employer/driver):");

            User user = new User(
                    name,
                    email.getText(),
                    new String(password.getPassword()),
                    role
            );

            if (auth.register(user)) {
                JOptionPane.showMessageDialog(this, "Registered successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
    

