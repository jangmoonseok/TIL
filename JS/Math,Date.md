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
```
// new Date(), Date()
let date_1 = new Date()
console.log(date_1) // 2021-09-28T12:12:43.192Z, UTC+0기준 Date 객체 반환
let date_2 = Date()
console.log(date_2) // Tue Sep 28 2021 21:16:28 GMT+0900 (대한민국 표준시) , 현재 시간을 문자열로 반환
// new Date(ms)
let date_ms_1 = new Date(0)
console.log(date_ms_1) // 1970-01-01T00:00:00.000Z ,1970년1월1일 기준 0ms지난 날짜
let date_ms_2 = new Date(1000 * 3600 * 24)
console.log(date_ms_2) // 1970-01-02T00:00:00.000Z , 1970년1월1일 기준 1000*3600*24 ms 지난 날짜

// new Date(datestring)
let date_parse_1 = new Date("2021-09-28")
console.log(date_parse_1) // 2021-09-28T00:00:00.000Z
// new Date(year,month,date,hours,minutes,seconds,ms)
let date_parse_2 = new Date(2021,8,28,21,26) // month 범위는 1월(0)~12월(11)
console.log(date_parse_2) // 2021-09-28T12:26:00.000Z, UTC+0기준 날짜 반환
let date_parse_3 = new Date(Date.UTC(2021,8,28,21,26))
console.log(date_parse_3) // 2021-09-28T21:26:00.000Z, UTC+9
```
### 날짜 정보 얻기
* 날짜 정보 얻기(년/월/일/요일): Date.getFullYear(), Date.getMonth(), Date.getDate(), Date.getDay()
* 날짜 정보 얻기(시/분/초/ms): Date.getHours(), Date.getMinutes(),Date.getSeconds()
* 주어진 일시 - 1970/1/1차분(ms): Date.getTime(),  현지시간 - 표준 시간 차분(min): Date.getTimezoneOffset()

```
let date = new Date(Date.UTC(2021,0,1))

console.log(date) // 2021-01-01T00:00:00.000Z

console.log(date.getFullYear()) //2021
console.log(date.getMonth()) //0, Month범위 1월(0)~12월(11)이므로 0은 1월
console.log(date.getDate())// 1
console.log(date.getDay()) // 5, Day범위 일요일(0)~토요일(6)이므로 5는 금요일 

console.log(date.getHours()) // 9, 현지 시간 기준으로 시를 반환 UTC+0기준 00시 이므로 현지는 9를 더해 9가 나옴
console.log(date.getUTCHours()) // 0, UTC보정

console.log(date.getTime()) // 1609459200000, 주어진 일자가 1970/1/1로 부터 시간이 얼마나 지났는지 ms로 반환
console.log(new Date(date.getTime())) // 2021-01-01T00:00:00.000Z, getTime으로 받은 ms를 Date생성자 매개변수로 넣어줌
console.log(date.getTimezoneOffset()) // -540, 현지시간와 표준 시간차이를 min으로 반환
```

### 날짜 정보 설정
* 날짜 정보 설정(년/월/일):Date.setFullYear(), Date.setMonth(), Date.setDate()
* 날짜 정보 설정(시/분/초/ms):Date.setHours(), Date.setMinutes(),Date.setSeconds()
```
let date = new Date()
let ms_year = date.setFullYear(2021,4,5)
console.log(date) // 2021-05-05T12:46:50.628Z, date가 설정한 날짜로 변경
console.log(ms_year) // 1620218810628, 설정한 날짜가 1970/1/1로 부터 얼마나 시간이 지났는지 ms로 반환

date.setDate(1)
console.log(date) // 2021-05-01T12:50:51.684Z, 날짜가 1일로 변경
date.setHours(date.getHours() + 2)
console.log(date) // 2021-05-05T14:50:13.195Z
```

### parse
* 문자열 기반 날짜 정보 설정: Date.parse(YYYY-MM-DDTHH:mm:ss.sssZ)
* YYYY-MM-DD -> 날짜(연-월-일), "T"-> 구분 기호, HH:mm:ss.sss -> 시:분:초.밀리초
* "Z"(option) -> 미 설정할 경우 현재 로컬 기준UTC, 설정할 경우 UTC+0기준
```
let parse_date = Date.parse("2021-01-01T12:00:00.000") //Z를 넣지 않으면 현지 시간, 넣으면 UTC+0기준 시간
console.log(parse_date) // 1609470000000
console.log(new Date(parse_date)) // 2021-01-01T03:00:00.000Z

console.log(new Date(Date.parse("2021-01-01T12:00:00.000Z"))) //2021-01-01T12:00:00.000Z
```
### benchmark
* 성능 측정
  * 벤치마크 측정 대상 함수 전후로 시간을 비교하여 알고리즘 성능 측정
```
function dateSub(old_date, new_date){
    return new_date - old_date
}

function getTime(old_date, new_date){
    return new_date.getTime() - old_date.getTime()
}

function benchmark(callback){
    let date_1 = new Date("2021-01-01")
    let date_2 = new Date()

    let start = Date.now() // 현재 시각을 ms로 반환
    for(let i = 0; i < 100000; i++){
        callback(date_1, date_2)
    }
    return Date.now() - start; // for문을 마친 후 Date.now와 for문을 돌리기전 Date.now의 차이
}
// date_1,date_2를 callback함수의 매개변수로 넣어 두 함수를 처리하는데 걸리는 시간을 확인하는 함수

console.log("dateSub: " + benchmark(dateSub) + "ms") // 28ms
console.log("getTime: " + benchmark(getTime) + "ms") // 7ms

```
