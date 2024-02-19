package hellojpa;

import java.util.Date;

import hellojpa.entity.Member;
import hellojpa.entity.Order;
import hellojpa.entity.OrderStatus;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
        	
        	Member member = new Member();
        	member.setName("memberA");
        	member.setCity("Seoul");
        	member.setStreet("aaaa");
        	member.setZipcode("bbbb");
        	
        	Order order = new Order();
        	order.setMember(member);
        	order.setOrderDate(new Date());
        	order.setOrderStatus(OrderStatus.ORDER);
        	
        	em.persist(order);
        	
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
        emf.close();
    }
}
