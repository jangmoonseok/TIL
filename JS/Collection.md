# Collection

## Map
![image](https://user-images.githubusercontent.com/64582209/134927348-e5f560e8-4a21-45a3-b596-52c1ab30381c.png)


### 요소 추가/삭제
* 요소 추가:Map.set(key,value), 요소 접근:Map.get(key),  요소 전체 삭제:Map.clear()
* 다양한 자료형을 key로 사용 가능하며, map.set 호출 시 map이 반환되므로 체이닝 가능


```
let map = new Map();


// 요소 추가
map.set("name","john") // string
map.set(123,456) // number
map.set(true, "bool_type") // boolean

console.log(map); // Map(3) { 'name' => 'john', 123 => 456, true => 'bool_type' }

// 요소 전체 삭제
map.clear();
console.log(map); // Map(0) {}

// 체이닝을 이용한 요소 추가
map.set("name","john").set(123,456).set(true,"bool_type")
console.log(map); // Map(3) { 'name' => 'john', 123 => 456, true => 'bool_type' }

// 요소 접근
console.log(map.get("name")) // john
console.log(map.get(123)) // 456
console.log(map.get(true)) // bool_type
```

### Map 반복문
* Collection 객체인 Map이 가지고 있는 iterator속성을 이용하여 for..of 구문을 통해 반복문 수행 가능

```
let juice_recipe = new Map([
    ["strawberry", 50],
    ["banana", 100],
    ["ice", 150]
])

// key만 호출
for(let item of juice_recipe.keys()){
    console.log(item)
}
//strawberry banana ice

// value만 호출
for(let amount of juice_recipe.values()){
    console.log(amount)
}
//50 100 150

// 전체 호출
for(let entity of juice_recipe){
    console.log(entity)
}
// [ 'strawberry', 50 ] [ 'banana', 100 ] [ 'ice', 150 ]
```

### Map <-> Object 변환
* Object.entries(Object), Object.fromEntries(Map)을 통해 Map과 Object간 변환 가능

```
let juice_recipe = new Map([
    ["strawberry", 50],
    ["banana", 100],
    ["ice", 150]
])

// Map >> Object
let juice_recipe_obj = Object.fromEntries(juice_recipe)
console.log(juice_recipe_obj); // { strawberry: 50, banana: 100, ice: 150 }

// Object >> [key,value]
let juice_recipe_kv = Object.entries(juice_recipe_obj);
console.log(juice_recipe_kv) // [ [ 'strawberry', 50 ], [ 'banana', 100 ], [ 'ice', 150 ] ]

// [key,value] >> Map
let juice_recipe_map = new Map(juice_recipe_kv)
console.log(juice_recipe_map) // Map(3) { 'strawberry' => 50, 'banana' => 100, 'ice' => 150 }
```

## Set
![image](https://user-images.githubusercontent.com/64582209/135072947-56e5f1fc-88f9-472d-921e-55419fa123b7.png)

### 요소 추가/삭제
* 요소 추가:Set.add(value), 요소 존재 여부:Set.has(value), 요소 삭제:Set.delete(value)
* 다양한 자료형을 value로 사용 가능하며, set.add호출 시 set이 반호나되므로 체이닝 가능
```
let set = new Set();
let nums = new Set([1,2,3,4,5])
let str = new Set("hello!!")

console.log(set) // Set(0) {}
console.log(nums) // Set(5) { 1, 2, 3, 4, 5 }
console.log(str) // Set(5) { 'h', 'e', 'l', 'o', '!' }, 중복된 데이터인 l한개가 빠짐

// 요소 추가
set.add(1)
console.log(set) // Set(1) { 1 }
// 요소 추가 체이닝
set.add(1).add(2).add(10).add(20)
console.log(set) // Set(4) { 1, 2, 10, 20 }, 중복된 데이터인 1은 추가안됨

// 요소 존재 여부
console.log(set.has(1)) // true
console.log(set.has(3)) // false

// 요소 삭제
set.delete(1)
console.log(set) // Set(3) { 2, 10, 20 }
set.delete(3)
console.log(set) // Set(3) { 2, 10, 20 }
console.log(set.delete(3)) // false
```

### Set 반복문
* Collection객체인 Set이 가지고 있는 iterator속성을 이용하여  for...of구문을 통해 반복문 수행 가능

```
let str = new Set("hello!!")

for(let item of str){
    console.log(item)
}
// h e l o !

for(let item of str.keys()){
    console.log(item)
}
// h e l o ! , Map과 똑같은 작동 원리이지만 Set은 key가 없으므로 요소 그대로 호출됨

for(let item of str.values()){
    console.log(item)
}
// h e l o !, str.keys()와 마찬가지

for(let item of str.entries()){
    console.log(item)
}
/* 
[ 'h', 'h' ]
[ 'e', 'e' ]
[ 'l', 'l' ]
[ 'o', 'o' ]
[ '!', '!' ]
호환성을 위해 Map과 똑같은 Collection객체의 원리로 작동
key,value가 따로 없으므로 key,value 모두 요소가 그대로 들어간다.
*/
```
