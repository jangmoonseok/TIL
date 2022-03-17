 -- 2022-0120-01)--------------------------------------------------------------------------------------------------------------
 
    외부조인(OUTER JOIN)
     - 조인에 참여하는 테이블 중 자료의 종류가 많은쪽을 기준으로 적은 테이블에 NULL행을 첨가하여 조인을 수행
     - 일반외부조인은 조인조건 기술시 부족한 테이블의 컬럼명 뒤에 외부조인 연산자'(+)'를 추가
     - 외부조인 조건이 복수개인 경우 모두 '(+)'연산자를 추가해야한다
     - 하나의 테이블이 동시에 다수개의 외부조인에 참여할 수 없다. 
       즉, A,B,C 테이블이 외부 조인에 참여하는 경우 A를 기준으로 B테이블이, C를 기준으로 B테이블이 동시에 외부조인 될 수 없다(A=B(+) AND C=B(+))
     - 일반조건과 외부조인조건이 동시에 사용되면 정확한 결과를 얻을수 없다 => 서브쿼리,ANSI JOIN으로 해결
     - 외부조인에서 COUNT 집계함수를 써야할 경우 '*'를 쓰지말고 기본키를 써야한다
     
     (사용형식-일반외부조인)
     SELECT 컬럼list
       FROM 테이블명1[별칭1],테이블명2[별칭2],....
      WHERE 컬럼명 = 컬럼명(+)
                  :
                  
     (사용형식-ANSI외부조인)
     SELECT 컬럼list
       FROM 테이블명1[별칭1]
     LEFT|RIGHT|FULL OUTER JOIN 테이블명2[별칭2] ON(조인조건 [AND 일반조건],...)
                                            :
      [WHERE 일반조건]                  
      
      . FROM 다음에 기술된 '테이블명1'의 자료가 '테이블명2'의 자료보다 종류가 많으면 LEFT, 적으면 RIGHT, 양쪽 모두 부족하면 FULL 기술
      . '테이블명1'과 '테이블명2'는 반드시 조인 가능할 것
      . 기타 조인 프로세스는 내부조인과 동일
      
    사용예)
        상품테이블에서 모든 분류별 상품의 수를 조회하시오
        Alias는 분류코드, 분류명, 상품수
        
        (일반외부조인)
        SELECT A.LPROD_GU AS 분류코드,
               A.LPROD_NM AS 분류명,
               COUNT(B.PROD_ID) AS 상품수
          FROM LPROD A, PROD B
         WHERE A.LPROD_GU = B.PROD_LGU(+)
         GROUP BY A.LPROD_GU,A.LPROD_NM
         ORDER BY 1;
         
         (ANSI 외부조인)
        SELECT A.LPROD_GU AS 분류코드,
               A.LPROD_NM AS 분류명,
               COUNT(B.PROD_ID) AS 상품수
          FROM LPROD A
          LEFT OUTER JOIN PROD B ON(A.LPROD_GU = B.PROD_LGU)
         GROUP BY A.LPROD_GU,A.LPROD_NM
         ORDER BY 1;
         
    사용예)
        2005년 7월 모든 제품별 판매현황을 조회하시오
        Alias는 상품코드, 상품명, 판매수량, 판매금액
        
        SELECT A.PROD_ID AS 상품코드,
               A.PROD_NAME AS 상품명,
               SUM(B.CART_QTY) AS 판매수량,
               SUM(B.CART_QTY * A.PROD_PRICE) AS 판매금액
          FROM PROD A, CART B
         WHERE A.PROD_ID = B.CART_PROD(+)
           AND B.CART_NO LIKE '200507%'
         GROUP BY A.PROD_ID,A.PROD_NAME
         ORDER BY 1;
 -- 2022-0120-02)--------------------------------------------------------------------------------------------------------------       
        (ANSI 외부조인) 
        SELECT A.PROD_ID AS 상품코드,
               A.PROD_NAME AS 상품명,
               NVL(SUM(B.CART_QTY),0) AS 판매수량,
               NVL(SUM(B.CART_QTY * A.PROD_PRICE),0) AS 판매금액
          FROM PROD A
          LEFT OUTER JOIN CART B ON(A.PROD_ID = B.CART_PROD AND B.CART_NO LIKE '200507%')
         GROUP BY A.PROD_ID,A.PROD_NAME
         ORDER BY 1;
         
 
    **다음 조건에 맞는 재고 수불테이블을 생성하고 기초자료를 입력하시오
    1)테이블명 : REMAIN
    2)컬럼
    ----------------------------------------------------------
    컬럼명         데이터타입(크기)    NULLABLE   PK/FK    DEFAULT
    ----------------------------------------------------------
    REMAIN_YEAR   CHAR(4)            N.N       PK      
    PROD_ID       VARCHAR2(10)       N.N       PK&FK
    REMAIN_J_00   NUMBER(5)                               0
    REMAIN_O      NUMBER(5)                               0
    REMAIN_I      NUMBER(5)                               0
    REMAIN_J_99   NUMBER(5)                               0
    REMAIN_DATE   DATE                                 SYSDATE
    -----------------------------------------------------------
    REMAIN_J_00 : 기초재고
    REMAIN_O : 출고재고
    REMAIN_I : 입고재고
    REMAIN_J_99 : 기말재고
    
    CREATE TABLE REMAIN(
        REMAIN_YEAR   CHAR(4),                
        PROD_ID       VARCHAR2(10),      
        REMAIN_J_00   NUMBER(5) DEFAULT 0,
        REMAIN_O      NUMBER(5) DEFAULT 0,
        REMAIN_I      NUMBER(5) DEFAULT 0,
        REMAIN_J_99   NUMBER(5) DEFAULT 0,
        REMAIN_DATE   DATE DEFAULT SYSDATE,
        CONSTRAINT pk_remain PRIMARY KEY(REMAIN_YEAR, PROD_ID),
        CONSTRAINT fk_remain_prod FOREIGN KEY(PROD_ID) REFERENCES PROD(PROD_ID)
    );
    
    COMMIT;
    
    (기초자료 입력)
     => 상품테이블의 상품코드와 적정재고를 재고수불테이블(REMAIN)의 상품코드와 기초재고에 삽입
     1)년도 : '2005'
     2)상품코드: 상품테이블의 상품코드
     3)기초, 기말재고 : 상품테이블의 적정재고(PROD_PROPERSTOCK)
     
     INSERT INTO REMAIN(REMAIN_YEAR, PROD_ID, REMAIN_J_00, REMAIN_J_99, REMAIN_DATE)
       SELECT '2005', PROD_ID, PROD_PROPERSTOCK, PROD_PROPERSTOCK, TO_DATE('20041231')
         FROM PROD;
     COMMIT; 
     
     SELECT * FROM REMAIN;
     
 -- 2022-0120-03)--------------------------------------------------------------------------------------------------------------
    
    사용예)
        2005년 1월 모든 상품별 매입수량을 조회하여 재고수불테이블을 갱신하시오
        
        (2005년 1월 모든 상품별 매입수량 조회)
        SELECT A.PROD_ID,
               NVL(SUM(B.BUY_QTY),0)
          FROM PROD A
          LEFT OUTER JOIN BUYPROD B ON(A.PROD_ID = B.BUY_PROD 
                                       AND B.BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131'))
         GROUP BY A.PROD_ID
         ORDER BY 1;
          
        (재고수불테이블 갱신)
        UPDATE REMAIN R
           SET (R.REMAIN_I, R.REMAIN_J_99, R.REMAIN_DATE) = 
               (SELECT R.REMAIN_I + C.ISUM,
                       R.REMAIN_J_99 + C.ISUM,
                       TO_DATE('20050201')
                  FROM (SELECT BUY_PROD AS PID,
                               SUM(BUY_QTY) AS ISUM
                          FROM BUYPROD
                         WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
                         GROUP BY BUY_PROD) C
                 WHERE R.PROD_ID = C.PID)
         WHERE R.PROD_ID IN (SELECT DISTINCT BUY_PROD
                              FROM BUYPROD
                             WHERE BUY_DATE BETWEEN TO_DATE('20050101') AND TO_DATE('20050131')
                              );       
        ROLLBACK REMAIN;
        ROLLBACK;
 -- 2022-0120-04)--------------------------------------------------------------------------------------------------------------       
    ** 상품테이블에 상품별 마일리지를 상품판매가의 0.01%로 조정하여 갱신하시오.
    UPDATE PROD
       SET PROD_MILEAGE = ROUND(PROD_PRICE * 0.0001);
    SELECT PROD_ID, PROD_PRICE, PROD_MILEAGE
      FROM PROD;
    COMMIT;
    ** 회원테이블의 회원마일리지를 2005년 매출자료를 집계하여 갱신하시오. 
    (2005년 회원별,상품별,매출수량)
    SELECT MEM_ID, CART_PROD, NVL(SUM(CART_QTY),0)
      FROM CART A
     RIGHT OUTER JOIN MEMBER B ON(B.MEM_ID = A.CART_MEMBER
            AND A.CART_NO LIKE '2005%')
     GROUP BY MEM_ID,CART_PROD
     ORDER BY 1;
     
    (2005년 회원별 구매에 따른 마일리지)
    SELECT B.MEM_ID AS BMID,
           SUM(B.CSUM * C.PROD_MILEAGE) AS BSUM
      FROM (SELECT MEM_ID, CART_PROD, 
                   NVL(SUM(CART_QTY),0) AS CSUM
              FROM CART A
             RIGHT OUTER JOIN MEMBER B ON(B.MEM_ID = A.CART_MEMBER
                   AND A.CART_NO LIKE '2005%')
             GROUP BY MEM_ID,CART_PROD
             ORDER BY 1) B , PROD C
     WHERE C.PROD_ID = B.CART_PROD
     GROUP BY B.MEM_ID
     ORDER BY 1;
     
    (회원테이블의 마일리지 갱신)
    UPDATE MEMBER M
       SET MEM_MILEAGE =
            (SELECT NVL(D.BSUM,0)
               FROM ( SELECT B.MEM_ID AS BMID,
                             SUM(B.CSUM * C.PROD_MILEAGE) AS BSUM
                        FROM (SELECT MEM_ID, CART_PROD, 
                                     NVL(SUM(CART_QTY),0) AS CSUM
                                FROM CART A
                               RIGHT OUTER JOIN MEMBER B ON(B.MEM_ID = A.CART_MEMBER
                                     AND A.CART_NO LIKE '2005%')
                               GROUP BY MEM_ID,CART_PROD
                               ORDER BY 1) B , PROD C
                         WHERE C.PROD_ID = B.CART_PROD
                         GROUP BY B.MEM_ID) D
              WHERE M.MEM_ID = D.BMID)
      WHERE M.MEM_ID IN (SELECT DISTINCT CART_MEMBER
                          FROM CART
                         WHERE CART_NO LIKE '2005%');
    

    SELECT MEM_ID, MEM_MILEAGE
      FROM MEMBER;
    COMMIT;     