DROP TYPE IF EXISTS s_User FORCE;
DROP TYPE IF EXISTS s_Person FORCE;
DROP TYPE IF EXISTS a_Person FORCE;

CREATE TYPE s_User AS OBJECT ( -- Der aktuelle Benutzer
	id		CHAR(10),
	name	VARCHAR(30),
	dept	VARCHAR
);

CREATE TYPE s_Person AS OBJECT ( -- Eine Person
	id			BIGINT,
	name		VARCHAR(30),
	salary		DECIMAL,
	dateOfBirth	DATE
);

CREATE TYPE a_Person AS TABLE OF s_Person; -- Collection der Person
