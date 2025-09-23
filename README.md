# User Management API

[🇺🇸 English](#english) | [🇧🇷 Português](#português)

---

## English

A Java project developed with Quarkus following Hexagonal Architecture principles (Ports and Adapters), focused on user management with JWT authentication.

## 🏗️ Architecture

This project implements **Hexagonal Architecture** in a simplified way, ensuring clear separation between business logic and external dependencies.

### Architecture Structure

```
Domain (Core) ← Application → Adapters
     ↑              ↑           ↑
   Models      Use Cases    REST/GraphQL
   Ports       Services     Persistence
```

### Module Organization

- **Domain**: Application core with business rules
- **Application**: Use case orchestration
- **Adapters**: External world integrations (APIs, DB, etc.)
- **Infrastructure**: Technical configurations and utilities

## 🚀 Technologies

- **Java** - Main programming language
- **Quarkus** - Reactive and cloud-native framework
- **JWT** - Authentication and authorization
- **GraphQL** - API for flexible queries
- **REST** - Traditional APIs
- **Maven** - Dependency management
- **Docker** - Containerization

## 📦 Features

### Main Modules

#### 🔐 Auth
- User authentication
- JWT token generation and validation
- Access control

#### 👤 User
- User creation
- Profile management
- Queries via REST and GraphQL

#### 📞 Contact
- User contact management
- Different contact types
- Validation states

#### 🔑 Password
- Password encryption
- Policy validation
- Password recovery

#### 🛠️ Shared
- Global exception handling
- Shared utilities
- Common validators

## 🏃‍♂️ How to Run

### Prerequisites
- Java 11+
- Maven 3.8+
- Docker (optional)

### Development Mode
```bash
# Run in dev mode with hot-reload
./mvnw compile quarkus:dev
```

### Native Build
```bash
# Compile to native binary
./mvnw package -Pnative
```

### Docker

#### JVM Mode
```bash
docker build -f src/main/docker/Dockerfile.jvm -t user-management-api .
docker run -i --rm -p 8080:8080 user-management-api
```

#### Native Mode
```bash
docker build -f src/main/docker/Dockerfile.native -t user-management-api-native .
docker run -i --rm -p 8080:8080 user-management-api-native
```

#### Native Micro Mode (GraalVM)
```bash
docker build -f src/main/docker/Dockerfile.native-micro -t user-management-api-micro .
docker run -i --rm -p 8080:8080 user-management-api-micro
```

## 🔧 Configuration

### Environment Variables

```properties
# Application settings
quarkus.http.port=8080
quarkus.log.level=INFO

# JWT Configuration
jwt.private.key=privateKey.pem
jwt.public.key=publicKey.pem

# Database (configure as needed)
quarkus.datasource.url=jdbc:postgresql://localhost:5432/userdb
quarkus.datasource.username=user
quarkus.datasource.password=password
```

## 📚 API Endpoints

### REST Endpoints

#### Authentication
```http
POST /auth/signin
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

#### Users
```http
# Create user
POST /users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "gender": "MALE"
}

# Get user
GET /users/{id}
Authorization: Bearer {token}
```

### GraphQL Endpoint
```http
POST /graphql
Content-Type: application/json
Authorization: Bearer {token}

{
  "query": "{ users { id name email } }"
}
```

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report
```

## 📁 Directory Structure

```
src/main/java/
├── features/
│   ├── auth/           # Authentication module
│   ├── user/           # User module
│   ├── contact/        # Contact module
│   └── password/       # Password module
└── shared/             # Shared components

# Each feature follows hexagonal structure:
feature/
├── domain/             # Business rules
│   ├── models/         # Domain entities
│   ├── useCase/        # Use case interfaces
│   ├── repository/     # Repository interfaces
│   └── services/       # Service interfaces
├── application/        # Use case implementations
│   ├── useCase/        # Concrete use cases
│   └── services/       # Application services
├── adapters/           # Adapters
│   ├── in/             # Input adapters
│   │   ├── http/       # REST Controllers
│   │   └── graphql/    # GraphQL Resources
│   └── out/            # Output adapters
│       ├── entities/   # Persistence entities
│       ├── mappers/    # Mappers
│       └── persistence/# Concrete repositories
└── infrastructure/     # Technical configurations
```

## 🛡️ Security

- **JWT**: RSA signed tokens
- **Validation**: Custom input validators
- **Exceptions**: Centralized error handling
- **CORS**: Production environment configuration

## 🚀 Deploy

### Production Configuration

1. **Configure environment variables**
2. **Generate production JWT keys**
3. **Configure database**
4. **Set up logging and monitoring**

### Performance

- **Native Build**: Startup time < 50ms
- **Memory Footprint**: < 100MB in native mode
- **Hot Reload**: Agile development with Quarkus Dev Mode

## 🤝 Contributing

1. Fork the project
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## 📞 Support

For questions and support:
- Open an issue on GitHub
- Contact the development team

---

## Português

Um projeto Java desenvolvido com Quarkus seguindo os princípios da Arquitetura Hexagonal (Ports and Adapters), focado na gestão de usuários com autenticação JWT.

## 🏗️ Arquitetura

Este projeto implementa a **Arquitetura Hexagonal** de forma simplificada, garantindo a separação clara entre lógica de negócio e dependências externas.

### Estrutura da Arquitetura

```
Domain (Core) ← Application → Adapters
     ↑              ↑           ↑
   Models      Use Cases    REST/GraphQL
   Ports       Services     Persistence
```

### Organização dos Módulos

- **Domain**: Núcleo da aplicação com regras de negócio
- **Application**: Orquestração dos casos de uso
- **Adapters**: Integrações com mundo externo (APIs, BD, etc.)
- **Infrastructure**: Configurações técnicas e utilitários

## 🚀 Tecnologias

- **Java** - Linguagem principal
- **Quarkus** - Framework reativo e cloud-native
- **JWT** - Autenticação e autorização
- **GraphQL** - API para consultas flexíveis
- **REST** - APIs tradicionais
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização

## 📦 Funcionalidades

### Módulos Principais

#### 🔐 Auth
- Autenticação de usuários
- Geração e validação de tokens JWT
- Controle de acesso

#### 👤 User
- Criação de usuários
- Gestão de perfis
- Consultas via REST e GraphQL

#### 📞 Contact
- Gestão de contatos de usuários
- Diferentes tipos de contato
- Estados de validação

#### 🔑 Password
- Criptografia de senhas
- Validação de políticas
- Recuperação de senhas

#### 🛠️ Shared
- Tratamento global de exceções
- Utilitários compartilhados
- Validadores comuns

## 🏃‍♂️ Como Executar

### Pré-requisitos
- Java 11+
- Maven 3.8+
- Docker (opcional)

### Modo Desenvolvimento
```bash
# Executar em modo dev com hot-reload
./mvnw compile quarkus:dev
```

### Build Nativo
```bash
# Compilar para binário nativo
./mvnw package -Pnative
```