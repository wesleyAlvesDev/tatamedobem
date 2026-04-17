# Tatame do Bem

Sistema de gerenciamento de projetos sociais de Jiu-Jitsu com backend em Spring Boot + PostgreSQL + Swagger e frontend em Angular 17.

## Backend

- API REST em `Spring Boot 3`
- Banco de dados `PostgreSQL`
- Documentação OpenAPI em `http://localhost:8080/swagger-ui.html`
- Dados iniciais automáticos para demonstração

### Principais endpoints

- `GET/POST /api/students`
- `GET/POST /api/attendance`
- `GET /api/attendance/alerts`
- `GET /api/attendance/monthly-report?month=2026-04`
- `GET/POST /api/belt-progress`
- `GET/POST /api/inventory`
- `GET/POST /api/material-assignments`
- `GET/POST /api/health-records`
- `GET/POST /api/achievements`
- `GET /api/dashboard/summary`
- `GET /api/dashboard/sponsor-report`

### Subir o PostgreSQL

```bash
docker compose up -d
```

### Rodar a API

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Se quiser alterar a conexão:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### Autenticação

- Login: `POST /api/auth/login`
- Cadastro autenticado de usuario: `POST /api/auth/register`
- Os demais endpoints exigem token JWT no header `Authorization: Bearer <token>`
- No Swagger, use o botão `Authorize`

Usuário inicial carregado automaticamente:

- CPF: `11122233344`
- Senha: `123456`

## Frontend

O frontend Angular 17 está em [`frontend`](./frontend).

### Rodar o frontend

```bash
cd frontend
npm install
npm start
```

O Angular usa proxy para `/api` apontando para `http://localhost:8080`.

## Testes

```bash
./mvnw test
```

Os testes usam H2 apenas no ambiente de teste para não depender do PostgreSQL externo.
