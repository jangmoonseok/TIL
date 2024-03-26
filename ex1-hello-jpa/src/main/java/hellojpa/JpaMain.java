package hellojpa;

import java.util.Date;
import java.util.List;

import hellojpa.entity.Book;
import hellojpa.entity.EnterpriseInfo;
import hellojpa.entity.EnterpriseUser;
import hellojpa.entity.Item;
import hellojpa.entity.Member;
import hellojpa.entity.Order;
import hellojpa.entity.OrderItem;
import hellojpa.entity.OrderStatus;
import hellojpa.entity.Song;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
        	EnterpriseInfo enterpriseInfo = em.find(EnterpriseInfo.class, 1L);
        	System.out.println(enterpriseInfo.toString());
        	
//        	EnterpriseUser enterpriseUser = em.find(EnterpriseUser.class, 1L);
//        	System.out.println(enterpriseUser.toString());
        	
        	
        	
        	
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
