package cn.itcast.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;

import cn.itcast.entity.Customer;
import cn.itcast.uitls.HibernateUtils;

public class HibernateManyTable {
	// hql内连接查询
	@Test
	public void testSelectDemo1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;

		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = HibernateUtils.getSessionObject();
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Customer c inner join c.contacts");

			List list = query.list();
			

			System.out.println(list.toString());

			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}

	}
	
	// hql迫切内连接查询
		@Test
		public void testSelectDemo2() {
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction transaction = null;

			try {
				sessionFactory = HibernateUtils.getSessionFactory();
				session = HibernateUtils.getSessionObject();
				transaction = session.beginTransaction();

				Query query = session.createQuery("from Customer c inner join fetch c.contacts");

				List list = query.list();
				
				System.out.println(list.toString());

				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				transaction.rollback();
			} finally {
				session.close();
				sessionFactory.close();
			}

		}
		
		
		// hql左外连接查询
		@Test
		public void testSelectDemo3() {
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction transaction = null;

			try {
				sessionFactory = HibernateUtils.getSessionFactory();
				session = HibernateUtils.getSessionObject();
				transaction = session.beginTransaction();

				Query query = session.createQuery("from Customer c left outer join c.contacts");

				List list = query.list();
				
				System.out.println(list.toString());

				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				transaction.rollback();
			} finally {
				session.close();
				sessionFactory.close();
			}

		}
		
		// hql迫切左外连接查询
		@Test
		public void testSelectDemo4() {
			SessionFactory sessionFactory = null;
			Session session = null;
			Transaction transaction = null;

			try {
				sessionFactory = HibernateUtils.getSessionFactory();
				session = HibernateUtils.getSessionObject();
				transaction = session.beginTransaction();

				Query query = session.createQuery("from Customer c left outer join fetch c.contacts");

				List list = query.list();
				
				System.out.println(list.toString());

				transaction.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				transaction.rollback();
			} finally {
				session.close();
				sessionFactory.close();
			}

		}
		
		// hql右外连接查询
				@Test
				public void testSelectDemo5() {
					SessionFactory sessionFactory = null;
					Session session = null;
					Transaction transaction = null;

					try {
						sessionFactory = HibernateUtils.getSessionFactory();
						session = HibernateUtils.getSessionObject();
						transaction = session.beginTransaction();

						Query query = session.createQuery("from Customer c right outer join c.contacts");

						List list = query.list();
						
						System.out.println(list.toString());

						transaction.commit();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						transaction.rollback();
					} finally {
						session.close();
						sessionFactory.close();
					}

				}
				
				//注意： hql没有迫切右外连接
				
		
}
