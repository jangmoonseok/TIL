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
