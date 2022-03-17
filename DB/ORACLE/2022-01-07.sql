--2022-0107) - 01
DML(Data Mainpulation Language)
1.데이터 삽입
 -insert into 명령으로 자료 삽입
 (사용형식)
 INSERT INTO 테이블명[(컬럼명[,컬럼명,...])]
 VALUES(값[,값,...]);
 '테이블명' : 데이터 사이 대상 테이블명
 '[(컬럼명',컬럼명,...])]':삽입할 컬럼을 선택할 경우 기술
 '[(컬럼명',컬럼명,...])]'이 생략되면 모든 컬럼에 기술된 순서에 맞게 자료를 VALUES절에 기술해야함
  테이블 생성시 컬럼에 NOT NULL제약조건이 기술된 경우
  '[(컬럼명',컬럼명,...])]'에서 생략 불가능 함
  '[(컬럼명',컬럼명,...])]'와 VALUES절의 값의 갯수, 타입은 일치 해야함
  
  사용 예)테이블 GOODS에 다음 자료를 삽입하시오.
  [자료]
  --------------------------------------
  상품코드       상품명        단가
  --------------------------------------
  P101         신라면          1000
  P102         안성탕면         1200
  P103         게토레이         800
  P104         삼다수          500
  ---------------------------------------
  INSERT INTO GOODS VALUES('P101','신라면',1000);
  INSERT INTO GOODS(GOOD_ID,GOOD_NAME) VALUES('P102','안성탕면');
  INSERT INTO GOODS(GOOD_ID) VALUES('P103');
  
  
  SELECT * FROM GOODS;
  
  
  
  2.데이터 수정
  존재하는 데이터에 대한 값을 수정
  UPDATE문을 사용
  (사용형식)
  UPDATE 테이블명
    SET   컬럼명=값[,
          컬럼명=값,
            :
          컬럼명=값]
          [WHERE 조건];
          '테이블명':수정할 대상자료를 저장한 테이블
          'WHERE 조건' : 생략되면 해당 테이블의 모든 행을 수정하며, 조건을 기술한 경우 조건을 만족하는 행(들)만 수정
  
  
  
  사용 예) 신라면의 가격을 1000으로 수정하시오
  UPDATE GOODS
  SET G_PRICE=1000;
  WHERE GOOD_ID='P101';
  
  SELECT * FROM GOODS;
  
  'P103'자료의 제품명을 '게토레이', 단가를 '800'으로 수정하시오
  UPDATE GOODS
  SET GOOD_NAME='게토레이';
      G_PRICE=800;
  WHERE GOOD_ID='P103';
  
   SELECT * FROM GOODS;
   
   COMMIT;
  
3. DELETE 문
 - 테이블내의 자료를 삭제할 때 사용
 - ROLLBACK의 대상
 (사용형식)
 DELETE FROM 테이블명
 [WHERE 조건]; --생략시에 전체를 말한다.
 . WHERE 절이 생략되면 모든 자료를 삭제
 
사용예) 테이블 GOODS의 모든 자료를 삭제
    DELETE FROM GOODS;
    SELECT * FROM GOODS;
    ROLLBACK
    SELECT * FROM GOODS;
    COMMIT; 
    
    --테이블에 있는 자료 중 상품코드가 'P102'보다 큰 자료를 삭제하시오.
    DELETE FROM GOODS
     WHERE GOOD_ID >='P102'; --문자와 숫자로 이루어진 문자열도 비교할 수 있다. 사전 순서로 비교가 된다.
     
     
데이터 검색명령 (SELECT)

** 객체 삭제 (DROP)
    . 테이블 삭제 
    DROP TABLE 테이블명;
    
사용 예) 쇼핑몰 테이블에서 (BUYER, BUYPROD, CART, LPROD, MEMBER)을 제외한 모든 테이블 삭제
    DROP TABLE ORDER_GOODS;
    DROP TABLE ORDERS;
    DROP TABLE CUSTOMERS;
    DROP TABLE GOODS;
    
    DROP TABLE TEMP_01;
    DROP TABLE TEMP_02;
    DROP TABLE TEMP_03;
    DROP TABLE TEMP_04;
    DROP TABLE TEMP_05;
    DROP TABLE TEMP_06;
    DROP TABLE TEMP_07;
    DROP TABLE TEMP_08;
    DROP TABLE TEMP_09;
    
