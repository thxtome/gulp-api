package gulp.rest.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlackToken {
	
	public BlackToken(String token) {
		this.token = token;
	}
	
	public BlackToken() {
	}

	@Id
	@Column(length = 512)
	private String token;
	
}
