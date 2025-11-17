# 💍 TrueUnion – Wedding Management API (B2C Version)

Plataforma backend completa para gestão de casamentos, desenvolvida em **Java 17 + Spring Boot**, estruturada com padrões profissionais de engenharia de software, arquitetura MVC e integrações avançadas com **SQL Server**.

O TrueUnion B2C foi criado para atender casais que desejam organizar o próprio casamento — controlando finanças, convidados, tarefas e eventos de forma intuitiva e segura.

---

## 📘 Sobre o Projeto

O TrueUnion B2C nasceu inicialmente como um projeto acadêmico, mas evoluiu para um **projeto enterprise**, com foco em:

- Arquitetura limpa  
- Separação de responsabilidades  
- Boas práticas modernas  
- Automação de regras de negócio  
- Uso profissional do SQL Server com triggers e views  
- MVC com fluxo REST totalmente padronizado  

---

## ⚙️ Stack Tecnológica

| Categoria | Tecnologias |
|----------|-------------|
| **Linguagem** | ☕ Java 17 |
| **Framework** | 🧩 Spring Boot |
| **ORM / Persistência** | Spring Data JPA / Hibernate |
| **Banco de Dados** | SQL Server (Triggers, Views) |
| **Agendamentos** | Spring Scheduler |
| **Arquitetura** | MVC + Camadas (Controller → Service → Repository → Entity) |
| **Versionamento** | Git & GitHub |

---

## 🧩 Principais Módulos

### 👤 1. **Usuários**
- Cadastro e autenticação  
- Perfis: **NOIVO / NOIVA / CONVIDADO**  
- Trigger automática para segurança:  
  - Hash de senhas ao inativar  
  - Restauração ao reativar  
- Histórico preservado  

---

### 💒 2. **Eventos**
- Criação e gerenciamento completo dos eventos  
- Controle de orçamento  
- Validações de data  
- Cancelamento com regras de negócio  
- Atualização automática de eventos concluídos via **Scheduler**  

---

### 💌 3. **Convites (RSVP)**
- Envio de convites  
- Status: **Pendente | Confirmado | Recusado**  
- Resposta via endpoint REST  
- Prevenção de duplicidade  

---

### 💰 4. **Gestão Financeira**
- Registro de despesas  
- Pagamentos com parcelamento  
- Baixa automática de parcelas  
- Relatórios financeiros  
- Views SQL para performance  

---

### 📝 5. **Tarefas**
- Criação de tarefas por evento  
- Identificação automática de tarefas atrasadas  
- Organização por datas e status  

---

## 🏗️ Arquitetura do Sistema

O projeto segue uma arquitetura em camadas:

Controller → Service → Repository → Entity → Database

yaml
Copiar código

### Destaques técnicos:
- Controllers limpos e RESTful  
- DTOs para requests e responses  
- Services centralizando toda regra de negócio  
- Entities bem mapeadas (ORM)  
- SQL Server com triggers, views e jobs  

---

## 🧠 Banco de Dados – Automação e Segurança

| Tipo | Implementação |
|------|--------------|
| **Trigger** | `TRG_HASH_INATIVOS` – Hasheia dados ao inativar o usuário |
| **Trigger** | `TRG_RESTORE_ATIVOS` – Restaura dados ao reativar |
| **View** | `VW_USUARIOS_INATIVOS` – Relatórios de contas inativas |
| **Scheduler (Java)** | Atualiza eventos concluídos diariamente |

---

## 🚀 Evoluções Implementadas

- Refatoração completa com arquitetura profissional  
- Padronização total dos endpoints REST  
- Implementação robusta de DTOs  
- Melhoria no fluxo financeiro (parcelamento + baixas automáticas)  
- Views SQL otimizadas  
- Controllers totalmente limpos  
- Tratamento de erros com `ResponseStatusException`  
- Schedulers substituindo jobs SQL  

---

## 📊 Status Atual

✔ **Back-end B2C finalizado e funcional**

### Próximas Etapas

- Implementar **Spring Security (JWT + Roles)**  
- Documentação com Swagger  
- Integração com front-end  
- Deploy em ambiente Cloud  
- Iniciar projeto **TrueUnion B2B**  

---

## 👨‍💻 Autor

**Gabriel Lima de Oliveira**  
Backend Developer – Java | Spring Boot | SQL Server  

📍 Blumenau – SC  
🔗 LinkedIn: *seu link aqui*  
💻 GitHub: *seu link aqui*  

---

## 💬 Mensagem Final

> “O TrueUnion começou como um projeto acadêmico, mas hoje é um laboratório real de engenharia de software, aplicando práticas corporativas, arquitetura limpa e automações inteligentes.”
