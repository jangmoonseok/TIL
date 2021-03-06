
CREATE TABLE STOCK (
	STOCK_NO VARCHAR2(100) NOT NULL,
	STOCK_CLASS VARCHAR2(100) NULL,
	STOCK_NAME VARCHAR2(100) NULL,
	CUR_PRICE NUMBER(38) NULL,
	START_PRICE NUMBER(38) NULL,
	BEF_PRICE NUMBER(38) NULL,
	COMPARE NUMBER(38) NULL,
	HIGH_PRICE NUMBER(38) NULL,
	LOW_PRICE NUMBER(38) NULL,
	TOTAL_VOL NUMBER(38) NULL,
	TOTAL_PRICE NUMBER(38) NULL,
	MARKET_TOTAL VARCHAR2(100) NULL
);

CREATE TABLE KOSPY (
	STOCK_NO VARCHAR2(100) NOT NULL,
	STOCK_NAME VARCHAR2(100) NOT NULL
);

-- 코스피
ALTER TABLE KOSPY
	ADD CONSTRAINT PK_KOSPY -- 코스피 기본키
	PRIMARY KEY (
	STOCK_NO -- 종목코드
	);
    
    ALTER TABLE STOCK
	ADD CONSTRAINT PK_STOCK -- 주식 기본키
	PRIMARY KEY (
	STOCK_NO -- 종목코드
	);
DROP TRIGGER TG_STOCK_KOSPY;

CREATE OR REPLACE TRIGGER TG_KOSPY_INSERT
    AFTER INSERT ON STOCK
    FOR EACH ROW
    WHEN (NEW.STOCK_CLASS = '코스피')
BEGIN
    INSERT INTO KOSPY
           VALUES(:NEW.STOCK_NO, :NEW.STOCK_NAME);
END;

CREATE OR REPLACE TRIGGER TG_KOSDAQ_INSERT
    AFTER INSERT ON STOCK
    FOR EACH ROW
    WHEN (NEW.STOCK_CLASS = '코스닥')
BEGIN
    INSERT INTO KOSDAQ
           VALUES(:NEW.STOCK_NO, :NEW.STOCK_NAME);
END;

DROP TRIGGER TG_STOCK_KOSPY;