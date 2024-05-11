# JWT Validation

Uma API para validação de um Json Web Token

> Esta API REST fornece um método para validar um token JWT (JSON Web Token). O método /token-validation recebe um
> token como payload, descriptografa e valida de acordo com a secret e os critérios definidos para as claims Name, Role e Seed.

## Requisitos
- Deve ser um JWT válido
- O payload deve conter somente as claims: Name, Role e Seed
- A claim Name não deve conter caracteres numéricos 
- A claim Name não pode ser maior que 256 caracteres
- A claim Role deve ser um dos valores: Admin, Member ou External
- A claim Seed deve ser um número primo

## Tecnologias utilizadas
- Java
- Lombok
- Spring Boot
- Spring Security
- JWT (Json Web Token)
- JUnit e Mockito

---

## Procedimento para instalação e execução da API

### Pré-requisitos:

- JDK 17
- Opcional: alguma IDE

### Baixar o projeto:

- Clonar o projeto ou baixar o ZIP pelo GitHub.

### 1ª Opção: Rodar pela IDE

- Abrir o projeto em alguma IDE de sua preferência (Intellij, Eclipse ou Visual Studio Code)
- Abrir o caminho: src/main/java/br/com/jwt/validation
- Clicar com o botão direito do mouse em cima da classe JwtValidationApplication
- Clicar em "Run JwtValidationApplication.main()"
- A aplicação vai começar a rodar, e você poderá acompanhar os logs pelo terminal da IDE

### 2ª Opção: Rodar o .jar pelo terminal

- Abrir o terminal do seu computador
- Ir até o caminho do projeto e entrar na pasta "target" do projeto (token-validation)    
- `cd token-validation/target`
- Rodar o comando: `java -jar jwt-validation-0.0.1-SNAPSHOT.jar`
- A aplicação vai começar a rodar, e você poderá acompanhar os logs pelo terminal do seu computador

---

### Como Testar: Testando pelo Insomnia ou Postman

- Com o projeto rodando na sua máquina, você pode testar os endpoints pelo Insomnia ou pelo Postman
- Crie uma nova requisição
- Coloque o método POST e o endpoint `http://localhost:8080/validate-token`
- No corpo da requisição coloque o [exemplo](#exemplo-de-corpo-de-requisição) que está citado mais abaixo na documentação, podendo alterar o token para os [exemplos](#exemplos-de-jwt-para-validar) de JWTs citados posteriormente
- Agora você consegue testar a requisição

### Importando a collection do postman

- Você também pode importar a collection do postman que está na pasta base do projeto
- Abra o postman e do lado de "My Workspace", clique em "Import"
- Selecione o arquivo: [authentication.postman_collection.json](authentication.postman_collection.json)
- Agora a coleção já aparece no seu postman, com os endpoints prontos para testar

---

## Endpoint

- POST `/validate-token`

### Campos da requisição

| campo | tipo   | obrigatório | descrição          |
|-------|--------|:-----------:|--------------------|
| token | String |     sim     | JWT a ser validado |

### Exemplo de corpo de requisição

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.8qBykiT3AAOZqS0ov2wYmuIMJZwDiEGiFiJ_Pvfl6-Y"
}
```

### Exemplos de respostas

- Status: 200 OK
```json
{
    "isTokenValid": true,
    "errorDescription": null
}
```

- Status: 400 Bad Request
```json
{
    "isTokenValid": false,
    "errorDescription": "Seed is not a prime number"
}
```

---

## Exemplos de JWT para validar

### Exemplo 1:

Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.8qBykiT3AAOZqS0ov2wYmuIMJZwDiEGiFiJ_Pvfl6-Y
```

Saída:
```
válido
```

Payload:
```json
{
  "Role": "Admin",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
```

### Exemplo 2:

Entrada:
```
eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
```

Saída:
```
inválido
```

Justificativa: Esse JWT é inválido

### Exemplo 3:

Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.pWKqHSiSZau7ORXrX70vgOkDiXptsyd6Lahxd9esW5E
```

Saída:
```
inválido
```

Payload:
```json
{
  "Role": "Member",
  "Org": "BR",
  "Seed": "14627",
  "Name": "Valdir Aranha"
}
```

Justificativa: Esse JWT é inválido pois possui uma Claim a mais.

### Exemplo 4

Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiMTYxNiIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.jdtm6AzXxpX_J2jsPqGPqR2hNQw3h8tkbqW1vkOkZdU
```

Saída:
```
inválido
```

Payload:
```json
{
  "Role": "Admin",
  "Seed": "1616",
  "Name": "Toninho Araujo"
}
```

Justificativa: Esse JWT é inválido pois a claim Seed não é um número primo

### Exemplo 5

Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQ2xpZW50IiwiU2VlZCI6Ijc4NDEiLCJOYW1lIjoiVG9uaW5obyBBcmF1am8ifQ.SMpV_So4WKWYmfSKMkwleqqyE2GKDRvfSHVh1UP-QPE
```

Saída:
```
inválido
```

Payload:
```json
{
  "Role": "Client",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
```

Justificativa: Esse JWT é inválido pois a claim Role não é "Admin", "Member" ou "External"
