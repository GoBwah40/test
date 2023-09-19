package simplon.ebrairie.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("simplon.ebrairie.domain")
@EnableJpaRepositories("simplon.ebrairie.repos")
@EnableTransactionManagement
public class DomainConfig {
}
