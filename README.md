# Cash Flow Control

O intuito da aplicação é atender ao desafio do cliente com um pequeno projeto de um serviço que faça o controle de lançamentos e possa gerar um consolidado desses lançamentos.

A ideia inicial seria criar um proxy reverso no ningx para tornar a url mais amigável, todavia, problemas de liberação do hardware inviabilizaram a configuração mais assertiva para teste, mas, segue configurada na stack do docker compose.

Falando em docker compose, a stack é composta de 3 serviços dentro da rede docker, onde apenas o endpoint liberado pelo proxy expõe, api e banco de dados ficam internalizados na rede do docker.

Na atual conjuntura, segue o endereço da documentação para execução direto no ambiente de dev:
http://localhost:8080/swagger-ui/index.html
## Diagrama da estrutura proposta docker compose

![Arquitetura_Macro](images/MacroArq.jpg)


## Serviços

- Movimentação (movimento-controller):
  Funcionalidades de debitar e creditar saldo ao cliente;
- Auth (auth-controller): Gerar token JWT para execução dos Serviços;
- Cliente (cliente-controller): Insere cliente

## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

`-Dspring.profiles.active=dev`


## Deploy

É necessário previamente rodar o docker compose para subir as dependências externas do projeto, logo na pasta raiz do projeto onde está o arquivo docker-compose.yaml executará o comando:
````bash 
 docker-compose up 
```` 
Assim que realizar o do os downloads das imagens, extrair e subir os containers dentro da rede.

Realize o import do projeto em sua IDE de preferência, após êxito no import, realize o processo no maven de clean install, algumas IDEs tem essa funcionalidade gráfica, mas por exemplo rodando no linux, basta entrar no diretório (pasta) onde está o arquivo pom.xml e executar o comando abaixo

```bash 
 mvn clean install 
``` 
Uma vez que o projeto está em execução, há duas maneiras de realizar os testes de consumo da API:
<br /> 1: exportar o json gerado na url do swagger http://localhost:8080/v3/api-docs e consumi-los no Insomnia ou Postman
<br /> 2: Realizar as requisições através da interface do swagger:
<br /> na segunda opção há um usuário admin default configurado, admin:admin, no serviço de autenticação habilite a execução do serviço clicando no botão try it out

![swagger Screenshot](images/Login1.jpg)

preencha o campo json dentro do campo request body

````json 
{ 
 "login": "admin", 
 "senha": "admin" 
} 
```` 
E clique em execute

Dentro do campo response body retornará um token:

````json 
{ 
 "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6IkFQSSBjYXNoZmxvdyIsImV4cCI6MTY4MzczNjQ1Nn0.tx_RFrf-RSjlydqw7SydHVMfxKILmKgdJCkwCFf-RmY" 
} 
```` 
Logo acima a um botão central:
![auth Screenshot](images/Login2.jpg)

Preencha com o valor do token gerado:
![auth Screenshot](images/Login3.jpg)

Observe que o acesso está liberado temporariamente:
![auth Screenshot](images/Login4.jpg)
![auth Screenshot](images/Login5.jpg)






## Stack utilizada

**Back-end:** Java 17, Maven 3.9.1, Docker 23.0.5, Docker-compose 1.29.2, Intelijj 2023.1.1 CE

## Observações

Essa aplicação está utilizando Migration para seed em tabelas.

## Expectativas

A aplicação não possui a implementação de verbo DELETE, PATH e PUT, uma vez que o GET paginado e o POST foi implementado os demais verbos são de simples implementação, ao invés disso preferi dar destaque a componentes de maior relevância, como arquitetura, segurança, documentação, estruturação distribuída (seja ela cloud, docker ou VM).  
A estruturação dentro do docker tem como intuito a inclusão de componentes voltados para observabilidade como uso do Prometheus e Grafana.  
Na parte de o teste unitário, foi gerado um único teste na intenção de demonstrar do uso do conceito de BDD e o padrão Gerkin.


## Autores

- [@dsbrum](https://www.github.com/dsbrum) 
 
 

 
