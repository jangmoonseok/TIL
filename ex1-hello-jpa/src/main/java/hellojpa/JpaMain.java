package hellojpa;

import java.util.Date;

import hellojpa.entity.Book;
import hellojpa.entity.Item;
import hellojpa.entity.Member;
import hellojpa.entity.Order;
import hellojpa.entity.OrderItem;
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
        	//즉시로딩을 통해 OrderItem까지 한번에 조회
        	Order findOrder = em.find(Order.class, 1L);
        	//지연로딩을 통해 조회
        	System.out.println(findOrder.getMember().getName());
//        	System.out.println(findOrder.getOrderItems().get(0).getItem().getName());
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
