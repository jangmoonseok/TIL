# Flex

## flexbox
* flexbox 인터페이스 내의 아이템 간 공간 배분과 강력한 정렬 기능을 제공하기 위한 1차원 레이아웃 모델
* 주축 : 주축은 flex-direction에 의해 정의
* 교차축 : 주축과 수직하는 축
* flex-container : flexbox영역 내에 있는 컨테이너 요소의 display속성을 flex 또는 inlien-flex라고 지정하면 생성되며, 이 컨테이너의 일차 자식요소들은 flex항목이 된다.

## 1. container에 사용하는 속성들
## display
* flex-container로 지정할 요소에 사용한다
* inline-flex : 컨테이너 바깥쪽 요소와의 관계는 inline , 컨테이너 내부 요소들은 flex항목으로 지정한다.
* flex : 컨테이너 내부 요소들을 flex항목으로 지정한다.

## flex-direction
* flex-container내의 아이템을 배치할 주축 및 방향을 지정한다.
* row : 기본 값으로 주축이 글의 작성 방향이고, 시작점과 끝점이 콘텐츠 방향과 동일
* row-reverse : row와 동일하게 동작하지만 시작점과 끝점이 row와 반대
* column : 주축이 블록 축과 동일하고, 시작점과 끝점이 글 작성 모드의 이전 지점 및 이후 지점과 동일
* column-reverse : column과 동일하게 동작하지만 시작점과 끝점이 column과 반대

## flex-wrap
* flex-item들을 강제로 한 줄에 배치할지, 가능한 영역 내에서 벗어나지 않고 여러행으로 나누어 배치할지 결정하는 속성
* nowrap : 기본 값으로 container영역을 벗어 나더라도 item요소 자기의 width,height을 줄여서라도 강제로 한 줄에 배치한다.
* wrap : item요소들이 자기의 width,height을 지켜 여러 행에 나누어서 배치된다.
* wrap-reverse : wrap과 동일하게 동작하지만 시작점과 끝점이 반대가 된다.

## flex-flow
* flex-direction과 flex-wrap을 합친 단축 속성

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
* item요소의 크기보다 container의 크기가 작아지면서 값이 제일큰 3번째 자식이 제일 많이 줄어들도 두번째로 값이 큰 2번째 자식이 그다음 줄어들고 첫번째 자식은 기본값이므로 
