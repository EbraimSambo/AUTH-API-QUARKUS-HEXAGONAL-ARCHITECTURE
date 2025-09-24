# Quarkus Auth API

[🇧🇷 Português](#português) | [🇺🇸 English](#english)

---

## 🇧🇷 Português

Uma API de autenticação moderna construída com Quarkus, seguindo os princípios da Clean Architecture e Hexagonal Architecture.

### 🏗️ Arquitetura

O projeto segue uma arquitetura hexagonal bem definida, organizada por features:

```
src/main/java/features/
├── auth/          # Autenticação e autorização
├── user/          # Gestão de usuários
├── contact/       # Gestão de contatos
├── password/      # Gestão de senhas
└── shared/        # Componentes compartilhados
```

Cada feature é estruturada em camadas:
- **Domain**: Modelos de negócio e ports (interfaces)
- **Application**: Casos de uso e serviços de aplicação
- **Infrastructure**: Adaptadores (REST, GraphQL, persistência)

### 🚀 Tecnologias

- **Java 17+**
- **Quarkus** - Framework supersônico para aplicações Java
- **Panache** - ORM simplificado do Quarkus
- **JWT** - Autenticação via JSON Web Tokens
- **REST + GraphQL** - APIs dual-protocol
- **Docker** - Containerização e deploy
- **Maven** - Gerenciamento de dependências

### 📋 Funcionalidades

#### Autenticação
- ✅ Registro de usuários (`POST /api/v1/auth/sign-up`)
- ✅ Login com JWT (`POST /api/v1/auth/sign-in`)
- ✅ Endpoints protegidos (`GET /api/v1/users/protected`)

#### Gestão de Usuários
- ✅ Criação de usuários com dados completos
- ✅ Suporte a múltiplos tipos de contato (EMAIL, PHONE)
- ✅ Gestão de gênero (FEMALE, MALE, OTHER)
- ✅ Criptografia segura de senhas

### 🛠️ Configuração e Execução

#### Pré-requisitos
- Java 17 ou superior
- Maven 3.8+
- Docker (opcional)

#### Executar em Modo Desenvolvimento
```bash
./mvnw compile quarkus:dev
```

A aplicação estará disponível em `http://localhost:8080`

#### Executar Testes
```bash
./mvnw test
```

#### Build para Produção
```bash
./mvnw package
```

#### Docker

**Executar com JVM**
```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-auth-api-jvm .
docker run -p 8080:8080 quarkus-auth-api-jvm
```

**Executar Native (GraalVM)**
```bash
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native -t quarkus-auth-api-native .
docker run -p 8080:8080 quarkus-auth-api-native
```

### 📚 Documentação da API

#### Endpoints Disponíveis

**Autenticação**

**POST** `/api/v1/auth/sign-up` - Criar conta
```json
{
  "name": "João Silva",
  "contact": "joao@exemplo.com",
  "contactType": "EMAIL",
  "password": "senha123",
  "gender": "MALE"
}
```

**POST** `/api/v1/auth/sign-in` - Fazer login
```json
{
  "contact": "joao@exemplo.com",
  "password": "senha123"
}
```

**Usuários**

**GET** `/api/v1/users/protected` - Endpoint protegido
- Requer: `Authorization: Bearer <jwt_token>`

#### Schemas

**CreateUserDto**
- `name` (obrigatório): Nome do usuário
- `contact` (obrigatório): Email ou telefone
- `password` (obrigatório): Senha
- `gender` (opcional): FEMALE | MALE | OTHER
- `contactType` (opcional): EMAIL | PHONE

**SignInRequest**
- `contact` (opcional): Email ou telefone
- `password` (obrigatório): Senha

#### Respostas HTTP
- `200` - Sucesso
- `400` - Dados inválidos
- `401` - Não autorizado
- `403` - Acesso negado

### 🔐 Segurança

- Autenticação via **JWT** com chaves RSA
- Senhas criptografadas com **bcrypt**
- Validação rigorosa de dados de entrada
- Headers de segurança configurados

#### Configuração JWT
As chaves RSA estão localizadas em:
- `src/main/resources/privateKey.pem`
- `src/main/resources/publicKey.pem`

### 🔧 Configuração

Principais configurações em `application.properties`:

```properties
# Base da aplicação
quarkus.http.port=8080

# JWT
mp.jwt.verify.publickey.location=publicKey.pem
smallrye.jwt.sign.key.location=privateKey.pem

# Base de dados
quarkus.datasource.db-kind=postgresql
quarkus.hibernate-orm.database.generation=update
```

### 🧪 Testes

O projeto inclui testes automatizados:
- Testes unitários para persistência
- Testes de integração para endpoints
- Cobertura de casos de uso

Executar com relatório:
```bash
./mvnw test jacoco:report
```

### 📊 Performance

Quarkus oferece:
- **Startup rápido**: < 1s em modo JVM
- **Baixo consumo de memória**: ~13MB RSS nativo
- **Compilação nativa**: Executáveis GraalVM
- **Live reload**: Desenvolvimento ágil

### 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

---

## 🇺🇸 English

A modern authentication API built with Quarkus, following Clean Architecture and Hexagonal Architecture principles.

### 🏗️ Architecture

The project follows a well-defined hexagonal architecture, organized by features:

```
src/main/java/features/
├── auth/          # Authentication and authorization
├── user/          # User management
├── contact/       # Contact management
├── password/      # Password management
└── shared/        # Shared components
```

Each feature is structured in layers:
- **Domain**: Business models and ports (interfaces)
- **Application**: Use cases and application services
- **Infrastructure**: Adapters (REST, GraphQL, persistence)

### 🚀 Technologies

- **Java 17+**
- **Quarkus** - Supersonic framework for Java applications
- **Panache** - Simplified Quarkus ORM
- **JWT** - Authentication via JSON Web Tokens
- **REST + GraphQL** - Dual-protocol APIs
- **Docker** - Containerization and deployment
- **Maven** - Dependency management

### 📋 Features

#### Authentication
- ✅ User registration (`POST /api/v1/auth/sign-up`)
- ✅ JWT login (`POST /api/v1/auth/sign-in`)
- ✅ Protected endpoints (`GET /api/v1/users/protected`)

#### User Management
- ✅ User creation with complete data
- ✅ Multiple contact type support (EMAIL, PHONE)
- ✅ Gender management (FEMALE, MALE, OTHER)
- ✅ Secure password encryption

### 🛠️ Setup and Execution

#### Prerequisites
- Java 17 or higher
- Maven 3.8+
- Docker (optional)

#### Run in Development Mode
```bash
./mvnw compile quarkus:dev
```

The application will be available at `http://localhost:8080`

#### Run Tests
```bash
./mvnw test
```

#### Production Build
```bash
./mvnw package
```

#### Docker

**Run with JVM**
```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-auth-api-jvm .
docker run -p 8080:8080 quarkus-auth-api-jvm
```

**Run Native (GraalVM)**
```bash
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native -t quarkus-auth-api-native .
docker run -p 8080:8080 quarkus-auth-api-native
```

### 📚 API Documentation

#### Available Endpoints

**Authentication**

**POST** `/api/v1/auth/sign-up` - Create account
```json
{
  "name": "John Silva",
  "contact": "john@example.com",
  "contactType": "EMAIL",
  "password": "password123",
  "gender": "MALE"
}
```

**POST** `/api/v1/auth/sign-in` - Login
```json
{
  "contact": "john@example.com",
  "password": "password123"
}
```

**Users**

**GET** `/api/v1/users/protected` - Protected endpoint
- Requires: `Authorization: Bearer <jwt_token>`

#### Schemas

**CreateUserDto**
- `name` (required): User name
- `contact` (required): Email or phone
- `password` (required): Password
- `gender` (optional): FEMALE | MALE | OTHER
- `contactType` (optional): EMAIL | PHONE

**SignInRequest**
- `contact` (optional): Email or phone
- `password` (required): Password

#### HTTP Responses
- `200` - Success
- `400` - Invalid data
- `401` - Unauthorized
- `403` - Access denied

### 🔐 Security

- **JWT** authentication with RSA keys
- **bcrypt** encrypted passwords
- Strict input data validation
- Configured security headers

#### JWT Configuration
RSA keys are located at:
- `src/main/resources/privateKey.pem`
- `src/main/resources/publicKey.pem`

### 🔧 Configuration

Main settings in `application.properties`:

```properties
# Application base
quarkus.http.port=8080

# JWT
mp.jwt.verify.publickey.location=publicKey.pem
smallrye.jwt.sign.key.location=privateKey.pem

# Database
quarkus.datasource.db-kind=postgresql
quarkus.hibernate-orm.database.generation=update
```

### 🧪 Testing

The project includes automated tests:
- Unit tests for persistence
- Integration tests for endpoints
- Use case coverage

Run with report:
```bash
./mvnw test jacoco:report
```

### 📊 Performance

Quarkus offers:
- **Fast startup**: < 1s in JVM mode
- **Low memory consumption**: ~13MB RSS native
- **Native compilation**: GraalVM executables
- **Live reload**: Agile development

### 🤝 Contributing

1. Fork the project
2. Create a branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

---

### 📜 License

This project is licensed under the [MIT License](LICENSE).

### 👥 Authors

- Your development team

---

**Quarkus Auth API** - Supersonic Subatomic Java Authentication 🚀