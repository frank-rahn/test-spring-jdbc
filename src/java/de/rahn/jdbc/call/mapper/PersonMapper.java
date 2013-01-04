package de.rahn.jdbc.call.mapper;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Date;

import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import org.springframework.stereotype.Component;

import de.rahn.jdbc.call.entity.Person;

/**
 * Mapping zwischen einer {@link Struct} und einem {@link Person}.
 * @author Frank W. Rahn
 */
@Component
public class PersonMapper extends SqlParameterMapper<Person> {

	/**
	 * {@inheritDoc}
	 * @see SqlReturnType#getTypeValue(CallableStatement, int, int, String)
	 */
	@Override
	public Object getTypeValue(CallableStatement cs, int paramIndex,
		int sqlType, String typeName) throws SQLException {
		Struct struct = cs.getObject(paramIndex, Struct.class);

		if (struct == null) {
			return null;
		}

		Object[] attributes = struct.getAttributes();

		Person person = new Person();
		person.setId((Long) attributes[0]);
		person.setName((String) attributes[1]);
		person.setSalary((BigDecimal) attributes[2]);
		person.setDateOfBirth((Date) attributes[3]);

		return person;
	}

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createSqlTypeValue(Object)
	 */
	@Override
	public SqlTypeValue createSqlTypeValue(final Person object) {
		return new AbstractSqlTypeValue() {

			/**
			 * @see AbstractSqlTypeValue#createTypeValue(Connection, int,
			 * String)
			 */
			@Override
			protected Object createTypeValue(Connection con, int sqlType,
				String typeName) throws SQLException {
				Object[] attributes = new Object[4];
				attributes[0] = object.getId();
				attributes[1] = object.getName();
				attributes[2] = object.getSalary();
				attributes[3] = object.getDateOfBirth();

				return con.createStruct(typeName, attributes);
			}
		};
	}

}