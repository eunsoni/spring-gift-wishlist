# 🎁 Spring Gift Project

> **카카오테크캠퍼스 학습용 프로젝트**  
> Spring Boot를 활용한 기본적인 상품 관리 REST API 구현

## 📖 프로젝트 소개

이 프로젝트는 카카오테크캠퍼스에서 Spring Boot를 학습하기 위해 제작된 교육용 애플리케이션입니다.  
선물 상품 관리를 주제로 하여 기본적인 CRUD(Create, Read, Update, Delete) 작업을 REST API로 구현했습니다.

## 🛠 기술 스택

- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Web MVC**
- **Thymeleaf** (템플릿 엔진)
- **H2 Database** (인메모리 데이터베이스)
- **Gradle** (빌드 도구)
- **JUnit 5** & **Mockito** (테스트)

## 📋 주요 기능

### 상품 관리 API
- **상품 전체 조회**: 등록된 모든 상품 목록을 조회합니다
- **상품 개별 조회**: 특정 ID의 상품 정보를 조회합니다
- **상품 등록**: 새로운 상품을 등록합니다
- **상품 수정**: 기존 상품 정보를 수정합니다
- **상품 삭제**: 등록된 상품을 삭제합니다

### 웹 인터페이스
- 기본적인 웹 페이지를 통한 API 정보 제공

## 🚀 실행 방법

### 1. 저장소 클론
```bash
git clone https://github.com/eunsoni/spring-gift-project.git
cd spring-gift-project
```

### 2. 애플리케이션 실행
```bash
# Gradle을 이용한 실행
./gradlew bootRun

# 또는 JAR 파일 빌드 후 실행
./gradlew build
java -jar build/libs/spring-gift-0.0.1-SNAPSHOT.jar
```

### 3. 애플리케이션 접속
- **웹 페이지**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (비어있음)

## 📚 API 명세

### Base URL
```
http://localhost:8081/api/products
```

### 엔드포인트

| 메서드 | 경로 | 설명 | 요청 바디 | 응답 |
|--------|------|------|-----------|------|
| GET | `/api/products` | 모든 상품 조회 | - | `Product[]` |
| GET | `/api/products/{id}` | 특정 상품 조회 | - | `Product` |
| POST | `/api/products` | 상품 생성 | `Product` | `Product` |
| PUT | `/api/products/{id}` | 상품 수정 | `Product` | `Product` |
| DELETE | `/api/products/{id}` | 상품 삭제 | - | - |

### Product 모델
```json
{
  "id": 1,
  "name": "상품명",
  "price": 10000,
  "imageUrl": "https://example.com/image.jpg"
}
```

## 📝 API 사용 예시

### 1. 모든 상품 조회
```bash
curl -X GET http://localhost:8081/api/products
```

### 2. 새 상품 등록
```bash
curl -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "카카오 머그컵",
    "price": 15000,
    "imageUrl": "https://example.com/mug.jpg"
  }'
```

### 3. 상품 수정
```bash
curl -X PUT http://localhost:8081/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "카카오 텀블러",
    "price": 25000,
    "imageUrl": "https://example.com/tumbler.jpg"
  }'
```

### 4. 상품 삭제
```bash
curl -X DELETE http://localhost:8081/api/products/1
```

## 🧪 테스트 실행

```bash
# 모든 테스트 실행
./gradlew test

# 테스트 결과 확인
./gradlew test --info
```

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/gift/
│   │   ├── Application.java              # 메인 애플리케이션 클래스
│   │   ├── controller/
│   │   │   ├── HomeController.java       # 웹 페이지 컨트롤러
│   │   │   └── ProductController.java    # 상품 REST API 컨트롤러
│   │   ├── domain/
│   │   │   └── Product.java              # 상품 엔티티
│   │   ├── repository/
│   │   │   ├── ProductRepository.java    # 상품 리포지토리 인터페이스
│   │   │   └── InMemoryProductRepository.java # 메모리 기반 구현체
│   │   └── service/
│   │       └── ProductService.java       # 상품 비즈니스 로직
│   └── resources/
│       ├── application.properties        # 애플리케이션 설정
│       └── templates/
│           └── index.html               # 메인 페이지
└── test/
    └── java/gift/
        └── service/
            └── ProductServiceTest.java  # 서비스 레이어 테스트
```

## ⚠️ 주의사항

- **학습용 프로젝트**: 이 프로젝트는 교육 목적으로 제작되었습니다
- **메모리 저장소**: 데이터는 메모리에만 저장되며, 서버 재시작 시 초기화됩니다
- **프로덕션 비권장**: 실제 운영 환경에서는 사용하지 마세요

## 🔧 개발 환경

- **IDE**: IntelliJ IDEA 또는 VS Code 권장
- **Java**: OpenJDK 21 이상
- **Gradle**: 8.4 (Wrapper 포함)

## 📈 향후 개선 사항

- [ ] 실제 데이터베이스 연동 (MySQL, PostgreSQL)
- [ ] Spring Security를 활용한 인증/인가
- [ ] 사용자별 상품 관리
- [ ] 상품 카테고리 분류
- [ ] 파일 업로드 기능
- [ ] API 문서화 (Swagger/OpenAPI)

## 🤝 기여

이 프로젝트는 학습용이므로 별도의 기여는 받지 않습니다.  
학습 과정에서 궁금한 점이 있다면 이슈를 통해 문의해 주세요.

## 📄 라이선스

이 프로젝트는 교육 목적으로 제작되었으며, 자유롭게 학습 자료로 활용하실 수 있습니다.

---

**Kakao Tech Campus Learning Project** | 2025
