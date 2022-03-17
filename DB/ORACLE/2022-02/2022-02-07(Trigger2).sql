 -- 2022-0208-01)--------------------------------------------------------------------------------------------------------------
 
    > hr계정의 사원테이블에 퇴직일자 컬럼을 추가하시오
      ALTER TABLE HR.EMPLOYEES ADD(RETIRE_DATE DATE);
      
    > hr계정에 퇴작자 테이블을 생성하시오
      테이블명 : RETIRES
    -------------------------------------------------- 
      컬럼명          데이터타입       NULABLE      PK/FK
    --------------------------------------------------
     EMPLOYEE_ID     NUMBER(6)       N.N         PK&FK
     RETIRE_DATE     DATE
     JOB_ID          VARCHAR2(10)                FK
     DEPARTMENT_ID   NUMBER(4)                   FK
    --------------------------------------------------
    CREATE TABLE RETIRES(
      EMPLOYEE_ID     NUMBER(6),       
      RETIRE_DATE     DATE,
      JOB_ID          VARCHAR2(10),              
      DEPARTMENT_ID   NUMBER(4),
      CONSTRAINT pk_retires PRIMARY KEY(EMPLOYEE_ID),
      CONSTRAINT fk_ret_emp FOREIGN KEY(EMPLOYEE_ID) REFERENCES EMPLOYEES(EMPLOYEE_ID),
      CONSTRAINT fk_ret_jobs FOREIGN KEY(JOB_ID) REFERENCES JOBS(JOB_ID),
      CONSTRAINT fk_ret_dept FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENTS(DEPARTMENT_ID)
    );
    
    사용예)
        사원테이블에서 2003년 이전에 입사한 사원들을 퇴직 처리하려한다. 퇴직자는 사원테이블 퇴직일자에 오늘 날짜로 변경하기전
        퇴직자테이블에 정보를 입력해야한다.
        
        CREATE TRIGGER TG_RETIRE
          BEFORE UPDATE ON EMPLOYEES
          FOR EACH ROW
        BEGIN
          INSERT INTO RETIRES VALUES(:OLD.EMPLOYEE_ID,SYSDATE,:OLD.JOB_ID,:OLD.DEPARTMENT_ID);
        END;
        
        UPDATE EMPLOYEES
           SET RETIRE_DATE = SYSDATE
         WHERE HIRE_DATE <= TO_DATE('20021231');
         
 -- 2022-0208-02)--------------------------------------------------------------------------------------------------------------

    (CART_NO를 자동으로 생성하는 함수)
    CREATE OR REPLACE FUNCTION FN_CREATE_CARTNO(
      P_DATE DATE)
      RETURN VARCHAR2
    IS
      V_CNO VARCHAR2(20):=TO_CHAR(P_DATE,'YYYYMMDD');
      V_FLAG NUMBER:=0;
    BEGIN
      SELECT COUNT(*) INTO V_FLAG
        FROM CART
       WHERE SUBSTR(CART_NO,1,8) = V_CNO;
       
      IF V_FLAG=0 THEN
         V_CNO:=V_CNO||TRIM('000001');
      ELSE
         SELECT MAX(CART_NO) + 1 INTO V_CNO
           FROM CART
          WHERE SUBSTR(CART_NO,1,8) = V_CNO;
      END IF;
      RETURN V_CNO;
    END;
    
    (실행)
    SELECT FN_CREATE_CARTNO(TO_DATE('20050505')),
           FN_CREATE_CARTNO(TO_DATE('20050513'))
      FROM DUAL;
      
     > CART테이블에 다음 자료를 저장하시오
      구매일자 : 20050728
      구매회원 : d001
      구매상품
      ------------------------
         상품코드       수량
      ------------------------
       P201000003      3
       P201000015      2
      ------------------------
    
    --트리거에서 처리해야할 내용 : 재고 UPDATE, 마일리지 UPDATE
    
    CREATE TRIGGER TG_CART_CHANGE
      AFTER INSERT OR UPDATE OR DELETE ON CART
      FOR EACH ROW
    DECLARE
      V_QTY NUMBER:=0; -- 재고를 변경할 수량
      V_PID PROD.PROD_ID%TYPE; -- 재고를 변경할 상품코드
      V_MILE NUMBER:=0; -- 변경할 마일리지
      V_DATE DATE; -- 변경할 날짜
      V_MID MEMBER.MEM_ID%TYPE; -- 마일리지를 변경할 멤버번호
    BEGIN
      IF INSERTING THEN
        V_QTY:=:NEW.CART_QTY;
        V_PID:=:NEW.CART_PROD;
        V_DATE:=TO_DATE(SUBSTR(:NEW.CART_NO,1,8));
        V_MID:=:NEW.CART_MEMBER;
        SELECT V_QTY * PROD_MILEAGE INTO V_MILE
          FROM PROD
         WHERE PROD_ID = V_PID;
      ELSIF UPDATING THEN
        V_QTY:=:NEW.CART_QTY - :OLD.CART_QTY;
        V_PID:=:NEW.CART_PROD;
        V_DATE:=TO_DATE(SUBSTR(:NEW.CART_NO,1,8));
        V_MID:=:NEW.CART_MEMBER;
        SELECT V_QTY * PROD_MILEAGE INTO V_MILE
          FROM PROD
         WHERE PROD_ID = V_PID;
      ELSIF DELETING THEN
        V_QTY:=-(:OLD.CART_QTY);
        V_PID:=:OLD.CART_PROD;
        V_DATE:=TO_DATE(SUBSTR(:OLD.CART_NO,1,8));
        V_MID:=:OLD.CART_MEMBER;
        SELECT V_QTY * PROD_MILEAGE INTO V_MILE
          FROM PROD
         WHERE PROD_ID = V_PID;
      END IF;
       
      UPDATE REMAIN
         SET REMAIN_O = REMAIN_O + V_QTY,
             REMAIN_J_99 = REMAIN_J_99 - V_QTY,
             REMAIN_DATE = V_DATE
       WHERE PROD_ID = V_PID
         AND REMAIN_YEAR = EXTRACT(YEAR FROM V_DATE);
      
      UPDATE MEMBER
         SET MEM_MILEAGE = MEM_MILEAGE + V_MILE
       WHERE MEM_ID = V_MID;
      
    END;
 
 -- 2022-0208-03)--------------------------------------------------------------------------------------------------------------
 
    (재고조회)
    SELECT * FROM REMAIN
     WHERE PROD_ID = 'P201000003';
    
    SELECT * FROM CART
     WHERE CART_NO = '2005072800005'
       AND CART_PROD = 'P201000003';
     
    SELECT MEM_MILEAGE
      FROM MEMBER
     WHERE MEM_ID = 'd001';
     
    (상품판매)
    INSERT INTO CART VALUES('d001',FN_CREATE_CARTNO(TO_DATE('20050728')),'P201000003',3);
    (판매수량변경)
    UPDATE CART
       SET CART_QTY = 30
     WHERE CART_NO = '2005072800005'
       AND CART_PROD = 'P201000003';
    (상품반품)
    DELETE FROM CART
     WHERE CART_NO = '2005072800005'
       AND CART_PROD = 'P201000003';
       

