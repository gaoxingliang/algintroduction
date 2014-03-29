package hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 14-3-29.
 * this is a class for test the hibernate implemenations....
 */
public class HibernateDemo
{


    public static void main(String[] args) throws Exception
    {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "hibernate/hibernate-config.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        //userDao.deleteAll();

        //cost cost :152s for count:10000
        //userDao.saveOrUpdateAll(10000);

        //cost :192 for count:10000 oops: this is much more long than before...
        userDao.saveOrUpdateAllM2(10000);

        //but i dit not see the error....

    }

}

/**
 * a entity ,mapping to database
 */
class User
{

    public User()
    {
        name = UUID.randomUUID().toString();
        addr = UUID.randomUUID().toString();
        email = UUID.randomUUID().toString();
        phone = UUID.randomUUID().toString();
    }

    public int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    //for simple,use the public attribute,and make attrs more.
    public String name,addr,email,phone;

}