** HR계정 활성화
1. HR계정 잠금해제
    ALTER USER HR ACCOUNT UNLOCK;

2. HR계정 암호설정
    ALTER USER HR IDENTIFIED BY java;

--2022-0107) - 02
** 데이터 검색 명령
    - SELECT 문이 제공
    - SQL명령 중 가장 많이 나용되는 명령
    (사용형식) --코드 실행 순서: WHERE - FROM - SELECT 순서로 진행
    SELECT * |[DISTINCT]컬럼명 [AS 컬럼별칭][,] --DISTINCT중목된 데이터를 제외시킨다. //컬럼별칭: SERVE쿼리에서 참조하기 위한 
            컬럼명 [AS 컬럼별칭][,]
                :
            컬럼명 [AS 컬럼별칭][,]
       FROM 테이블명
     [WHERE 조건 --컬럼별칭을 사용할 수 없다.
       [AND 조건,...]]
  [GROUP BY 컬럼명 [,컬럼명...]] -- SUM, SOUNT, AVG, MAX, MIN
    [HAVING 조건]
  [ORDER BY 컬럼명:컬럼index[ASC/DESC][,컬럼명:컬럼index[ASC/DESC],....]]; --정렬 (ASC:오름차순/DESC:내림차순) --컬럼index SELETE절에 선언된 순서 기준
    . 'DISTINCT' : 중복배제
    . 'AS 컬럼별칭' : 컬럼에 부여한 또다른 이름, 컬럼의 제목
    . '컬럼별칭'은 특수문자(공백 등이나 예약어)가 포함된 경우 반드시 ""로 묶어주어야함     
     
특정 테이블이 없거나 불필요한 경우 시스템이 제공하는 더미테이블인 DUAL을 사용

사용예) 회원테이블(MEMBER)에서 여성회원들의 정보를 조회하시오
        Alias는 회원번호, 회원명, 주소, 나이, 마일리지이다.
        SELECT MEM_ID AS 회원번호, 
            MEM_NAME AS 회원명, 
            CONCAT(MEM_ADD1, MEM_ADD2) AS 주소, 
            EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR) AS 나이,
            MEM_MILEAGE AS 마일리지
        FROM MEMBER
        WHERE SUBSTR(MEM_REGNO2,1,1) = '2';
        
사용예) 2636363*24242 값을 출력
    SELECT 2636363*24242 FROM DUAL;
    SELECT SYSDATE FROM DUAL;
    
--2022-0107) - 03    
1)연산자
 - 산술연산자 (+,-,/,*)
 - 비교(관계)연산자 (>,<,=,>=,<=,!=)
 - 논리연산자 (NOT,AND,OR)
 
사용예) HR계정의 사원테이블(EMPLOYEES)에서 보너스를 구하고 각 사원의 급여 지급액을 구하시오.
    보너스 = 기본급(SALARY) * 영업실적(COMMISSION_PCT)
    지급액 = 기본급+보너스
    Alias는 사원번호, 사원명, 기본급, 보너스, 지급액이다.
    SELECT EMPLOYEE_ID AS 사원번호,
        FIRST_NAME ||' '|| LAST_NAME AS 사원명,
        SALARY AS 기본급,
        COMMISSION_PCT AS 영업실적,
        NVL(SALARY * COMMISSION_PCT, 0) AS 보너스,
        SALARY + NVL(SALARY * COMMISSION_PCT, 0) AS 지급액
    FROM HR.EMPLOYEES;
    
사용예)회원테이블에서 마일리지가 3000이상인 회원을 조회하시오
    Alias(별칭)는 회원번호,회원명,마일리지,직업
    SELECT MEM_ID AS 회원번호,
            MEM_NAME AS 회원명,
            MEM_MILEAGE AS 마일리지,
            MEM_JOB AS 직업
        FROM MEMBER
        WHERE MEM_MILEAGE >= 3000
        ORDER BY MEM_MILEAGE DESC;
    
사용예)회원테이블에서 마일리지가 3000이상이면서 거주지가 '대전'인 회원을 조회하시오.
    Alias는 회원번호,회원명,마일리지,직업,성별
    성별난에는 '여성회원', '남성회원' 중 하나를 출력
    SELECT MEM_ID AS 회원번호,
            MEM_NAME AS 회원명,
            MEM_MILEAGE AS 마일리지,
            MEM_JOB AS 직업,
            CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' THEN '남성회원'
             ELSE '여성회원'
             END AS 성별
        FROM MEMBER
        WHERE MEM_MILEAGE >= 3000 AND MEM_ADD1 LIKE '대전%';
        
