package gulp.rest.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gulp.rest.member.model.Member;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {

	Member findByEmailAndPassword(String email, String password);
	
	Member findByEmail(String email);
}
