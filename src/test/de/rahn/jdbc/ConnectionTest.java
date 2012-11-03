package de.rahn.jdbc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * Teste die Datenbankverbindung.
	 */
	@Test
	public void testDatabaseConnection() {
		int result = template.queryForInt("SELECT 1");

		assertThat("Testquery 'SELECT 1' fehlgeschlagen", result, is(1));
	}

}