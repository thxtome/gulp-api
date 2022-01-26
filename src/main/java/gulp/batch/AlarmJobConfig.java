package gulp.batch;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gulp.rest.alarm.model.Alarm;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AlarmJobConfig {
	
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
	
    private final EntityManagerFactory entityManagerFactory;
    
    @Bean
    public Job alarmUpdateJob() {
        return jobBuilderFactory.get("alarmUpdateJob")
                .preventRestart() 
                .start(alarmUpdateJobStep()) 
                .build();
    }
    
    @Bean(destroyMethod="")
    public JpaPagingItemReader<Alarm> alarmUpdateJpaReader() {
        JpaPagingItemReader<Alarm> jpaPagingItemReader = new JpaPagingItemReader<>();
        jpaPagingItemReader.setQueryString("select a from Alarm a join fetch a.alarmMedicines where a.isRemoved = false");
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        jpaPagingItemReader.setPageSize(10); 
        return jpaPagingItemReader;
    }
    
    @Bean  
    public JpaItemWriter<Alarm> alarmUpdateJpaWriter() {
        JpaItemWriter<Alarm> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
    
    @Bean   
    public ItemProcessor<Alarm, Alarm> alarmUpdateProcessor() {
        return alarm -> alarm.reset();
    }
    
    @Bean
    public Step alarmUpdateJobStep() {
        return stepBuilderFactory.get("alarmUpdateJobStep") 
                .<Alarm, Alarm> chunk(10) 
                .reader(alarmUpdateJpaReader())
                .processor(alarmUpdateProcessor())
                .writer(alarmUpdateJpaWriter())
                .build();
    }
    
    
    
}