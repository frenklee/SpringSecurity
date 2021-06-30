package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;
import web.model.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Component
public class DBInit {

    @Autowired
    private UserDAO userDAO;

    public void postConstruct() {
        User admin = new User(1,"ADMIN",1,1,"100");
        Role role = new Role(1,"ROLE_ADMIN");
        Set<Role> roles = null;
        roles.add(role);
        admin.setRoles(roles);
        userDAO.addUser(admin);
    }
}
