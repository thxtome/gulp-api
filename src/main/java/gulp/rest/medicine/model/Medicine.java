package gulp.rest.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gulp.rest.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
	
	@Id @GeneratedValue
	@Column(name = "medicine_id")
	private Long id;
	
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
	
	public Medicine create(String name, Long categoryId, Long brandId) {
		this.name = name;
		this.brand = Brand.builder().id(brandId).build();
		this.category = Category.builder().id(categoryId).build();
		return this;
	}
	
}


