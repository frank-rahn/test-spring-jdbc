package de.rahn.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.jdbc4.Jdbc4Connection;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Dieser Test testet den Verbindungsaufbau zur PostgreSQL-Datenbank.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("connectionTest.xml")
@ActiveProfiles("PostgreSQL")
public class PostgreSQLConnectionTest extends AbstractConnectionTest {

	// Spezielle Tests nur für PostgreSQL

	/**
	 * Im Driver ist die Methode
	 * {@link Jdbc4Connection#createStruct(String, Object[])} noch nicht
	 * implementiert.
	 * @see de.rahn.jdbc.AbstractConnectionTest#testDatabaseConnectionPerApi()
	 */
	@Override
	@Test(expected = UncategorizedSQLException.class)
	public void testDatabaseConnectionPerApi() {
		super.testDatabaseConnectionPerApi();
	}

}
