# 💍 TrueUnion – Wedding Management API

> **Enterprise-level wedding management system built with Java 17 and Spring Boot**, designed to streamline event planning, financial control, and guest management — all within a clean, scalable, and RESTful architecture.

---

## 📘 Sobre o Projeto

O **TrueUnion** é uma plataforma backend desenvolvida para gerenciar casamentos de forma completa — desde o cadastro de usuários e convidados até o controle financeiro e cronograma do evento.

Embora tenha nascido como um **projeto acadêmico**, o sistema foi **totalmente refatorado com arquitetura profissional**, aplicando práticas de engenharia de software modernas e integração avançada com **SQL Server**, **triggers**, **views** e **schedulers automáticos**.

---

## ⚙️ Stack Tecnológica

| Categoria | Tecnologias |
|------------|--------------|
| **Linguagem** | ☕ Java 17 |
| **Framework** | 🧩 Spring Boot |
| **ORM / Persistência** | 🗃️ Hibernate / Spring Data JPA |
| **Banco de Dados** | 🧠 SQL Server – Triggers, Views e Jobs |
| **Agendamentos** | ⏱️ Spring Scheduler |
| **Controle de Versionamento** | 🧰 Git / GitHub |
| **Arquitetura** | MVC + Camadas (Controller → Service → Repository → Entity) |

---

## 🧩 Principais Módulos

### 👤 Módulo de Usuários
- Cadastro, autenticação e controle de perfis (`NOIVO`, `NOIVA`, `CONVIDADO`)
- Hash automático de dados sensíveis (senha, e-mail, CPF) via **trigger SQL**
- Reativação e inativação de contas com histórico mantido
- Lógica de upgrade de perfil (de convidado para noivo/noiva)

### 💒 Módulo de Eventos
- Criação e gerenciamento completo de eventos vinculados ao usuário logado  
- Controle de orçamento, local, descrição e período  
- Cancelamento validado por regras de negócio  
- Atualização automática de eventos concluídos via **scheduler em Java**

### 💌 Convites (RSVP)
- Envio de convites com base no e-mail dos convidados  
- Status dinâmico: *Pendente*, *Confirmado*, *Recusado*  
- Resposta direta via endpoint REST (`/eventos/{id}/convites`)

### 💰 Gestão Financeira
- Registro e acompanhamento de **despesas e pagamentos**
- Integração com tabela de **categorias de despesa**
- Relatórios orçamentários automatizados (orçado x gasto)
- **Views SQL** para otimizar consultas e relatórios

### ✅ Tarefas e Cronograma
- Criação de tarefas vinculadas ao evento  
- Identificação automática de tarefas atrasadas via **Spring Scheduler**  
- Organização por data e status  

---

## 🏗️ Arquitetura do Sistema

O TrueUnion segue uma **arquitetura em camadas**, baseada em boas práticas e separação de responsabilidades:

**Controller → Service → Repository → Entity → Database**

**Controller** – expõe os endpoints RESTful  
**Service** – centraliza as regras de negócio e transações  
**Repository** – abstrai a persistência com JPA/Hibernate  
**Database** – implementa triggers, views e automações SQL Server

Aplicando ainda os princípios de **Clean Architecture** e **SOLID**, o sistema é modular, escalável e fácil de manter.

---

## 🧠 Banco de Dados e Automação

| Tipo | Implementação |
|------|----------------|
| **Trigger** | `SEQUESTAR_SENHAS_DE_INATIVOS` – Armazena e hasheia senhas de contas inativas |
| **Trigger** | `T_DISPARA_ATIVACAO` – Restaura senhas ao reativar contas |
| **View** | `W_USUARIOS_INATIVOS` – Relatório de contas inativas |
| **Job (Java)** | `settarEventosConcluidos()` – Atualiza eventos finalizados automaticamente |

---

## 🚀 Evoluções e Melhorias

- Migração e otimização total para **SQL Server**
- Triggers aprimoradas com `HASHBYTES` e controle de reativação
- Substituição de eventos SQL por **Schedulers Java**
- Padronização RESTful em todos os endpoints (`/api-trueunion/...`)
- Refatoração completa de controllers e services
- Melhoria de tratamento de erros e responses (`ResponseEntity` e `ResponseStatusException`)

---

## 📊 Status do Projeto

🧩 **Versão atual:** Back-end completo e funcional  
🔧 **Próximas etapas:**  
- Implementar Spring Security (autenticação e roles)  
- Documentação de API com Swagger  
- Integração com o front-end (em desenvolvimento)  
- Deploy em ambiente Cloud (Render / AWS)

---

## 👨‍💻 Autor

**Gabriel Lima de Oliveira**  
Backend Developer | Java | Spring Boot | SQL Server  

📍 Blumenau - SC  
🔗 [LinkedIn](https://www.linkedin.com/in/gabriel-lima-892682213)  
💻 [GitHub](https://github.com/gabriellima-oliveira)

---

> 💬 *"TrueUnion começou como um projeto acadêmico, mas hoje é um laboratório real de engenharia de software, aplicando boas práticas, arquitetura limpa e automações corporativas."*
