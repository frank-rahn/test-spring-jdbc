CREATE OR REPLACE
PACKAGE BODY Test
AS

	PROCEDURE searchPersons( -- Suche Personen
		p_num		IN	INTEGER,
		p_user		IN	s_User,
		p_persons	OUT	_s_Person
	) IS
		i			INTEGER;
		p			s_Person;
		dateOfBirth	DATE := CURRENT_DATE;
	BEGIN -- Ausführungsteil
		DBMS_OUTPUT.PUT_LINE('Die Stored Procedure searchPersons wurde aufgerufen. User=:'||:p_user);

		p_persons := _s_Person();

		FOR i IN 1..p_num
		LOOP -- Fake der Suche
			p := s_Person(
				i,						-- id
				p_user.name,			-- name
				dateOfBirth,			-- dateOfBrith
				ACOS(-1.0) * i * 100.0	-- Salary
			);
			p_persons.extend();
			p_persons(p_persons.LAST) := p;
		END LOOP;
	END searchPersons;

END Test;