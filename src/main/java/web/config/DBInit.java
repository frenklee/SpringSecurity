package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;
import web.model.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    public void postConstruct() {

            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            if(em.find(User.class, 1)==null) {
                User admin = new User("ADMIN", 1,
                        1, passwordEncoder.encode("100"));
                Role role1 = new Role("ROLE_ADMIN");
                Role role2 = new Role("ROLE_USER");
                Set<Role> roles = new HashSet<>();
                roles.add(role1);
                admin.setRoles(roles);

                em.persist(role1);
                em.persist(role2);
                em.persist(admin);
                em.getTransaction().commit();
                em.close();
            } else {
                em.close();
            }

    }
}
