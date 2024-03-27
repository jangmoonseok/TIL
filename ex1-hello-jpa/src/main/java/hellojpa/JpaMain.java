package hellojpa;

import java.util.Date;
import java.util.List;

import hellojpa.entity.Book;
import hellojpa.entity.EnterpriseInfo;
import hellojpa.entity.EnterpriseUser;
import hellojpa.entity.Item;
import hellojpa.entity.Locker;
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
//        	Locker locker = new Locker();
//        	locker.setName("라커A");
//        	
//        	Member member = new Member();
//        	member.setName("회원A");
//        	member.setLocker(locker);
//        	
//        	em.persist(member);
        	
        	// pk를 외래키로 일대일 양방향 매핑시
        	System.out.println("=====find Locker start=====");
        	Locker locker = em.find(Locker.class, 1L);
        	Member member = locker.getMember();
        	System.out.println(member.getClass());
        	System.out.println("=====find Locker end=====");
        	System.out.println("=====find member start=====");
        	System.out.println("memberName : " + member.getName());
        	System.out.println("=====find member end=====");
        	
        	
//        	System.out.println("=====find Locker start=====");
//        	Locker locker = em.find(Locker.class, 1L);
//        	System.out.println("=====find Locker end=====");
//        	Member member = locker.getMember();
//        	System.out.println("memberName : " + member.getName());
//        	System.out.println("=====find member end=====");
        	
        	
        	
        	
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
