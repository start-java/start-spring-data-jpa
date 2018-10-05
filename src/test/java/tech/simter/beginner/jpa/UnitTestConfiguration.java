package tech.simter.beginner.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author RJ
 */
@Configuration
@EnableJpaRepositories("tech.simter.beginner.jpa.repository")
@EntityScan("tech.simter.beginner.jpa.po")
public class UnitTestConfiguration {
}