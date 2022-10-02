package jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
	
	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;
	
	@JsonIgnore	// 양방향 연관관계시 무한루프를 방지하기위해 제이슨이그노어 어노테이션을 설정한다
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; // READY,COMP
}
