 -- 2022-0127-01)--------------------------------------------------------------------------------------------------------------
 
    1)변수(상수) 선언
     - 개발언어의 변수,상수와 같은의미
     (사용형식)
     변수(상수)명 [CONSTANT] 데이터 타입[(크기)]|NOT NULL [:=초기값];
     . 상수선언은 'CONSTANT'예약어 사용
     . 상수선언시 초기화가 필요
     . 데이터타입 : 표준SQL에서 사용하는 데이터타입,
                  PLS_INTEGER, BINARY_INTEGER : 4BYTE 정수,
                  BOOLEAN 타입 사용 가능
     ** 참조타입->데이터타입 대신 기술
        테이블명.컬럼명%TYPE : 해당 테이블의 컬럼과 같은 타입/크기로 변수(상수)를 선언
        테이블명%ROWTYPE : 해달 테이블의 한 행 전체와 같은 타입/크기로 변수 선언(c 언어의 STRUCTURE 타입과 유사)
     . 'NOT NULL' 지정시 반드시 초기값 지정해야함
     
    사용예)
        임의의 부서번호(10-110번)을 생성하고 해당부서에 속한 사원 중 입사일이 가장 빠른 사원의 사원번호, 사원명, 입사일, 부서명을 출력
        
        DECLARE
         V_DEPTNO HR.DEPARTMENTS.DEPARTMENT_ID%TYPE; -- 부서번호
         V_ENAME HR.EMPLOYEES.EMP_NAME%TYPE; -- 사원명
         V_EID HR.EMPLOYEES.EMPLOYEE_ID%TYPE; -- 사원번호
         V_HDATE DATE; -- 입사일
         V_DNAME VARCHAR2(100); -- 부서명
        BEGIN
         V_DEPTNO:=TRUNC(DBMS_RANDOM.VALUE(10,119),-1);
         SELECT TBL.AID,TBL.ANAME,TBL.ADATE,TBL.BNAME
           INTO V_EID,V_ENAME,V_HDATE,V_DNAME
           FROM (SELECT A.EMPLOYEE_ID AS AID,
                        A.EMP_NAME AS ANAME,
                        A.HIRE_DATE AS ADATE,
                        B.DEPARTMENT_NAME AS BNAME
                   FROM HR.EMPLOYEES A ,HR.DEPARTMENTS B
                  WHERE A.DEPARTMENT_ID = B.DEPARTMENT_ID
                    AND A.DEPARTMENT_ID = V_DEPTNO
                  ORDER BY 3)TBL
          WHERE ROWNUM=1;
          DBMS_OUTPUT.PUT_LINE('사원번호 : '||V_EID);
          DBMS_OUTPUT.PUT_LINE('사원명 : '||V_ENAME);
          DBMS_OUTPUT.PUT_LINE('입사일 : '||V_HDATE);
          DBMS_OUTPUT.PUT_LINE('부서명 : '||V_DNAME);
          DBMS_OUTPUT.PUT_LINE('-----------------');
        END;
        
  -- 2022-0127-02)--------------------------------------------------------------------------------------------------------------        
    
    사용예)
        길이를 하나 입력받아 그 길이를 반지름으로하는 원의 너비, 그 길이를 한 변으로하는 정사각형 너비를 각각 구하시오.
        
        ACCEPT P_LEN PROMPT '길이를 입력 : '
        DECLARE
         V_LENGTH NUMBER:=TO_NUMBER('&P_LEN');
         V_SEQUARE NUMBER:=0; -- 정사각형 너비
         V_CIRCLE NUMBER:=0; -- 원 너비
         V_PIE CONSTANT NUMBER:=3.1415926;
        BEGIN
         V_SEQUARE:=V_LENGTH*V_LENGTH;
         V_CIRCLE:=V_LENGTH*V_LENGTH*V_PIE;
         
         DBMS_OUTPUT.PUT_LINE('정사각형 너비 : '||V_SEQUARE);
         DBMS_OUTPUT.PUT_LINE('원의 너비 : '||V_CIRCLE);
        END;
        
    * 비교문
    - 오라클의 분기문으로 IF와 CASE WHEN ~ THEN 이 제공
    - IF문은 개발언어의 IF와 동일기능
    - CASE WHEN ~ THEN은 개발언어의 다중 분기와 같은 기능
    1. IF 문
     - 제시된 조건을 판단하여 서로 다른 방향으로 제어 이동
     (사용형식 - 1)
     IF 조건식 THEN
        명령-1;
     [ELSE
        명령-2;]
     END IF;
     
     (사용형식 - 2)
     IF 조건식-1 THEN
        명령-1;
     ELSIF 조건식-2 THEN
        명령-2;
          :
     ELSE
        명령-n;
     END IF;
     
     (사용형식 - 3)
     IF 조건식-1 THEN
        IF 조건식-2 THEN
            명령-1;
        ELSE
            명령-2;
        END IF;
     ELSIF 조건식-3 THEN
        명령-3;
          :
     ELSE
        명령-n;
     END IF;

 -- 2022-0127-03)--------------------------------------------------------------------------------------------------------------     
   
    사용예)
        첫 날에 100원, 두째날부터 전날의 2배씩 저축할때 최초로 100만원을 넘는 날과 그때까지 저축된 금액을 구하시오
        
        DECLARE
         V_DAYS NUMBER:=1; --날수
         V_SUM NUMBER:=0; --저축액수
         V_AMT NUMBER:=100; --그날 저축할 금액 
        BEGIN
         LOOP
           V_SUM:= V_SUM + V_AMT;
           EXIT WHEN V_SUM >= 1000000;
           V_AMT:= V_AMT * 2;
           V_DAYS:=V_DAYS + 1;
         END LOOP;
         DBMS_OUTPUT.PUT_LINE('경과일수 : '||V_DAYS);
         DBMS_OUTPUT.PUT_LINE('저축액 : '||V_SUM);
        END;
        
    사용예)
        회원테이블에서 회원들의 마일리지를 조회하여 마일리지가 
        0-1000 이면 '일반회원',
        1001-2000 이면 '열심회원',
        2001 이상이면 'VIP 회원'을 출력하는 프로시져를 작성하시오.
        Alias는 회원번호, 회원명, 마일리지, 비고
        
        DECLARE
         V_MID MEMBER.MEM_ID%TYPE;
         V_MNAME MEMBER.MEM_NAME%TYPE;
         V_MILE NUMBER:=0;
         V_REMARKS VARCHAR2(50);
         CURSOR CUR_MEM01
         IS
           SELECT MEM_ID,MEM_NAME,MEM_MILEAGE
           FROM MEMBER;
        BEGIN
         OPEN CUR_MEM01;
         DBMS_OUTPUT.PUT_LINE('회원번호   회원명   마일리지   비고');
         LOOP
           FETCH CUR_MEM01 INTO V_MID,V_MNAME,V_MILE; -- CURSOR에서 한줄씩 읽어와서 변수로 INTO
           EXIT WHEN CUR_MEM01%NOTFOUND; -- CURSOR에서 읽어올 자료가 없을때까지
           
           IF V_MILE <= 1000 THEN
             V_REMARKS:='일반회원';
           ELSIF V_MILE <= 2000 THEN
             V_REMARKS:='열심회원';
           ELSE
             V_REMARKS:='VIP 회원';
           END IF;
         DBMS_OUTPUT.PUT_LINE(V_MID||'     '||V_MNAME||'     '||V_MILE||'     '||V_REMARKS);
         DBMS_OUTPUT.PUT_LINE('----------------------------------------');
         END LOOP;
         DBMS_OUTPUT.PUT_LINE('전체회원수 : '||CUR_MEM01%ROWCOUNT);
         CLOSE CUR_MEM01;
        END;
        
 -- 2022-0127-04)--------------------------------------------------------------------------------------------------------------
 
    * 반복문
    - 오라클에서 제공하는 반복문은 LOOP,WHILE,FOR문이 있음
    
    1. LOOP
     - 반복문의 기본 구조 제공
     - 무한 루프
     - JAVA의 DO문과 유사
     (사용형식)
     LOOP
      반복처리 명령문(들);
      [EXIT WHEN 조건;]
          :
     END LOOP;
     . EXIT WHEN 조건 : '조건'이 참인경우 반복문을 빠져나감
     
    사용예)
        구구단의 6단을 LOOP문을 이용하여 작성
        
        DECLARE
         V_CNT NUMBER:=1;
        BEGIN
         LOOP
          EXIT WHEN V_CNT>9;
          DBMS_OUTPUT.PUT_LINE('6 * '||V_CNT||' = '||V_CNT * 6);
          V_CNT:=V_CNT+1;
         END LOOP;
        END;
        
    사용예)
        상품테이블에서 분류코드'P102'에 속한 상품정보를 모두 삭제하시오
        'P102'에 속한 상품코드는 'P102000001'부터 'P102000007'이다.
        
        DECLARE
         V_START NUMBER:=0; --시작값(6자리수)
         V_END NUMBER:=0; --끝값
         V_CNT NUMBER:=0; --상품코드를 1씩 증가시킬 값
         V_PID GOODS_1.PROD_ID%TYPE; --상품코드
        BEGIN
         SELECT MIN(TO_NUMBER(SUBSTR(PROD_ID,5))) INTO V_START   
           FROM GOODS_1
          WHERE PROD_LGU = 'P102';
         V_CNT:=V_START; 
         SELECT MAX(TO_NUMBER(SUBSTR(PROD_ID,5))) INTO V_END   
           FROM GOODS_1
          WHERE PROD_LGU = 'P102';
         LOOP 
          EXIT WHEN V_CNT>V_END;
          V_PID:='P102'||TRIM(TO_CHAR(V_CNT,'000000'));
          DELETE FROM GOODS_1
           WHERE PROD_ID = V_PID;
          V_CNT:=V_CNT + 1;
         END LOOP;
        END;