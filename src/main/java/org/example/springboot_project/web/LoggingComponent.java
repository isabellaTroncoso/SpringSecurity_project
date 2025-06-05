package org.example.springboot_project.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class LoggingComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoggingComponent.class);

    public void log(String message) {
        logger.info(message);
    }
}
