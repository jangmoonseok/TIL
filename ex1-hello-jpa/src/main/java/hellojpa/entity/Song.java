package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.LAZY) //Song임장에서  Album과 관계가 N:1 
	@JoinColumn(name="album_id", nullable = false) //nullable = false 는 연관관계 조회시 inner join
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
		//테이블 입장에서는 필요없지만 순수 객체 상태를 고려한 연관관계 편의를 위해 추가
		album.getSongs().add(this);
	}

	public Long getSongId() {
		return songId;
	}

	public String getSongName() {
		return songName;
	}
	
	
}
