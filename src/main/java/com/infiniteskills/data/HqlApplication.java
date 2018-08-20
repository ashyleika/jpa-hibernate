package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infiniteskills.data.entities.Transaction;

public class HqlApplication {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		try{
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);
			
			Root<Transaction> root = criteriaQuery.from(Transaction.class);
			
			Path<BigDecimal> amountPath = root.get("amount");
//			Path<String> transactionTypePath = root.get("transactionType");
			
			criteriaQuery.select(root).where(cb.and(cb.le(amountPath, 20)));
						
			
			TypedQuery<Transaction> query = session.createQuery(criteriaQuery);
			
			List<Transaction> transactions = query.getResultList();
			
			transactions.stream()
						.map(Transaction::getTitle)
						.forEach(System.out::println);
			
			
			tx.commit();
			System.out.println('a' > 'A');
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			scanner.close();
			session.close();
			factory.close();
		}
	}
}

