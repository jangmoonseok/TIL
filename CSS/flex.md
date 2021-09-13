# Flex

## flexbox
* flexbox 인터페이스 내의 아이템 간 공간 배분과 강력한 정렬 기능을 제공하기 위한 1차원 레이아웃 모델
* 주축 : 주축은 flex-direction에 의해 정의
* 교차축 : 주축과 수직하는 축
* flex-container : flexbox영역 내에 있는 컨테이너 요소의 display속성을 flex 또는 inlien-flex라고 지정하면 생성되며, 이 컨테이너의 일차 자식요소들은 flex항목이 된다.

## 1. container에 사용하는 속성들

### display
* flex-container로 지정할 요소에 사용한다
* inline-flex : 컨테이너 바깥쪽 요소와의 관계는 inline , 컨테이너 내부 요소들은 flex항목으로 지정한다.
* flex : 컨테이너 내부 요소들을 flex항목으로 지정한다.

### flex-direction
* flex-container내의 아이템을 배치할 주축 및 방향을 지정한다.
* row : 기본 값으로 주축이 글의 작성 방향이고, 시작점과 끝점이 콘텐츠 방향과 동일
* row-reverse : row와 동일하게 동작하지만 시작점과 끝점이 row와 반대
* column : 주축이 블록 축과 동일하고, 시작점과 끝점이 글 작성 모드의 이전 지점 및 이후 지점과 동일
* column-reverse : column과 동일하게 동작하지만 시작점과 끝점이 column과 반대

### flex-wrap
* flex-item들을 강제로 한 줄에 배치할지, 가능한 영역 내에서 벗어나지 않고 여러행으로 나누어 배치할지 결정하는 속성
* nowrap : 기본 값으로 container영역을 벗어 나더라도 item요소 자기의 width,height을 줄여서라도 강제로 한 줄에 배치한다.
* wrap : item요소들이 자기의 width,height을 지켜 여러 행에 나누어서 배치된다.
* wrap-reverse : wrap과 동일하게 동작하지만 시작점과 끝점이 반대가 된다.

### flex-flow
* flex-direction과 flex-wrap을 합친 단축 속성

### justify-content
* 주축을 기준으로 item 정렬방법을 지정한다.
* flex-start : 주축의 시작점부터 정렬한다.
* flex-end : 주축의 끝나는점에 맞춰 정렬한다.
* center : 주축의 가운데에  정렬한다.
* space-between : 주축의 시작점과 끝점에 여백없이 첫번째 요소와 마지막 요소가 오고 나머지 요소들은 동일한 간격으로 떨어져 위치한다.
* space-around : 각각의 요소의 앞뒤로 동일한 간격을 가진채 떨어져 위치한다.

