package by.brgtu.david.krasko.diplom.service.impl;

import by.brgtu.david.krasko.diplom.dao.RoleDao;
import by.brgtu.david.krasko.diplom.model.Role;
import by.brgtu.david.krasko.diplom.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
