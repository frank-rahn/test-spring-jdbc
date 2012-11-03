package de.rahn.jdbc.call.mapper;

import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.SqlTypeValue;

/**
 * Ein abstracter Mapper.
 * @author Frank W. Rahn
 */
public abstract class SqlParameterMapper<UserObject> implements SqlReturnType {

	public abstract SqlTypeValue createSqlTypeValue(UserObject object);

}