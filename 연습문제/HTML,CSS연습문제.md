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
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .title, .menu{
            display: inline-block;
        }
        .navBar{
            height: 64px;
            position: relative;
            line-height: 64px;
            border-bottom: 1px solid rgb(231, 231, 231);
            padding: 0 16px;
        }
        .title{
            font-size: 24px;
            font-weight: bold;
        }
        .menu{
            position: absolute;
            right: 0;
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <nav class="navBar">
        <div class="title">
            네카라쿠배
        </div>
        <div class="menu">
            <span>로그인</span>
            <span>회원가입</span>
            <span>마이페이지</span>
        </div>
    </nav>
</body>
</html>
```

## 3번문제
[문제 설명] 
flex, grid 에 대해 이해해고 있는지, 어떻게 사용하고 있는지 확인하는 문제입니다.  
[제한 사항] 
1. 화면의 가로 크기는 400px 로 가정합니다 
2. 각 아이콘의 크기는 100x100px 입니다. 
[가이드라인] 
1. flex 혹은 grid 를 사용하여 구현해보세요 
[문제]  
![image](https://user-images.githubusercontent.com/64582209/133448078-6aa7e2b6-1e9c-4258-ba05-0fa2bb2beca7.png)

HTML/CSS 를 사용해 다음과 같은 화면을 만들어보세요. 
위 이미지는 이미지 출처.txt를 참고해주세요
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .container{
            width: 400px;
            display: flex;
            flex-wrap: wrap;

        }
        .item{
            width: 100px;
            height: 100px;
            background-size: cover;
            margin: 10px;
        }
        .item > img{
            width: 100%;
            height: 100%;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/ccWDU4A7fX1R24v-vvT480ySh26AYp97g1VrIB_FIdjRcuQB2JP2WdY7h_wVVAeSpg=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/h9jWMwqb-h9hjP4THqrJ50eIwPekjv7QPmTpA85gFQ10PjV02CoGAcYLLptqd19Sa1iJ=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/Ob9Ys8yKMeyKzZvl3cB9JNSTui1lJwjSKD60IVYnlvU2DsahysGENJE-txiRIW9_72Vd=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/PCpXdqvUWfCW1mXhH1Y_98yBpgsWxuTSTofy3NGMo9yBTATDyzVkqU580bfSln50bFU=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/lMoItBgdPPVDJsNOVtP26EKHePkwBg-PkuY9NOrc-fumRtTFP4XhpUNk_22syN4Datc=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/eN0IexSzxpUDMfFtm-OyM-nNs44Y74Q3k51bxAMhTvrTnuA4OGnTi_fodN4cl-XxDQc=s180-rw" alt="">
        </div>
        <div class="item">
            <img src="https://play-lh.googleusercontent.com/z5nin1RdQ4UZhv6fa1FNG7VE33imGqPgC4kKZIUjgf_up7E-Pj3AaojlMPwNNXaeGA=s180-rw" alt="">
        </div>
    </div>
</body>
</html>
```

## 4번문제
[문제 설명] 
media query를 사용한 반응형 페이지 구현에 대해 알고 있는지 확인하는 문제입니다. 
[가이드라인] 
1. media query를 사용하여 반응형 페이지를 만들어보세요. 
[문제]  
그림1) 

