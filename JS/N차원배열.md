# N차원 배열
* 배열안에 N개 만큼의 배열이 존재하는 객체
* 2/3차원 지도 정보, RGB를 저장하는 2차원 사진 파일 등을 표현할 때 활용 가능
![image](https://user-images.githubusercontent.com/64582209/135244308-53419550-be46-4ac5-898c-46cda4990f7e.png)
```
let array = [
    [101,102,103],
    [201,202,203],
    [301,302,303]
]

console.log(array) // [ [ 101, 102, 103 ], [ 201, 202, 203 ], [ 301, 302, 303 ] ]
console.log(array[0]) // [101,102,103]
console.log(array[0][1]) // 102

let array_2 = array.pop()
console.log(array_2) // [ 301, 302, 303 ]
console.log(array) // [ [ 101, 102, 103 ], [ 201, 202, 203 ] ]

let array_3 = array.push([401,402,403])
console.log(array_3) // 3
console.log(array) // [ [ 101, 102, 103 ], [ 201, 202, 203 ], [ 401, 402, 403 ] ]
```

