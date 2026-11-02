-- logic as it exists so far
DROP PROCEDURE IF EXISTS wolfecj1.factorial;

CREATE PROCEDURE wolfecj1.factorial(INOUT whatis INT)
	@whatis int,
	@x int
BEGIN
    -- DECLARE x INT;
	SET @whatis = whatis;

    IF @whatis = 0 THEN
        SET @whatis = 1;
    ELSEIF @whatis > 1 THEN
        SET x = @whatis - 1;

        WHILE x > 1 DO
            SET @whatis = @whatis * x;
            SET @x = @x - 1;
        END WHILE;
    END IF;
END //

DELIMITER ;
