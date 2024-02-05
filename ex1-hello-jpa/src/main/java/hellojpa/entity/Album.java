package hellojpa.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@Column(name = "album_id")
	@GeneratedValue
	private Long albumId;
	
	@Column(name = "album_name")
	private String albumName;
	
	//Album -> Song 방향으로 연관관계 추가
	@OneToMany(mappedBy = "album", fetch = FetchType.EAGER) //Album입장에서 Song과 관계는 1:N, Song의 멤버 변수 album에 의해 매핑된다는 의미(연관관계의 주인은 Song)
	private List<Song> songs = new ArrayList<>(); // null방지 초기화
	
	public Album(){}

	public Album(String albumName) {
		this.albumName = albumName;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public String getAlbumName() {
		return albumName;
	}
	
	
	
	
	
}
