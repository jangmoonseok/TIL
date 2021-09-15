# HTML/CSS 연습문제 풀이

## 1번문제
[문제 설명] 
border, padding, content, margin에 대해 알고 있는지 묻는 문제입니다. 
[제한 사항] 
1. box-sizing은 default 값으로 합니다. (content-box) 
2. css 선택자는 “.content” 로 합니다. 
[가이드라인] 
1. content (width, height), padding, border, margin의 순서에 대해 알아봅시다. 2. content (width, height), padding, border, margin 이 각각 어떤 속성인지 알아봅 시다. 
[문제]  
다음 조건을 만족하는 HTML 마크업, css 코드를 작성하세요 
1. margin은 상단 10px, 하단 20px, 왼쪽 오른쪽 15px 
2. width 100px, height 100px 
3. padding 은 10px 
4. border 는 검정색 1px

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .content{
            margin: 10px 15px 20px;
            width: 100px;
            height: 100px;
            padding: 10px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <div class="content"></div>
</body>
</html>
```

## 2번문제
[문제 설명] 
css 기본적인 속성에 대해 알고 있고 응용할 수 있는지 확인하는 문제입니다. 
[가이드라인] 
1. line-height를 사용하여 텍스트의 높이를 지정해봅시다. 
2. font-size, font-weight 를 사용하여 텍스트의 스타일을 바꾸어 봅시다. 
[문제]  
![image](https://user-images.githubusercontent.com/64582209/133447955-fc3512f9-ab52-4fb8-808a-fc7ae862194d.png)

위 이미지와 같은 네비게이션 메뉴를 만들어보세요 
요구사항 
1) 네비게이션 바 높이 64px 
2) 하단 border 크기: 1px, 색상: rgb 231, 231, 231 
3) 네비게이션 바 우측 좌측 padding 16px 
4) 제목 폰트 크기 24px 
5) 제목 굵기: bold 
6) 메뉴 아이템 좌우측 margin 10px
