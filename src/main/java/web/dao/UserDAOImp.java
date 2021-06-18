package web.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImp implements UserDAO{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> listUsers() {
        return em.createQuery("SELECT p FROM User p", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> q = em.createQuery(
                "SELECT u FROM User u WHERE u.id = :id",
                User.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void updateUser(int id, User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        em.remove(getUser(id));
    }

    public User findByUsername(String username){
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name",username).getSingleResult();
    }
   /* @Override
    public void adminExist(){
        User user = new User();
        Set<Role> roles = null;
        roles.add(new Role(1,"ROLE_ADMIN"));
        user.setId(1);
        user.setAge(1);
        user.setName("Admin");
        user.setPassword("100");
        user.setWeight(1);
        user.setRoles(roles);
        if(!em.contains(user)){
            em.persist(user);
        }
    }*/

}
