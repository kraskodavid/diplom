package by.brgtu.david.krasko.diplom.service;

import by.brgtu.david.krasko.diplom.model.UserRegistrationDto;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Load user by login user.
     *
     * @param login the login
     * @return the user
     */
    Long loadUserIdByLogin(String login);

    /**
     * Save.
     *
     * @param user the user
     */
    void save(UserRegistrationDto user);

    /**
     * Auto login.
     *
     * @param userName the user name
     * @param password the password
     */
    void autoLogin(String userName, String password);
}
