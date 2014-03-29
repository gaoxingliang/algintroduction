package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-3-29.
 */
public class UserDao
{
    private HibernateTemplate hibernateTemplate = null;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
    {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void saveOrUpdateAll(int count)
    {
        List<User> u = new ArrayList<User>(count);
        for (int i=0;i<count;i++)
        {
            u.add(new User());
        }
        long start =System.currentTimeMillis();
        this.hibernateTemplate.saveOrUpdateAll(u);
        long end = System.currentTimeMillis();
        System.out.println("cost :" +(end-start)/1000 +" for count:" +count);
    }

    public void saveOrUpdateSingle(List<User> users)
    {

    }

    public void deleteAll()
    {
        //clear data
        SessionFactory sf =this.hibernateTemplate.getSessionFactory();
        Session s =sf.getCurrentSession();
        s.beginTransaction();
        s.createSQLQuery("delete from users").executeUpdate();
        s.getTransaction().commit();
    }

    public void saveOrUpdateAllM2(int count)
    {
        List<User> u = new ArrayList<User>(count);
        for (int i=0;i<count;i++)
        {
            u.add(new User());
        }
        long start =System.currentTimeMillis();
        for (User ifcObject : u) {
            if (u.contains(ifcObject)) {
                hibernateTemplate.merge(ifcObject);

            } else {
                hibernateTemplate.saveOrUpdate(ifcObject);

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("cost :" +(end-start)/1000 +" for count:" +count);
    }

}
