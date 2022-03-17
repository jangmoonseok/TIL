 -- 2022-0117-1)---------------------------------------------------------------------------------------------------------------
 
    사용예)
        상품테이블에서 상품분류별 평균판매가, 평균매입가를 조회하시오
        
        SELECT PROD_LGU AS 상품분류코드,
               ROUND(AVG(PROD_PRICE)) AS 평균판매가,
               ROUND(AVG(PROD_COST)) AS 평균매입가,
               ROUND(AVG(PROD_PRICE)-AVG(PROD_COST)) AS 평균조수익
        FROM PROD
        GROUP BY PROD_LGU
        ORDER BY 1;
        
    사용예)
        사원테이블에서 근무지가 미국 이외인 부서에 근무하는 사원들의 평균급여와 평균근속년수를 조회하시오
        Alias는 부서코드, 부서명, 평균급여, 평균근속년수
        
        SELECT A.DEPARTMENT_ID AS 부서코드,
               B.DEPARTMENT_NAME AS 부서명,
               ROUND(AVG(A.SALARY)) AS 평균급여,
               ROUND(AVG(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM A.HIRE_DATE))) AS 평균근속년수
        FROM HR.EMPLOYEES A, HR.DEPARTMENTS B, HR.LOCATIONS C
        WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
          AND B.LOCATION_ID = C.LOCATION_ID
          AND C.COUNTRY_ID != 'US'
        GROUP BY A.DEPARTMENT_ID, B.DEPARTMENT_NAME
        ORDER BY 1;
        
    **COUNT 함수
     - 각 그룹내의 행의 수(자료 수)를 반환
     (사용형식)
        COUNT(컬럼명|*)
        . 외부조인과 같은 특별한 경우를 제외하고 '*'를 사용하며, 이 경우 모든 칼럼이 NULL인 행은 COUNT되지 않음
        . '컬렴명': 해당 컬럼명이 NULL이 아닌 자료의 수를 반환하며, 주로 외부조인에 사용한다. 
          이때 해당 테이블의 기본키를 사용하는 것이 안전함
          
    사용예)
        사원테이블에서 각 부서별 사원수를 조회하시오.
        
        SELECT DEPARTMENT_ID AS 부서코드,
               COUNT(*)사원수,
               COUNT(EMPLOYEE_ID) AS 사원수2, -- 기본키 사용
               COUNT(SALARY) AS 사원수3
        FROM HR.EMPLOYEES
        GROUP BY DEPARTMENT_ID
        ORDER BY 1;
        
    사용예)
        사원테이블에서 부서에 근무하는 사원수가 5명 이상인 부서를 조회하시오.
        
        SELECT DEPARTMENT_ID AS 부서코드,
               COUNT(*)사원수
        FROM HR.EMPLOYEES
        GROUP BY DEPARTMENT_ID
        HAVING COUNT(*) >= 5
        ORDER BY 1;
        
 -- 2022-0117-2)---------------------------------------------------------------------------------------------------------------        
   
    사용예)
        상품테이블에서 분류별 상품수를 조회하시오
        
        SELECT PROD_LGU AS 분류코드,
               COUNT(*) AS 상품수
        FROM PROD
        GROUP BY PROD_LGU
        ORDER BY 1;
        
        
    사용예)
        2005년 5~7월 장바구니테이블에서 월별 매출건수를 조회하시오
        
        SELECT SUBSTR(A.CNO,5,2)||'월' AS 월,
               COUNT(*) AS 판매건수
        FROM (SELECT DISTINCT CART_NO AS CNO
                FROM CART
               WHERE SUBSTR(CART_NO,1,6) BETWEEN '200505' AND '200507'
               GROUP BY CART_NO) A
        GROUP BY SUBSTR(A.CNO,5,2)
        ORDER BY 1;
        
        
    **MAX(컬럼명|수식), MIN(컬럼명|수식)
    
    사용예)
        사원테이블에서 각 부서별 최대급여와, 최소급여를 조회하시오.
        
        SELECT DEPARTMENT_ID AS 부서코드,
               MAX(SALARY) AS 최대급여,
               MIN(SALARY) AS 최소급여
        FROM HR.EMPLOYEES
        GROUP BY DEPARTMENT_ID
        ORDER BY 1;
    
    사용예)
        사원테이블에서 각 부서별 최대급여와, 최소급여를 수령하는 사원을 조회하시오.
        **최대급여
        SELECT A.DEPARTMENT_ID AS 부서코드,
               A.EMP_NAME AS 사원명,
               B.MXS AS 최대급여
        FROM HR.EMPLOYEES A,
             (SELECT DEPARTMENT_ID AS DID,
                     MAX(SALARY) AS MXS
                FROM HR.EMPLOYEES
                GROUP BY DEPARTMENT_ID) B
        WHERE B.DID = A.DEPARTMENT_ID
          AND A.SALARY = B.MXS
        ORDER BY 1;
        
        **최소급여
        SELECT B.DID AS 부서코드,
               A.EMP_NAME AS 사원명,
               B.MNS AS 최소급여
        FROM HR.EMPLOYEES A,
             (SELECT DEPARTMENT_ID AS DID,
                     MIN(SALARY) AS MNS
                FROM HR.EMPLOYEES
              GROUP BY DEPARTMENT_ID) B
        WHERE A.DEPARTMENT_ID = B.DID
          AND A.SALARY = B.MNS
        ORDER BY 1;
        
    사용예)
        2005년 1월 제품별 매입금액합계 중 최소 금액을 기록한 제품을 조회하시오
        Alias는 제품코드, 매입금액
        
        **제품별 매입액
        SELECT BUY_PROD AS 제품코드,
               SUM(BUY_QTY * BUY_COST) AS 매입금액
          FROM BUYPROD
        WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
        GROUP BY BUY_PROD
        ORDER BY 1;
        
        **제품별 최소매입금액
        SELECT MIN(A.SQC) AS 매입금액
          FROM (SELECT BUY_PROD AS BID,
                       SUM(BUY_QTY * BUY_COST) AS SQC
                  FROM BUYPROD
                 WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
                 GROUP BY BUY_PROD) A
        GROUP BY A.BID
        ORDER BY 1;
        
        ** 제품별 매입금액합계 중 최소 금액을 기록한 제품
        SELECT C.BID AS 제품코드,
               C.SQC AS 매입금액
         FROM  (SELECT MIN(A.SQC) AS MBS
                         FROM (SELECT BUY_PROD AS BID,
                                      SUM(BUY_QTY * BUY_COST) AS SQC
                                 FROM BUYPROD
                                WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
                               GROUP BY BUY_PROD) A) B,
               (SELECT BUY_PROD AS BID,
                       SUM(BUY_QTY * BUY_COST) AS SQC
                  FROM BUYPROD
                WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
                GROUP BY BUY_PROD) C
         WHERE C.SQC = B.MBS;              
         
 -- 2022-0117-3)---------------------------------------------------------------------------------------------------------------
 
    ROLLUP 과 CUBE
    - GROUP BY 절에서 사용되어 다양한 집계를 제공
    1)ROLLUP
     . 그룹내의 집계에 사용된 컬럼들을 레벨별로 구분하여 레벨별 집계와 전체 집계를 반환
     . 반드시 GROUP BY 절에서만 사용해야 함
     (사용형식)
     GROUP BY ROLLUP(컬럼명1[,컬럼명2,...,컬럼명n]) [,컬럼명,...]
      - ROLLUP()안에 기술된 컬럼명n 부터 컬럼명1까지 레벨을 부여하여 각 레벨별 집계와 전체 집계를 반환
      - ROLLUP()안에 기술된 컬럼의 수가 n개이면 전체 집계의 종류는 n+1개임
      - 컬럼명1[,컬럼명2,...,컬럼명n] 모두를 기준으로 집계를 반환한 뒤 컬럼명1[,컬럼명2]을 기준으로 집계 반환,
        컬럼명1을 기준으로 집계 반환, 전체 집계 반환
        
    사용예)
        2005년 4월-7월 사이의 판매자료를 대상으로 월별, 회원별, 제품별 판매집계를 구하시오
        Alias는 월,회원번호,제품번호,판매수량집계
        
        (GROUP BY 절만 사용)
        SELECT SUBSTR(CART_NO,5,2) AS 월,
               CART_MEMBER AS 회원번호,
               CART_PROD AS 제품번호,
               SUM(CART_QTY) AS 판매수량집계
          FROM CART
         WHERE SUBSTR(CART_NO,1,6) BETWEEN '200504' AND '200507'
        GROUP BY SUBSTR(CART_NO,5,2), CART_MEMBER, CART_PROD
        ORDER BY 1;
        
        (ROLLUP 사용)
        SELECT SUBSTR(CART_NO,5,2) AS 월,
               CART_MEMBER AS 회원번호,
               CART_PROD AS 제품번호,
               SUM(CART_QTY) AS 판매수량집계
          FROM CART
         WHERE SUBSTR(CART_NO,1,6) BETWEEN '200504' AND '200507'
        GROUP BY ROLLUP(SUBSTR(CART_NO,5,2), CART_MEMBER, CART_PROD)
        ORDER BY 1;
        
    **부분 ROLLUP
     - GROUP BY 절에 사용된 컬럼 중 일부만 ROLLUP의 대상이 되는 경우
     ex)GROUP BY A1,ROLLUP(B1,B2,B3) 로 기술된 경우
        . A1,B1,B2,B3 컬럼을 기준으로 합계 반환,
          A1,B1,B2 컬럼을 기준으로 합계 반환,
          A1,B1 컬럼을 기준으로 합계 반환,
          A1 칼럼을 기준으로 합계 반환
          
    사용예)
        SELECT SUBSTR(CART_NO,5,2) AS 월,
               CART_MEMBER AS 회원번호,
               CART_PROD AS 제품번호,
               SUM(CART_QTY) AS 판매수량집계
          FROM CART
         WHERE SUBSTR(CART_NO,1,6) BETWEEN '200504' AND '200507'
        GROUP BY SUBSTR(CART_NO,5,2), ROLLUP(CART_MEMBER, CART_PROD)
        ORDER BY 1;
        
    2)CUBE
     . 그룹내의 집계에 사용된 컬럼들의 조합 가능한 경우의 가짓 수 만큼 집계 반환
     . 반드시 GROUP BY 절에서만 사용해야함
     (사용형식)
     GROUP BY CUBE(컬럼명1[,컬럼명2,...,컬럼명n])[,컬럼명,..]
      - CUBE안에 사용된 컬럼의 수가 n개이면 2의 n승 가지만큼 집계의 종류를 반환
      ex) GROUP BY CUBE(A1,B1,C1)
       => A1,B1,C1을 기준으로하는 집계
          A1,B1을 기준으로하는 집계
          A1,C1을 기준으로하는 집계
          B1,C1을 기준으로하는 집계
          A1을 기준으로하는 집계
          B1을 기준으로하는 집계
          C1을 기준으로하는 집계
          전체 집계
          
    사용예)
        SELECT SUBSTR(CART_NO,5,2) AS 월,
               CART_MEMBER AS 회원번호,
               CART_PROD AS 제품번호,
               SUM(CART_QTY) AS 판매수량집계
          FROM CART
         WHERE SUBSTR(CART_NO,1,6) BETWEEN '200504' AND '200507'
        GROUP BY CUBE(SUBSTR(CART_NO,5,2), CART_MEMBER, CART_PROD)
        ORDER BY 1;
        
        