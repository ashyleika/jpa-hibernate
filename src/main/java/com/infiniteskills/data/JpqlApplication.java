package com.infiniteskills.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.infiniteskills.data.entities.Transaction;

public class JpqlApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			factory = Persistence.createEntityManagerFactory("persistence");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);
			
			Root<Transaction> r = criteriaQuery.from(Transaction.class);
			criteriaQuery.select(r);
			
			TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
			
			List<Transaction> transactions = query.getResultList();
			
			transactions.stream()
						.map(Transaction::getTitle)
						.forEach(System.out::println);
			
			
	
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			em.close();
			factory.close();
		}
	}
}
