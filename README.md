# 영화정보 검색 어플리케이션
## 설명
- 네이버 검색 API를 활용하여 영화정보를 검색하는 어플리케이션 제작
- 사용자로부터 검색어를 입력받아 검색결과 목록을 표시

# 구현
## 필수 구현사항

- EditText를 통해 검색어를 입력받아 `검색`버튼으로 영화 검색 (완)
- [네이버 검색 API](https://developers.naver.com/docs/search/movie/)를 활용하여 검색어에 해당하는 결과 받아오기 (완)
- 검색결과를 RecyclerView에 표시하기 (완)
- 각 영화정보에는 아래 정보가 모두 표시
: 썸네일 이미지, 제목, 평점, 연도, 감독, 출연배우  (완)
- 목록에서 영화 선택시 해당 영화 정보 link페이지로 이동 (완)
## 선택 구현사항
필수 구현사항은 아니지만 아래 내용을 구현한경우 가산점을 받습니다.
### 구조 및 라이브러리
- RxJava 사용 (미구현)
- Databinding 사용 (부분 완)
- MVP / MVVM / MVI 패턴 사용 (미구현)

### 기능 구현
- 검색결과 무한 스크롤 구현 (미구현)
- 유효하지 않은 검색어, 검색결과 처리 (부분 완)
- 기타 알수 없는 에러발생 처리 (미구현)