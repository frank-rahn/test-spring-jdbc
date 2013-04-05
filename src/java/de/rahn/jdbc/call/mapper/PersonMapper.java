package de.rahn.jdbc.call.mapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Date;

import org.springframework.stereotype.Component;

import de.rahn.jdbc.call.entity.Person;

/**
 * Mapping zwischen einer {@link Struct} und einem {@link Person}.
 * @author Frank W. Rahn
 */
@Component
public class PersonMapper extends SqlParameterMapper<Person, Struct> {

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createObject(Object)
	 */
	@Override
	protected Person createObject(final Struct struct) throws SQLException {
		return new Person() {
			{
				Object[] attributes = struct.getAttributes();

				BigDecimal d = (BigDecimal) attributes[0];
				if (d != null) {
					setId(d.longValue());
				}

				setName((String) attributes[1]);
				setSalary((BigDecimal) attributes[2]);
				setDateOfBirth((Date) attributes[3]);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createSqlValue(Connection, String, Object)
	 */
	@Override
	protected Struct createSqlValue(Connection con, String typeName,
		Person person) throws SQLException {

		return con.createStruct(typeName,
			new Object[] { person.getId(), person.getName(),
				person.getSalary(), person.getDateOfBirth() });
	}

}