package de.rahn.jdbc.call.dao;

import static java.sql.Types.ARRAY;
import static java.sql.Types.STRUCT;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import de.rahn.jdbc.call.entity.Person;
import de.rahn.jdbc.call.entity.User;
import de.rahn.jdbc.call.mapper.PersonsMapper;
import de.rahn.jdbc.call.mapper.UserMapper;

/**
 * Das DAO für die Stored Procedure.
 * @author Frank W. Rahn
 */
@Repository
public class StandardSearchPersonsDAO implements SearchPersonsDAO {

	private DataSource dataSource;

	private SimpleJdbcCall jdbcCall;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PersonsMapper personsMapper;

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
			new SimpleJdbcCall(dataSource).withProcedureName("searchPersons")
				.declareParameters(
					new SqlParameter("p_user", STRUCT, "S_USER"),
					new SqlOutParameter("p_persons", ARRAY, "A_PERSON",
						personsMapper));

		jdbcCall.compile();
	}

	/**
	 * {@inheritDoc}
	 * @see SearchPersonsDAO#searchPersons(int, User)
	 */
	@Override
	public Person[] searchPersons(int num, User user) {
		Map<String, Object> in = new HashMap<>();
		in.put("p_num", num);
		in.put("p_user", userMapper.createSqlTypeValue(user));

		Map<String, Object> out = jdbcCall.execute(in);

		return (Person[]) out.get("p_persons");
	}

}