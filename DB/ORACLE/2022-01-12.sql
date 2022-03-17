 -- 2022-0112) - 1
    4)FLOOR(n1), CEIL(n1)
     - FLOOR(n1) : n1과 같거나 작은수 중 제일 큰 정수(소숫점 내림)
     - CEIL(n1) : n1과 같거나 큰수 중 제일 작은 정수 => 소숫점 이하의 값이 존재하면 무조건 반올림한 값 반환
     - 급여, 세금 등 금액에 관련된 항목에 주로 사용
     
    사용예)
        SELECT FLOOR(12.987), FLOOR(12),
               FLOOR(-12.987), FLOOR(-12),
               CEIL(12.987), CEIL(12),
               CEIL(-12.987), CEIL(-12)
        FROM DUAL;
        
    5)MOD(n1,e), REMAINDER(n1,e)
     - MOD(n1,e) : n1을 e로 나눈 나머지를 반환
     - REMAINDER(n1,e) : n1을 e로 나눈 몫의 소숫점이하가 0.5보다 크면(나머지가 e의 중간값 보다 크면) 다음 몫이 되기위한 값 반환
     - MOD : 나머지 = n1 - e * FLOOR(n1/e)
     - RAMAINDER : 나머지 = n1 - e * ROUND(n1/e)
     
     ex) MOD(15,4)
         15 - 4 * FLOOR(15/4)
         15 - 4 * FLOOR(3.75)
         15 - 4 * 3 = 3
         
         REMAINDER(15,4)
         15 - 4 * ROUND(15/4)
         15 - 4 * ROUND(3.75)
         15 - 4 * 4 = -1
         
    사용예) SELECT CASE MOD((TRUNC(SYSDATE) - TO_DATE('00010101')-1),7)
                       WHEN 0 THEN '일요일'
                       WHEN 1 THEN '월요일'
                       WHEN 2 THEN '화요일'
                       WHEN 3 THEN '수요일'
                       WHEN 4 THEN '목요일'
                       WHEN 5 THEN '금요일'
                       ELSE '토요일'
                  END AS 요일       
           FROM DUAL;
 -- 2022-0112) - 2          
    6) WIDTH_BUCKET(val,min,max,b) -- 하한값은 범위에 포함되지만 상한값은 범위에 포함되지않음
     - 범위 하한 값 min에서 상한 값 max를 b개의 구간으로 나누었을때 제시된 값 val이 어느 구간에 속하는지를 판단하여 구간의 index를 반환
     
    사용예) 회원테이블에서 각 회원들의 마일리지를 입력받아 마일리지를 1000~9000사이를 9개 구간으로 구분할때 회원들의 마일리지가 어느 구간에 속하는지 
           판별하시오
    Alias는 회원번호, 회원명, 마일리지, 구간값
    
    SELECT MEM_ID AS 회원번호,
           MEM_NAME AS 회원명,
           MEM_MILEAGE AS 마일리지,
           WIDTH_BUCKET(MEM_MILEAGE,1000,8700,9)구간값
    FROM MEMBER;
    
    사용예)회원테이블에서 각 회원들의 마일리지를 입력받아 마일리지를 1000~9000사이를 9개 구간으로 구분할때 회원들의 마일리지가 어느 구간에 속하는지 판별하여
          등급을 나타내시오.단, 가장 많은 마일리지를 가지고있는 회원이 1등급이다
    Alias는 회원번호, 회원명, 마일리지, 등급
    
    SELECT MEM_ID AS 회원번호,
           MEM_NAME AS 회원명,
           MEM_MILEAGE AS 마일리지,
           10 - WIDTH_BUCKET(MEM_MILEAGE,1000,9000,9) AS 등급
    FROM MEMBER;
    
    사용예)사원테이블에서 사원들의 급여가 
         2000-5000 사이에 속하면 '저임금 사원' 
         5001-10000 "" '평균임금 사원'
         10001-25000 "" '고임금 사원'
         을 비고란에 출력
         Alias는 사원번호, 사원명, 부서코드, 직무코드, 급여, 비고
         
         SELECT EMPLOYEE_ID AS 사원번호,
                EMP_NAME AS 사원명,
                DEPARTMENT_ID AS 부서코드,
                JOB_ID AS 직무코드,
                SALARY AS 급여,
              --  CASE WHEN WIDTH_BUCKET(SALARY,2000,5000,1)=1 THEN '저임금 사원'
              --       WHEN WIDTH_BUCKET(SALARY,5001,10000,1)=1 THEN '평균임금 사원'
              --       ELSE '고임금 사원'
                  CASE WIDTH_BUCKET(SALARY,1,25000,5)
                       WHEN 1 THEN '저임금 사원'
                       WHEN 2 THEN '평균임금 사원'
                       ELSE '고임금 사원'
                END AS 비고
         FROM HR.EMPLOYEES;
         
 -- 2022-0112) - 3
    날짜함수
    1)SYSDATE
     - 시스템에서 제공하는 날짜정보(년,월,일,시,분,초)반환
     - 덧셈과(장래에 도래할 날짜) 뺄셈(지나간 날짜) 가능
     - 시간정보(시,분,초)출력은 TO_CHAR함수 사용
    사용예)
     SELECT SYSDATE, SYSDATE - 20, SYSDATE + 20
     FROM DUAL;
     
     SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS'),
            TO_CHAR(SYSDATE - 20,'YYYY-MM-DD HH24:MI:SS'),
            TO_CHAR(SYSDATE + 20,'YYYY-MM-DD HH24:MI:SS')
     FROM DUAL;
     
    2)ADD_MONTHS(d1,n)
     - 주어진 날짜 d1에 n개월을 더한 날짜 반환
     사용예)
      SELECT ADD_MONTHS(SYSDATE,4) FROM DUAL;
      SELECT EMP_NAME,
             HIRE_DATE,
             ADD_MONTHS(HIRE_DATE,3)
      FROM HR.EMPLOYEES;
      
    3)NEXT_DAY(d1,c)
     - d1일자이후 가장 먼저 만나는 c요일의 날짜 반환
     - c는 요일을 지칭하며 '일요일','일','월요일','월' 등으로 기술
     사용예)
      SELECT NEXT_DAY(SYSDATE, '월'),
             NEXT_DAY(SYSDATE,'수요일')
      FROM DUAL;
      
    4)LAST_DAY(d1)
     - 제시된 날짜 d1에 포함된 월의 마지막일자를 반환
     - 주로 2월의 마지막일을 반환받을때 사용
     사용예)
      매입테이블(BUYPROD)에서 2005년 2월 일자별 판매집계를 조회하시오
      Alias는 일자, 수량합계, 매입금액합계
      
      SELECT BUY_DATE AS 일자,
             SUM(BUY_QTY) AS 수량합계,
             SUM(BUY_QTY * BUY_COST) AS 매입금액합계
      FROM BUYPROD
      WHERE BUY_DATE BETWEEN TO_DATE('20050201') AND LAST_DAY(TO_DATE('20050201'))
      GROUP BY BUY_DATE
      ORDER BY 1;
 -- 2022-0112) - 4   
    5)EXTRACT(fmt FROM d1)
     - 제시된 날짜자료 d1에서 'fmt'에 해당하는 요소를 추출하여 반환
     - 반환되는 데이터 타입은 숫자데이터
     - 'fmt(Format String:형식문자열)'은 YEAR, MONTH, DAY, HOUR, MINUTE, SECOND 중 하나
     
     사용예)
      회원테이블에서 연령을 구하고 각 연령대별 회원수를 조회하시오
      Alias는 연령대, 회원수
      
      SELECT TRUNC((EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR)),-1)||'대' AS 연령대,
             COUNT(*) AS 회원수
      FROM MEMBER
      GROUP BY TRUNC((EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM MEM_BIR)),-1)
      ORDER BY 1;
      
    사용예)
     사원테이블에서 이번달에 입사한 사원들을 조회하시오.
     Alias는 사원번호, 사원명, 부서코드, 입사일
     
     SELECT EMPLOYEE_ID AS 사원번호,
            EMP_NAME AS 사원명,
            DEPARTMENT_ID AS 부서코드,
            HIRE_DATE AS 입사일
     FROM HR.EMPLOYEES
     WHERE EXTRACT(MONTH FROM HIRE_DATE) = EXTRACT(MONTH FROM SYSDATE)
     ORDER BY 4;
     
    6)MONTHS_BETWEEN(d1,d2)
     - 제시된 두 날짜자료 사이의 월 수를 반환
    사용예)
     1998년 3월 10일에 태어난 사람의 정확한 나이는?
     SELECT TRUNC(MONTHS_BETWEEN(SYSDATE, TO_DATE('19980310'))/12)||'년'||
            MOD(ROUND(MONTHS_BETWEEN(SYSDATE, TO_DATE('19980310'))),12)||'개월'
     FROM DUAL;