package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_locker")
public class Locker {

	@Id
	@GeneratedValue
	@Column(name = "locker_id")
	private Long id;
	
	@Column(name = "locker_name")
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_name", referencedColumnName = "member_name")
	private Member member;

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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
