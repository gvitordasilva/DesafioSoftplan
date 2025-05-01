# DesafioSoftplan
Criação de um CRUD de gerenciamento de Produtos

# API de Gerenciamento de Produtos

Uma API RESTful para gerenciar produtos e categorias, construída com Spring Boot e PostgreSQL.

## Funcionalidades

- **Gestão de Categorias**: Operações CRUD para categorias de produtos.
- **Gestão de Produtos**: Operações CRUD para produtos com associação a categorias.
- **Validação**: Verificação de dados em requisições (campos obrigatórios, restrições de preço, etc.).
- **Tratamento de Exceções**: Exceções personalizadas para entidades não encontradas.
- **Padrão DTO**: Separação entre objetos de requisição/resposta e modelos de domínio.
- **Suporte a Docker**: Configuração do PostgreSQL via Docker Compose.
- **Testes Unitários**: Lógica principal testada com JUnit e Mockito.

## Tecnologias

- **Java 17**
- **Spring Boot 3**: Framework para construção da API REST
- **PostgreSQL**: Banco de dados relacional
- **Hibernate/JPA**: Camada ORM
- **Lombok**: Redução de código repetitivo
- **MapStruct**: Mapeamento de objetos
- **Docker**: Containerização do banco de dados
- **Maven**: Gerenciamento de dependências

## Pré-requisitos

- JDK Java 17
- Docker & Docker Compose
- Maven

## Instalação

1. **Iniciar PostgreSQL**:
   ```bash
   docker-compose up -d

## URL Base

http://localhost:8080/api/v1

## Endpoints de Categorias

Método	Caminho	Descrição
POST	/categories	- Criar nova categoria
GET	/categories	- Listar todas categorias
GET	/categories/{id} - Buscar categoria por ID
PUT	/categories/{id} - Atualizar categoria
DELETE	/categories/{id} - Excluir categoria

## Endpoints de Produtos

Método	Caminho	Descrição
POST	/products	- Criar novo produto
GET	/products - Listar todos produtos
GET	/products/{id} - Buscar produto por ID
PUT	/products/{id} - Atualizar produto
DELETE	/products/{id} - Excluir produto

## Exemplos de requisições

**Criar Categoria**

POST /api/v1/categories
Content-Type: application/json

```{
  "name": "Eletrônicos",
  "description": "Dispositivos e gadgets"
}

**Response**
```{
  "id": 1,
  "name": "Eletrônicos",
  "description": "Dispositivos e gadgets",
  "createdAt": "2023-10-05T10:00:00",
  "updatedAt": "2023-10-05T10:00:00"
}

**Criar Produto**

POST /api/v1/products
Content-Type: application/json

```{
  "name": "Fone Bluetooth",
  "description": "Fone com cancelamento de ruído",
  "price": 199.99,
  "categoryId": 1
}

**Response**
```{
  "id": 1,
  "name": "Fone Bluetooth",
  "description": "Fone com cancelamento de ruído",
  "price": 199.99,
  "createdAt": "2023-10-05T10:05:00",
  "updatedAt": "2023-10-05T10:05:00",
  "category": {
    "id": 1,
    "name": "Eletrônicos",
    "description": "Dispositivos e gadgets",
    "createdAt": "2023-10-05T10:00:00",
    "updatedAt": "2023-10-05T10:00:00"
  }
}

## Executando Testes

```mvn test
