
![GitHub License](https://img.shields.io/github/license/Vitoriomic/furia)

# FURIA Fan Portal

Portal interativo para torcedores da FURIA votarem nos jogos, acompanharem estatísticas e se conectarem com a torcida por meio de um dashboard visual. O projeto utiliza Angular 19 no front-end e Spring Boot no back-end.

---

## 📌 Funcionalidades

### 👥 Autenticação
- Login e cadastro com e-mail e senha.
- Tokens JWT com expiração de 24h.
- Proteção de rotas para permitir apenas usuários logados em certas funcionalidades.

### 🗳️ Sistema de Votação
- Votação em resultados dos próximos jogos: Vitória da FURIA, Empate ou Derrota.
- Modal de votação acessada pela lista de jogos.
- Controle de voto único por usuário, com base no token.

### 📅 Listagem de Jogos
- Página principal pública com lista de jogos da FURIA.
- Exibição de informações: adversário, data/hora e campeonato.
- Modal mostra percentual de votos por resultado.

### 👤 Página de Perfil
- Página protegida com dados do usuário logado.
- Edição de nome, data de nascimento, região, redes sociais e times favoritos.
- Não é possível alterar a senha por essa interface (por segurança).

### 📊 Dashboard Interativo
- Ranking de estados com mais torcedores.
- Mapa do Brasil interativo com destaque por estado.
- Times favoritos mais escolhidos.
- Lista animada com redes sociais dos usuários (estilo créditos de filme).

---

## 🧱 Estrutura do Projeto

### 📦 Backend (Spring Boot)
- **Endpoints principais:**
  - `POST /auth/login` – autenticação e geração de token.
  - `POST /usuario` – cadastro de novo usuário.
  - `GET /usuario/me` – informações sobre o usuario logado.
  - `PUT /usuario/me` – atualiza as informações sobre o usuario logado.
  - `GET /jogos` – lista de jogos da FURIA.
  - `GET /jogos/?furiaId={id}` – detalhes sobre o jogo.
  - `GET /votos/{id}/resultado` – percentual de votos por jogo.
  - `POST /votos/{id}?opcao={FURIA|EMPATE|ADVERSARIO}` – registrar voto.
- **Segurança:**
  - Spring Security + JWT
  - Filtros e interceptadores para proteger rotas.
- **Banco de Dados:**
  - Usuários, votos, jogos, times, estados, etc.
  - Relacionamentos modelados com JPA.

### 💻 Frontend (Angular 19)
- **Pages:**
  - `/login` – tela de login.
  - `/registro` – tela de cadastro de usuário.
  - `/games` – página principal com lista de jogos e modais de votação.
  - `/perfil` – perfil do usuário autenticado.
  - `/dashboard` – visualizações e rankings.
- **Componentes standalone** para melhor modularidade.
- **Rotas protegidas** com guardas que verificam o JWT.
- **Serviços de autenticação** com armazenamento de token em `localStorage`.

---

## 🚀 Como Executar

### ✅ Pré-requisitos
- Node.js 18+
- Angular CLI
- Java 21
- Maven

### 📁 Backend

```bash
cd backend
./mvnw spring-boot:run
```

### 🌐 Frontend

```bash
cd frontend
npm install
ng serve
```

---

## 🔐 Segurança

- JWT armazenado apenas em `localStorage`.
- Token é adicionado automaticamente no `Authorization` nas rotas protegidas.
- Tokens expiram em 24h. Após isso, o usuário precisa logar novamente.

---

## 🛠 Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- PostgreSQL ou MySQL

### Frontend
- Angular 19
- Standalone Components
- Angular Router
- Angular Forms
- TailwindCSS (opcional)
- Angular Animations

---

## 👨‍💻 Contribuidores

- **Vitório Micheletto** – Desenvolvedor Full Stack

---