그림2) 
![image](https://user-images.githubusercontent.com/64582209/133448306-3968d46f-d128-4f65-8240-05a30e5d63b3.png)

위 이미지와 같은 종목 소개페이지를 만들어보세요. 
본문 내용한 첨부한 본문내용.txt 를 참고해주세요. 
요구사항 
1) 종목 명, 종목코드, 회사 소개, 시가총액 순위 표시 
2) 그림1) 그림2) 모바일 페이지, 데스크탑 페이지 반응형으로 표시되어야 합니다. (단 데스크탑, 모바일 페이지가 변경되는 기준 width는 720px 로 합니다.)
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            margin: 0;
            padding: 0;
        }
        main{
            width: calc(100% - 300px);
            float: left;
        }
        aside{
            width: 300px;
            float: right;
            
        }
        .main_title{
            background-color: blue;
            color: white;
            padding: 16px;
        }
        .main_description{
            padding: 24px;
        }
        .aside_container{
            padding: 16px;
        }
        .aside_list>li{
            font-weight: bold;
            padding: 8px 0;
            border-bottom: 1px solid black;
        }
        @media screen and (max-width:720px) {
            main{
                width: 100%;
            }
            aside{
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <main>
        <div class="main_title">
            <h1>삼성전자</h1>
            <span>종목코드: 005930</span>
        </div>
        <div class="main_description">
            <h3>회사소개</h3>
            <p>한국 및 CE, IM부문 해외 9개 지역총괄과 DS부문 해외 5개 지역총괄,
                Harman 등 241개의 종속기업으로 구성된 글로벌 전자기업임. 세트사업에는
                TV, 냉장고 등을 생산하는 CE부문과 스마트폰, 네트워크시스템, 컴퓨터
                등을 생산하는 IM부문이 있음. 부품사업(DS부문)에서는 D램, 낸드 플래쉬,
                모바일AP 등의 제품을 생산하는 반도체 사업과 TFT-LCD 및 OLED 디스플레이
                패널을 생산하는 DP사업으로 구성됨.</p>
        </div>
    </main>
    <aside>
        <div class="aside_container">
            <h3>시가총액 순위</h3>
            <ol class="aside_list">
                <li>삼성전사</li>
                <li>SK하이닉스</li>
                <li>NAVER</li>
                <li>카카오</li>
                <li>삼성바이오로직스</li>
                <li>삼성전자우</li>
                <li>삼성SDI</li>
                <li>LG화학</li>
                <li>현대차</li>
                <li>셀트리온</li>
            </ol>

        </div>
    </aside>
</body>
</html>
```


## 5번문제
[문제 설명] 
HTML/CSS에 대한 전체적인 응용능력 에 대해 확인하는 문제입니다. 
실제 서비스되고 있는 페이지를 클론코딩 해보면서, HTML/CSS에 대해 이해하고 있는지 확인할 수 있는 문제입니다. 
[제한 사항] 
1. 모바일 화면 기준으로 개발 해주세요. 
[문제]  
![image](https://user-images.githubusercontent.com/64582209/133448438-a0e6cfd3-437d-46ae-a9a5-a3620053c414.png)

위 이미지 처럼 댓글리스트를 만들어보세요. 
댓글내용은 첨부한 댓글내용.txt 파일을 참고해주세요. 
요구사항 
1) 각 댓글 컨테이너의 width는 100%, 좌우 padding 은 20px 로 합니다. (단 box sizing은 border-box 기준) 
2) 댓글 컨테이너의 상하 padding은 18px 입니다 
3) 댓글 수 텍스트의 색상은 #06a27d 로 합니다. 
4) 날짜 텍스트의 색상은 #939393 으로 합니다 
5) 줄간격은 1.5로 합니다. 
6) 댓글 내용 줄을 바꿀 때 단어가 끊기지 않도록 css 속성을 설정해 주세요.
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .comment_container{
            width: 400px;
            margin: 0 auto;
        }
        .title{
            font-size: 32px;
            font-weight: bold;
            border-bottom: 2px solid black;
            padding-bottom: 10px;
        }
        .count{
            color:  #06a27d;
            margin-left: 10px;
        }
        .comment_list{
            width: 100%;
            padding: 18px 20px;
            border-bottom: 1px solid gray;
            box-sizing: border-box;
        }
        .name{
            font-weight: bold;
        }
        .date{
            color: #939393 ;
        }
        .content{
            line-height: 1.5;
            word-break: keep-all;
        }

    </style>
</head>
<body>
    <div class="comment_container">
        <div class="title">댓글<span class="count">923</span></div>
        <div class="comment_list">
            <div class="name">정*석</div>
            <span class="date">2021.03.16</span>
            <p class="content">1차 합격하고 실제 네이버간 본인입니다 ㅎㅎ 저는 전공자긴 하지만 학점은 3.0 아래고 나이도 30세가 넘었습니다. 제가 가진건 끈기 단 하나였어요. 목표를 잡고 일부는 성취하고, 일부는 실패하더라도 전략을 수정하여 계속 도전해 결국엔 목표를 이루려고 하였습니다. '이쯤 했으면 됐다' 생각할 때 포기하지 마시고 한발자국씩 더 나아가보세요. 분명히 좋은 결과가 있을거에요! 화이팅입니다.</p>
        </div>
        <div class="comment_list">
            <div class="name">한*현</div>
            <span class="date">2021.03.21</span>
            <p class="content">포기하기마시고 즐거운 개발하셔서 꽃길 걸을 수 있길 바랍니다!</p>
        </div>
        <div class="comment_list">
            <div class="name">이*자</div>
            <span class="date">2021.03.21</span>
            <p class="content">능력있고 열심히 하는 분들에게 좋은 결과 있기를 바랍니다. 항상 좋은 일만 가득하셨으면 좋겠습니다. 멋진 앞날을 기원하겠습니다.</p>
        </div>
        <div class="comment_list">
            <div class="name">이*윤</div>
            <span class="date">2021.03.21</span>
            <p class="content">네카라쿠배 1기 모집 글을 본게 엊그제 같은데 벌써 최종 15명이네요. 꼭 네카라쿠배 가셔서 즐겁게 개발일 하시면 좋겠습니다! 앞으로의 길을 응원해요. 저도 2기 지원할게요~!!</p>
        </div>
        <div class="comment_list">
            <div class="name">박*호</div>
            <span class="date">2021.03.21</span>
            <p class="content">최종 15분이 멋진 길을 이뤄주시면 다음에 시도하시는 분들도 앞선 지원자들을 보고 더욱 열심히 공부를 하며 동기부여가 될거라고 생각합니다. 최선을 다하신것에 보답이라고 생각하시면서 꽃길만 걸으시기 바랍ㄴ디ㅏ.</p>
        </div>
        <div class="comment_list">
            <div class="name">김*성</div>
            <span class="date">2021.03.21</span>
            <p class="content">존버필승입니다 화이팅하십쇼</p>
        </div>
    </div>
</body>
</html>
```
