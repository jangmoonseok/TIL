 -- 2022-0113-1)---------------------------------------------------------------------------------------------------------------
 
    * 형변환함수
    1)CAST(expr AS 타입)
     - 'expr'로 정의된 컬럼 또는 수식의 데이터 타입이 '타입명' 형식으로 변환
     
    사용예)
     장바구니테이블에서 2005년 5월 판매정보를 조회하시오
     Alias는 일자, 상품명, 수량, 금액 이며 일자순으로 출력하시오
     
     SELECT CAST(SUBSTR(A.CART_NO,1,8) AS DATE) AS 일자,
            B.PROD_NAME AS 상품명,
            A.CART_QTY As 수량,
            A.CART_QTY * B.PROD_PRICE AS 금액
     FROM CART A, PROD B
     WHERE A.CART_PROD = B.PROD_ID
       AND A.CART_NO LIKE '200505%'
     ORDER BY 1;   
     
    2)TO_CHAR(expr[,fmt])
     - 문자열,숫자,날짜 자료를 문자열 자료로 형변환
     - 변환형식을 지정할때에는 'fmt'(형식지정문자열)을 기술
     
     - 날짜관련 형식문자열
     -----------------------------------------------------------------------------
     FORMAT          의미               사용예
     -----------------------------------------------------------------------------
     BC, AD         서기,기원전          SELECT TO_CHAR(SYSDATE,'BC') FROM DUAL;
     CC             세기               SELECT TO_CHAR(SYSDATE, 'CC') FROM DUAL; 
     YYYY,YYY,YY,Y  년도               SELECT TO_CHAR(SYSDATE, 'YYYY'),
                                             TO_CHAR(SYSDATE, 'YYY'),
                                             TO_CHAR(SYSDATE, 'YY'),
                                             TO_CHAR(SYSDATE, 'Y')
                                        FROM DUAL;
     MM             (01~12)월표시
     MON, MONTH     월                 SELECT TO_CHAR(SYSDATE,'MM'),
                                              TO_CHAR(SYSDATE,'MONTH'),
                                              TO_CHAR(SYSDATE,'MON')
                                         FROM DUAL;
     DD             일(01~31)
     DDD            일(01~365)
     D              주중 요일 순번 값
     DY             '월'~'일'
     DAY            '월요일'~'일요일'     SELECT TO_CHAR(SYSDATE,'DD'),
                                               TO_CHAR(SYSDATE,'DDD'),
                                               TO_CHAR(SYSDATE,'D'),
                                               TO_CHAR(SYSDATE,'DY'),
                                               TO_CHAR(SYSDATE,'DAY')
                                          FROM DUAL;
     WW             연중 주(01-53)       SELECT TO_CHAR(SYSDATE,'WW') FROM DUAL;
     AM, PM                             SELECT TO_CHAR(SYSDATE,'P.M.') FROM DUAL;
     A.M.,P.M.      오전, 오후
     HH,HH12,HH24   시간                 SELECT TO_CHAR(SYSDATE,'HH'),
                                               TO_CHAR(SYSDATE,'HH12'),
                                               TO_CHAR(SYSDATE,'HH24')
                                          FROM DUAL;
     MI             분
     SS             초(01~60)            SELECT TO_CHAR(SYSDATE, 'HH:MI:SS'),
                                                TO_CHAR(SYSDATE, 'HH:MI:SSSSS')
                                           FROM DUAL;
     SSSSS          초(01~86000)
     -----------------------------------------------------------------------------
 
 -- 2022-0113-2)---------------------------------------------------------------------------------------------------------------
 
    사용예)
     SELECT TO_CHAR(TO_DATE(SUBSTR(CART_NO,1,8)))
     FROM CART
     WHERE CART_NO LIKE '200504%';
     
     SELECT TO_CHAR(CART_NO) FROM CART;
     
     - 숫자관련 형식문자열
     -----------------------------------------------------------------------------
     FORMAT          의미               사용예
     -----------------------------------------------------------------------------
     9               대응되는 무효의 0을   SELECT TO_CHAR(12345,'9999999') AS A1 FROM DUAL;
                     공백처리
     0               대응되는 무효의 0을   SELECT TO_CHAR(12345,'0000000') AS A1 FROM DUAL;
                     '0'으로 출력
     PR              자료가 음수이면       
                     <>안에 출력         SELECT TO_CHAR(1234,'00000PR'),
                                               TO_CHAR(-1234,'00000PR')
                                          FROM DUAL;
     ,(COMMA)        자리점
     .(DOT)          소숫점              SELECT TO_CHAR(1234,'99,999.9PR'),
                                               TO_CHAR(-1234,'99,999.0PR')
                                          FROM DUAL;
     $,L             화폐기호             SELECT TO_CHAR(1234,'$99,999.0'),
                                               TO_CHAR(1234,'L99,999.0')
                                          FROM DUAL;
     -----------------------------------------------------------------------------
    사용예)
     오늘이 2005년 7월31일이고 쇼핑몰 페이지에 처음 로그인 한 경우 장바구니 번호를 생성하시오.
     
     SELECT TO_CHAR(SYSDATE,'YYYYMMDD')||TRIM(TO_CHAR(1,'00000'))
     FROM DUAL;
     
     오늘이 2005년 7월28일이고 쇼핑몰에 새롭게 로그인한 경우 장바구니 번호를 생성하시오.
     
     SELECT TO_CHAR(SYSDATE,'YYYYMMDD')||TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(CART_NO),9)) + 1,'00000')) AS A
     FROM CART
     WHERE SUBSTR(CART_NO,1,8) = TO_CHAR(SYSDATE, 'YYYYMMDD');
     
     SELECT TO_NUMBER(MAX(CART_NO)) + 1 AS A
     FROM CART
     WHERE SUBSTR(CART_NO,1,8) = TO_CHAR(SYSDATE, 'YYYYMMDD');
     
 -- 2022-0113-3)---------------------------------------------------------------------------------------------------------------
 
    3)TO_NUMBER(expr[,fmt])
     - 문자열을 숫자타입으로 변환
     - 변환시킬 문자열은 숫자로 변환 가능한 문자열이어야 함
     - 'fmt'는 TO_CHAR에서 사용된 것과 동일
    

    CREATE TABLE GOODS_1 AS
    SELECT PROD_ID, PROD_NAME, PROD_LGU, PROD_PRICE
    FROM PROD;
    
    COMMIT;
    
    SELECT * FROM GOODS_1;
    
    사용예)
        상품테이블(GOODS_1)에 다음 자료를 추가로 등록하시오
        상품명 : 삼성 노트북 15인치
        거래처코드 : p101
        판매가격 : 1200000원
    
    (상품코드 생성)
    SELECT 'P101'||TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(PROD_ID),5))+1,'000000')) AS P_CODE
    FROM GOODS_1
    WHERE PROD_LGU = 'P101';
    
    (추가등록)
    INSERT INTO GOODS_1
    SELECT A.P_CODE, '삼성 노트북 15인치', 'P101', '1200000'
    FROM (SELECT 'P101'||TRIM(TO_CHAR(TO_NUMBER(SUBSTR(MAX(PROD_ID),5))+1,'000000')) AS P_CODE
          FROM GOODS_1
          WHERE PROD_LGU = 'P101') A;
          
    SELECT * FROM GOODS_1;
      

    SELECT TRIM(TO_CHAR(1234, 'L99,999'))
    FROM DUAL;
    
    SELECT TO_NUMBER('￦1,234', 'L99,999') -- 변형된 문자열을 원본숫자타입으로 바꾸기 위해서 문자열로 바꿀때 사용한 fmt를 다시 사용해서 숫자로 바꾼다.
    FROM DUAL;
    
 -- 2022-0113-4)---------------------------------------------------------------------------------------------------------------
 
    4)TO_DATE(expr[,fmt])
     - 날짜형식의 문자열 자료를 날짜형식으로 변환하여 반환
     - 'fmt'는 TO_CHAR에서 사용된 날짜형 형식문자열과 동일
     
    사용예)
        SELECT TO_DATE('20050708'),
               TO_CHAR(TO_DATE('20220113092035','YYYYMMDDHHMISS'), 'YYYY/MM/DD HH:MI:SS')
        FROM DUAL;
        
    사용예)
        회원테이블에서 주민등록번호를 이용하여 다음과 같은 형식으로 자료를 출력하시오
        출력
        회원번호  회원명  생년월일          직업    마일리지
          XXXX    XXX  1997년 00월 00일  자영업  9999
        
        SELECT MEM_ID AS 회원번호,
               MEM_NAME AS 회원명,
               CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR
                         SUBSTR(MEM_REGNO2,1,1) = '2' THEN
                         TO_CHAR(TO_DATE('19'||MEM_REGNO1),'YYYY"년" MM"월" DD"일"')
                    ELSE TO_CHAR(TO_DATE('20'||MEM_REGNO1),'YYYY"년" MM"월" DD"일"')
                    END AS 생년월일,
               MEM_JOB AS 직업,
               MEM_MILEAGE AS 마일리지
        FROM MEMBER;
        
    * NULL처리함수
     - NULL은 길이가 없는 자료
     - 연산에 NULL이 사용되면 모든 결과는 NULL이다.
     - NULL 관련 함수는 NVL, NVL2, NULLIF와 데이터가 NULL과 같은지 판단을 위해 IS NULL, IS NOT NULL 연산자가 제공
     
    1. IS NULL, IS NOT NULL
     . NULL값과 동등성 평가를 위한 연산자
     . NULL은 '='연산자로 판별 불가능
      
    사용예)
        사원테이블에서 영업실적코드(COMMISSION_PCT)가 NULL이 아닌 사원을 조회하시오
        Alias는 사원번호, 사원명, 부서코드, 영업실적코드
        
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               DEPARTMENT_ID AS 부서코드,
               COMMISSION_PCT AS 영업실적코드
        FROM HR.EMPLOYEES
        WHERE COMMISSION_PCT IS NOT NULL;
        
    사용예)
        상품테이블에서 색상정보(PROD_COLOR)가 존재하지 않는 상품을 조회하시오
        Alias는 상품코드, 상품명, 매입단가, 색상정보
        
        SELECT PROD_ID AS 상품코드,
               PROD_NAME AS 상품명,
               PROD_COST AS 매입단가,
               PROD_COLOR AS 색상정보
        FROM PROD
        WHERE PROD_COLOR IS NULL;
        
    2. NULL 처리함수
     1)NVL(col,val)
      - 'col'의 값이 NULL이면 'val'을 반환하고 NULL이 아니면 'col'값을 반환
      - 'col'과 'val'은 반드시 같은 타입이어야 한다.
      
    사용예)
        2005년 6월 모든 상품에 대한 상품별 매입현황을 조회하시오
        Alias는 상품명, 매입수량집계, 매입금액집계
        
        (2005년 6월 매입 상품)
        SELECT DISTINCT BUY_PROD
        FROM BUYPROD
        WHERE BUY_DATE BETWEEN TO_DATE('20050601') AND TO_DATE('20050630')
        ORDER BY 1;
        
        SELECT B.PROD_NAME AS 상품명,
               SUM(A.BUY_QTY) AS 매입수량집계,
               SUM(A.BUY_QTY * B.PROD_COST) AS 매입금액집계
        FROM BUYPROD A
        RIGHT OUTER JOIN PROD B ON(A.BUY_PROD=B.PROD_ID
              AND A.BUY_DATE BETWEEN TO_DATE('20050601') AND TO_DATE('20050630'))
        GROUP BY B.PROD_NAME;      