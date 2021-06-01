package by.brgtu.david.krasko.diplom.dao;

import by.brgtu.david.krasko.diplom.model.Role;
import by.brgtu.david.krasko.diplom.model.RoleDto;

import java.util.List;

/**
 * The interface Role dao.
 */
public interface RoleDao {

    /**
     * Gets roles.
     *
     * @return the roles
     */
    List<Role> getRoles();

    /**
     * Create user role boolean.
     *
     * @param roles the roles
     * @return the boolean
     */
    Boolean createUserRole(List<RoleDto> roles);
}
