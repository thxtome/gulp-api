package gulp.rest.alarm.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static gulp.rest.alarm.model.QAlarm.alarm;
import static gulp.rest.alarm.model.QAlarmHist.alarmHist;
import static gulp.rest.alarm.model.QAlarmHistMedicine.alarmHistMedicine;
import static gulp.rest.alarm.model.QAlarmMedicine.alarmMedicine;
import static gulp.rest.medicine.model.QMedicine.medicine;
import static gulp.rest.member.model.QMember.member;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gulp.rest.alarm.dto.AlarmHistDto;
import gulp.rest.alarm.dto.QAlarmHistDto;
import gulp.rest.alarm.model.AlarmHist;
import gulp.rest.medicine.dto.QMedicineDto;

@Repository
public class AlarmHistRepositoryImpl implements CustomAlarmHistRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public AlarmHistRepositoryImpl(EntityManager entityManager) {
		this.jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<AlarmHistDto> findByAlarmIdAndCreatedAt(Long memberId, String searchDay) {
		JPQLQuery<AlarmHist> query = jpaQueryFactory.selectFrom(alarmHist);
		
		query
			.innerJoin(alarm)
			.on(alarm.eq(alarmHist.alarm))
			.innerJoin(member)
			.on(alarm.member.id.eq(memberId))
			.leftJoin(alarmHistMedicine)
			.on(alarmHistMedicine.in(alarmHist.alarmHistMedicines))			
			.leftJoin(medicine)
			.on(alarmHistMedicine.medicine.eq(medicine))
			.where(Expressions.stringTemplate("DATE_FORMAT({0}, {1})", alarmHist.createdAt, "%Y%m%d").eq(searchDay));
			
		List<AlarmHistDto> list = query.transform(
			groupBy(alarm.id)
				.list(
					new QAlarmHistDto(alarmHist.id, alarmHist.time, alarm.day
						, list(new QMedicineDto(medicine.id, medicine.name))
					)
				)
		);
		

		
		return list;
	}
	
	

}
