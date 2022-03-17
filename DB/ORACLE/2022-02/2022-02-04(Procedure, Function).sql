 -- 2022-0204-01)--------------------------------------------------------------------------------------------------------------
 
    * 저장프로시져(Stored Procedure : Procedure)
    - 서버에 저장된 실행 가능한 프로그램 모듈
    - 모든 응용프로그램에서 사용할 수 있도록 기능을 캡슐화
    - 보안성확보
    - 반환값이 없음 -- OUT을 사용하면 매개변수를 통해 결과를 반환할 수 있다.
    (사용형식)
    CREATE [OR REPLACE] PROCEDURE 프로시져명[(매개변수 [모드] 데이터타입[:=DEFAULT] 값)],... 
    AS|IS
        선언부;
    BEGIN
        실행부;
        [EXCEPTION
            예외처리;
        ]
    END;
    . '모드' : 매개변수의 역활 => IN(입력용), OUT(출력용), INOUT(입출력용:사용자제) -- 기본값 IN
    . '데이터타입' : 크기를 지정하면 안됨
    
    (실행문 형식)
    EXECUTE|EXEC 프로시져명(매개변수list);
     --단독 실행
    OR
    프로시져명(매개변수list);
     --다른 프로시져 또는 함수 및 익명블록에서 실행
     
    사용예)
        상품코드를 입력받아 2005년 매출수량과 매출금액 및 상품명을 출력하는 프로시져 작성
        
        CREATE OR REPLACE PROCEDURE PROC_CART01(P_PID IN PROD.PROD_ID%TYPE ) --VARCHAR2
        IS
          V_NAME PROD.PROD_NAME%TYPE;
          V_QTY NUMBER:=0;
          V_AMT NUMBER:=0;
        BEGIN
          SELECT A.PROD_NAME,SUM(B.CART_QTY),SUM(B.CART_QTY * A.PROD_PRICE)
            INTO V_NAME,V_QTY,V_AMT
            FROM PROD A, CART B
           WHERE A.PROD_ID = B.CART_PROD
             AND A.PROD_ID = P_PID
             AND B.CART_NO LIKE '2005%'
           GROUP BY A.PROD_NAME;
           
           DBMS_OUTPUT.PUT_LINE('상품코드 : '||P_PID);
           DBMS_OUTPUT.PUT_LINE('상품명 : '||V_NAME);
           DBMS_OUTPUT.PUT_LINE('매출수량 : '||V_QTY);
           DBMS_OUTPUT.PUT_LINE('매출금액 : '||V_AMT);
           DBMS_OUTPUT.PUT_LINE('----------------------------');
        END;
        
        (호출문)
        EXEC PROC_CART01('P202000002');
    
 -- 2022-0204-02)--------------------------------------------------------------------------------------------------------------
        
    사용예)
        부서번호를 입력받아 부서명,인원수,주소를 반환하는 프로시져 작성
        
        CREATE OR REPLACE PROCEDURE PROC_EMP01(
            P_DID IN HR.DEPARTMENTS.DEPARTMENT_ID%TYPE,
            P_DNAME OUT VARCHAR2,
            P_CNT OUT NUMBER,
            P_ADDR OUT VARCHAR2)
        IS
        BEGIN
          SELECT A.DEPARTMENT_NAME,
                 B.POSTAL_CODE||' '||B.STREET_ADDRESS||' '||B.CITY||' '||B.STATE_PROVINCE 
            INTO P_DNAME,P_ADDR
            FROM HR.DEPARTMENTS A, HR.LOCATIONS B
           WHERE A.LOCATION_ID = B.LOCATION_ID
             AND A.DEPARTMENT_ID = P_DID;
             
          SELECT COUNT(*)
            INTO P_CNT
            FROM HR.EMPLOYEES
           WHERE DEPARTMENT_ID = P_DID;
        END;
        
    (실행)
    ACCEPT P_ID PROMPT '부서코드 입력(10~110) : '
    DECLARE
      V_DNAME VARCHAR2(200);
      V_CNT NUMBER:=0;
      V_ADDR VARCHAR2(200);
    BEGIN
      PROC_EMP01(TO_NUMBER('&P_ID'),V_DNAME,V_CNT,V_ADDR);
      
      DBMS_OUTPUT.PUT_LINE('부서코드 : '||'&P_ID');
      DBMS_OUTPUT.PUT_LINE('부서명 : '||V_DNAME);
      DBMS_OUTPUT.PUT_LINE('인원수 : '||V_CNT);
      DBMS_OUTPUT.PUT_LINE('주소 : '||V_ADDR);
      DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END;
  
 -- 2022-0204-03)--------------------------------------------------------------------------------------------------------------  
    
    사용예)
        년도와 월을 입력 받아 해당 월에 가장 많이 구매한 회원의 회원번호,이름,주소,마일리지를 반환하는 프로시져 작성
        프로시져명은 'PROC_MEM01'이다
        
        (프로시져)
        CREATE OR REPLACE PROCEDURE PROC_MEM01(
          P_PERIOD IN NUMBER,
          P_MID OUT MEMBER.MEM_ID%TYPE,
          P_MNAME OUT MEMBER.MEM_NAME%TYPE,
          P_ADDR OUT VARCHAR2,
          P_MIL OUT NUMBER)
        IS
          V_PERIOD VARCHAR2(7):=P_PERIOD||'%';
        BEGIN
          SELECT C.CID
            INTO P_MID
            FROM (SELECT A.CART_MEMBER AS CID,
                         SUM(A.CART_QTY * B.PROD_PRICE)
                    FROM CART A, PROD B
                   WHERE A.CART_PROD = B.PROD_ID
                     AND A.CART_NO LIKE '2005'||'04'||'%'
                   GROUP BY A.CART_MEMBER
                   ORDER BY 2 DESC) C
           WHERE ROWNUM = 1;
           
           SELECT MEM_NAME,
                  MEM_ADD1||' '||MEM_ADD2,
                  MEM_MILEAGE
             INTO P_MNAME,P_ADDR,P_MIL
             FROM MEMBER
            WHERE MEM_ID = P_MID;
        END;
        
        (실행)
        ACCEPT P_PERIOD PROMPT ' 년도와 월 입력: '
        DECLARE
         V_MID MEMBER.MEM_ID%TYPE;
         V_MNAME MEMBER.MEM_NAME%TYPE;
         V_ADDR VARCHAR2(200);
         V_MIL NUMBER:=0;
        BEGIN
         PROC_MEM01(TO_NUMBER('&P_PERIOD'),V_MID,V_MNAME,V_ADDR,V_MIL);
         
         DBMS_OUTPUT.PUT_LINE('년도/월 : '||'&P_PERIOD');
         DBMS_OUTPUT.PUT_LINE('회원번호 : '||V_MID);
         DBMS_OUTPUT.PUT_LINE('회원명 : '||V_MNAME);
         DBMS_OUTPUT.PUT_LINE('주소 : '||V_ADDR);
         DBMS_OUTPUT.PUT_LINE('마일리지 : '||V_MIL);
        END;
            
    사용예)
        년도와 월을 입력받아 제품별 매입수량을 구한 뒤 재고수불테이블을 갱신하시오
        -- 제품별 매입수량은 데이터가 여러개이기 때문에 조건에 맞는 데이터를 CURSOR를 통해 구하고 FOR문을 통해 순차적으로 업데이트 해야한다.
        CREATE OR REPLACE PROCEDURE PROC_REMAIN01(
          P_PERIOD VARCHAR2
        )
        IS
          V_SDATE DATE:=TO_DATE(P_PERIOD||'01');
          V_EDATE DATE:=LAST_DAY(V_SDATE);
          V_QTY NUMBER:=0;
          CURSOR CUR_BUY02
          IS
            SELECT BUY_PROD AS BID,
                   SUM(BUY_QTY) AS BAMT
              FROM BUYPROD
             WHERE BUY_DATE BETWEEN V_SDATE AND V_EDATE
             GROUP BY BUY_PROD;
        BEGIN
          FOR REC IN CUR_BUY02 LOOP
            UPDATE REMAIN
               SET REMAIN_I = REMAIN_I + REC.BAMT,
                   REMAIN_J_99 = REMAIN_J_99 + REC.BAMT,
                   REMAIN_DATE = V_EDATE 
              WHERE REMAIN_YEAR = SUBSTR(P_PERIOD,1,4)
                AND PROD_ID = REC.BID;
             COMMIT;
          END LOOP;
        END;
        
        EXECUTE PROC_REMAIN01('200503');
        SELECT * FROM REMAIN;
        
 -- 2022-0204-04)--------------------------------------------------------------------------------------------------------------
 
    * 함수(User Defined Function : Function)
    - 특징은 procedure와 동일하나 반환값이 존재함
    - select 문의 select절, where절, update구문 등에 사용
    (사용형식)
    CREATE [OR REPLACE] FUNCTION 함수명[(
      변수명 [모드] 타입명 [:=[DEFAULT] 값])],...
      RETURN 타입명
    AS|IS
      선언부;
    BEGIN
      실행부;
      RETURN expr;
      [EXCEPTION
        예외처리문;
       ]
    END;
    
    사용예)
        거래처코드를 입력받아 해당 거래처에서 납품하는 상품정보를 조회하시오(함수사용)
        Alias는 거래처코드, 상품코드, 상품명, 매입단가
        
        (Function 생성)
        CREATE OR REPLACE FUNCTION FN_PROD_INFO(
          P_BID IN BUYER.BUYER_ID%TYPE)
          RETURN VARCHAR2
        IS
          V_RES VARCHAR2(100);
        BEGIN
          SELECT PROD_ID||' '||RPAD(PROD_NAME,20,' ')||' '||LPAD(PROD_COST,8,' ')
            INTO V_RES
            FROM PROD
           WHERE PROD_BUYER = P_BID
             AND ROWNUM = 1;
          RETURN V_RES;
          
          EXCEPTION
            WHEN OTHERS THEN
              DBMS_OUTPUT.PUT_LINE('에러발생'||SQLERRM);
              RETURN NULL;
        END;
        
        (실행)
        SELECT BUYER_ID,BUYER_NAME,FN_PROD_INFO(BUYER_ID)
          FROM BUYER;
          
    사용예)
        회원번호를 입력받아 회원명을 출력하는 함수를 작성하시오
        
        (Function 생성)
        CREATE OR REPLACE FUNCTION FN_MEM_NAME(
          P_MID MEMBER.MEM_ID%TYPE)
          RETURN MEMBER.MEM_NAME%TYPE
        IS
          V_NAME MEMBER.MEM_NAME%TYPE;
        BEGIN
          SELECT MEM_NAME
            INTO V_NAME
            FROM MEMBER
           WHERE MEM_ID = P_MID;
          RETURN V_NAME;
        END;
        
        (실행)
        SELECT MEM_ID AS 회원번호,
               FN_MEM_NAME(MEM_ID) AS 회원명
          FROM MEMBER;