```
.container {
  height: 200px;
  border: 2px solid greenyellow;
  display: flex;
}

.item {
  width: 50px;
  height: 50px;
  margin: 5px;
  background-color: skyblue;
  border: 1px solid blue;
}

.container:nth-child(1) {
  justify-content: space-between;
}

.container:nth-child(2) {
  justify-content: space-around;
}
```
![image](https://user-images.githubusercontent.com/64582209/133074340-05b0ead6-1bba-47e7-b8a1-b6cee1293345.png)
* space-between 과 space-around 차이점

### align-items
* 한줄의 flex-container를 교차축 어디에 위치할지 지정한다.
* stretch :기본 값으로 요소의 크기가 지정되지 않았을 때  flex-item요소의 크기가 교차축의 시작점 부터 끝점까지 차지한다.
* flex-start : 컨테이너를 교차축의 시작점에 위치시킨다.
* flex-end : 컨테이너를 교차축의 끝점에 위치시킨다.
* center : 컨테이너를 교차축의 중간에 위치시킨다.

### align-content
* 교차축을 기준으로 여러줄의 item들의 정렬방법을 지정한다
* 사용방법은 justify-content와 완전히 동일하고 기준이 되는 축만 주축에서 교차축으로 바뀜

## 2. flex-item에 사용하는 속성

### order
* 플렉스 또는 그리드 컨테이너 안에 요소의 순서를 지정한다.
* 정렬은 오름차순이고, order값이 같은 요소는 코드 순서에따라 배치된다.
* 기본 값은 0으로 정수만 사용가능하다.

### flex-grow
* flex-item요소를 flex-container요소 내부에 할당 가능한 공간의 정도를 선언한다.
* 형제 요소인 item이 모두 같은 값을 같는다면 동일한 공간을 할당 받고, 각자 다른 값을 같는다면 그에 따라 다른 공간을 할당 받는다.

### flex-shrink
* flex-item요소의 크기가 flex-container요소의 크기보다 클 때 설정된 숫자 값에 따라 flex-item의 요소의 크기가 축소된다.

```
.container {
  height: 200px;
  border: 2px solid greenyellow;
  display: flex;
}

.item {
  width: 50px;
  height: 50px;
  margin: 5px;
  background-color: blue;
  border: 1px solid skyblue;
}

.item:nth-child(1) {
  flex-grow: 1;
}
.item:nth-child(2) {
  flex-grow: 2;
}
.item:nth-child(3) {
  flex-grow: 3;
}
```
![image](https://user-images.githubusercontent.com/64582209/133068911-0a063b47-b5b8-4df5-b890-a71a4cd7d779.png)
* item요소의 크기가 원래 크기일때 남은 빈 공간을 1:2:3비율로 나누어가진다.

```
.item {
  width: 300px;
  height: 50px;
  margin: 5px;
  background-color: blue;
  border: 1px solid skyblue;
}

.item:nth-child(1) {
  flex-shrink: 1;
}
.item:nth-child(2) {
  flex-shrink: 2;
}
.item:nth-child(3) {
  flex-shrink: 3;
}
```
![GIF 2021-09-13 오후 7-35-41](https://user-images.githubusercontent.com/64582209/133069436-7bd013a2-b642-4564-92a9-56c2f207cb2b.gif)
* item요소의 크기보다 container의 크기가 작아지면서 값이 제일큰 3번째 자식부터 순서대로 줄어든다

### flex-basis
* flex-item의 초기 크기를 지정한다.
* box-sizing을 따로 지정하지 않는다면 콘텐츠 박스의 크기를 변경한다.
```
.container {
  height: 200px;
  border: 2px solid greenyellow;
  display: flex;
}

.item {
  height: 50px;
  margin: 5px;
  background-color: blue;
  border: 1px solid skyblue;
  flex-basis: 0;
}

.item:nth-child(1) {
  flex-grow: 1;
}
.item:nth-child(2) {
  flex-grow: 3;
}
.item:nth-child(3) {
  flex-grow: 5;
}
```
![image](https://user-images.githubusercontent.com/64582209/133070846-920f76da-e606-42a8-8f55-940e631185eb.png)
* flex-basis : 0을 지정해 모든 아이템요소들의 크기를 동일하게 만들고 각 아이템에 flex-grow를 지정해 정확히 요소들의 크기가 1:3:5비율로 가지게 된다.

### flex
* flex-grow, flex-shrink, flex-basis를 합쳐서 사용하는 단축속성
* 한개 또는 두개의 단위 없는 숫자 값을 사용할 땐, flex-basis값은 auto가 아니라 0이다.
* initial : 0 1 auto와 동일
* auto : 1 1 auto와 동일
* none : 0 0 auto와 동일
* 값이 한 개일 때
  * number를 지정하면 flex-grow이다
  * length 또는 percentage를 지정하면 flex-basis이다.
  * none, auto, initial중 하나를 지정할 수 있다.
* 값이 두 개일때
  * 첫번째 값은 number이어야 하며 flex-grow가 딘다.
  * 두번째 값은 number이면 flex-shrink, length나 percentage이면 flex-basis
* 값이 세 개일 때 순서는 flex-grow > flex-shrink > flex-basis로 작성해야 한다.

### align-self
* 한개의 item을 따로 교차축을 기준으로 정렬하는 방법을 지정한다.
* 사용방법은 align-items와 동일하다.
