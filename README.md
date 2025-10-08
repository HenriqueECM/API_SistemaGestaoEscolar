# Sistema de Gestão Escolar (API)

Este projeto é uma API para um Sistema de Gestão Escolar, desenvolvida em Java utilizando JDBC, DTOs e SQL. O objetivo é fornecer uma estrutura completa para o gerenciamento de alunos, professores, cursos, turmas, aulas e notas, seguindo boas práticas de arquitetura em camadas (DTO, Repository, Service, Controller).

## Objetivo

A API permite o cadastro e gerenciamento dos principais componentes de uma escola, facilitando operações como matrícula de alunos em turmas, registro de aulas, atribuição de notas, e consultas detalhadas sobre o desempenho dos alunos e organização das turmas.

## Principais entidades e relacionamentos

As entidades principais do sistema são:

- **Aluno**: Representa os estudantes.
- **Professor**: Representa os professores e suas disciplinas.
- **Curso**: Representa os cursos ofertados.
- **Turma**: Agrupa alunos e professores em um curso.
- **Aula**: Representa os encontros de cada turma.
- **Nota**: Armazena as notas atribuídas aos alunos em cada aula.

Os relacionamentos entre as entidades são tratados com chaves estrangeiras e tabelas de associação (exemplo: `turma_aluno` para o relacionamento N:N entre alunos e turmas).

## Exemplo do modelo relacional

O projeto inclui um script SQL para criação das tabelas, com exemplos de inserção de dados. As principais tabelas são:

- `aluno`, `professor`, `curso`, `turma`, `turma_aluno`, `aula`, `nota`

A modelagem garante integridade referencial e validação de dados (por exemplo: email único, valor da nota entre 0 e 10).

## DTOs

Cada entidade possui DTOs de requisição e resposta, garantindo que os dados trafegados pela API estejam sempre estruturados e seguros. Exemplo:

- **AlunoDTO (Requisição)**: `nome`, `email`, `matricula`, `dataNascimento`
- **AlunoDTO (Resposta)**: `id`, `nome`, `email`, `matricula`, `dataNascimento`

## Endpoints REST

A API implementa endpoints REST para CRUD completo de todas as entidades. Exemplos:

- `GET /alunos` → Lista todos os alunos.
- `GET /alunos/{id}` → Busca um aluno específico.
- `POST /alunos` → Cria um novo aluno.
- `PUT /alunos/{id}` → Atualiza os dados de um aluno.
- `DELETE /alunos/{id}` → Remove um aluno.

Endpoints extras:
- `GET /alunos/{id}/notas` → Lista as notas de um aluno.
- `GET /turmas/{id}/alunos` → Lista os alunos de uma turma.
- `GET /cursos/{id}/turmas` → Lista as turmas de um curso.

## Arquitetura

O sistema segue uma arquitetura em camadas:

- **DTOs**: Objetos de transferência de dados.
- **Repositories**: Persistência e consultas via JDBC/PreparedStatement.
- **Services**: Regras de negócio e conversão de entidades para DTOs.
- **Controllers**: Endpoints REST, expõem apenas os DTOs.

## Validações e Consultas

- Validação de dados antes de inserção (exemplo: email único, nota entre 0 e 10).
- Consultas JOIN para preencher DTOs com informações completas (nomes, assuntos, etc).

## Testes

Os endpoints podem ser testados com POSTMAN ou outra ferramenta de API, garantindo que todas as requisições e respostas estejam conforme os DTOs definidos.

## Como executar

1. Configure o banco de dados MySQL e execute o script SQL de criação das tabelas.
2. Configure as credenciais de acesso ao banco no projeto.
3. Compile e rode o projeto.
4. Teste os endpoints via POSTMAN.

---
