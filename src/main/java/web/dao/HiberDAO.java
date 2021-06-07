package web.dao;


import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class HiberDAO implements UserDAO{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;


    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
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
        User user1 = getUser(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setAge(user.getWeight());
    }

    @Override
    public void deleteUser(int id) {
        em.remove(getUser(id));
    }
}
