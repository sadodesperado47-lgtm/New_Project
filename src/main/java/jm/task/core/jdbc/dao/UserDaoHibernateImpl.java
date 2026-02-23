package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            Transaction tx = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null) {
                session.delete(user);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            return session.createQuery("from jm.task.core.jdbc.model.User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from jm.task.core.jdbc.model.User").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
