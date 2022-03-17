 -- 2022-0119-1)---------------------------------------------------------------------------------------------------------------
    문제]
        사원테이블 등을 이용하여 미국내에 위치한 부서별 사원수와 평균임금을 조회하시오
        Alias는 부서코드, 부서명, 사원수, 평균임금
        
        SELECT A.DEPARTMENT_ID AS 부서코드,
               B.DEPARTMENT_NAME AS 부서명,
               COUNT(*) AS 사원수,
               ROUND(AVG(A.SALARY),-1) AS 평균임금
          FROM HR.EMPLOYEES A, HR.DEPARTMENTS B, HR.LOCATIONS C
        WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
          AND B.LOCATION_ID = C.LOCATION_ID
          AND C.COUNTRY_ID = 'US'
        GROUP BY A.DEPARTMENT_ID,B.DEPARTMENT_NAME
        ORDER BY 1;
        
    문제]
        장바구니테이블(CART)에서 2005년 매출자료를 분석하여 거래처별, 상품별 매출현황을 조회하시오
        Alias는 거래처코드, 거래처명, 상품명, 매출수량, 매출금액
        
        (일반 JOIN)
        SELECT C.BUYER_ID AS 거래처코드,
               C.BUYER_NAME AS 거래처명,
               B.PROD_NAME AS 상품명,
               SUM(A.CART_QTY) AS 매출수량,
               SUM(A.CART_QTY * B.PROD_PRICE) AS 매출금액
          FROM CART A, PROD B, BUYER C
         WHERE A.CART_PROD = B.PROD_ID
           AND B.PROD_BUYER = C.BUYER_ID
           AND A.CART_NO LIKE '2005%'
         GROUP BY C.BUYER_ID, C.BUYER_NAME, B.PROD_NAME
         ORDER BY 1;
         
         (ANSI JOIN)
        SELECT C.BUYER_ID AS 거래처코드,
               C.BUYER_NAME AS 거래처명,
               B.PROD_NAME AS 상품명,
               SUM(A.CART_QTY) AS 매출수량,
               SUM(A.CART_QTY * B.PROD_PRICE) AS 매출금액
          FROM CART A
          INNER JOIN PROD B ON(A.CART_PROD = B.PROD_ID)
          INNER JOIN BUYER C ON(B.PROD_BUYER = C.BUYER_ID AND
                             SUBSTR(A.CART_NO,1,4) = '2005')
         GROUP BY C.BUYER_ID, C.BUYER_NAME, B.PROD_NAME
         ORDER BY 1;
         
 -- 2022-0119-2)---------------------------------------------------------------------------------------------------------------

    3. NON-EQUI JOIN
     - 조인 조건문에 '='연산자 이외의 연산자가 사용되는 조인
     
    **HR계정에 급여에 따른 등급표를 작성하시오
     1)테이블명 : SAL_GRADE
     2)컬럼명
     -------------------------------
     GRADE      LOW_SAL      MAX_SAL
     -------------------------------
        1         1000         2999
        2         3000         4999 
        3         5000         7999 
        4         8000        12999
        5        13000        19999
        6        20000        40000   
     -------------------------------
     3)기본키 : GRADE
     
     CREATE TABLE SAL_GRADE(GRADE NUMBER(2) PRIMARY KEY, LOW_SAL NUMBER(6), MAX_SAL NUMBER(6))
     
     INSERT INTO SAL_GRADE VALUES(1,1000,2999);
     INSERT INTO SAL_GRADE VALUES(2,3000,4999);
     INSERT INTO SAL_GRADE VALUES(3,5000,7999);
     INSERT INTO SAL_GRADE VALUES(4,8000,12999);
     INSERT INTO SAL_GRADE VALUES(5,13000,19999);
     INSERT INTO SAL_GRADE VALUES(6,20000 ,40000);
     
     SELECT * FROM SAL_GRADE;
     
     
    사용예)
        HR계정의 사원테이블에서 급여에 따른 등급을 조회하여 출력하시오
        Alias는 사원번호, 사원명, 부서명, 급여, 등급
        
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               B.DEPARTMENT_NAME AS 부서명,
               A.SALARY AS 급여,
               C.GRADE AS 등급
          FROM HR.EMPLOYEES A, HR.DEPARTMENTS B, SAL_GRADE C
         WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
           AND (A.SALARY >= C.LOW_SAL AND C.MAX_SAL >= A.SALARY) -- NON - EQUI JOIN
         ORDER BY 3;
         
    사용예)
        사원테이블에서 사원들의 평균급여보다 많은 급여를 받는 사원들을 조회하시오
        Alias는 사원번호,사원명,직무명,급여
        
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               B.JOB_TITLE AS 직무명,
               A.SALARY AS 급여
          FROM HR.EMPLOYEES A, HR.JOBS B,
               (SELECT ROUND(AVG(SALARY),-1) AS ASAL
                  FROM HR.EMPLOYEES) C
         WHERE A.JOB_ID = B.JOB_ID
           AND A.SALARY > C.ASAL
         ORDER BY 4 DESC;
         
    숙제]
        사원테이블에서 부서별 평균임금을 구하고 해당부서에 속한 사원 중 자기부서의 평균 급여보다 많은 급여를 받는 사원을 조회하시오
        Alias는 사원번호,사원명,부서명,부서평균급여,급여
        
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               B.DEPARTMENT_NAME AS 부서명,
               C.CAVG AS 부서평균급여,
               A.SALARY AS 급여
          FROM HR.EMPLOYEES A, HR.DEPARTMENTS B, 
               (SELECT D.DEPARTMENT_NAME AS CNAME,
                       ROUND(AVG(C.SALARY),-1) AS CAVG
                  FROM HR.EMPLOYEES C, HR.DEPARTMENTS D
                 WHERE C.DEPARTMENT_ID = D.DEPARTMENT_ID
                 GROUP BY D.DEPARTMENT_NAME) C
         WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
           AND B.DEPARTMENT_NAME = C.CNAME
           AND A.SALARY > C.CAVG
         ORDER BY 5;
                 
        
        제출일자 : 2022/1/28
        제출방법 : 메모장 등을 활용하여 txt 또는 doc 또는 hwp파일로 저장하여 전송(\\Sem\공유폴더\Oracle\homework01)
        파일명 : 이름작성일자.txt(ex 홍길동20220127.txt)
        
 -- 2022-0119-3)---------------------------------------------------------------------------------------------------------------
 
    4. SELF JOIN
     - 하나의 테이블에 여러 개의 별칭을 부여하여 수행하는 조인
     
    사용예)
        회원테이블에서 '라준호'회원의 마일리지보다 많은 마일리지를 보유한 회원의 회원번호,회원명,직업,마일리지를 조회하시오
        
        SELECT B.MEM_ID AS 회원번호,
               B.MEM_NAME AS 회원명,
               B.MEM_JOB AS 직업,
               B.MEM_MILEAGE AS 마일리지
          FROM MEMBER A, MEMBER B
         WHERE A.MEM_NAME = '라준호'
           AND B.MEM_MILEAGE > A.MEM_MILEAGE;
           
    사용예)
        사원테이블에서 각 사원의 관리자사원 이름을 조회하시오
        관리자가 없으면 'CEO'를 출력하시오
        Alias는 사원번호, 사원명, 부서명, 관리자명
        
        
        (서브쿼리)
        SELECT B.EMPLOYEE_ID AS 사원번호,
               B.EMP_NAME AS 사원명,
               C.DEPARTMENT_ID AS 부서명,
               NVL((SELECT A.EMP_NAME
                      FROM HR.EMPLOYEES A
                     WHERE A.EMPLOYEE_ID = B.MANAGER_ID), 'CEO') AS 관리자명
          FROM HR.EMPLOYEES B, HR.DEPARTMENTS C
         WHERE B.DEPARTMENT_ID = C.DEPARTMENT_ID
         ORDER BY 1;
        
        (OUTER JOIN) 
        SELECT B.EMPLOYEE_ID AS 사원번호,
               B.EMP_NAME AS 사원명,
               C.DEPARTMENT_ID AS 부서명,
               NVL(A.EMP_NAME,'CEO') AS 관리자명
          FROM HR.EMPLOYEES A, HR.EMPLOYEES B, HR.DEPARTMENTS C
         WHERE B.DEPARTMENT_ID = C.DEPARTMENT_ID
           AND A.EMPLOYEE_ID(+) = B.MANAGER_ID
         ORDER BY 1;