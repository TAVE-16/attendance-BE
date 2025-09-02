# ---------- 1단계: 빌드 ----------
FROM gradle:8-jdk17-alpine AS build
WORKDIR /workspace

# gradle wrapper, 빌드 스크립트 먼저 복사 (캐시 활용)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew

# 의존성 캐싱
RUN ./gradlew dependencies --no-daemon || true

# 소스 복사 후 빌드
COPY src ./src
RUN ./gradlew clean bootJar --no-daemon

# ---------- 2단계: 실행 ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 빌드 산출물만 복사
COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]