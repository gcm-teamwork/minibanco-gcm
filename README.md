# MiniBanco

Este projeto tem como objetivo simular o funcionamento básico de uma conta bancária digital. Ele permite que o usuário realize operações essenciais como **criar uma conta**, **consultar saldo**, **realizar crédito**, **realizar débito** e **transferências**.

## Integrantes
- Gabriele Queiroz Rêgo, github `@gabrielerego123`
- Ana Maria Gomes Holanda, github `@anamgholandadev`

## Linguagem de Programação
No projeto, é utilizado a linguagem de programação Java com Spring Boot.

### Requisitos
- Java 19+.
- Docker
- Postman ou Insomnia

### Executar projeto com Docker

Rode os comandos abaixo:

```
docker pull gabrielerego/minibanco-gcm:1.0 && docker run -p 8080:8080 gabrielerego/minibanco-gcm:1.0
```

[Clique aqui para acessar a imagem do projeto no repositório docker hub](https://hub.docker.com/r/gabrielerego/minibanco-gcm)

### Como chamar endpoints

- Retorna todas as contas
```
curl --request GET \
  --url http://localhost:8080/banco/conta \
  --header 'User-Agent: insomnia/9.3.0'
```
- Criação de conta
```
curl --request POST \
  --url http://localhost:8080/banco/conta \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.0' \
  --data '{
  "number": "123",
  "type": "SAVINGS",
  "initialBalance": 100.0
}'
```
- Transferência
```
curl --request PUT \
  --url http://localhost:8080/banco/conta/transferencia \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.0' \
  --data '{
  "from": "123",
  "to": "1234",
  "amount": 40.0
}'
```
- Crédito
```
curl --request PUT \
  --url http://localhost:8080/banco/conta/123/credito \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.0' \
  --data '{
  "amount": 50.0
}'
```
- Débito
```
curl --request PUT \
  --url http://localhost:8080/banco/conta/123/debito \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.0' \
  --data '
{
  "amount": 10.0
}'
```
- Saldo da conta
```
curl --location 'http://localhost:8080/banco/conta/123/saldo' \
--header 'User-Agent: insomnia/9.3.0'
```
- Rendimento
```
curl --request PUT \
  --url http://localhost:8080/banco/conta/rendimento \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.0' \
  --data '{
  "rate": 10.5
}'
```