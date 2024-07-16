![서비스 로고](results/logo_dark.png)  
<br/>

# Spirit of Parenthesis (**최현준** **박창빈**)

## Introduction of Myfit

운동 목표와 나의 체성분 분석결과를 바탕으로,  
**사용자별 맞춤** 운동영상을 제공하고,  
목적과 기호에 맞춘 식단 추천 서비스를 가진 맞춤형 헬스케어 서비스

## Tech Stacks
<div style="text-align:center;">

<h4 style="text-align:left;">Framework</h4>  

![vue3](https://img.shields.io/badge/vue-4FC08D.svg?&style=for-the-badge&logo=vue.js&logoColor=white)
![spring](https://img.shields.io/badge/spring-6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)

<h4 style="text-align:left;">IDE</h4>  

![vue3](https://img.shields.io/badge/eclipse-2C2255.svg?&style=for-the-badge&logo=eclipse&logoColor=white)
![vscode](https://img.shields.io/badge/vs_code-007ACC.svg?&style=for-the-badge&logo=visualstudiocode&logoColor=white)

<h4 style="text-align:left;">Language</h4>

![java](https://img.shields.io/badge/java-E34F26.svg?&style=for-the-badge&logo=java&logoColor=white)
![javascript](https://img.shields.io/badge/javascript-F7DF1E.svg?&style=for-the-badge&logo=javascript&logoColor=white)
![mysql](https://img.shields.io/badge/mysql-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white)
![html5](https://img.shields.io/badge/html5-E34F26.svg?&style=for-the-badge&logo=html5&logoColor=white)
![css3](https://img.shields.io/badge/css3-1572B6.svg?&style=for-the-badge&logo=css3&logoColor=white)

<h4 style="text-align:left;">API</h4>

![html5](https://img.shields.io/badge/naver_clova-03C75A.svg?&style=for-the-badge&logo=naver&logoColor=white)
![css3](https://img.shields.io/badge/youtube_api-FF0000.svg?&style=for-the-badge&logo=youtube&logoColor=white)

</div>

## Installation

1. 레포지토리를 클론합니다.
   ```bash
   git clone https://github.com/HJtheKing/myfit.git
   ```

2. 프로젝트 폴더로 이동합니다.
   ```bash
   cd spirit-of-parenthesis
   ```

3. DB를 설치합니다.
   ```
   BaseSchema.sql
   ```

4. 필요한 package를 설치합니다.
   ```bash
    npm install
   ```

5. REST API를 실행합니다.
   ```
    run MYFIT
   ```

6. YOUTUBE API 키를 작성합니다.
    ```
    .env.local -> VITE_YOUTUBE_API_KEY 추가
    ```
    AIzaSyAX4oR97pbfb6S--j20qsCbKmAiJ2rq-qU

7. MYFIT-PROJECT를 실행합니다.
   ```bash
    npm run dev
   ```

7. 접속하기
    ```URL
    http://localhost:5173/
    ```

## ERD (Entity Relationship Diagram) 
![erd](results/erd.png) 

## Swagger-ui 
![swaggerui](results/swaggerui.png) 

## Functions

0. 로그인, 회원가입 기능
   ![로그인](results/gifs/login_out.gif)
   ![회원가입1](results/gifs/signup_1_check_email.gif)
   ![회원가입2](results/gifs/signup_2_base_infos.gif)
   ![회원가입3](results/gifs/signup_3_auto_hyp.gif)
   ![회원가입4](results/gifs/signup_4_check_pass.gif)

1. Youtube API를 활용한 영상 추가기능
   ![영상 추가](results/gifs/video_add.gif)

2. 게시글 게시 / 수정 / 삭제
   ![게사글 추가](results/gifs/post_write_edit.gif)
   - 작성자
   ![게사글 추가](results/gifs/writer.gif)
   - 방문자
   ![게사글 추가](results/gifs/non_writer.gif)

3. 좋아요
   ![좋아요](results/gifs/like.gif)  
   ![취소](results/gifs/unlike.gif)  

4. 마이페이지를 통한 정보 관리
   ![마이페이지1](results/gifs/mypage_profile.gif)
   ![마이페이지2](results/gifs/activity.gif)
   ![마이페이지3](results/gifs/helpdesk.gif)

5. 체성분 분석 결과를 활용한 운동 추천
   ![체성분측정1](results/gifs/inbody_ocr.gif)
   ![체성분측정2](results/gifs/inbody_edit.gif)
   ![체성분측정3](results/gifs/inbody_delete_recommand.gif)

6. 목적과 개인별 정보를 활용한 식단 추천
   ![식단](results/gifs/diet.gif)

## Authors and Feelings

### 최현준
   - 프로젝트의 성공적인 완수를 위해 팀원 간의 원활한 소통과 협업이 필수적임을 깨달았습니다.
   - 또한, 다양한 기술 스택을 통합하여 하나의 시스템을 구축하는 경험을 통해 기술 간의 상호작용을 이해할 수 있었습니다.
   

### 박창빈
   - 작은 버튼, 작은 동작일수록 오히려 더 많은 고민이 필요함을 느꼈습니다.
   - 또한, 검색해야 할 데이터의 양이 많아질수록 데이터를 처리하기 위한 고민이 깊어질텐데, 모든 처리를 서버에서 수행하여 넘겨주기보다, 클라이언트 단에서 활용하는 것이 이득일 수도 있음을 이해할 수 있었습니다.
   

## License
이 프로젝트의 저작권은 원작자에게 있습니다.

ⓒ MYFIT 11th 최현준 박창빈, 2024
