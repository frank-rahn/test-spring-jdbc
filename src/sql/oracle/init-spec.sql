CREATE OR REPLACE
PACKAGE Test
AS

	PROCEDURE searchPersons( -- Suche Personen
		p_num		IN	INTEGER,
		p_user		IN	s_User,
		p_persons	OUT	s_Person[]
	);

END Test;