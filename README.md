# User Management API

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

## 🔧 Configuração

### Variáveis de Ambiente

```properties
# Configurações da aplicação
quarkus.http.port=8080
quarkus.log.level=INFO

# JWT Configuration
jwt.private.key=privateKey.pem
jwt.public.key=publicKey.pem

# Database (configurar conforme necessário)
quarkus.datasource.url=jdbc:postgresql://localhost:5432/userdb
quarkus.datasource.username=user
quarkus.datasource.password=password
```

## 📚 API Endpoints

### REST Endpoints

#### Autenticação
```http
POST /auth/signin
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "senha123"
}
```

#### Usuários
```http
# Criar usuário
POST /users
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "senha123",
  "gender": "MALE"
}

# Buscar usuário
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

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Executar testes com coverage
./mvnw test jacoco:report
```

## 📁 Estrutura de Diretórios

```
src/main/java/
├── features/
│   ├── auth/           # Módulo de autenticação
│   ├── user/           # Módulo de usuários
│   ├── contact/        # Módulo de contatos
│   └── password/       # Módulo de senhas
└── shared/             # Componentes compartilhados

# Cada feature segue a estrutura hexagonal:
feature/
├── domain/             # Regras de negócio
│   ├── models/         # Entidades do domínio
│   ├── useCase/        # Interfaces dos casos de uso
│   ├── repository/     # Interfaces de repositório
│   └── services/       # Interfaces de serviços
├── application/        # Implementação dos casos de uso
│   ├── useCase/        # Casos de uso concretos
│   └── services/       # Serviços de aplicação
├── adapters/           # Adaptadores
│   ├── in/             # Adaptadores de entrada
│   │   ├── http/       # Controllers REST
│   │   └── graphql/    # Resources GraphQL
│   └── out/            # Adaptadores de saída
│       ├── entities/   # Entidades de persistência
│       ├── mappers/    # Mapeadores
│       └── persistence/# Repositórios concretos
└── infrastructure/     # Configurações técnicas
```

## 🛡️ Segurança

- **JWT**: Tokens com assinatura RSA
- **Validação**: Validadores personalizados para entrada
- **Exceções**: Tratamento centralizado de erros
- **CORS**: Configuração para ambientes de produção

## 🚀 Deploy

### Configuração de Produção

1. **Configurar variáveis de ambiente**
2. **Gerar chaves JWT específicas**
3. **Configurar banco de dados**
4. **Configurar logs e monitoramento**

### Performance

- **Native Build**: Tempo de inicialização < 50ms
- **Memory Footprint**: < 100MB em modo nativo
- **Hot Reload**: Desenvolvimento ágil com Quarkus Dev Mode

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 📞 Suporte

Para dúvidas e suporte:
- Abra uma issue no GitHub
- Entre em contato com a equipe de desenvolvimento

---

**Desenvolvido com ❤️ usando Arquitetura Hexagonal e Quarkus**