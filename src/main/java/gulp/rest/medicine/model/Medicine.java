package gulp.rest.medicine.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gulp.rest.member.model.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medicine {
	
	@Id @GeneratedValue
	private Long medicineId;
	
	private String name;

	@ManyToOne(targetEntity=Member.class)
	@JoinColumn(name="member_id") // (2)
	private Member member;
	
	@ManyToOne(targetEntity=Brand.class)
	@JoinColumn(name="brand_id") // (2)
	private Brand brand;
	
	@ManyToOne(targetEntity=Category.class)
	@JoinColumn(name="category_id") // (2)
	private Category category;
	
}


