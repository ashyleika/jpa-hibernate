package com.infiniteskills.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.entities.Bank;
import com.infiniteskills.data.entities.Bond;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.Currency;
import com.infiniteskills.data.entities.Investment;
import com.infiniteskills.data.entities.Market;
import com.infiniteskills.data.entities.Portfolio;
import com.infiniteskills.data.entities.Stock;
import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.entities.User;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			configuration.addAnnotatedClass(Currency.class)
			.addAnnotatedClass(Market.class)
			.addAnnotatedClass(Account.class)
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Transaction.class)
			.addAnnotatedClass(Credential.class)
			.addAnnotatedClass(Stock.class)
			.addAnnotatedClass(Bond.class)
			.addAnnotatedClass(Portfolio.class)
			.addAnnotatedClass(Investment.class)
			.addAnnotatedClass(Bank.class);
			
			return configuration
					.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		} catch(Exception exc) {
			exc.printStackTrace();
			throw new RuntimeException("There was an error building the session factory");
		}
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
