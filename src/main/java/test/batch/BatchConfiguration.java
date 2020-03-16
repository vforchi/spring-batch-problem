package test.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Override
    public JobLauncher getJobLauncher() {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

    @Bean
    public Job myJob(Step myStep) {
        return jobBuilderFactory.get("myJob")
//                .incrementer(new RunIdIncrementer())
                .start(myStep)
                .build();
    }

    @Bean
    public Step myStep(Tasklet myServiceTasklet) {
        return this.stepBuilderFactory.get("myStep")
                .listener(myServiceTasklet)
                .tasklet(myServiceTasklet)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet myServiceTasklet(
             @Value("#{jobParameters['param']}") String param
    ) {
        SystemCommandTasklet tasklet = new SystemCommandTasklet();
        System.out.println(param);
        tasklet.setCommand("sleep 10");
        tasklet.setTimeout(20000);
        return tasklet;
    }

}
