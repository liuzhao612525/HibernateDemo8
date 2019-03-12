package cn.itcast.hibernate;
import org.junit.Test;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcast.entity.Contact;
import cn.itcast.entity.Customer;
import cn.itcast.uitls.HibernateUtils;

public class HibernateDemo {
	
	
	//对象导航查询/OID查询
	@Test
	public void testSelectDemo1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			//get立即查询，调用get方法会立即执行sql语句，调用load方法则不会马上执行sql语句,返回对象里只有id，得到对象里不是id的其他值的时候才会发送语句
//			Customer customer = session.load(Customer.class, 8);
//			System.out.println(customer.getCid());//不执行sql语句，因为调用load方法返回对象里只有id，所以此语句不会执行sql语句，只有调用其他属性时才会执行sql语句
//			System.out.println(customer.getCustName());//才执行sql语句
			
			Customer customer = session.get(Customer.class, 8);
			Set<Contact> contacts = customer.getContacts();
			
			System.out.println("contacts........................." + contacts.size());
			
			
			transaction.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	
	//hql查询
	@Test
	public void testSelectDemo2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
//			Query<Customer> query = session.createQuery("from Customer");
			
			//条件查询
//			Query<Customer> query = session.createQuery("from Customer where cid=? and custName=?");
//			//第一个参数：int类型，第一个问号的位置。从0开始
//			//第二参数：设置问号里面的值
//			query.setParameter(0, 8);
//			query.setParameter(1, "百度");
			
//			Query<Customer> query = session.createQuery("from Customer where custName like ?");
//			//第一个参数：int类型，第一个问号的位置。从0开始
//			//第二参数：设置问号里面的值
//			query.setParameter(0, "%goole%");
			
			//排序查询
//			Query<Customer> query = session.createQuery("from Customer order by cid desc ");
			
			//分页查询
			Query<Customer> query = session.createQuery("from Customer");
			query.setFirstResult(0);
			query.setMaxResults(3);
			
			
			List<Customer> list = query.list();

			System.out.println("contacts.........................");
			System.out.println(list);
			
			
			transaction.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	//图影查询
	@Test
	public void testSelectDemo3() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("select custName from Customer");
			
			List<Object> list = query.list();
			for (Object object : list) {
				System.out.println(object);
			}
			
			
			transaction.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	//聚集函数查询
		@Test
		public void testSelectDemo4() {
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction transaction = null;
			
			try {
				sessionFactory = HibernateUtils.getSessionFactory();
				session = HibernateUtils.getSessionObject();
				transaction = session.beginTransaction();
				
				Query query = session.createQuery("select count(*) from Customer");
				Object object = query.uniqueResult();
				System.out.println(object);
				
				transaction.commit();
			} catch (Exception e) {
				
				e.printStackTrace();
				transaction.rollback();
			}finally {
				session.close();
				sessionFactory.close();
			}
		}
	
	
	@Test
	public void testAddDemo() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Customer customer = new Customer();
			customer.setCustName("百度");
			customer.setCustLevel("vip");  
			customer.setCustMobile("222");
			customer.setCustPhone("3333");
			customer.setCustSource("网络");
			
			Contact contact = new Contact();
			contact.setCngender("女");
			contact.setCnname("里三");
			contact.setCnphone("34444");
			
			//在客户里表示所有联系人，在联系人里表示客户
			//建立客户和联系人之间的关系
			//把联系人对象放到set集合中
			customer.getContacts().add(contact);
			
			//把客户对象放到联系人里面
			contact.setCustomer(customer);
			
			//保存
			session.save(customer);
			session.save(contact);
			
			transaction.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}
}
