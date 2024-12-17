# CREDITAS-BACKEND (Desafio)
O projeto a seguir trata-se de um processo seletivo para Engenharia Backend Pleno na empresa Creditas,
Empresa cujo ramo se centraliza em serviços de empréstimo e seguros.

Neste projeto optei por uma adaptação de clean architecture, abrindo possibilidade para futura implementação de novos
domínios, além de uma estrutura de código mais limpa e organizada.
Utilizei padrão de código limpo, em inglês, sempre prezando por nomes descritivos e claros, além de comentários para 
métodos com mais de uma responsabilidade.

# Rodando a aplicação

Execute os comandos abaixo para builder e executar sua imagem docker, se preferir, pode mudar a porta de inicialização.

```
docker image build -t cb .
docker run -d -p 8080:8080 --name creditas-backend cb
```

### Funcionalidades

## Endpoints

### Loan Simulation

- **POST /emprestimo**
    - Simula o empréstimo de um cliente.
    - Request Body:
      ```json
      {
        "customerName": "John Doe",
        "birthDate": "1980-01-01",
        "loanValue": 10000.0,
        "installments": 12,
        "customerEmail": "john.doe@example.com"
      }
      ```
    - Response:
      ```json
      {
        "quantidadeParcelas": "12",
        "valorParcelas": "850.00",
        "totalComJuros": "10200.00"
      }
      ```
    - `curl` Command:
      ```sh
      curl -X POST "http://localhost:8080/emprestimo" -H "Content-Type: application/json" -d '{
        "customerName": "John Doe",
        "birthDate": "1980-01-01",
        "loanValue": 10000.0,
        "installments": 12,
        "customerEmail": "john.doe@example.com"
      }'
      ```

### Batch Loan Simulation

- **POST /batch/simulate**
    - Simula o empréstimo de vários clientes em lote de forma assíncrona.
    - Request Body:
      ```json
      [
        {
          "customerName": "John Doe",
          "birthDate": "1980-01-01",
          "loanValue": 10000.0,
          "installments": 12,
          "customerEmail": "john.doe@example.com"
        },
        {
          "customerName": "Jane Doe",
          "birthDate": "1985-05-15",
          "loanValue": 15000.0,
          "installments": 24,
          "customerEmail": "jane.doe@example.com"
        }
      ]
      ```
    - Response:
      ```json
      "123e4567-e89b-12d3-a456-426614174000"
      ```
    - `curl` Command:
      ```sh
      curl -X POST "http://localhost:8080/batch/simulate" -H "Content-Type: application/json" -d '[
        {
          "customerName": "John Doe",
          "birthDate": "1980-01-01",
          "loanValue": 10000.0,
          "installments": 12,
          "customerEmail": "john.doe@example.com"
        },
        {
          "customerName": "Jane Doe",
          "birthDate": "1985-05-15",
          "loanValue": 15000.0,
          "installments": 24,
          "customerEmail": "jane.doe@example.com"
        }
      ]'
      ```

- **GET /batch/status/{batchId}**
    - Consulta o status de um lote de simulação de empréstimos.~~~~
    - Response:
      ```json
      {
        "status": "COMPLETED",
        "createdAt": "2023-10-01T12:00:00.000",
        "elapsedTime": "PT1H"
      }
      ```
    - `curl` Command:
      ```sh
      curl -X GET "http://localhost:8080/batch/status/123e4567-e89b-12d3-a456-426614174000"
      ```

