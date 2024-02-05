package hellojpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Album {

	@Id
	@Column(name = "album_id")
	@GeneratedValue
	private Long albumId;
	
	@Column(name = "album_name")
	private String albumName;
	
	public Album(){}

	public Album(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName + "]";
	}
	
	
	
}
