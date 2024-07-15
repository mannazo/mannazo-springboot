# bellsoft 의 openjre17을 사용하여 가벼운 이미지 생성
FROM bellsoft/liberica-openjre-alpine:17

# 애플리케이션 JAR 파일을 컨테이너에 추가
ADD /build/libs/*.jar app.jar

#EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]