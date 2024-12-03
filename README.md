# 리본 : AI 이미지 인식 재활용 안내

🏛 경상국립대학교 코딩 동아리 이카루스  
💻 공개SW 개발자 대회 출품작  
</br>

## 프로젝트 소개  

```bash
• 간단한 스마트폰 촬영과 사진 속 쓰레기를 인식하는 인공지능 모델을 통해 올바른 재활용 방법을 제공하고자합니다.
• YOLOV5 이미지 인식 모델을 학습 및 개발하고 플라스크 자체 구축 서버를 통해 안드로이드 앱과 통신하여 동작합니다.
```

</br>
  
## 아키텍쳐
![이미지 설명](https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98_%EC%88%98%EC%A0%95%EB%B3%B8.png)

</br>

## 설치 방법

**1. 깃허브 Release 에서 앱을 다운로드합니다.** 

[Recycle App 릴리즈 v0.1.0](https://github.com/ICARUS-Coding-Club/Recycle-App/releases/tag/v0.1.0)

</br>

**2. 앱을 설치합니다.**    
  ```bash
  다운로드된 APK 파일을 실행하고 출처를 알 수 없는 앱 허용을 누른 다음 앱을 설치합니다.
  ```
</br>

## 주요 기능  

**1. 쓰레기 이미지 인식 기능**

  ```bash
  • 스마트폰 앱으로 쓰레기를 촬영하거나 사진을 업로드하여 서버로 전송합니다.
  • 서버로 전송된 사진이 저장되어 이미지 감지 학습 인공지능 모델을 실행합니다.
  • 미리 분류된 16가지 클래스로 쓰레기를 판별하여 어떤 쓰레기인지 판별합니다.
  • 판별된 쓰레기의 카테고리를 검색하여 판별된 쓰레기와 함께 관련된 쓰레기를 정보를 앱으로 반환합니다.
  • 서버로부터 반환된 쓰레기의 정보(설명, 분리수거, 배출 방법 등등)를 사용자가 알기 쉽게 제공합니다.
  ```

</br>

**2. 분리수거, 재활용과 관련된 정보 제공 기능**  

  ```bash
 • 카테고리별 쓰레기 검색을 통해 쓰레기의 배출 방법 및 분리수거 방법을 제공합니다.
 • 일반인에게 익숙하지 않은 재활용 마크에 대한 정보를 제공합니다.
 • 지역 선택을 통해 각 지역의 쓰레기 유형별(생활, 음식물, 재활용) 쓰레기 배출 방법, 장소 및 시간을 안내합니다.
 • 우리나라의 환경, 재활용, 분리수거 관련 기사를 한눈에 보기 쉽도록 안내합니다.
 • 쓰레기 재활용 현황을 한눈에 보기 쉽도록 차트 형태로 안내합니다.
  ```
    
</br>

## 사용 방법

**1. 앱 실행 및 접속**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/onborading.gif" width="400" />

</td>
<td valign="top">

#### 앱 실행
- Release 다운로드 링크에서 APK 파일을 다운 받아 설치합니다.
- 설치된 애플리케이션을 클릭하여 실행합니다.

#### 앱 접속
- 앱 접속에 로그인이 필요하지 않습니다.
- 불필요한 개인정보를 저장하지 않습니다.
- 앱 처음 실행 시 Onboarding 화면을 통해 튜토리얼을 진행합니다.
- 이후 접속 부터는 Onboarding 화면이 나타나지 않습니다.
- Next 버튼을 눌러 메인 화면으로 이동합니다.
  
</td>
</tr>
</table>
</br>

**2. 카테고리별 쓰레기 정보 표시**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/category_1.gif" width="400" />

</td>
<td valign="top">

#### 메인 화면 및 카테고리 이동
- 하단 네비게이션바의 홈 버튼을 눌러서 메인 화면으로 이동합니다.
- 검색뷰를 통해 직접 입력하거나 카테고리 버튼을 눌러서 이동합니다.

</td>
</tr>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/category_2.gif" width="400" />

</td>
<td valign="top">

#### 쓰레기 정보 안내
- 선택한 쓰레기의 자세한 배출 방법 및 재활용 방법을 안내합니다.
- 안내 화면의 우측 상단에 하트 마크를 클릭하여 즐겨찾기를 설정 할 수 있습니다.
- 메인 화면 하단에서 즐겨찾기가 설정된 쓰레기 정보로 바로 이동 가능합니다.
- 쓰레기 정보는 웹 크롤링을 통해 구축된 데이터베이스가 사용되었습니다.
- 안내 화면은 리사이클러뷰의 리니어 레이아웃과 그리드 레이아웃을 사용했습니다.
  
</td>
</tr>
</table>
</br>


**3. 재활용 마크 및 분리수거, 환경 정보 안내 화면**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/trash_info_1.gif" width="400" />

</td>
<td valign="top">

#### 재활용 마크 안내
- 대한민국에서 사용되는 모든 재활용 마크를 안내합니다.
- 스크롤을 내려 원하는 재활용 마크를 클릭하면 상세 정보가 나타납니다.
- Expandable View를 기반으로 커스텀된 뷰가 사용되었습니다.
  
</td>
</tr>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/trash_info_2.gif" width="400" />

</td>
<td valign="top">

#### 지역 쓰레기 배출 장소 및 방법 안내
- 쓰레기 관리 구역 정보를 통해 사용자가 설정한 지역의 정보를 제공합니다.
- 다음 주소 API와 쓰레기 관리 구역 API가 사용되었습니다.
  
</td>
</tr>
</tr>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/trash_info_3.gif" width="400" />

</td>
<td valign="top">

#### 환경 및 재활용 관련 기사 안내
- 관련 기사를 한눈에 제공하기 위해 웹 크롤링된 정보를 안내합니다.
  
</td>
</tr>
</table>
</br>


**4. 사진 촬영 및 갤러리 사진을 통한 인공지능 쓰레기 인식**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/image_search_g.gif" width="400" />

</td>
<td valign="top">
  
#### 사진 촬영
- 하단 중앙 버튼을 눌러서 사진 촬영 및 갤러리에서 사진을 불러옵니다.
  
#### 사진 비트맵 변환 및 서버 전송
- 촬영된 사진을 비트맵으로 변환하여 저장합니다.
- 저장된 사진을 바이트 배열로 서버에 전송합니다.
  
#### 인공지능을 통한 쓰레기 인식
- 미리 분류되어 학습된 클래스 기준으로 이미지를 인식하여 감지된 결과를 반환합니다.
- 판별된 쓰레기에 더해 관련된 카테고리의 쓰레기를 반환합니다.
- 해당 쓰레기의 배출 정보 및 분리수거 방법을 사용자에게 안내합니다.
  
</td>
</tr>
</table>
</br>



**5. 쓰레기 현황 통계**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/barchart.gif" width="400" />

</td>
<td valign="top">

#### 우리나라 및 지역의 쓰레기 현황 통계
- 쓰레기 통계를 바차트 형태로 보기 쉽게 안내합니다. 추후 추가적인 개발을 통해 다양한 기능을 제공하고자 합니다.
  
</td>
</tr>
</table>
</br>

**6. 앱 정보 및 설정 화면**  

<table>
<tr>
<td>

<img src="https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/image_view/gif/Screenshot_20230905_175753.png" width="400" />

</td>
<td valign="top">

#### 앱의 전반적인 정보 안내
- 프로젝트 팀 설명 및 앱의 정보를 안내합니다.
- 스마트폰의 환경설정 및 이메일 액티비티를 통해 앱을 초기화하거나 문의사항얼 전달할 수 있습니다.
  
</td>
</tr>
</table>
</br>

## 무선 디버깅 세팅 방법

1. 터미널에서 adb 명령어를 사용하기위해 환경변수 설정(시스템 변수/Path)
     ex) C:\Users\dofury\AppData\Local\Android\Sdk\platform-tools

  3. adb pair 아이피:port

  4. adb connect 아이피:port
    ex) adb connect 192.168.0.144:37207

</br>

## 라이선스

[리본 : Apache License 2.0](https://github.com/ICARUS-Coding-Club/Recycle-App/blob/master/LICENSE)    
[CircleIndicator : Apache License 2.0](https://github.com/ongakuer/CircleIndicator/blob/master/LICENSE)    
[Retrofit : Apache License 2.0](https://square.github.io/retrofit/#license)    
[Okhttp : Apache License 2.0](https://square.github.io/okhttp/#license)    
[Glide : Apache License 2.0](https://github.com/bumptech/glide/blob/master/LICENSE)    

<br/>

## 개발 환경

```bash
1. 안드로이드 스튜디오 2023.1 (Kotlin)
2. Retrofit HTTP API
3. Visual Studio Code (Python 3.8)
4. Anaconda
5. Cuda 11.8 RTX 3070, RAM 32GB
6. Flask
7. MySQL 8.0
```

</br>

## 인공지능 Git Hub  

```bash
https://github.com/ICARUS-Coding-Club/Recycle-AI
```

</br>

## 서버 Git Hub  

```bash
https://github.com/ICARUS-Coding-Club/Recycle-Server
```

</br>
