drop table STUDENT if exists;

CREATE TABLE STUDENT (
    NO VARCHAR(10) NOT NULL PRIMARY KEY,
    NAME VARCHAR(10) NOT NULL,
    ENT_YEAR INTEGER,
    CLASS_NUM CHAR(3),
    IS_ATTEND BOOLEAN,
    SCHOOL_CD CHAR(3)
);

INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD)
VALUES ('2231111', '太原太郎', 2023, '131', TRUE, 'oom');