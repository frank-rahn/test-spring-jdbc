package de.rahn.jdbc;

import org.junit.runner.RunWith;
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

}
