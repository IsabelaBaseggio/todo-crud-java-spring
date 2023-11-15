# ToDo CRUD Java Spring Project :sparkles:

Este é um projeto CRUD (Create, Read, Update, Delete) desenvolvido em Java com o framework Spring para gerenciar tarefas (To-Do).

## Descrição :information_source:

O projeto consiste em um serviço RESTful para gerenciamento de tarefas (tasks), permitindo as operações básicas de um CRUD.

## Configuração do Projeto :gear:

### Pré-requisitos

- Java JDK 17
- Gradle
- IDE (recomendado: IntelliJ IDEA)

### Instalação :hammer_and_wrench:

1. Clone o repositório para sua máquina local.
2. Abra o projeto na sua IDE.
3. Certifique-se de ter as dependências necessárias instaladas (Spring Boot Starter Web, Spring Boot Starter Validation e Spring Boot Starter Test).

### Execução :arrow_forward:

1. No terminal, vá até o diretório raiz do projeto.
2. Execute o comando `./gradlew bootRun` para iniciar o servidor Spring Boot.

## Estrutura do Projeto :file_folder:

O projeto está estruturado da seguinte maneira:

- **`src/main/java`**: Contém o código-fonte da aplicação.
  - **`com.principal.todoservice`**: Pacote principal.
    - `ToDoServiceApplication.java`: Classe principal da aplicação Spring Boot.
    - `controllers`: Pacote com controladores REST para gerenciar as requisições.
    - `models`: Pacote com as classes de modelos das tarefas.
    - `services`: Pacote com os serviços que realizam a lógica de negócio.

- **`src/test/java`**: Contém os testes unitários.
  - `com.principal.todoservice.services`: Pacote com os testes para os serviços.

## Funcionalidades :heavy_check_mark:

- Listar todas as tarefas.
- Adicionar uma nova tarefa.
- Editar uma tarefa existente.
- Excluir uma tarefa.

## Uso :rocket:

### Listar todas as tarefas

Faça uma requisição GET para `/tasks` para listar todas as tarefas.

### Adicionar uma nova tarefa

Faça uma requisição POST para `/tasks/add` com um corpo JSON contendo a descrição e a data de vencimento da tarefa.

Exemplo de corpo da requisição:
```json
{
  "description": "Descrição da tarefa",
  "dueDate": "yyyy-MM-dd"
}
```

### Editar uma tarefa

Faça uma requisição PUT para /tasks/edit/{taskId} com um corpo JSON contendo os campos a serem atualizados.

Exemplo de corpo da requisição:
```json
{
  "description": "Nova descrição",
  "dueDate": "yyyy-MM-dd",
  "isCompleted": "true"
}
```

### Excluir uma tarefa

Faça uma requisição DELETE para /tasks/delete/{taskId} para excluir uma tarefa pelo ID.

## Teste :bar_chart:

O projeto inclui testes unitários para garantir o funcionamento correto das operações CRUD.

## Autor :woman_technologist:

[Isabela Baséggio](https://github.com/IsabelaBaseggio) 
