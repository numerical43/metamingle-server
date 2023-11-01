<div align="center">

## 🦄 Meta Mingle 메타밍글 : Backend

<Br>

![meta mingle_text](https://github.com/meta-mingles/metamingle-server/assets/61495627/f0baa8f0-2d6d-4e7b-b10d-95f1220eda63)



[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fmeta-mingles%2Fmetamingle-server&count_bg=%23FFA49F&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=views&edge_flat=false)](https://hits.seeyoufarm.com)

</div>



<br>

## 👋 팀원 소개
<table>
  <tr>
    <td align="center"><a href="https://github.com/Dylan-SonJungin"><img src="https://avatars.githubusercontent.com/Dylan-SonJungin" width="150px;" alt="">
    <td align="center"><a href="https://github.com/numerical43"><img src="https://avatars.githubusercontent.com/numerical43" width="150px;" alt="">
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/Dylan-SonJungin"><b>손정인</b></td>
    <td align="center"><a href="https://github.com/numerical43"><b>강수의</b></td>
  </tr>
    <tr>
    <td align="center"><strong>회원, 아바타</strong></td>
    <td align="center"><strong>숏폼, 인터렉티브 무비</strong></td>
  </tr>
</table>

<br>

## ⚒️ 기술 스택
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

<br>

## ✨ 협업 도구
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black"/> <img src="https://img.shields.io/badge/Miro-F7DF1E?style=for-the-badge&logo=Miro&logoColor=black"/>

<br>
<br>
<br>



## 📌 컨벤션

<br>

### 커뮤니케이션 컨벤션

<br>

- 모여서 회의가 불가능할 경우 **Discord**를 통해 회의합니다.
- 논의 사항은 **Git Discussion**에 정리합니다.
- 협업 툴 : **Github**, **Discord**

<br>

### 코드 컨벤션
<br>

#### 🎉 클래스 명칭


```
🐤 [ 도메인 이름 ] + [ Command / Query ] + [ Domain / Infra ] + [ Controller / Service / Repository]
```

<br>

#### 🎉 메소드 명칭

```
C : create + [ 명사 ]

R : find + [ 명사 ]

U : update + [ 명사 ]

D : delete + [ 명사 ]
```

메소드 명은 🐫(Camel Case)로 표기할 것!

<br>

#### 🎉 API 응답

![image](https://github.com/cca-ffodregamdi/running-hi-back/assets/115992753/4dd76c8d-dcc3-486d-830c-cda93a5ecb39)

> 출처 : https://wildeveloperetrain.tistory.com/m/240


응답의 형태는 Common 패키지에서 공용으로 사용되며 공통 양식을 유지할 것!

<br>

#### 🎉 DTO


DTO(Data Transfer Object)를 request와 response로 나누어 제작할 것!

<br>

## GitHub : PR & Commit  컨벤션

#### ✅ **Git Convention**
| **Convention**  | **내용**                                                         |
|-----------------|----------------------------------------------------------------|
| **Feat**        | 새로운 기능 추가                                                      |
| **BugFix**         | 버그 수정                                                          |
| **Test**        | 테스트 코드, 리펙토링 테스트 코드 추가, Production Code(실제로 사용하는 코드) 변경 없음     |
| **Comment**     | 필요한 주석 추가 및 변경                                                 |
| **Rename**      | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우                                   |
| **Remove**      | 파일을 삭제하는 작업만 수행한 경우                                            |
| **Refactor** | 프로덕션 코드 리팩토링                                                   |
| **API** | 서버 API 통신                                                   |
| **Deploy** | 배포 관련                                                   |
| **Setting** | 개발환경 세팅                                                   |

<br>
<br>

---------------------------------------------------

#### ✅ **Branch 명칭**
```
🧸 [ Commit 이름 ] / [ 도메인 이름 ] / [ 구현 기능 ]
```

#### ✅ **PR 제목**
```
🐘 [Label 이름(첫 글자 대문자)] - {작업 컨텍스트(대문자)} pr 내용
ex) [Feature] - {USER} 엔티티 설계
```


#### ✅ **PR & Commit 규칙**

- main branch에 바로 push 금지! develop branch로 Pull requests 하기.
- git convention을 지키기.
- PR 전에 이슈 발행 필수, PR 할 때 이슈 번호 입력 필수!
- 이슈 하나는 본인이 하루 내에 해결할 수 있는 양으로 선정하기.
- PR에 적극적으로 코드 리뷰 남기기 (LGTM 금지🙅).
- Action이 통과해야만 Merge 가능.
- Action 실패 시 원인 파악 및 테스트 성공 시까지 수정.
