## Math
![image](https://user-images.githubusercontent.com/64582209/135075471-7ce55170-5d51-4f1d-b79b-9a07d53e143e.png)

### 최대/최소/절대값
* 최대값:Math.max(...x), 최소값:Math.min(...x), 절대값:Math.abs(x)
* 배열을 인수로 받아 최대/최소를 산출하려면 apply함수 혹은 스프레드 문법 사용 필요
```
// 최대/최소/절대값
console.log(Math.max(1,-1,-4,10,20)) // 20, value중에 가장 큰 수 반환
console.log(Math.min(1,-1,-4,10,20)) // -4, value중에 가장 작은 수 반환
console.log(Math.abs(-1)) // 1 , -1의 절대값 반환
console.log(Math.abs(1)) // 1, 1의 절대값 반환

// 인수를 배열로 받을 경우 최대/최소 산출
let nums = [1,-1,-4,10,20]
console.log(Math.max.apply(null,nums)) // 20
console.log(Math.min.apply(null,nums)) // -4
```
### 속성 및 랜덤
* 0과 1사이의 난수 랜덤 값:Math.random()
```
// 속성
console.log(Math.PI) // 3.141592653589793, 파이값 반환
console.log(Math.E) // 2.718281828459045, 오일러 상수 e 반환

// 랜덤
console.log(Math.random()) // 0과 1사이 랜덤 값 반환
for(let i = 0; i < 10; i++){
    console.log(Math.floor(Math.random() * 10))
} // 0~9사이 값 랜덤 반환
```
### 제곱/제곱근/소수점처리
* 제곱:Math.pow(x,y), 제곱근:Math.sqrt(x)
* 소수점 이하 반올림:Math.round(x)
* 소수점 이하 올림:Math.ceil(x), 소수점 이라 내림:Math.floor(x)
```
// 제곱, 제곱근
console.log(Math.pow(2,3)) // 8, 2의 3제곱
console.log(Math.sqrt(4)) // 2, 4의 제곱근

// 소수점 처리
console.log(Math.round(3.5)) // 4 , 3.5의 반올림 4
console.log(Math.round(3.2)) // 3, 3.2의 반올림 3
console.log(Math.round(-2.2)) // -2, -2.2의 반올림 2

console.log(Math.ceil(3.3)) // 4
console.log(Math.ceil(-3.3)) // -3

console.log(Math.floor(3.6)) // 3
console.log(Math.floor(-3.6)) // -4
```

## Date
![image](https://user-images.githubusercontent.com/64582209/135079078-6310dc34-53c4-4696-89ac-8cc490495713.png)

### Date 생성자
* Date 생성자 종류 : new Date(), new Date(miniseconds), new Date(datestring), new Date(year,month,date,hours,minutes,seconds,ms)
* month값 범위는 1월(0)~12월(11)

### 날짜 정보 얻기
* 날짜 정보 얻기(년/월/일/요일): Date.getFullYear(), Date.getMonth(), Date.getDate(), Date.getDay()
* 날짜 정보 얻기(시/분/초/ms): Date.getHours(), Date.getMinutes(),Date.getSeconds()
* 주어진 일시 - 1970/1/1차분(ms): Date.getTime(),  현지시간 - 표준 시간 차분(min): Date.getTimezoneOffset()

### 날짜 정보 설정
* 날짜 정보 설정(년/월/일):Date.setFullYear(), Date.setMonth(), Date.setDate()
* 날짜 정보 설정(시/분/초/ms):Date.setHours(), Date.setMinutes(),Date.setSeconds()

### parse
* 문자열 기반 날짜 정보 설정: Date.parse(YYYY-MM-DDTHH:mm:ss.sssZ)
* YYYY-MM-DD -> 날짜(연-월-일), "T"-> 구분 기호, HH:mm:ss.sss -> 시:분:초.밀리초
* "Z"(option) -> 미 설정할 경우 현재 로킬 기준UTC, 설정할 경우 UTC+0기준

### benchmark
* 성능 측정
  * 벤치마크 측정 대상 함수 전후로 시간을 비교하여 알고리즘 성능 측정
