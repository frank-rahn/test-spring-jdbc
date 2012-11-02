package de.rahn.jdbc.call.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import de.rahn.jdbc.call.entity.Person;
import de.rahn.jdbc.call.entity.User;

/**
 * Das DAO für die Stored Procedure.
 * @author Frank W. Rahn
 */
@Repository
public class StandardSearchPersonsDAO implements SearchPersonsDAO {

	private DataSource dataSource;

	private SimpleJdbcCall jdbcCall;

	/**
	 * @param dataSource the dataSource to set
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Initialisiere das DAO.
	 */
	@PostConstruct
	public void initialize() {
		jdbcCall =
			new SimpleJdbcCall(dataSource).withFunctionName("searchPersons");

		jdbcCall.compile();
	}

	/**
	 * {@inheritDoc}
	 * @see SearchPersonsDAO#searchPersons(int, User)
	 */
	@Override
	public Person[] searchPersons(int num, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}