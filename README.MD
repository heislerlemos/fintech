
---

# 🚀 KwanzaZap MVP – Assistente Financeiro Digital

>
> **Visão:** Tornar-se o principal assistente financeiro digital em Angola, oferecendo uma experiência de gestão de dinheiro e pagamentos tão simples quanto enviar uma mensagem no WhatsApp.
>
>

---

## 📋 Sobre o Projeto

O **KwanzaZap** é um Produto Mínimo Viável (MVP) desenhado para validar a aceitação de operações financeiras através do WhatsApp no mercado angolano. O projeto foca-se na simplicidade e acessibilidade, utilizando uma interface conversacional para democratizar o acesso a serviços financeiros básicos.

### 🎯 Objetivos Principais

*
**Validação de Mercado:** Confirmar a disposição dos utilizadores em realizar transferências P2P e consultas via WhatsApp.


*
**Modelo Operacional:** Testar a viabilidade de um sistema de "Agentes/Correspondentes" para depósitos e levantamentos.


*
**Base Técnica Sólida:** Desenvolver uma arquitetura segura e escalável para futuras integrações com gateways oficiais.



---

## ✨ Funcionalidades (MVP)

O sistema foi desenhado com os seguintes Requisitos Funcionais (RF):

*
**🔐 RF01: Autenticação Segura** – Registo por número de telefone e proteção de operações críticas via PIN.


*
**💰 RF02: Gestão de Carteira** – Consulta de saldo em tempo real diretamente no chat.


*
**📥 RF03: Depósitos** – Geração de referências para depósito com confirmação manual por um Agente.


*
**💸 RF04: Transferências P2P** – Envio de fundos entre utilizadores com validação de saldo e segurança transacional.


*
**🏦 RF05: Saques** – Pedidos de levantamento para contas bancárias processados por Agentes.


*
**📱 RF06: Notificações** – Confirmações instantâneas via WhatsApp para todas as transações.



---

## 🛠️ Stack Técnica e Arquitetura

A aplicação segue padrões modernos de desenvolvimento para garantir a evolução do ecossistema:

*
**Backend:** Arquitetura de Monólito Modular utilizando **NestJS**, facilitando a futura migração para microsserviços.


*
**Frontend (Admin):** Painel administrativo para gestão de transações e agentes.


*
**Base de Dados:** PostgreSQL (Gerido).


*
**Comunicação:** API do WhatsApp Business.


