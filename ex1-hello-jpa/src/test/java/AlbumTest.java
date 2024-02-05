import java.util.ArrayList;
import java.util.List;

import org.hibernate.event.spi.PersistContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import hellojpa.entity.Album;
import hellojpa.entity.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AlbumTest {

	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	private EntityTransaction tx;
	
	@Before
	public void before() {
		emf = Persistence.createEntityManagerFactory("hello");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	@Test
	@DisplayName("N:1 단방향 insert")
	public void addAlbumTest() {
		tx.begin();
		
		try {
			for(int i = 1; i <= 10; i++) {
				Album album = new Album("Album_" + i);
				em.persist(album);
				Song song1 = new Song("Song1_" + "Album_" + i);
				Song song2 = new Song("Song2_" + "Album_" + i);

				song1.setAlbum(album);
				song2.setAlbum(album);
				
				em.persist(song1);
				em.persist(song2);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("N:1 단방향 select")
	public void selectSongs() {
		tx.begin();
		try {
			Song song1 = em.find(Song.class, 1L);
			Song song2 = em.find(Song.class, 3L);
			
			System.out.println("song1.album = " + song1.getAlbum().toString());
			System.out.println("song2.album = " + song2.getAlbum().toString());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
}
