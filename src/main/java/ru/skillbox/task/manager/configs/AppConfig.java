package ru.skillbox.task.manager.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secrets.properties")
public class AppConfig {
}
