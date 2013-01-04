package de.rahn.jdbc;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ConnectionCallback;
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

	@Value("${jdbc.testquery}")
	private String query;

	/**
	 * Teste die Datenbankverbindung mit einer Testabfrage.
	 */
	@Test
	public void testDatabaseConnectionPerQuery() {
		int result = template.queryForInt(query);

		assertThat("Testquery '" + query + "' fehlgeschlagen", result, is(1));
	}

	/**
	 * Teste die Datenbankverbindung über die JDBC-API.
	 */
	@Test
	public void testDatabaseConnectionPerApi() {
		Object result = template.execute(new ConnectionCallback<Object>() {

			@Override
			public Object doInConnection(Connection con) throws SQLException {
				return con.createStruct("s_User", null);
			}

		});

		assertThat("Die Datenbankaktion lieferte keine Struktur", result,
			instanceOf(Struct.class));
	}

}