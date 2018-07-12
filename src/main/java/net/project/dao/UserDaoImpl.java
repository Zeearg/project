package net.project.dao;

import net.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl(){}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User findByID(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user){
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    @Transactional
    public void update(User user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null)
            session.delete(user);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> listUsers(){
        Session session = sessionFactory.getCurrentSession();
        List<User> listUsers = session.createQuery("FROM User").list();
        return listUsers;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> searchUsers(String search){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query query = session.createQuery("FROM User WHERE firstName = :param OR lastName = :param OR address = :param OR phone = :param OR email = :param");
        search = search.substring(0, search.length() - 7);
        query.setParameter("param", search);
        return query.list();
    }

}
