package de.rahn.jdbc.call.mapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

/**
 * Ein abstracter Mapper, zwischen dem User Objekt und dem JDBC Datenbankobjekt
 * mappt.
 * @author Frank W. Rahn
 * @param <UserObject> das Userobjekt
 * @param <JdbcType> das JDBC-Datenbankobjekt (z.B. {@link Struct}...)
 */
public abstract class SqlParameterMapper<UserObject, JdbcType> implements
	SqlReturnType {

	/**
	 * {@inheritDoc}
	 * @see SqlReturnType#getTypeValue(CallableStatement, int, int, String)
	 */
	@Override
	public final Object getTypeValue(CallableStatement cs, int paramIndex,
		int sqlType, String typeName) throws SQLException {

		@SuppressWarnings("unchecked")
		final JdbcType jdbcType = (JdbcType) cs.getObject(paramIndex);

		if (jdbcType == null) {
			return null;
		}

		return createObject(jdbcType);
	}

	/**
	 * Konvertiere das JDBC-Datenbankobjekt in ein Userobjekt.
	 * @param jdbcType JDBC-Datenbankobjekt
	 * @return das neue Userobjekt
	 * @throws SQLException falls ein Fehler bei den Datenbankzugriffen auftritt
	 */
	protected abstract UserObject createObject(JdbcType jdbcType)
		throws SQLException;

	/**
	 * Erzeuge einen {@link SqlTypeValue} Objekt aus dem Userobjekt.
	 * @param userObject das Userobjekt
	 * @return das {@link SqlTypeValue} Objekt
	 */
	public final SqlTypeValue createSqlTypeValue(final UserObject userObject) {
		return new AbstractSqlTypeValue() {

			/**
			 * @see AbstractSqlTypeValue#createTypeValue(Connection, int,
			 * String)
			 */
			@Override
			protected final Object createTypeValue(Connection con, int sqlType,
				String typeName) throws SQLException {

				return createSqlValue(con, typeName, userObject);
			}
		};
	}

	/**
	 * Konvertiere das Userobjekt in ein JDBC-Datenbankobjekt.
	 * @param con die Datenbankverbindung
	 * @param typeName der JDBC-Typname
	 * @param userObject das Userobjekt
	 * @return das neue JDBC-Datenbankobjekt
	 * @throws SQLException falls ein Fehler bei den Datenbankzugriffen auftritt
	 */
	protected abstract JdbcType createSqlValue(Connection con, String typeName,
		UserObject userObject) throws SQLException;

}