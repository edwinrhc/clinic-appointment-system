# 🏥 Clinic Appointment System - Backend API

Este es un sistema backend para la gestión de citas médicas desarrollado con **Spring Boot 3**, **JWT**, y **MySQL**. El sistema permite registrar usuarios, autenticarse y proteger recursos mediante autenticación basada en tokens JWT.

## 🚀 Características principales

- Registro de usuarios (`/user/signup`)
- Login con generación de token JWT (`/user/login`)
- Validación de roles (`admin`, `user`)
- Seguridad con Spring Security + JWT
- Endpoints protegidos por token
- Documentación Swagger UI
- Arquitectura limpia (controller, service, repository, utils)
- Base de datos MySQL
- Spring Boot 3.x y Java 17

---

## 🧱 Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.10
- Spring Security
- JWT (`jjwt 0.11.5`)
- Spring Data JPA
- MySQL
- Swagger OpenAPI 3 (`springdoc-openapi`)
- Lombok

---

## 📦 Estructura del proyecto
```
src/main/java/com/edwin/clinic/
├── entity/         # Entidades JPA (User, etc.)
├── repository/     # Interfaces JPA Repository
├── service/        # Interfaces de servicios
├── serviceImpl/    # Implementaciones de los servicios
├── rest/           # Interfaces REST (controladores)
├── restImpl/       # Implementaciones REST
├── jwt/            # Clases de JWT (JwtFilter, JwtUtil)
├── utils/          # Clases utilitarias (validaciones, respuestas)
└── security/       # Configuración de Spring Security
```
---

## 🔐 Seguridad con JWT

El sistema utiliza JWT para autenticar y autorizar usuarios. Los tokens incluyen:

- `sub`: email del usuario
- `role`: rol del usuario (`admin` o `user`)
- `exp`: tiempo de expiración

---

## 📄 Endpoints principales

| Método | Ruta             | Descripción                     |
|--------|------------------|---------------------------------|
| POST   | `/user/signup`   | Registro de nuevos usuarios     |
| POST   | `/user/login`    | Autenticación y token JWT       |
| GET    | `/user/info`     | (Pendiente) Datos del usuario actual (protegido) |

---

## 🔧 Configuración

### `application.properties`

```properties
server.port=8091
spring.datasource.url=jdbc:mysql://localhost:3306/clinicadb
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
jwt.secret=UEZyZk1XM2dBTDFuU2t5djE4eUZnZkNMaGtsZGx4Nmk= # mínimo 32 caracteres


