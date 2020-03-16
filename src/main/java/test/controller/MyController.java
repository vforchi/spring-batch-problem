package test.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    final JobLauncher jobLauncher;

    final Job job;

    public MyController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("test")
    ResponseEntity<?> submitJob() throws JobExecutionException, InterruptedException {
        var params = new JobParametersBuilder().addString("my_param", "value1");
        var jobExecution = jobLauncher.run(job, params.toJobParameters());
        while (jobExecution.getEndTime() == null)
            Thread.sleep(1000);
        return ResponseEntity.ok("OK");
    }

}
