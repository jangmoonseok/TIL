 -- 2022-0203-01)--------------------------------------------------------------------------------------------------------------
 
    2. WHILE 문 -- CURSOR를 쓸때 WHILE문을 잘 사용하지 않음
     - 개발언어의 WHILE 문과 같은 구조 및 기능 제공
     (사용형식)
     WHILE 조건문 LOOP
        반복처리명령문;
     END LOOP;
      . 조건문의 판단 결과가 참이면 반복수행, 거짓이면 반복문을 벗어남
      
    사용예)
        구구단의 6단을 WHILE 문으로 구성하시오.
        
        DECLARE
         V_CNT NUMBER:=1;
         V_RES VARCHAR2(100);
        BEGIN
         WHILE V_CNT<=9 LOOP
          V_RES:='6 * '||V_CNT||' = '||V_CNT*6;
          DBMS_OUTPUT.PUT_LINE(V_RES);
          V_CNT:=V_CNT+1;
         END LOOP;
        END;
        
    사용예)
        2005년도에 장바구니테이블에서 분류코드 'P102'에 속한 상품들의 매출집계를 구하시오(커서이용)
        Alias는 상품코드,상품명,수량집계,금액
        ('P102'에 속한 상품코드)
        SELECT PROD_ID
          FROM PROD
         WHERE PROD_LGU = 'P102';
         
        DECLARE
         V_PID PROD.PROD_ID%TYPE;
         V_PNAME PROD.PROD_NAME%TYPE;
         V_QTY NUMBER:=0; --수량집계
         V_AMT NUMBER:=0; --금액
         CURSOR CUR_CART01 IS
           SELECT PROD_ID
             FROM PROD
            WHERE PROD_LGU = 'P102';
        BEGIN
         OPEN CUR_CART01;
         FETCH CUR_CART01 INTO V_PID; -- WHILE문의 조건문인 FOUND를 만나기 전에 제일 첫번째 행을 FETCH한다.
         WHILE CUR_CART01%FOUND LOOP -- FETCH문을 만나기 전에 FOUND는 참,거짓 판별을 할 수 없다.
          SELECT B.PROD_NAME,
                 SUM(A.CART_QTY),
                 SUM(A.CART_QTY*B.PROD_PRICE)
            INTO V_PNAME,V_QTY,V_AMT
            FROM CART A, PROD B
           WHERE B.PROD_ID = V_PID
             AND A.CART_PROD = B.PROD_ID
             AND A.CART_NO LIKE '2005%'
           GROUP BY B.PROD_NAME;
          DBMS_OUTPUT.PUT_LINE('상품코드 : '||V_PID);
          DBMS_OUTPUT.PUT_LINE('상품명 : '||V_PNAME);
          DBMS_OUTPUT.PUT_LINE('수량집계 : '||V_QTY);
          DBMS_OUTPUT.PUT_LINE('금액 : '||V_AMT);
          DBMS_OUTPUT.PUT_LINE('----------------------------');
          FETCH CUR_CART01 INTO V_PID; -- 두번째 행 부터 WHILE문을 돌며 FETCH
         END LOOP;
         CLOSE CUR_CART01;
        END;
        
 -- 2022-0203-02)--------------------------------------------------------------------------------------------------------------
        
    3. FOR 문
     - 개발언어의 FOR 문과 같은 기능 제공
     - 반복횟수가 중요하거나 반복횟수를 정확히 알고 있는 경우 사용
     (사용형식-일반 FOR 문)
        FOR 인덱스 IN[REVERSE] 초기값..최종값
        LOOP
         반복처리 명령문;
        END LOOP;    
        . '인덱스' : 제어변수 역할(시스템에서 자동 생성)
        . 'REVERSE' : 역순으로 반복하는 경우
        . '초기값..최종값' : 초기값부터 최종값 1씩 증가시켜 인덱스에 할당
            
     사용예)
         구구단 6단 출력
        
        DECLARE
        BEGIN
         FOR I IN 1..9 LOOP
          DBMS_OUTPUT.PUT_LINE('6 * '||I||' = '|| 6 * I);
         END LOOP;
        END;
        
     (사용형식-커서 FOR 문)
        FOR 레코드명 IN 커서명|커서문
        LOOP
         반복처리 명령문;
        END LOOP;    
         . CURSOR 문을 FOR 문을 이용하여 처리하는 경우 OPEN/FETCH/CLOSE 문 생략
         . 커서 선언문도 FOR 문 안에 in-line 서브쿼리형식으로 기술 가능
         . 커서내의 컬럼은 '레코드명.컬럼명'으로 참조함
         
    ** 커서(CURSOR)
     - 커서는 SQL 명령으로 영향받은 행들의 집합
     - SELECT 문의 커서는 뷰와 같음
     - 커서는 묵시적커서(IMPLICITE CURSOR)와 명시적커서(EXPLICITE CURSOR)가 존재
     1. 묵시적 커서
      . 이름이 없는 커서
      . SELECT 문의 결과 집합
      . 묵시적커서는 항상 CLOSE되어 있어 커서 내부의 자료 접근이 불가능
      . 묵시적커서 속성
      ---------------------------------------------------
       커서            의미
      ---------------------------------------------------
       SQL%ISOPEN      커서가 접근가능한 상태(OPEN)인가를 판단
                       하여 OPEN상태이면 참, 아니면 거짓을 반환
                       묵시적 커서는 항상 FALSE 임
       SQL%FOUND       커서내의 행(ROW)가 존재하면 참, 없으면 거짓
                       반환
       SQL%NOTFOUND    커서내의 행(ROW)가 존재하면 거짓, 없으면 참
                       반환
       SQL%ROWCOUNT    커서내의 행의 수 반환
      ---------------------------------------------------
      
     2. 명시적 커서
      . 이름이 있는 커서
      . 보통 선언부에서 선언
      . 명시적 커서는 '선언' -> 'OPEN' -> 'FETCH' -> 'CLOSE' 단계 순으로 처리
        (단, FOR문은 예외)
      1)선언
       - 선언부에 기술
       (선언형식)
       CURSOR 커서명[(변수명 타입[,...])]
       IS
         SELECT 문;
       . '변수명'에 값을 할당하는 곳은 OPEN 문에서 수행
      2)OPEN 문
       - 커서를 사용하기 위한 명령으로 선언된 커서를 접근 가능한 상태로 만듬
       - 커서에 매개변수를 사용한 경우 OPEN 문에서 값을 배정
       - BEGIN 블록에 기술
       (사용형식)
       OPEN 커서명[(값,..)];
      3)FETCH 문
       - 커서내부에 각 행단위로 자료를 읽어 변수에 할당
       - BEGIN 블록에 기술
       (사용형식)
       FETCH 커서명 INTO 변수명1[,변수명2,...]
        . 커서내부의 컬럼의 갯수와 순서 및 자료타입과 INTO 절의 변수 갯수,순서,자료타입은 일치해야한다.
        . 커서컬럼 값을 변수에 차례대로 할당
      4)CLOSE 문
       - 사용이 종료된 커서는 반드시 CLOSE 되어야 한다.
       - 커서는 다시 OPEN 가능하나 데이터를 FETCH,갱신,삭제할 수 없다.
       
 -- 2022-0203-03)--------------------------------------------------------------------------------------------------------------
    
    사용예)
        부서번호 100번 부서에 속한 사원정보를 커서를 이용하여 출력하시오
        
        (LOOP 사용)
        DECLARE
         CURSOR CUR_EMP01(P_DID HR.DEPARTMENTS.DEPARTMENT_ID%TYPE)
         IS
           SELECT A.EMPLOYEE_ID, A.EMP_NAME, B.JOB_TITLE, A.HIRE_DATE
             FROM HR.EMPLOYEES A, HR.JOBS B
            WHERE A.JOB_ID = B.JOB_ID
              AND A.DEPARTMENT_ID = P_DID;
         V_EID HR.EMPLOYEES.EMPLOYEE_ID%TYPE;
         V_ENAME HR.EMPLOYEES.EMP_NAME%TYPE;
         V_JTITLE HR.JOBS.JOB_TITLE%TYPE;
         V_HDATE HR.EMPLOYEES.HIRE_DATE%TYPE;
        BEGIN
         OPEN CUR_EMP01(100);
         LOOP
          FETCH CUR_EMP01 INTO V_EID,V_ENAME,V_JTITLE,V_HDATE;
          EXIT WHEN CUR_EMP01%NOTFOUND;
          DBMS_OUTPUT.PUT_LINE('사원번호 : '||V_EID);
          DBMS_OUTPUT.PUT_LINE('사원명 : '||V_ENAME);
          DBMS_OUTPUT.PUT_LINE('직무명 : '||V_JTITLE);
          DBMS_OUTPUT.PUT_LINE('입사일 : '||V_HDATE);
          DBMS_OUTPUT.PUT_LINE('-----------------------------');
         END LOOP;
        END;
        
        --WHILE 사용
        DECLARE
         CURSOR CUR_EMP01(P_DID HR.DEPARTMENTS.DEPARTMENT_ID%TYPE)
         IS
           SELECT A.EMPLOYEE_ID, A.EMP_NAME, B.JOB_TITLE, A.HIRE_DATE
             FROM HR.EMPLOYEES A, HR.JOBS B
            WHERE A.JOB_ID = B.JOB_ID
              AND A.DEPARTMENT_ID = P_DID;
         V_EID HR.EMPLOYEES.EMPLOYEE_ID%TYPE;
         V_ENAME HR.EMPLOYEES.EMP_NAME%TYPE;
         V_JTITLE HR.JOBS.JOB_TITLE%TYPE;
         V_HDATE HR.EMPLOYEES.HIRE_DATE%TYPE;
        BEGIN
         OPEN CUR_EMP01(100);
         FETCH CUR_EMP01 INTO V_EID,V_ENAME,V_JTITLE,V_HDATE;
         WHILE CUR_EMP01%FOUND
         LOOP
          DBMS_OUTPUT.PUT_LINE('사원번호 : '||V_EID);
          DBMS_OUTPUT.PUT_LINE('사원명 : '||V_ENAME);
          DBMS_OUTPUT.PUT_LINE('직무명 : '||V_JTITLE);
          DBMS_OUTPUT.PUT_LINE('입사일 : '||V_HDATE);
          DBMS_OUTPUT.PUT_LINE('-----------------------------');
          FETCH CUR_EMP01 INTO V_EID,V_ENAME,V_JTITLE,V_HDATE;
         END LOOP;
         CLOSE CUR_EMP01;
        END;
        
        --FOR 문 사용
         DECLARE
          CURSOR CUR_EMP01
          IS
            SELECT A.EMPLOYEE_ID AS AEID,
                   A.EMP_NAME AS AENAME,
                   B.JOB_TITLE AS BJTITLE,
                   A.HIRE_DATE AS AHDATE
              FROM HR.EMPLOYEES A, HR.JOBS B
             WHERE A.JOB_ID = B.JOB_ID
               AND A.DEPARTMENT_ID = 100;
         BEGIN
          FOR REC IN CUR_EMP01 LOOP
           DBMS_OUTPUT.PUT_LINE('사원번호 : '||REC.AEID);
           DBMS_OUTPUT.PUT_LINE('사원명 : '||REC.AENAME);
           DBMS_OUTPUT.PUT_LINE('직무명 : '||REC.BJTITLE);
           DBMS_OUTPUT.PUT_LINE('입사일 : '||REC.AHDATE);
           DBMS_OUTPUT.PUT_LINE('-----------------------------');
          END LOOP;
         END;
         
    사용예)
        거주지가 '충남'인 회원들의 2005년 구매정보를 조회하시오
        Alias는 회원번호,회원명,주소,총구매금액,마일리지
        (커서 : 거주지가 충남인 회원들의 회원번호)
        
        DECLARE
         V_RES VARCHAR2(500);
         V_AMT NUMBER:=0; --총 구매금액
         CURSOR CUR_CART02 IS
          SELECT MEM_ID,
                 MEM_NAME,
                 MEM_ADD1||' '||MEM_ADD2 AS ADDR,
                 MEM_MILEAGE
            FROM MEMBER
           WHERE MEM_ADD1 LIKE '충남%';
        BEGIN
         FOR REC IN CUR_CART02 LOOP
            SELECT SUM(A.CART_QTY * B.PROD_PRICE) INTO V_AMT
              FROM CART A, PROD B
             WHERE A.CART_MEMBER = REC.MEM_ID
               AND A.CART_PROD = B.PROD_ID
               AND A.CART_NO LIKE '2005%';
             V_RES:=REC.MEM_ID||' '||REC.MEM_NAME||' '||RPAD(REC.ADDR,30,' ')||' '||LPAD(V_AMT,9,' ')||' '||
             LPAD(REC.MEM_MILEAGE,6,' ');
             DBMS_OUTPUT.PUT_LINE(V_RES);
         END LOOP;
        END;
        
 -- 2022-0203-04)--------------------------------------------------------------------------------------------------------------
 
    사용예)
        재고량이 많은 5개의 제품에 대한 2005년 매입정보를 조회하시오
        Alias는 제품코드,제품명,매입일자,매입수량
        
        DECLARE
         CURSOR CUR_BUYPROD01 IS
           SELECT A.PROD_ID AS APID
             FROM (SELECT PROD_ID,
                          REMAIN_J_99
                     FROM REMAIN
                    ORDER BY 2 DESC) A
            WHERE ROWNUM <= 5;
         V_PNAME PROD.PROD_NAME%TYPE;
         V_DATE DATE;
         V_QTY NUMBER:=0;
        BEGIN
         FOR REC IN CUR_BUYPROD01 LOOP
           SELECT PROD_NAME INTO V_PNAME
             FROM PROD
            WHERE PROD_ID = REC.APID;
            
           SELECT SUM(BUY_QTY) INTO V_QTY
            -- INTO V_PNAME,V_DATE,V_QTY
             FROM BUYPROD 
            WHERE BUY_PROD = REC.APID
              AND EXTRACT(YEAR FROM BUY_DATE) = 2005;
           DBMS_OUTPUT.PUT_LINE('제품코드 : '||REC.APID);
           DBMS_OUTPUT.PUT_LINE('제품명 : '||V_PNAME);
           DBMS_OUTPUT.PUT_LINE('매입수량 : '||V_QTY);
           DBMS_OUTPUT.PUT_LINE('----------------------------------');
         END LOOP;
        END;
           