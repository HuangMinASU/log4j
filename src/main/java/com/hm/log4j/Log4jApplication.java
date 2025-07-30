package com.hm.log4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Log4jApplication {

    // 定义一个Log4j 2的Logger对象
    private static final Logger logger = LogManager.getLogger(Log4jApplication.class);

    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(Log4jApplication.class, args);

        // 记录不同级别的日志
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.warn("Warn log message");
        logger.error("Error log message");
        logger.fatal("Fatal log message");
    }

	    // 定义一个定时任务方法
    @Scheduled(fixedRate = 5000)  // 每5000毫秒(5秒)执行一次
    public void printScheduledMessage() {
        logger.info("Scheduled task execution - printing log message");
    }

	    // 定时将信息写入文件的任务
    @Scheduled(fixedRate = 10000)  // 每10000毫秒（10秒）执行一次
    public void runFilePrintTask() {
        String message = "Current time: " + LocalDateTime.now();
        try (FileWriter writer = new FileWriter("run_output.txt", true)) {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            logger.error("Error writing to file", e);
        }
        logger.info("Wrote to file: " + message);
    }
}
