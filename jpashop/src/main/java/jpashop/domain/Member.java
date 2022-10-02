package jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    
	@NotEmpty
	private String name;
    
	@Embedded
    private Address address;
	
	@JsonIgnore // 양방향 연관관계시 무한루프를 방지하기위해 제이슨이그노어 어노테이션을 설정한다
	@OneToMany( mappedBy ="member")
	private List<Order> orders = new ArrayList<>();
}
