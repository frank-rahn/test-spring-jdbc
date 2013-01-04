package de.rahn.jdbc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Dieser Test testet den Verbindungsaufbau zur Datenbank.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ConnectionTest {

	@Autowired
	private JdbcTemplate template;

	@Value("#{jdbc.testquery}")
	private String query;

	/**
	 * Teste die Datenbankverbindung.
	 */
	@Test
	public void testDatabaseConnection() {
		int result = template.queryForInt(query);

		assertThat("Testquery '" + query + "' fehlgeschlagen", result, is(1));
	}

}