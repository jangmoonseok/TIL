package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Song {

	@Id
	@Column(name = "song_id")
	@GeneratedValue
	private Long songId;
	
	@Column(name = "song_name")
	private String songName;
	
	@ManyToOne
	@JoinColumn(name="album_id", nullable = false)
	private Album album;
	
	public Song() {}

	public Song(String songName) {
		this.songName = songName;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	
	
	
}
