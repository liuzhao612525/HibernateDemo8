package cn.itcast.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import cn.itcast.entity.Customer;
import cn.itcast.uitls.HibernateUtils;

public class HibernateQBC {
	//演示查询所以
	@Test
	public void testSelectDemo1() {
		SessionFactory sessionFactory =  null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	@Test
	public void testSelectDemo2() {
		SessionFactory sessionFactory =  null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
//			criteria.add(Restrictions.eq("cid", 1));
//			criteria.add(Restrictions.eq("custName", "百度"));
			criteria.add(Restrictions.ilike("custName", "%百%"));
			
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	//排序查询
	@Test
	public void testSelectDemo3() {
		SessionFactory sessionFactory =  null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.addOrder(Order.desc("cid"));
			
			
			List<Customer> list = criteria.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	//统计查询查询
	@Test
	public void testSelectDemo4() {
		SessionFactory sessionFactory =  null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.setProjection(Projections.rowCount());
			Object object = criteria.uniqueResult();
			
			System.out.println(object);
			
			
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	//离线查询
		@Test
		public void testSelectDemo5() {
			SessionFactory sessionFactory =  null;
			Session session = null;
			Transaction transaction = null;
			
			try {
				sessionFactory = HibernateUtils.getSessionFactory();
				session = HibernateUtils.getSessionObject();
				transaction = session.beginTransaction();
				
				
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				
				List<Customer> list = criteria.list();
				
				for (Customer customer : list) {
					System.out.println(customer);
				}
				
				
				
				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				transaction.rollback();
			}finally {
				session.close();
				sessionFactory.close();
			}
			
		}
	
	
}
