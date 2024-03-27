package hellojpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_member")
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "member_name", unique = true)
	private String name;
	
	@Embedded
	private Address homeAddress;
	
	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Locker locker;

	public Member() {}
	
	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Locker getLocker() {
		return locker;
	}

	public void setLocker(Locker locker) {
		this.locker = locker;
		locker.setMember(this);
	}



	
	
	
	
	
	
	
}
