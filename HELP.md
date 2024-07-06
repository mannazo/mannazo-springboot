# Git

## Git Command

프로젝트에서 자주 사용하는 깃 명령어입니다.

```shell
# 서브 모듈 블러오기
git submodule update --init --recursive

# 서브모듈 main 브랜치로 최신화
cd ./mannanzo-springboot-secret
git checkout main
```

![image](https://obsidian-images-diligentp.s3.ap-northeast-2.amazonaws.com/47453ab6cea5d9599d9861597f8aa9d6.png)


## Conventional Commits

`Git Convention` 은 커밋 메시지에 사용자와 기계 모두가 이해할 수 있는 의미를 부여하기 위해 사용합니다.

우리는 다음과 같은 `Convention` 을 사용하고 있습니다.

```text
 - Feat : 새로운 기능과 관련된 것을 의미한다.

 - Fix : 오류와 같은 것을 수정했을 때 사용한다.

 - Docs : 문서와 관련하여 수정한 부분이 있을 때 사용한다.

 - Style : 코드의 의미와 무관한 변경사항 (포맷이나 세미콜론 등)

 - Refactor : 코드 리팩토링

 - Test : 테스트 코드 추가

 - Chore : 빌드 부분 or 패키지 매니저 코드 수정

 - Rename : 파일 수정 및 이동

 - Remove : 파일 삭제
```