사용예)년도를 입력받아 윤년과 평년을 구별하시오.
    ACCEPT P_YEAR PROMPT '년도입력 : '
    DECLARE
     V_YEAR NUMBER := TO_NUMBER('&P_YEAR');
     V_RES VARCHAR2(100);
    BEGIN
     IF (MOD(V_YEAR,4)=0 AND MOD(V_YEAR,100)!=0) OR
        (MOD(V_YEAR,400)=0) THEN
        V_RES:=V_YEAR||'년도는 윤년입니다.';
     ELSE
        V_RES:=V_YEAR||'년도는 평년입니다.';
     END IF;
     DBMS_OUTPUT.PUT_LINE(V_RES);
    END;
    
--2022-0107) - 04

2)기타연산자
 - 범위지정이나 복수개의 표현식을 지정할때 등을 표현
 - IN, ANY, SOME, ALL, BETWEEN, LIKE, EXISTS 등이 제공
 (1) IN 연산자
    . 질의 탐색을 위해 사용될 둘 이상의 표현식을 지정
    . 제시된 복수개의 자료 중 어느하나와 일치하면 전체 결과가 참을 반환
    (사용형식)
    컬럼명 IN (값1,[값2,....])
     - '컬럼명'에 저장된 값이 '값1,[값2,....]'중 어느하나와 일치하면 결과가 참(true)을 반환
     - =ANY, =SOME 으로 바꾸어 사용 가능함
     - 연속된 값은 BETWEEN으로, 불연속적인 값의 비교는 IN으로 수행
     - OR 연산자로 치환 가능
사용예) 사원테이블에서 부서번호 20, 50, 90, 110에 속한 사원정보를 조회하시오
    Alias는 사원번호, 사원명, 부서번호, 급여
    (OR 연산자 사용)
    SELECT EMPLOYEE_ID AS 사원번호,
        FIRST_NAME||' '||LAST_NAME AS 사원명,
        DEPARTMENT_ID AS 부서번호,
        SALARY AS 급여
        FROM HR.EMPLOYEES
        WHERE DEPARTMENT_ID=20
            OR DEPARTMENT_ID=50
            OR DEPARTMENT_ID=90
            OR DEPARTMENT_ID=110
        ORDER BY 3;
    (IN 연산자 사용)
    SELECT EMPLOYEE_ID AS 사원번호,
        FIRST_NAME||' '||LAST_NAME AS 사원명,
        DEPARTMENT_ID AS 부서번호,
        SALARY AS 급여
        FROM HR.EMPLOYEES
        WHERE DEPARTMENT_ID IN (20,50,90,110)
        ORDER BY 3;
        
 (2)ANY(SOME) 연산자
    . IN 연산자와 유사한 기능제공
    . 주어진 데이터 중 어느 하나의 값과 ANY(SOME)앞에 기술된 관계연산자를 만족하면 참(true)인 결과를 반환
    (사용형식)
     컬럼명 관계연산자ANY|SOME(값1,[값2,...])
     
사용예) 사원테이블에서 부서번호 20, 50, 90, 110에 속한 사원정보를 조회하시오
    Alias는 사원번호, 사원명, 부서번호, 급여
     SELECT EMPLOYEE_ID AS 사원번호,
        FIRST_NAME||' '||LAST_NAME AS 사원명,
        DEPARTMENT_ID AS 부서번호,
        SALARY AS 급여
        FROM HR.EMPLOYEES
        WHERE DEPARTMENT_ID =ANY (20,50,90,110)
        ORDER BY 3;
        
사용예) 회원테이블에서 직업이 공무원인 회원들이 보유한 마일리지보다 많은 마일리지를 보유한 회원을 조회하시오
    Alias는 회원번호,회원명,직업,마일리지
    SELECT MEM_ID AS 회원번호,
           MEM_NAME AS 회원명,
           MEM_JOB AS 직업,
           MEM_MILEAGE AS 마일리지
        FROM MEMBER
        WHERE MEM_MILEAGE >ANY (SELECT MEM_MILEAGE
                                    FROM MEMBER
                                    WHERE MEM_JOB = '공무원');