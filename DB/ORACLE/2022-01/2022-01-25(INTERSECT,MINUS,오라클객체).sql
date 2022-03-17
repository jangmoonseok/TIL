 -- 2022-0125-01)--------------------------------------------------------------------------------------------------------------
 
    3) DENSE_RANK()
     - 순위 부여
     - 같은 값이면 같은 순위 부여하며, 차순위는 같은 순위 갯수와 관계없이 다음 순위 부여(ex. 1,1,1,2,3,4,...)
     - 나머지 특징은 RANK()와 동일
     
    4) ROW_NUMBER()
     - 순위 부여                    값  9 9 9 8 7 6...
     - 같은 값이라도 순차적인 순위 부여(ex. 1,2,3,4,5,6...)
     - 나머지 특징은 RANK()와 동일 
    
    사용예)
        사원테이블에서 급여가 5000이하인 사원들을 조회하고 급여에 따른 순위를 부여하시오
        
        (RANK() 함수 사용)
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               DEPARTMENT_ID AS 부서코드,
               SALARY AS 급여,
               RANK() OVER(ORDER BY SALARY DESC) AS 순위
          FROM HR.EMPLOYEES
         WHERE SALARY <= 5000;
         
        (DENS_RANK() 함수 사용)
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               DEPARTMENT_ID AS 부서코드,
               SALARY AS 급여,
               DENSE_RANK() OVER(ORDER BY SALARY DESC) AS 순위
          FROM HR.EMPLOYEES
         WHERE SALARY <= 5000;
         
        (ROW_NUMBER() 함수 사용)
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               DEPARTMENT_ID AS 부서코드,
               SALARY AS 급여,
               ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS 순위
          FROM HR.EMPLOYEES
         WHERE SALARY <= 5000;
    
    사용예)
        2005년도 금액기준 매입순위 상위 5개 품목과 매출순위 상위 5개 품목을 조회하여 양쪽 모두를 만족하는 상품정보를 출력하시오
        Alias는 상품코드, 상품명, 금액, 순위
        
        (2005년 금액기준 매입순위 상위5개 품목)
          SELECT C.BID AS 상품코드,
                 C.BNAME AS 상품명,
                 C.BSUM AS 금액,
                 C.BRANK AS 순위
            FROM (SELECT A.BUY_PROD AS BID,
                         B.PROD_NAME AS BNAME,
                         SUM(A.BUY_QTY * B.PROD_COST) AS BSUM,
                         RANK() OVER(ORDER BY SUM(A.BUY_QTY * B.PROD_COST) DESC) AS BRANK
                    FROM BUYPROD A, PROD B
                   WHERE A.BUY_PROD = B.PROD_ID
                     AND EXTRACT(YEAR FROM A.BUY_DATE) = 2005
                   GROUP BY A.BUY_PROD,B.PROD_NAME) C
           WHERE C.BRANK <= 5
          INTERSECT 
        --(2005년 금액기준 매출순위 상위5개 품목)
          SELECT D.CID AS 상품코드,
                 D.CNAME AS 상품명,
                 D.CSUM AS 금액,
                 D.CRANK AS 순위
            FROM (SELECT A.CART_PROD AS CID,
                         B.PROD_NAME AS CNAME,
                         SUM(A.CART_QTY * B.PROD_COST) AS CSUM,
                         RANK() OVER(ORDER BY SUM(A.CART_QTY * B.PROD_COST) DESC) AS CRANK
                    FROM CART A, PROD B
                   WHERE A.CART_PROD = B.PROD_ID
                     AND A.CART_NO LIKE '2005%'
                   GROUP BY A.CART_PROD,B.PROD_NAME) D
           WHERE D.CRANK <= 5;
           
    사용예)
        2005년 1월과 5월에 동시에 매입된 상품정보를 조회하시오.
        Alias는 상품코드, 상품명, 분류명
        
        (2005년 1월 매입상품)
        SELECT A.BUY_PROD AS 상품코드,
               B.PROD_NAME AS 상품명,
               C.LPROD_NM AS 분류명
          FROM BUYPROD A, PROD B, LPROD C
         WHERE A.BUY_PROD = B.PROD_ID
           AND B.PROD_LGU = C.LPROD_GU
           AND A.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
        INTERSECT   
        --(2005년 5월 매입상품)
        SELECT A.BUY_PROD AS 상품코드,
               B.PROD_NAME AS 상품명,
               C.LPROD_NM AS 분류명
          FROM BUYPROD A, PROD B, LPROD C
         WHERE A.BUY_PROD = B.PROD_ID
           AND B.PROD_LGU = C.LPROD_GU
           AND A.BUY_DATE BETWEEN TO_DATE('20050501') AND LAST_DAY('20050501')
         ORDER BY 1
         
 -- 2022-0125-02)--------------------------------------------------------------------------------------------------------------
 
    3. MINUS
     - 두 집합의 차집합결과를 반환
     - 기술 순서가 중요
     
    사용예)
        2005년 6,7월 중 6월달에만 판매된 상품정보를 조회하시오
        Alias는 상품코드, 상품명, 판매가, 매입가
        
        (2005년 6월에 판매된 상품)
        SELECT DISTINCT A.CART_PROD AS 상품코드,
               B.PROD_NAME AS 상품명,
               B.PROD_PRICE AS 판매가,
               B.PROD_COST AS 매입가
          FROM CART A, PROD B
         WHERE A.CART_PROD = B.PROD_ID
           AND A.CART_NO LIKE '200506%'
        MINUS   
        --(2005년 7월에 판매된 상품)
        SELECT DISTINCT A.CART_PROD AS 상품코드,
               B.PROD_NAME AS 상품명,
               B.PROD_PRICE AS 판매가,
               B.PROD_COST AS 매입가
          FROM CART A, PROD B
         WHERE A.CART_PROD = B.PROD_ID
           AND A.CART_NO LIKE '200507%'
         ORDER BY 1;
         
        (EXISTS 연산자)
        SELECT DISTINCT A.CART_PROD AS 상품코드,
               B.PROD_NAME AS 상품명,
               B.PROD_PRICE AS 판매가,
               B.PROD_COST AS 매입가
          FROM CART A, PROD B
         WHERE A.CART_PROD = B.PROD_ID
           AND A.CART_NO LIKE '200506%'
           AND NOT EXISTS (SELECT 1
                             FROM CART C
                            WHERE C.CART_PROD = A.CART_PROD
                              AND C.CART_NO LIKE '200507%')
         ORDER BY 1;         
         
    * 오라클 객체
    - 오라클에서 제공하는 OBJECT로 VIEW,INDEX,PROCEDURE,FUNCTION,PACKAGE,TRIGGER,SYNONYM,SEQUENCE,DIRECTORY 등이 있음
    - 생성시 CREATE, 제거시 DROP 명령 사용
    
    1. VIEW
     - 가상의 테이블
     - 기존의 테이블이나 뷰를 통하여 새로운 SELECT문의 결과를 테이블처럼 사용
     - 테이블과 독립적
     - 필요한 정보가 여러 테이블에 분산된 경우
     - 테이블의 모든 자료에 대한 접근을 제한하고 필요한 자료만을 제공하는 경우
     (사용형식)
     CREATE [OR REPLACE] VIEW 뷰이름[(컬럼list)]
     AS
        SELECT 문
        [WITH CHECK OPTION] -- 내용을 변경했을때 조건에 위배되는 내용은 UPDATE,INSERT,DELETE될 수 없다.
        [WITH READ ONLY]; -- VIEW를 수정했을 때 원본이 수정되는것을 막기 위해 고칠수 없는 형식으로 만들기위해 사용
        
 -- 2022-0125-03)--------------------------------------------------------------------------------------------------------------
    
    사용예)
        회원테이블에서 마일리지가 2000이상인 회원의 회원번호, 이름, 직업, 마일리지로 구성된 뷰를 생성하시오
        
        (1)
        CREATE OR REPLACE VIEW V_MEM(MID,MNAME,MJOB,MILE)
        AS   
            SELECT MEM_ID AS 회원번호,
                   MEM_NAME AS 이름,
                   MEM_JOB AS 직업,
                   MEM_MILEAGE AS 마일리지
              FROM MEMBER
             WHERE MEM_MILEAGE >= 2000;
             
        (2)
        CREATE OR REPLACE VIEW V_MEM
        AS   
            SELECT MEM_ID AS 회원번호,
                   MEM_NAME AS 이름,
                   MEM_JOB AS 직업,
                   MEM_MILEAGE AS 마일리지
              FROM MEMBER
             WHERE MEM_MILEAGE >= 2000;
             
        (3)
        CREATE OR REPLACE VIEW V_MEM
        AS   
            SELECT MEM_ID,
                   MEM_NAME,
                   MEM_JOB,
                   MEM_MILEAGE
              FROM MEMBER
             WHERE MEM_MILEAGE >= 2000;
             
        SELECT * FROM V_MEM;
    
    사용예)
        생성된 뷰 V_MEM에서 'r001'회원의 마일리지를 500으로 변경하시오
        
        UPDATE V_MEM
           SET MEM_MILEAGE = 500
         WHERE MEM_ID = 'r001'; 
         
        SELECT * FROM V_MEM;
        
        SELECT MEM_ID,MEM_MILEAGE
          FROM MEMBER
         WHERE MEM_ID = 'r001'; -- VIEW에서 변경된 내용이 원본에서도 변경됨
        
        --(WITH CHECK OPTION)
        CREATE OR REPLACE VIEW V_MEM
        AS   
            SELECT MEM_ID AS 회원번호,
                   MEM_NAME AS 이름,
                   MEM_JOB AS 직업,
                   MEM_MILEAGE AS 마일리지
              FROM MEMBER
             WHERE MEM_MILEAGE >= 2000
            WITH CHECK OPTION;
        
    사용예)
        뷰 V_MEM의 'r001'회원의 마일리지를 1500으로 변경하시오
        
        UPDATE V_MEM
           SET 마일리지 = 1500
         WHERE 회원번호 = 'r001'; -- V_MEM에 WITH CHECK OPTION이 설정되었으므로 조건에 위배되는 내용으로 변경불가
        
        ** 회원테이블에서 'n001'회원의 마일리지를 2500으로 변경하시오
        UPDATE MEMBER
           SET MEM_MILEAGE = 2500
         WHERE MEM_ID = 'n001'; -- 원본에서 V_MEM의 조건에 맞도록 내용을 변경하면 V_MEM도 변경된다.
         
        SELECT * FROM V_MEM;
        
        ** 회원테이블에서 'f001'회원의 마일리지를 1500으로 변경하시오
        UPDATE MEMBER
           SET MEM_MILEAGE = 1500
         WHERE MEM_ID = 'f001'; -- V_MEM에 WITH CHECK OPTION이 설정되어있지만 원본테이블은 제한없이 변경가능하다
        
       COMMIT;
       
       --(WITH READ ONLY)
       CREATE OR REPLACE VIEW V_MEM(MID,MNAME,MJOB,MILE)
       AS   
         SELECT MEM_ID AS 회원번호,
                MEM_NAME AS 이름,
                MEM_JOB AS 직업,
                MEM_MILEAGE AS 마일리지
           FROM MEMBER
          WHERE MEM_MILEAGE >= 2000
          WITH READ ONLY;
         
        ** 생성된 뷰 V_MEM의 모든 자료를 삭제하시오.
        DELETE FROM V_MEM; -- READ ONLY VIEW이므로 삭제불가
        
 -- 2022-0125-04)--------------------------------------------------------------------------------------------------------------
 
    ** VIEW 사용시 주의할 점
     (1) VIEW 생성시 WITH절을 사용한 제약조건이 부여된 경우 ORDER BY 절 사용불가.
     (2) VIEW 생성에 집계함수가 사용된 경우 뷰에 INSERT,UPDATE,DELETE를 사용할 수 없다.
     (3) VIEW의 컬럼이 표현식(CASE~WHEN)이나 함수가 사용된 경우 컬럼추가 또는 수정이 불가
     (4) Pseudo Column(CURVAL, NEXTVAL등) 사용 불가
     
    사용예)
        CREATE OR REPLACE VIEW V_CART
        AS
          SELECT CART_PROD AS CID,
                 COUNT(*) AS CNT
            FROM CART
           WHERE CART_NO LIKE '200505%'
           GROUP BY CART_PROD
           ORDER BY 1;
        
        SELECT * FROm V_CART;
        
        UPDATE V_CART
           SET CNT = 10
          WHERE CID = 'P101000001'; -- 집계함수가 사용된 VIEW는 UPDATE 불가
          
    사용예)
    CREATE OR REPLACE VIEW V_MEM02
    AS
        SELECT MEM_ID AS MID,
               MEM_NAME AS MNAME,
               CASE WHEN SUBSTR(MEM_REGNO2,1,1) = '1' OR
                         SUBSTR(MEM_REGNO2,1,1) = '3' THEN
                         '남성'
               ELSE
                         '여성'
               END AS GUBUN
          FROM MEMBER;
    
    UPDATE V_MEM02
       SET GUBUN = '여성회원'
     WHERE GUBUN = '여성';  -- CASE WHEN 표현식을 사용한 VIEW는 UPDATE 불가
     
     
    2. SEQUENCE
     - 차례대로 증가되는 연속적인 값 반환
     - 테이블과 독립적이며 여러 테이블이 동시에 사용 가능
     - 기본키가 없거나 PK를 의미있게 만들지 않아도 되는 경우
     - 자동적으로 부여되는 번호가 필요한 경우 -- ex)CART테이블의 CART_NO 날짜다음에오는 5자리 숫자
     (사용형식)
     CREATE SEQUENCE 시퀀스명
       [START WITH n] -- 시작값(n)설정 생략하면 MINVALUE가 할당
       [INCREMENT BY n] -- 증가[감소]값, 음수이면 감소값
       [MAXVALUE n|NOMAXVALUE] -- 최대값 설정, 기본은 NOMAXVALUE이며(최대10^27)
       [MINVALUE n|NOMINVALUE] -- 최소값 설정, 기본은 NOMINVALUE이며(최소1)
       [CYCLE|NOCYCLE] -- 최대(최소)값 도달 후 다시 시퀀스생성여부, 기본은 NOCYCLE
       [CACHE n|NOCACHE] -- 메모리에 미리 생성여부, 기본은 CACHE 20
       [ORDER|NOORDER] -- 위의 선택사항대로 시퀀스 생성을 보증여부, NOORDER가 기본
        