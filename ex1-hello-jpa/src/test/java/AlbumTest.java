import java.util.ArrayList;
import java.util.List;

import org.hibernate.event.spi.PersistContext;
import org.junit.After;
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
	@DisplayName("N:1 연관관계 등록")
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
	@DisplayName("N:1 연관관계 조회")
	public void selectSongs() {
		tx.begin();
		try {
			Song song1 = em.find(Song.class, 1L);
			Song song2 = em.find(Song.class, 3L);
			System.out.println("=====================================");
			System.out.println("song1.albumName = " + song1.getAlbum().getAlbumName());
			System.out.println("song2.albumName = " + song2.getAlbum().getAlbumName());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("1:N 연관관계 조회")
	public void selectAlbum() {
		tx.begin();
		try {
			Album album = em.find(Album.class, 1L);
			
			List<Song> songs = album.getSongs();
			
			for(Song song : songs) {
				System.out.println(song.getSongId());
				System.out.println(song.getSongName());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		em.clear();
		emf.close();
	}
}
