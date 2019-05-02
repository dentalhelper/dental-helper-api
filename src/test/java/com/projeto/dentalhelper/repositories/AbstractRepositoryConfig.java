package com.projeto.dentalhelper.repositories;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.projeto.dentalhelper.config.S3Config;
import com.projeto.dentalhelper.config.property.DentalHelperApiProperty;

@DataJpaTest
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@EntityScan("com.projeto.dentalhelper.domains")   
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {S3Config.class, DentalHelperApiProperty.class})
public abstract class AbstractRepositoryConfig {

	@Rule
	public ExpectedException exceptedException = ExpectedException.none();

}
