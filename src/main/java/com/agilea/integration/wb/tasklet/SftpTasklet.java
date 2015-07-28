package com.agilea.integration.wb.tasklet;

/**
 * Created by brucechristie on 15-07-08.
 */
import java.io.File;


import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class SftpTasklet implements Tasklet {
    private String fileName;
    private MessageChannel sftpChannel;

    private final Logger log = Logger.getLogger(SftpTasklet.class);
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        File file = new File(fileName);

        if (file.exists()) {
            Message<File> message = MessageBuilder.withPayload(file).build();
            try {
                sftpChannel.send(message);
            } catch (Exception e) {
                log.error("Could not send file per SFTP: " + e);
                throw e;

            }
        } else {
            log.warn("File does not exist.");
        }
        return RepeatStatus.FINISHED;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MessageChannel getSftpChannel() {
        return sftpChannel;
    }

    public void setSftpChannel(MessageChannel sftpChannel) {
        this.sftpChannel = sftpChannel;
    }
}
