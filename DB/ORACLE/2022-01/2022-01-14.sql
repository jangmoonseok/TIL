 -- 2022-0114-1)---------------------------------------------------------------------------------------------------------------
   
    * 집계함수(그룹함수)
     - 주어진 자료를 특정 컬럼(들)을 기준으로 그룹화하고 각 그룹에서 합계(SUM), 평균(AVG), 빈도수(COUNT), 최댓값(MAX), 최솟값(MIN)을 반환하는 함수
     - SELECT 절에 집계함수를 제외한 일반 칼럼과 같이 사용되면 반드시 GROUP BY 절이 사용되어야 함
     - 집계함수가 사용된 컬럼(수식)에 조건이 부여된 경우 HAVING 절로 처리
     - 집계함수는 다른 집계함수를 포함할 수 없다
     (사용형식)
        SELECT 컬럼list
        FROM 테이블명
        [WHERE 조건]
        [GROUP BY 컬럼명1[,컬럼명2,...]]
        [HAVING 조건]
        [ORDER BY 컬럼명||컬럼인덱스 [ASC|DESC][,...]];
        . [GROUP BY 컬럼명1[,컬럼명2,...]] : 컬럼명1을 기준으로 그룹화하고 각 그룹에서 다시 '컬럼명2'로 그룹화
        . SELECT 절에 사용된 일반컬럼은 반드시 GROUP BY 절에 기술해야하며, SELECT 절에 사용하지 않은 컬럼도 GROUP BY 절에 기술가능
        . SELECT 절에 그룹함수만 사용된 경우 GROUP BY 절 생략(테이블 전체를 하나의 그룹으로 간주)
        . SUM(expr), AVG(expr), COUNT(*|expr), MAX(expr), MIN(expr)
    사용예)
        사원테이블에서 각 부서별 급여합계를 구하시오
        Alias는 부서코드, 급여합계
        
        SELECT DEPARTMENT_ID AS 부서코드,
               SUM(SALARY) AS 급여합계
        FROM HR.EMPLOYEES
        GROUP BY DEPARTMENT_ID
        ORDER BY 1;
        
     사용예)
        사원테이블에서 각 부서별 급여합계를 구하되, 급여합계가 100000이상인 부서만 조회하시오.
        Alias는 부서코드, 급여합계
        
        SELECT DEPARTMENT_ID AS 부서코드,
               SUM(SALARY) AS 급여합계
        FROM HR.EMPLOYEES
       -- WHERE SUM(SALARY) >= 100000 >> WHERE절에서는 집계함수가 적용되지않음
        GROUP BY DEPARTMENT_ID
        HAVING SUM(SALARY) >= 100000
        ORDER BY 1;
        
 -- 2022-0114-2)---------------------------------------------------------------------------------------------------------------
   
    문제]
        장바구니테이블에서 2005년 4월 제품별 판매수량집계를 구하시오
        
        SELECT CART_PROD AS 상품코드,
               SUM(CART_QTY) AS 판매수량
        FROM CART
        WHERE CART_NO LIKE '200504%'
        --WHERE SUBSTR(CART_NO,1,6) = '200504'
        GROUP BY CART_PROD
        ORDER BY 1;
        
    문제]
        장바구니테이블에서 2005년 4월 제품별 판매수량 합계가 10개 이상인 제품을 조회하시오
        
        SELECT CART_PROD AS 상품코드,
               SUM(CART_QTY) AS 판매수량
        FROM CART
        WHERE CART_NO LIKE '200504%'
        GROUP BY CART_PROD
        HAVING SUM(CART_QTY) >= 10
        ORDER BY 1;
    
    문제]
        매입테이블에서 2005년 1월-6월 월별 매입집계를 구하시오
        
        --SELECT TO_CHAR(BUY_DATE, 'YYYY/MM') AS 월,
        SELECT EXTRACT(MONTH FROM BUY_DATE) AS 월,
               SUM(BUY_QTY) AS 매입수량,
               SUM(BUY_COST * BUY_QTY) AS 매입금액합계
        FROM BUYPROD
        --WHERE TO_CHAR(BUY_DATE, 'YYYY/MM') BETWEEN '2005/01' AND '2005/06'
        WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050630')
        GROUP BY EXTRACT(MONTH FROM BUY_DATE)
        ORDER BY 1;
        
    
    문제]
        매입테이블에서 2005년 1월-6월 월별,제품별 매입금액 합계가 1000만원 이상인 정보만 조회하시오    
        
        SELECT TO_CHAR(BUY_DATE, 'YYYY/MM') AS 월,
               BUY_PROD AS 제품코드,
               SUM(BUY_QTY) AS 매입수량합계,
               SUM(BUY_COST * BUY_QTY) AS 매입금액합계
        FROM BUYPROD
        WHERE TO_CHAR(BUY_DATE, 'YYYY/MM') BETWEEN '2005/01' AND '2005/06'
        GROUP BY TO_CHAR(BUY_DATE, 'YYYY/MM'),BUY_PROD
        HAVING SUM(BUY_COST * BUY_QTY) >= 10000000
        ORDER BY 1;
        
 -- 2022-0114-3)---------------------------------------------------------------------------------------------------------------        
   
    문제]
        회원테이블에서 성별 마일리지 합계를 구하시오
        Alias는 구분, 마일리지 합계이며, 구분에는 '여성회원'과 '남성회원'을 출력
        
        SELECT CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR 
                         SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성회원'
                    ELSE '여성회원'
               END AS 구분,
               SUM(MEM_MILEAGE) AS 마일리지합계
        FROM MEMBER
        GROUP BY CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR 
                         SUBSTR(MEM_REGNO2,1,1) = '3' THEN '남성회원'
                    ELSE '여성회원'
               END;

        
    문제]
        회원테이블에서 연령대별 마일리지 합계를 조회하시오
        Alias는 구분, 마일리지 합계이며, 구분에는 '10대'~ '70대'를 출력
        
        (생년월일 사용)
        SELECT TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)||'대' AS 구분,
               SUM(MEM_MILEAGE) AS 마일리지합계
        FROM MEMBER
        GROUP BY TRUNC(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR),-1)||'대'
        ORDER BY 1;
        
        (주민등록번호 사용)
        SELECT CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR
                         SUBSTR(MEM_REGNO2,1,1) = '2' THEN
                         TRUNC(EXTRACT(YEAR FROM SYSDATE) - (TO_NUMBER(SUBSTR(MEM_REGNO1,1,2))+1900),-1)
                    ELSE TRUNC(EXTRACT(YEAR FROM SYSDATE) - (TO_NUMBER(SUBSTR(MEM_REGNO1,1,2))+2000),-1)
                    END ||'대' AS 구분,
               SUM(MEM_MILEAGE) AS 마일리지합계
        FROM MEMBER
        GROUP BY CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR
                         SUBSTR(MEM_REGNO2,1,1) = '2' THEN
                         TRUNC(EXTRACT(YEAR FROM SYSDATE) - (TO_NUMBER(SUBSTR(MEM_REGNO1,1,2))+1900),-1)
                    ELSE TRUNC(EXTRACT(YEAR FROM SYSDATE) - (TO_NUMBER(SUBSTR(MEM_REGNO1,1,2))+2000),-1)
                    END
        ORDER BY 1;
    사용예)
        사원테이블에서 각 부서별 평균급여를 구하시오
        Alias는 부서코드, 부서명, 평균급여
        
        SELECT A.DEPARTMENT_ID AS 부서코드,
               B.DEPARTMENT_NAME AS 부서명,
               ROUND(AVG(A.SALARY),1) AS 평균급여
        FROM HR.EMPLOYEES A, HR.DEPARTMENTS B
        WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
        GROUP BY A.DEPARTMENT_ID, B.DEPARTMENT_NAME
        ORDER BY 1;
        
 -- 2022-0114-4)---------------------------------------------------------------------------------------------------------------        
    
    사용예)
        전체사원의 평균급여를 출력
        
        SELECT ROUND(AVG(SALARY),-1) AS 평균급여,
               SUM(SALARY) AS 급여합계,
               COUNT(*) AS 사원수
        FROM HR.EMPLOYEES;
        
    사용예)
        사원들의 급여가 평균급여보다 적은 사원정보를 조회하시오
        Alias는 사원번호, 사원명, 부서코드, 직무코드, 급여, 평균급여
        
        SELECT A.EMPLOYEE_ID AS 사원번호,
               A.EMP_NAME AS 사원명,
               A.DEPARTMENT_ID AS 부서코드,
               A.JOB_ID AS 직무코드,
               A.SALARY AS 급여,
               B.ASAL AS 평균급여
        FROM HR.EMPLOYEES A, (SELECT ROUND(AVG(SALARY)) AS ASAL FROM HR.EMPLOYEES) B 
        WHERE A.SALARY < B.ASAL;
        
    
    사용예)
        상품테이블에서 분류별 평균매입가를 구하시오
        
        SELECT PROD_LGU AS 분류코드,
               ROUND(AVG(PROD_COST),-1) AS 평균매입가
        FROM PROD
        GROUP BY PROD_LGU
        ORDER BY 1;
        
        
    사용예)
        사원테이블에서 각 부서별 사원수를 구하시오
        Alias는 부서코드, 부서명, 사원수
        
        SELECT A.DEPARTMENT_ID AS 부서코드,
               B.DEPARTMENT_NAME AS 부서명,
               COUNT(*) AS 사원수
        FROM HR.EMPLOYEES A, HR.DEPARTMENTS B
        WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
        GROUP BY A.DEPARTMENT_ID, B.DEPARTMENT_NAME 
        ORDER BY 1;
        
    사용예)
        사원테이블에서 각 부서별 최대급여와 최소급여를 구하시오
        Alias는 부서코드, 부서명, 최대급여, 최소급여
        