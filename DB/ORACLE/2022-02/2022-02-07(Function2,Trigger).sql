 -- 2022-0207-01)--------------------------------------------------------------------------------------------------------------
 
    사용예)
        기간(년,월)을 입력 받아 상품별 매출금액집계를 조회하시오
        
        (함수 생성)
        CREATE OR REPLACE FUNCTION FN_SUM_CART(
          P_PERIOD VARCHAR2,
          P_PID PROD.PROD_ID%TYPE
          )
          RETURN NUMBER
        IS
          V_PERIOD CHAR(7):=P_PERIOD||'%';
          V_SUM NUMBER:=0;
        BEGIN
          SELECT SUM(A.CART_QTY*B.PROD_PRICE) INTO V_SUM
            FROM CART A, PROD B
           WHERE A.CART_PROD = P_PID
             AND A.CART_NO LIKE V_PERIOD
             AND A.CART_PROD = B.PROD_ID;
          RETURN V_SUM;
          
          EXCEPTION WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('예외발생 : '||SQLERRM);
            RETURN NULL;
        END;
        
        (실행)
        ACCEPT P_PERIOD PROMPT '기간입력(년/월)'
        DECLARE
          V_AMT NUMBER:=0;
          V_RES VARCHAR2(100);
          CURSOR CUR_PROD01
          IS
            SELECT PROD_ID,PROD_NAME
              FROM PROD;
        BEGIN
          FOR REC IN CUR_PROD01 LOOP
            V_AMT:=NVL(FN_SUM_CART('&P_PERIOD',REC.PROD_ID),0);
            V_RES:=REC.PROD_ID||' '||RPAD(REC.PROD_NAME,25,' ')||LPAD(V_AMT,9,' ');
          DBMS_OUTPUT.PUT_LINE(V_RES);
          DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------');
          END LOOP;
        END;
        
 -- 2022-0207-02)--------------------------------------------------------------------------------------------------------------
 
    사용예)
        사원번호를 입력받아 해당 사원이 속한 부서명과 주소를 반환하는 함수를 작성하시오.
        
        CREATE OR REPLACE FUNCTION FN_EMP_ADDR(
          P_EID HR.EMPLOYEES.EMPLOYEE_ID%TYPE)
          RETURN VARCHAR2
        IS
          V_RES VARCHAR2(200);
          V_ADDR VARCHAR2(100);
        BEGIN
          SELECT A.DEPARTMENT_NAME,
                 'ZIP CODE : '||B.POSTAL_CODE||' '||B.STREET_ADDRESS||', '||B.CITY||' '||B.STATE_PROVINCE
            INTO V_RES,V_ADDR
            FROM HR.DEPARTMENTS A, HR.LOCATIONS B, HR.EMPLOYEES C
           WHERE C.EMPLOYEE_ID = P_EID
             AND C.DEPARTMENT_ID = A.DEPARTMENT_ID
             AND A.LOCATION_ID = B.LOCATION_ID;
          V_RES:=V_RES||'  '||V_ADDR;
          RETURN V_RES;
          
          EXCEPTION WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('예외발생 : '||SQLERRM);
            RETURN NULL; 
        END;
        
        SELECT EMPLOYEE_ID AS 사원번호,
               EMP_NAME AS 사원명,
               FN_EMP_ADDR(EMPLOYEE_ID) AS "부서명 및 주소"
          FROM HR.EMPLOYEES;
          
    사용예)
        년도를 입력받아 해당년도의 상품별 매입수량합계와 매입금액합계를 구하는 함수들을 만들고
        매입금액 기준 상위 5개의 매입집계를 출력하시오.
        Alias는 상품코드,상품명,매입수량합계,매입금액합계
        
        CREATE OR REPLACE FUNCTION FN_QTY_BUY(
          P_YEAR VARCHAR2,
          P_PID PROD.PROD_ID%TYPE)
          RETURN NUMBER
        IS
          V_QTY NUMBER:=0;
        BEGIN
          SELECT SUM(BUY_QTY)
            INTO V_QTY
            FROM BUYPROD
           WHERE BUY_PROD = P_PID
             AND EXTRACT(YEAR FROM BUY_DATE) = P_YEAR;
           RETURN V_QTY;
           
           EXCEPTION WHEN OTHERS THEN
             DBMS_OUTPUT.PUT_LINE('예외발생: '||SQLERRM);
             RETURN NULL;
        END;
        
        CREATE OR REPLACE FUNCTION FN_AMT_BUY(
          P_YEAR VARCHAR2,
          P_PID PROD.PROD_ID%TYPE)
          RETURN NUMBER
        IS
          V_AMT NUMBER:=0;
        BEGIN
          SELECT SUM(BUY_QTY * BUY_COST)
            INTO V_AMT
            FROM BUYPROD
           WHERE BUY_PROD = P_PID
             AND EXTRACT(YEAR FROM BUY_DATE) = P_YEAR;
           RETURN V_AMT;
           
           EXCEPTION WHEN OTHERS THEN
             DBMS_OUTPUT.PUT_LINE('예외발생: '||SQLERRM);
             RETURN NULL;
        END;
        
        (실행)
        SELECT A.PROD_ID AS 상품코드,
               A.PROD_NAME AS 상품명,
               A.FQB AS 매입수량,
               TO_CHAR(A.FAB,'99,999,999') AS 매입금액
          FROM (SELECT PROD_ID,
                       PROD_NAME,
                       FN_QTY_BUY('2005',PROD_ID) AS FQB,
                       FN_AMT_BUY('2005',PROD_ID) AS FAB
                  FROM PROD
                 ORDER BY 4 DESC) A
         WHERE ROWNUM <= 5;
         
 -- 2022-0207-03)--------------------------------------------------------------------------------------------------------------
 
    * 트리거(TRIGGER)
    - 특정 테이블에 발생된(발생될) 이벤트(INSERT, UPDATE, DELETE)에 의하여 별도의 동작이 필요한 일종의 프로시져
    (사용형식)
    CREATE TRIGGER 트리거명
      BEFORE|AFTER INSERT|UPDATE|DELETE
      ON 테이블명
      [FOR EACH ROW]
      [WHEN 조건]
    [DECLARE]
      선언부;
    BEGIN
      트리거 본문;
    END;
    . 'BEFORE|AFTER' : timing으로 트리거 본문이 실행되는 시점
    . 'INSERT|UPDATE|DELETE' : event로 트리거 본문을 실행시키는 원인이되는 dml명령, 조합사용 가능(INSERT OR UPDATE 등)
    . 'ON 테이블명' : 트리거 원인이 되는 이벤트가 발생된 테이블명 -- 트리거 본문에 오는 테이블명과 달라야함
    . 'FOR EACH ROW' : 행단위 트리거(이벤트 결과 각 행마다 트리거수행). 생략하면 문장단위 트리거
    . 'WHEN 조건' : 행단위 트리거에서만 사용할 수 있으며 트리거를 발생시키는 이벤트가 발생되는 테이블에서 이벤트가 발생할 때 좀 더 구체적인
                   검색조건 제시에 사용

 -- 2022-0207-04)--------------------------------------------------------------------------------------------------------------
    
    ** 트리거 유형
    - 행단위 트리거와 문장단위 트리거로 구분
     1)문장단위 트리거 : 이벤트 수행결과에 관계없이 1번만 수행. 'FOR EACH ROW' 생략
     2)행단위 트리거 : 이벤트명령 실행 결과가 복수개의 행이 반한되는 경우 각 행마다 트리거 본문 실행. 'FOR EACH ROW' 기술
     
    ** 의사레코드
    - 행단위 트리거에서만 사용 가능
    --------------------------------------------------------------------------------
     의사레코드      의미
    --------------------------------------------------------------------------------
     :NEW          이벤트가 INSERT, UPDATE일때만 사용.데이터가 삽입(갱신)될때 새롭게 입력되는
                   자료(행)를 지칭, DELETE시 사용되면 모든 컬럼값이 NULL
    --------------------------------------------------------------------------------               
     :OLD          이벤트가 DELETE, UPDATE일때만 사용.데이터가 삭제(갱신)될때 해당 연산의 대상이
                   되는 자료(행)를 지칠, INSERT시 사용되면 모든 컬럼값이 NULL
    --------------------------------------------------------------------------------
    
    ** 트리거 함수
    - 이벤트로 정의된 명령을 구분하기 위해 사용
    --------------------------------------------------------------------------------
     함수           의미
    --------------------------------------------------------------------------------
     INSERTING     이벤트가 INSERT 이면 참(true)반환
    --------------------------------------------------------------------------------               
     UPDATING     이벤트가 UPDATE 이면 참(true)반환         
    --------------------------------------------------------------------------------
     DELETING     이벤트가 DELETE 이면 참(true)반환
    --------------------------------------------------------------------------------
            
    사용예)
        분류테이블(LPROD)에서 순번 10부터 모두 삭제하시오. 삭제후 '분류코드가 삭제됐습니다'라는 메시지를 출력하시오.
        
        CREATE TRIGGER TG_DEL_LPROD
          AFTER DELETE ON LPROD
        BEGIN
          DBMS_OUTPUT.PUT_LINE('분류코드가 삭제됐습니다');
        END;
        
    
        DELETE FROM LPROD
         WHERE LPROD_ID = 12;
        COMMIT;

    사용예)
        CUSTOMER 테이블의 자료를 변경하면 '자료가 수정되었습니다.'메시지를, 자료가 삭제되면 '자료가 삭제되었습니다.'메시지를
        출력하는 트리거를 작성하시오.
        
        CREATE TRIGGER TG_DELUPATE_CUSTOM
          AFTER UPDATE OR DELETE ON CUSTOMER
        BEGIN
          IF UPDATING THEN
            DBMS_OUTPUT.PUT_LINE('자료가 수정되었습니다.');
          ELSIF DELETING THEN
            DBMS_OUTPUT.PUT_LINE('자료가 삭제되었습니다.');
          END IF;
        END;
        
        (CUSTOMER의 마일리지를 모두 500씩 증가)
        UPDATE CUSTOMER
           SET MEM_MILEAGE = MEM_MILEAGE + 500;
        
        COMMIT;
        
        (CUSTOMER의 자료 중 마일리지가 3000미만인 자료 삭제)
        DELETE FROM CUSTOMER
         WHERE MEM_MILEAGE < 3000;
        SELECT * FROM CUSTOMER;
            