 -- 2022-0124-01)-------------------------------------------------------------------------------------------------------------- 
 
    사용예)
        2005년 모든 거래처별 매입금액합계를 조회하시오
        Alias는 거래처코드, 거래처명, 매입금액합계
        
        (일반적인 외부조인--내부조인)
        SELECT A.BUYER_ID AS 거래처코드,
               A.BUYER_NAME AS 거래처명,
               SUM(B.BUY_QTY * C.PROD_COST) AS 매입금액합계
          FROM BUYER A, BUYPROD B, PROD C
         WHERE A.BUYER_ID = C.PROD_BUYER(+)
           AND B.BUY_PROD(+) = C.PROD_ID
           AND B.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20051231')
         GROUP BY A.BUYER_ID,A.BUYER_NAME
         ORDER BY 1;
         
         (ANSI 외부조인)
         SELECT A.BUYER_ID AS 거래처코드,
               A.BUYER_NAME AS 거래처명,
               SUM(B.BUY_QTY * C.PROD_COST) AS 매입금액합계
          FROM BUYER A
          LEFT OUTER JOIN PROD C ON(A.BUYER_ID = C.PROD_BUYER)
          LEFT OUTER JOIN BUYPROD B ON(B.BUY_PROD = C.PROD_ID
               AND B.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20051231'))     
         GROUP BY A.BUYER_ID,A.BUYER_NAME
         ORDER BY 1;
         
         (SUBQUERY사용 외부조인)
         SELECT A.BUYER_ID AS 거래처코드,
               A.BUYER_NAME AS 거래처명,
               SUM(B.BUY_QTY * C.PROD_COST) AS 매입금액합계
          FROM BUYER A,(2005년도 거래처별 매입금액합계) B
          
         (SUBQUERY : 2005년도 거래처별 매입금액합계)
         SELECT A.BUYER_ID AS BID,
                SUM(B.BUY_QTY * C.PROD_COST) AS BSUM
           FROM BUYER A, BUYPROD B, PROD C
          WHERE A.BUYER_ID = C.PROD_BUYER
            AND B.BUY_PROD = C.PROD_ID
            AND EXTRACT(YEAR FROM B.BUY_DATE) = 2005
          GROUP BY A.BUYER_ID
          
          (결합)
         SELECT D.BUYER_ID AS 거래처코드,
                D.BUYER_NAME AS 거래처명,
                NVL(E.BSUM,0) AS 매입금액합계
           FROM BUYER D,(SELECT A.BUYER_ID AS BID,
                                SUM(B.BUY_QTY * C.PROD_COST) AS BSUM
                           FROM BUYER A, BUYPROD B, PROD C
                          WHERE A.BUYER_ID = C.PROD_BUYER
                            AND B.BUY_PROD = C.PROD_ID
                            AND EXTRACT(YEAR FROM B.BUY_DATE) = 2005
                          GROUP BY A.BUYER_ID) E
          WHERE D.BUYER_ID = E.BID(+)
          ORDER BY 1;
        
         
         
         
    사용예)
        회원테이블에서 직업이 자영업인 회원들의 마일리지보다 더 많은 마일리지를 보유한 회원정보를 조회하시오
        Alias는 회원번호, 회원명, 직업, 마일리지
        
        (메인쿼리)
        SELECT MEM_ID AS 회원번호,
               MEM_NAME AS 회원명,
               MEM_JOB AS 직업,
               MEM_MILEAGE AS 마일리지
          FROM MEMBER A
         WHERE MEM_MILEAGE > (직업이 자영업인 회원들의 마일리지)
         ORDER BY 1;
          
        (SUBQUERY : 직업이 자영업인 회원들의 마일리지)
        SELECT MEM_MILEAGE
          FROM MEMBER
         WHERE MEM_JOB = '자영업'
         
        (결합)
        SELECT MEM_ID AS 회원번호,
               MEM_NAME AS 회원명,
               MEM_JOB AS 직업,
               MEM_MILEAGE AS 마일리지
          FROM MEMBER A
         WHERE MEM_MILEAGE >ALL (SELECT MEM_MILEAGE
                                   FROM MEMBER
                                   WHERE MEM_JOB = '자영업')
         ORDER BY 1;

 -- 2022-0124-02)-------------------------------------------------------------------------------------------------------------- 
 
    * 집합 연산자
     - 복수개의 QUERY 결과를 연산하여 새로운 결과를 반환
     - JOIN 연산을 줄일 수 있음
     - 합집합(UNION, UNION ALL), 교집합(INTERSECT), 차집합(MINUS) 제공
      . UNION : 두 집합의 모든 원소를 중복하지 않게 반환(정렬)
      . UNION ALL : 중복을 허용한 두 집합의 모든 원소를 반환(정렬하지않음)
      . INTERSECT : 두 집합의 공통된 원소 반환(정렬)
      . MINUS : 피감수집합에서 감수집합결과를 차감한 결과 반환
     (사용형식)
      QUERY1
      UNION|UNION ALL|INTERSECT|MINUS
      QUERY2
      [UNION|UNION ALL|INTERSECT|MINUS
      QUERY3]
        :
      [UNION|UNION ALL|INTERSECT|MINUS
      QUERYn]
     - 모든 쿼리의 SELECT 절의 컬럼의 수와 타입, 순서가 동일해야함
     - 출력의 기본은 첫 번째 SELECT 문임
     - ORDER BY 절은 맨 마지막 QUERY만 사용 가능
     
    1. UNION
     - 합집합의 결과 출력
     - 중복을 배제
     
     사용예)
        사원테이블에서 2005년도에 입사한 사원과 부서가 시애틀인 사원을 조회하시오
        Alias는 사원번호, 사원명, 입사일, 부서명
        
     --(2005년도에 입사한 사원)
     SELECT A.EMPLOYEE_ID AS 사원번호,
            A.EMP_NAME AS 사원명,
            A.HIRE_DATE AS 입사일,
            B.DEPARTMENT_NAME AS 부서명
       FROM HR.EMPLOYEES A, HR.DEPARTMENTS B
      WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
        AND EXTRACT(YEAR FROM A.HIRE_DATE) = 2005
     UNION   
    --(부서가 시애틀인 사원)
     SELECT A.EMPLOYEE_ID,
            A.EMP_NAME,
            A.HIRE_DATE,
            B.DEPARTMENT_NAME
       FROM HR.EMPLOYEES A, HR.DEPARTMENTS B, HR.LOCATIONS C
      WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
        AND B.LOCATION_ID = C.LOCATION_ID
        AND C.CITY = 'Seattle'
        
    사용예)
        2005년 4월에 매입된 상품과 매출된 상품을 중복되지 않게 모두 조회하시오
        Alias는 상품코드, 상품명, 거래처명
        --(2005년 4월 매입된 상품)
        SELECT DISTINCT A.PROD_ID AS 상품코드,
               A.PROD_NAME AS 상품명,
               B.BUYER_NAME AS 거래처명
          FROM PROD A, BUYER B ,BUYPROD C
         WHERE A.PROD_BUYER = B.BUYER_ID
           AND A.PROD_ID = C.BUY_PROD
           AND C.BUY_DATE BETWEEN TO_DATE('20050401') AND TO_DATE('20050430')
        UNION   
        --(2005년 4월 매출된 상품)
        SELECT DISTINCT A.PROD_ID,
               A.PROD_NAME,
               B.BUYER_NAME
          FROM PROD A, BUYER B ,CART C
         WHERE A.PROD_BUYER = B.BUYER_ID
           AND A.PROD_ID = C.CART_PROD
           AND C.CART_NO LIKE '200504%'

 -- 2022-0124-03)-------------------------------------------------------------------------------------------------------------- 
      
    사용예)
        2005년 6월과 7월에 상품을 구입한 회원을 조회하시오
        Alias는 회원번호, 회원명, 주소, 마일리지
        
        --6월
        SELECT DISTINCT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               A.MEM_ADD1 ||' '||A.MEM_ADD2 AS 주소,
               A.MEM_MILEAGE AS 마일리지
          FROM MEMBER A, CART B
         WHERE A.MEM_ID = B.CART_MEMBER
           AND B.CART_NO LIKE '200506%'
        UNION
        --7월
        SELECT DISTINCT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               A.MEM_ADD1 ||' '||A.MEM_ADD2 AS 주소,
               A.MEM_MILEAGE AS 마일리지
          FROM MEMBER A, CART B
         WHERE A.MEM_ID = B.CART_MEMBER
           AND B.CART_NO LIKE '200507%'
           
    2. INTERSECT
     - 두 SQL 결과의 교집합(공통된 영역)을 반환
        
    **순위함수(RANK OVER)
    - 특정컬럼을 기준으로 순서화시키고 등수부여
    - RANK OVER, DENSE_RANK 등이 있다.
    1) RANK
     . 순위를 부여
     . 같은 값은 같은 순위를 부여하고 차 순위는 중복된 개수만큼 순위를 증분하여 부여(ex 1,2,2,2,5,6...)
     . SELECT 절에 사용
    (사용형식)
    RANK() OVER (ORDER BY 컬럼명 [ASC|DESC]) [AS 별칭] -- ASC는 적은것부터 높은등수를 매김
     - '컬럼명'을 기준으로 등수부여
    사용예)
        2005년 구매금액이 많은 5명의 회원을 조회하시오
        Alias는 회원번호, 회원명, 구매금액
        
        SELECT A.MEM_ID AS 회원번호,
               A.MEM_NAME AS 회원명,
               SUM(B.CART_QTY * C.PROD_PRICE) AS 구매금액
          FROM MEMBER A, CART B, PROD C
         WHERE A.MEM_ID = B.CART_MEMBER
           AND B.CART_PROD = C.PROD_ID
           AND ROWNUM <= 5 -- QUERY는 WHERE절 부터 실행되기 때문에 조건에 맞는 컬럼이 계산되고 출력되기전부터 ROWNUm이 실행된 결과가 나온다
         GROUP BY A.MEM_ID,A.MEM_NAME
         ORDER BY 3 DESC
         
        (서브쿼리 사용)
        -- 서브쿼리 : 2005년 회원별 구매금액 계산, 구매금액 순으로 내림차순정렬
        SELECT A.CART_MEMBER AS CID,
               SUM(A.CART_QTY * B.PROD_PRICE) AS CSUM
          FROM CART A, PROD B
         WHERE A.CART_PROD = B.PROD_ID
           AND A.CART_NO LIKE '2005%'
         GROUP BY A.CART_MEMBER
         ORDER BY 2 DESC
        
        -- 메인쿼리 : 구매금액이 많은 5명의 회원의 회원번호,회원명,구매금액
        SELECT M.MEM_ID AS 회원번호,
               M.MEM_NAME AS 회원명,
               C.CSUM AS 구매금액
          FROM MEMBER M,(SELECT A.CART_MEMBER AS CID,
                                SUM(A.CART_QTY * B.PROD_PRICE) AS CSUM
                           FROM CART A, PROD B
                          WHERE A.CART_PROD = B.PROD_ID
                            AND A.CART_NO LIKE '2005%'
                          GROUP BY A.CART_MEMBER
                          ORDER BY 2 DESC) C
         WHERE M.MEM_ID = C.CID
           AND ROWNUM<=5
           
 -- 2022-0124-04)--------------------------------------------------------------------------------------------------------------
 
     사용예)
        2005년 구매금액이 많은 회원부터 등수를 부여하여 조회하시오.
        Alias는 회원번호, 회원명, 구매금액, 등수
        
        SELECT A.CART_MEMBER AS 회원번호,
               B.MEM_NAME AS 회원명,
               SUM(A.CART_QTY * C.PROD_PRICE) AS 구매금액,
               RANK() OVER(ORDER BY SUM(A.CART_QTY * C.PROD_PRICE) DESC) AS 등수
          FROM CART A, MEMBER B, PROD C
         WHERE A.CART_MEMBER = B.MEM_ID
           AND A.CART_PROD = C.PROD_ID
           AND A.CART_NO LIKE '2005%'
         GROUP BY A.CART_MEMBER,B.MEM_NAME
         
    2) 그룹내에서 순위
     (사용형식)
     RANK() OVER(PARTITION BY 컬럼명[,컬럼명,....]
                 ORDER BY 컬럼명[,컬럼명,...] [ASC|DESC]) [AS 별칭]
     . PARTITION BY 컬럼명 : 그룹으로 묶을 컬럼명 기술
     
    사용예)
        사원테이블에서 각 부서별 사원들의 급여를 기준으로 순위를 부여하여 출력하시오.
        순위는 급여가 많은 사람 순으로 부여하고 같은 급여이면 입사일이 빠른 순으로 부여하시오
        
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               B.DEPARTMENT_NAME AS 부서명,
               A.SALARY AS 급여,
               A.HIRE_DATE AS 입사일,
               RANK() OVER(PARTITION BY A.DEPARTMENT_ID
                           ORDER BY A.SALARY DESC, A.HIRE_DATE ASC) AS 순위
          FROM HR.EMPLOYEES A, HR.DEPARTMENTS B
         WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
