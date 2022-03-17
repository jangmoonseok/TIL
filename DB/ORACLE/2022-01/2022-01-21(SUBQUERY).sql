 -- 2022-0121-01)-------------------------------------------------------------------------------------------------------------- 
 
    사용예)
        모든 부서별 인원수를 조회하시오
        Alias는 부서코드, 부서명, 인원수
        
        SELECT NVL(TO_CHAR(B.DEPARTMENT_ID),'미배정') AS 부서코드,
               NVL(B.DEPARTMENT_NAME,'프리랜서') AS 부서명,
               COUNT(EMPLOYEE_ID) AS 인원수 -- COUNT함수에 들어갈 컬럼명은 기본키를 사용하자
          FROM HR.EMPLOYEES A
          FULL OUTER JOIN HR.DEPARTMENTS B ON(A.DEPARTMENT_ID = B.DEPARTMENT_ID)
         GROUP BY B.DEPARTMENT_ID,B.DEPARTMENT_NAME
         ORDER BY B.DEPARTMENT_ID;
         
    사용예)
        2005년 모든 상품별 매입/매출수량을 집계하시오
        Alias는 상품코드, 상품명, 매입수량, 매출수량
        
        (2005년 모든 상품별 매입수량 집계)
        SELECT B.PROD_ID AS 상품코드,
               B.PROD_NAME AS 상품명,
               SUM(A.BUY_QTY) AS 매입수량
          FROM BUYPROD A
          RIGHT OUTER JOIN PROD B ON(A.BUY_PROD = B.PROD_ID 
                AND A.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20051231'))
         GROUP BY B.PROD_ID,B.PROD_NAME
         ORDER BY 1;
         
        (2005년 모든 상품별 매출수량 집계)
        SELECT B.PROD_ID AS 상품코드,
               B.PROD_NAME AS 상품명,
               NVL(SUM(A.CART_QTY),0) AS 매출수량
          FROM CART A
          RIGHT OUTER JOIN PROD B ON(A.CART_PROD = B.PROD_ID 
                AND A.CART_NO LIKE '2005%')
         GROUP BY B.PROD_ID,B.PROD_NAME
         ORDER BY 1;
         
         
        (2005년 모든 상품별 매입/매출수량 집계)
        SELECT B.PROD_ID AS 상품코드,
               B.PROD_NAME AS 상품명,
               SUM(A.BUY_QTY) AS 매입수량,
               NVL(SUM(C.CART_QTY),0) AS 매출수량
          FROM BUYPROD A
          RIGHT OUTER JOIN PROD B ON(A.BUY_PROD = B.PROD_ID 
                AND A.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20051231'))
          LEFT OUTER JOIN CART C ON(C.CART_PROD = B.PROD_ID 
                AND C.CART_NO LIKE '2005%')
         GROUP BY B.PROD_ID,B.PROD_NAME
         ORDER BY 1;
         
        (SUBQUERY 사용)
        SELECT A.PROD_ID AS 상품코드,
               A.PROD_NAME AS 상품명,
               NVL(B.BSUM,0) AS 매입수량,
               NVL(C.CSUM,0) AS 매출수량
          FROM PROD A,
               (SELECT BUY_PROD AS BID,
                       SUM(BUY_QTY) AS BSUM
                  FROM BUYPROD 
                 WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20051231')
                 GROUP BY BUY_PROD) B,
               (SELECT CART_PROD AS CID,
                       SUM(CART_QTY) AS CSUM
                  FROM CART 
                 WHERE CART_NO LIKE '2005%'
                 GROUP BY CART_PROD) C
         WHERE A.PROD_ID = B.BID(+)
           AND A.PROD_ID = C.CID(+) -- A테이블을 기준으로 B,C테이블의 개수를 맞추는건 가능
         ORDER BY 1;

 -- 2022-0121-02)-------------------------------------------------------------------------------------------------------------- 
 
    * SUBQUERY
     - SQL구문 안에 또 다른 SELECT문이 존재하는 경우
     - JOIN이나 복잡도를 개선하기 위해 사용
     - 모든 SUBQUERY문은 ()안에 기술해야함. 단, INSERT문에 사용되는 SUBQUERY는 제외
     - 서브쿼리가 WHERE절 등에서 연산과 같이 사용될때 반드시 연산자 오른쪽에 위치
     - 서브쿼리의 분류
      . 사용 위치에 따라 : 일반서브쿼리(SELECT 절), 중첩서브쿼리(WHERE 절), IN-LINE서브쿼리(FROM 절)
      . 메인쿼리와의 관계에 따라 : 관련성 없는 서브쿼리(메인쿼리에 사용된 테이블과 JOIN 없이 구성된 서브쿼리),
                              관련성 있는 서브쿼리(메인쿼리에 사용된 테이블과 JOIN 으로 연결된 서브쿼리)
      . 반환되는 행/열에 따라 : 단일열|다중열 / 단일행|다중행 서브쿼리 => 사용되는 연산자에 의한 구별
     - 알려지지 않은 조건에 근거한 값들을 검색하는 SELECT문 등에 활용
     - 메인쿼리가 실행되기 전에 한번 실행된다.
     
    사용예)
        사원테이블에서 사원들의 평균임금보다 많은 급여를 받는 사원들의 사원번호, 사원명, 부서코드, 급여를 조회하시오
       
        (IN-LINE VIEW 서브쿼리)
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               A.DEPARTMENT_ID AS 부서코드,
               A.SALARY AS 급여,
               B.BAVG AS 평균급여
          FROM HR.EMPLOYEES A,
               (SELECT ROUND(AVG(SALARY)) AS BAVG
                  FROM HR.EMPLOYEES) B
         WHERE A.SALARY > B.BAVG
         ORDER BY 4;
         
 -- 2022-0121-03)-------------------------------------------------------------------------------------------------------------- 
 
        (중첩 서브쿼리)
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               DEPARTMENT_ID AS 부서코드,
               SALARY AS 급여
          FROM HR.EMPLOYEES 
         WHERE SALARY > (SELECT ROUND(AVG(SALARY))
                              FROM HR.EMPLOYEES) 
         ORDER BY 4;
         
    사용예)
        회원테이블에서 회원의 직업별 최대마일리지를 갖고있는 회원정보를 조회하시오
        Alias 회원번호,회원명,직업,마일리지
        
        
        (서브쿼리 : 회원의 직업별 최대마일리지)
        SELECT MEM_JOB,
               MAX(MEM_MILEAGE)
          FROM MEMBER
         GROUP BY MEM_JOB;
    
        (메인쿼리)
        --관련성 없는 다중행 중첩서브쿼리
        SELECT MEM_ID AS 회원번호,
               MEM_NAME AS 회원명,
               MEM_JOB AS 직업,
               MEM_MILEAGE AS 마일리지
          FROM MEMBER
         WHERE (MEM_JOB,MEM_MILEAGE) IN (SELECT MEM_JOB,
                                                MAX(MEM_MILEAGE)
                                           FROM MEMBER
                                          GROUP BY MEM_JOB)
         ORDER BY 1;
         
         -- EXISTS 연산자 사용
         SELECT A.MEM_ID AS 회원번호,
                A.MEM_NAME AS 회원명,
                A.MEM_JOB AS 직업,
                A.MEM_MILEAGE AS 마일리지
           FROM MEMBER A
          WHERE EXISTS(SELECT 1
                          FROM(SELECT MEM_JOB,
                                      MAX(MEM_MILEAGE) AS BMILE
                                 FROM MEMBER 
                                GROUP BY MEM_JOB)B
                        WHERE A.MEM_JOB = B.MEM_JOB
                          AND A.MEM_MILEAGE = B.BMILE)
          ORDER BY 1;
          
 -- 2022-0121-04)--------------------------------------------------------------------------------------------------------------           
    
    사용예)
        상품테이블에서 상품의 판매가가 평균판매가보다 큰 상품을 조회하시오
        Alias는 상품번호, 상품명, 판매가, 평균판매가
        
        (서브쿼리)
        SELECT ROUND(AVG(PROD_PRICE))
          FROM PROD;
        
        (메인쿼리)
        SELECT A.PROD_ID AS 상품번호,
               A.PROD_NAME AS 상품명,
               A.PROD_PRICE AS 판매가,
               B.BAVG AS 평균판매가
          FROM PROD A,
               (SELECT ROUND(AVG(PROD_PRICE)) AS BAVG
                  FROM PROD) B
         WHERE A.PROD_PRICE > B.BAVG
    
    사용예)
        장바구니테이블에서 회원별 최대 구매수량을 기록한 상품을 조회하시오
        Alias는 회원번호, 회원명, 상품명, 구매수량
        
        (서브쿼리 : 회원별 최대 구매수량)
   
        SELECT CART_MEMBER,
               MAX(CART_QTY)
          FROM CART
         GROUP BY CART_MEMBER
        
        (메인쿼리)
        SELECT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               B.PROD_NAME AS 상품명,
               C.CART_QTY AS 구매수량
          FROM MEMBER A, PROD B, CART C
         WHERE A.MEM_ID = C.CART_MEMBER
           AND B.PROD_ID = C.CART_PROD
           AND C.CART_QTY = (서브쿼리)
         ORDER BY 1;
         
        (결합)
        -- 방법1
         SELECT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               B.PROD_NAME AS 상품명,
               C.CART_QTY AS 구매수량
          FROM MEMBER A, PROD B, CART C
         WHERE A.MEM_ID = C.CART_MEMBER
           AND B.PROD_ID = C.CART_PROD
           AND (C.CART_MEMBER,C.CART_QTY) 
                IN (SELECT CART_MEMBER,
                           MAX(CART_QTY)
                      FROM CART
                     GROUP BY CART_MEMBER)
         ORDER BY 1;
         
        -- 방법2 
        SELECT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               B.PROD_NAME AS 상품명,
               C.CART_QTY AS 구매수량
          FROM MEMBER A, PROD B, CART C
         WHERE A.MEM_ID = C.CART_MEMBER
           AND B.PROD_ID = C.CART_PROD
           AND C.CART_QTY = (SELECT MAX(CART_QTY)
                               FROM CART
                              WHERE CART_MEMBER = C.CART_MEMBER)
         ORDER BY 1;
  