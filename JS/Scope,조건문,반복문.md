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

# 반복문

## for 반복문
* 선언문, 조건문, 증감문 형태로 이루어진 반복문
* 조건문이 fail이 되기 전까지 코드 블록을 계속 반복 수행
* 선언문, 조건문, 증감문 자리에 공백 입력 가능

```
for(let i = 0; i < 5; i++){
    console.log(i)
}
// 0,1,2,3,4

for(let i = 10; i < 3; i++){
    console.log(i)
}
// 

let num = 0
for(; num < 3;){
    console.log(num)
    num++;
}
// 0,1,2

for(let i = 0; i < 3; i++){
    for(let j = 0; j < 3; j++){
        console.log(`${i} + ${j} = ${i + j}`)
    }
}
/* 0 + 0 = 0
0 + 1 = 1
0 + 2 = 2
1 + 0 = 1
...
2 + 2 = 4
*/

```
* 조건문이 fail이 되면 블럭안에있는 코드는 아예 실행되지 않는다, 선언문과 증감문이 꼭 ()안에 없어도 된다.

### for 반복문 확장
* for .. in 반복문
    * 객체의 key,value 형태를 반복하여 수행하는데 최적화 된 유형
    * 첫번째부터 마지막까지, 객체의 키 개수만큼 반복
```
const person = {
    name:"John",
    age:26,
    height:180,
    job:"student"
}

let key = "", value="";
for(let x in person){
    key += x+" ";
    value += person[x]+" "
}
console.log(key) //name age height job 
console.log(value) //John 26 180 student 

```
* for.. of 반복문
    * Collection 객체 자체가 Symbol.iterator속성을 가지고 있어야 동작 가능한 유형
    * ES6에 새로 추가된 Collection기반의 반복 구문
```
const languege = "JavaScript";
let text = "";

for(let x of languege){
    text += x;
    console.log(x)//J, a, v, a, ..., t
}
console.log(text) //JavaScript
```

## while 반복문
* 조건문이 참일 때 코드 블록을 계속해서 반복 수행하는 반복문
* for 문에 비해 선언문과 증감문 없이 loop를 수행하며, 무한loop등 수행 시 많이 사용
* 조건문을 코드 블록보다 아래로 옮긴 do...while 반복문도 존재(최소 한번 수행이 필요할 때 많이 사용)

```
let i = 0;
while(i < 3){
    console.log(i);
    i++;
}
// 0,1,2

i = 0;
do{
    console.log(i);
    i++;
}while(i < 3)
// 0,1,2

i = 4;
while(i < 3){
    console.log(i);
    i++;
}
// 

do{
    console.log(i);
    i++;
}while(i < 3)
// 4
```
* 이처럼 그냥 while과 do..while의 차이점은 do구문은 조건을 따지지 않기 때문에 최소1회는 실행이 된다는 점이다.

## 반복문 제어
* break
    * 반복문 수행 시 코드 블록을 탈출할 때 사용되는 식별자
    * 다중 반복문일 경우 가장 안쪽의 반복문을 종료
    * Label을 통하여 다중 반복문을 한번에 종료가능

* continue
    * 반복문 수행 시 코드 블록 실행을 해당 라인에서 중지하고 블록 코드를 종료 시킨 후 반복문 내 명시된 조건 판단

```
// break
let text = "";
for(let i = 0; i < 10; i++){
    if(i === 3) break;
    text += i;
}
console.log(text)// 012

// continue
text = "";
for(let i = 0; i < 10; i++){
    if(i === 3) continue;
    text += i
}
console.log(text) // 012456789
```
* break는 바로 반복문을 탈출하고, continue는 그 조건에 해당하는 조건문은 건너뛰고 반복 수행
