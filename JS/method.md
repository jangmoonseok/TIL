# method
* 객체에 저장된 값이 함수인 경우, 이를 메서드(method)라고 부름

![image](https://user-images.githubusercontent.com/64582209/134893072-6ebfdfdd-9f7e-4e22-be22-fbc12581cd96.png)

## method 변경
* method변경: 객체 내 초기 선언된 함수를 다른 함수로 변경

```
function hello_func(){
    console.log("hello");
}

function hi_func(){
    console.log("hi");
}

let obj = {
    name:"john",
    age:27,
    func:hello_func
}

hello_func(); //hello
hi_func(); // hi
obj.func(); // hello

obj.func = hi_func
obj.func(); // hi
```
* obj의 func속성이 가리키는 함수를 hello_func에서 hi_func로 변경

## this
* 메서드에서 객체 내부의 속성값을 접근할 수 있는 지시자

![image](https://user-images.githubusercontent.com/64582209/134894164-6b19d99a-5909-428b-953c-cfb175b42fd3.png)
```
let user = {name:"john"};
let admin = {name:"admin"};

function hello_func(){
    console.log("Hello " + this.name);
}

user.func = hello_func;
admin.func = hello_func;

user.func(); // Hello john
admin.func(); // Hello admin
```
* hello_func함수가 user객체와 admin객체의 메서드가 돼서 객체 내부에 name속성에 접근하기 위해 this사용
