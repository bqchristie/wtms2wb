package com.agilea.integration.wb;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {

	private static String[] ARGS;

	private static final String JOB_LAUNCHER = "jobLauncher";
	private static final String SPRING_APP_CONTEXT_XML = "spring-application-context.xml";
	public static final String ADP = "adp";


	public static void main(String args[]) {

		ARGS = args;

		String testJobName = System.getProperty( "job", "adp" );
		System.out.println(testJobName);

		String jobName = "adp";

		Logger log  = Logger.getLogger(Main.class);

		ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_APP_CONTEXT_XML);

		JobLauncher jobLauncher = (JobLauncher) context.getBean(JOB_LAUNCHER);
		Job job = (Job) context.getBean(jobName);

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			log.debug("Job Exit Status : " + execution.getStatus());

		} catch (JobExecutionException e) {
			log.error("Job BalanceRecord failed");
			log.error(e.getMessage());
		}
	}

}
