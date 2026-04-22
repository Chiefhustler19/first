package services;

import dao.UserDAO;
import model.User;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    public boolean register(User user) {
        return userDAO.register(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
