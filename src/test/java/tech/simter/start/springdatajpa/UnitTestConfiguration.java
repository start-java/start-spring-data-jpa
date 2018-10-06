package tech.simter.start.springdatajpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author RJ
 */
@Configuration
@EnableJpaRepositories
@EntityScan
public class UnitTestConfiguration {
}