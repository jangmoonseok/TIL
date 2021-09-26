# Scope란?
* 변수 혹은 상수에 접근할 수 있는 범위
* 모듈/함수 내 코드에서 동일한 변수 사용시 간섭을 줄이는 용도로 사용
* Global Scope: 전역에 선언되어 어디에서도 접근 가능
* Local Scope(Block Level, Function Level): 특정 지역에 선언되어, 해당 지역 내에서만 접근 가능

```
let x = 1
let y = 2

{
    let x = 3
    let y = 4
    let z = 5
    console.log(x) // 3
    console.log(y) // 4
    console.log(z) // 5
}

console.log(x) // 1
console.log(y) // 2
console.log(z) // ReferenceError: z is not defined
```
* 중괄호 안에 선언된 변수들은 Block Level Scope에 선언된 변수들로 Global Scope에서 호출하려고하면 호출되지 않고 오류메세지가 나온다.

# 조건문

## if-else
* 알고리즘에서 논리적 비교를 할 때 사용되는 조건식
* if, if else, else 키워드를 통해 구성되며, 조건식에 맞을 경우 {} 내에 있는 명령문 수행
* 단, 실행 문장이 단일 문장이면 {} 생략 가능

```
let apple_price = 5

if(apple_price > 10){
    console.log("very expensive")
}else if(apple_price <= 5){
    console.log("very cheap")
}else{
    console.log("nice")
}

console.log("done")
```
* if else문은 조건에 맞는 명령문만 수행하고, 조건에 맞지 않는 명령문은 무시한채 빠져나온다.

## 3항 연산자
* 3항 연산자를 통해 if-else를 대체하여 사용
* 변수 = 조건식 ? 참일 때 값 : 거짓일 때 값

```
let age = 20

// if-else
if(age < 19){
    msg = "미성년자 입니다."
}else{
    msg = "성인 입니다"
}
console.log(msg) // "성인 입니다"
// 3항 연산자
another_msg = age < 19 ? "미성년자 입니다." : "성인 입니다"
console.log(another_msg) // "성인 입니다"
```

## switch
* switch는 표현식을 평가하여 그 값이 일치하는 case문을 실행하는 조건문
* switch, case, break, default키워드를 통해 구성되며, switch의 조건에 맞는 case 구문을 수행
* 일반적으로 하나의 case만 수행되도록 case 끝을 break로 끝맺음

```
let browser = "Chrome";

switch(browser){
    case "Explorer":
        msg = " ActiveX installation required";
        break;
    case "Chrome":
    case "Firefox":
    case "Safari":
    case "Opera":
        msg = "Supported browser";
        break;
    default:
        msg = "Unsupported browser";
        break;
}
console.log(msg);
```
* 같은 case문을 실행하고 싶은 경우엔 break를 쓰지않고 조건을 쭉 나열해서 쓴다.
