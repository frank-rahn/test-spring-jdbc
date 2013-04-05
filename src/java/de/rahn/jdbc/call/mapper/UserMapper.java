package de.rahn.jdbc.call.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.springframework.stereotype.Component;

import de.rahn.jdbc.call.entity.User;

/**
 * Mapping zwischen einer {@link Struct} und einem {@link User}.
 * @author Frank W. Rahn
 */
@Component
public class UserMapper extends SqlParameterMapper<User, Struct> {

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createObject(Object)
	 */
	@Override
	protected User createObject(final Struct struct) throws SQLException {
		return new User() {
			{
				Object[] attributes = struct.getAttributes();
				setId((String) attributes[0]);
				setName((String) attributes[1]);
				setDepartment((String) attributes[2]);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createSqlValue(Connection, Object)
	 */
	@Override
	protected Struct createSqlValue(Connection con, String typeName, User user)
		throws SQLException {

		return con
			.createStruct(typeName, new Object[] { user.getId(),
				user.getName(), user.getDepartment() });
	}

}