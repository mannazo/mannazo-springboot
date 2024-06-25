# Eclipse Adoptium Temurin JDK 17을 사용하여 최종 이미지 생성
FROM eclipse-temurin:17

# 빌드 이미지에서 생성된 JAR 파일 복사
COPY *.jar /app.jar

# 애플리케이션이 실행될 포트 노출
EXPOSE 8080

# JAR 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
