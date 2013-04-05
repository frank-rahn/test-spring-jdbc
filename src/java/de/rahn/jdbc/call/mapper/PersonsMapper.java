package de.rahn.jdbc.call.mapper;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rahn.jdbc.call.entity.Person;

/**
 * Mapping zwischen einem {@link Array} und einer Liste von {@link Person}s.
 * @author Frank W. Rahn
 */
@Component
public class PersonsMapper extends SqlParameterMapper<Person[], Array> {

	/**
	 * Der Mapper für eine Peson.
	 */
	@Autowired
	private PersonMapper mapper;

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createObject(Object)
	 */
	@Override
	protected Person[] createObject(Array array) throws SQLException {
		Object[] values = (Object[]) array.getArray();

		if (values == null || values.length == 0) {
			return null;
		}

		Person[] persons = new Person[values.length];

		for (int i = 0; i < values.length; i++) {
			persons[i] = mapper.createObject((Struct) values[i]);
		}

		return persons;
	}

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createSqlValue(Connection, String, Object)
	 */
	@Override
	protected Array createSqlValue(Connection con, String typeName,
		Person[] persons) throws SQLException {

		Object[] values = new Object[persons.length];

		for (int i = 0; i < persons.length; i++) {
			values[i] = mapper.createSqlValue(con, "S_PERSON", persons[i]);
		}

		return con.createArrayOf(typeName, values);
	}

}