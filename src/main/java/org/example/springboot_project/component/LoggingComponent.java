package org.example.springboot_project.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// den här klassen är en komponent kan användas av andra klasser för att logga händelser
@Component
public class LoggingComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoggingComponent.class);

    // loggar på info-nivå med meddelande
    public void log(String message) {
        logger.info(message);
    }
}
