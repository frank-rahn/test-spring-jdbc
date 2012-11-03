package de.rahn.jdbc.call.mapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import org.springframework.stereotype.Component;

import de.rahn.jdbc.call.entity.User;

/**
 * Mapping zwischen einer {@link Struct} und einem {@link User}.
 * @author Frank W. Rahn
 */
@Component
public class UserMapper extends SqlParameterMapper<User> {

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

		User user = new User();
		user.setId((String) attributes[0]);
		user.setName((String) attributes[1]);
		user.setDepartment((String) attributes[2]);

		return user;
	}

	/**
	 * {@inheritDoc}
	 * @see SqlParameterMapper#createSqlTypeValue(Object)
	 */
	@Override
	public SqlTypeValue createSqlTypeValue(final User object) {
		return new AbstractSqlTypeValue() {

			/**
			 * @see AbstractSqlTypeValue#createTypeValue(Connection, int,
			 * String)
			 */
			@Override
			protected Object createTypeValue(Connection con, int sqlType,
				String typeName) throws SQLException {
				Object[] attributes = new Object[3];
				attributes[0] = object.getId();
				attributes[1] = object.getName();
				attributes[2] = object.getDepartment();

				return con.createStruct(typeName, attributes);
			}
		};
	}

}