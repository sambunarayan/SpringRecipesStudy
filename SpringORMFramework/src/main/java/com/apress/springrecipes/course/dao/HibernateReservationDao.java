package com.apress.springrecipes.course.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import com.apress.springrecipes.course.Reservation;

public class HibernateReservationDao implements ReservationDao {

	private final SessionFactory sessionFactory;
	
	public HibernateReservationDao() {
		Configuration configuration = new Configuration()
				.setProperty(AvailableSettings.URL, "jdbc:oracle:thin:@localhost:1521:XE")
				.setProperty(AvailableSettings.USER, "recipes")
				.setProperty(AvailableSettings.PASS, "recipes")
//				.setProperty(AvailableSettings.DIALECT, "oracle.jdbc.driver.OracleDriver")
				.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true))
				.setProperty(AvailableSettings.HBM2DDL_AUTO, "update")
				.addAnnotatedClass(Reservation.class);
		sessionFactory = configuration.buildSessionFactory();
	}

	@Override
	public Reservation book(Reservation reserv) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(reserv);
			tx.commit();
			return reserv;
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Long reservId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			Reservation reserv = session.get(Reservation.class, reservId);
			session.delete(reserv);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public Reservation findById(Long reservId) {
		Session session = sessionFactory.openSession();
		try {
			return session.get(Reservation.class, reservId);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reservation> findAll() {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("SELECT c FROM RESERVATION c ", Reservation.class).list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
