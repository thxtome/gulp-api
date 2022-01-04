package gulp.rest.alarm.repository;

import static gulp.rest.alarm.model.QAlarm.alarm;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gulp.rest.alarm.model.Alarm;
import gulp.rest.member.model.Member;

@Repository
public class AlarmRepositoryImpl implements CustomAlarmRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public AlarmRepositoryImpl(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public List<Alarm> findAllByMemberIdAndDayContains(Long memberId, String day) {

		JPQLQuery<Alarm> query = jpaQueryFactory.selectFrom(alarm);
		query.innerJoin(alarm.member)
				.on(alarm.member.eq(Member.builder().id(memberId).build()))
				.innerJoin(alarm.alarmMedicines).fetchJoin();

		if (StringUtils.hasText(day)) {
			query.where(alarm.day.contains(day));
		}
		
		return query.fetch();
	}

	@Override
	public Alarm findAllByIdAndMemberId(Long memberId, Long alarmId) {
		JPQLQuery<Alarm> query = jpaQueryFactory.selectFrom(alarm);
		return query.innerJoin(alarm.member)
				.on(alarm.member.eq(Member.builder().id(memberId).build()))
				.innerJoin(alarm.alarmMedicines)
				.fetchJoin()
				.where(alarm.id.eq(alarmId))
				.fetchOne();
	}
}
