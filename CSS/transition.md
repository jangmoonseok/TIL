# transition
* 요소가 두가지 상태 사이에서 변화를 할 때 여러가서 속성을 부여해서 시간의 흐름을 가지고 동적인 변화를 줄 수 있다.
* 각 상태는 :hover , :active또는 자바스크립트를 사용해 동적으로 만들어진 것들이다.

## transition-property
* transition할 특정 CSS property를 지정한다.

## transition-duration
* transition에 걸리는 시간을 지정한다. 

## transition-delay
* transition을 얼마만큼 지연시킬지 지정한다.

## transition-timing-function
* transition될때  변하는 속도를 지정한다.
* ease : 기본값으로 중간까지 빨라졌다가 끝에 느려진다
*  ease-in : 천천히 시작해서 점점 빨라진다.
* ease-out : 빠르게 시작해서 점점 느려진다.
* ease-in-out : 천천히 시작해서 빨라졌다가 다시 느려진다.
* linear : 일정한 속도로 변환한다.

```
.box {
  width: 100px;
  height: 100px;
  background-color: blue;
  transition: all 1s;
}

.box:hover {
  background-color: aqua;
  height: 200px;
}
```
![GIF 2021-09-12 오후 1-52-19](https://user-images.githubusercontent.com/64582209/132972712-8c024251-bcf1-4307-8d98-2e438f280583.gif)
* transition-property : all, transition-duration : 1s

```
.box {
  width: 100px;
  height: 100px;
  background-color: blue;
  transition: all linear 1s;
}

.box:hover {
  background-color: aqua;
  transform: scale(1.5);
}
```
![GIF 2021-09-12 오후 1-54-19](https://user-images.githubusercontent.com/64582209/132972740-b1e87db7-58ae-4358-ba7b-332f8a8c1258.gif)
* transition-property : all, transition-timing-function : linear, transition-duration : 1s
