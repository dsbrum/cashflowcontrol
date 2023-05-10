
# Cash Flow Control

O intuito da aplicação é atender ao desafio do cliente com um pequeno projeto de um serviço que faça o controle de lançamentos e possa gerar um consoliddado desses lançamentos.

A ideia inicial seria criar um proxy reverso no ningx para tornar a url mais amigável, todavia, problemas de liberação do hardware inviabilizaram a configuração mais acertiva para teste, mas, segue configurada na stack do docker compose.

Falando em docker compose, a stack é composta de 3 serviços dentro da rede docker, onde apenas o endpoint liberado pelo proxy expõe, api e banco de dados ficam internalizados na rede do docker.

Na atual conjuntura, segue o endereço da documentação para execução direto no ambiente de dev:
http://localhost:8080/swagger-ui/index.html
## Diagrama da estrutura proposta docker compose

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Serviços

- Movimentação (movimento-controller):
    Funcionalidades de debitar e creditar saldo ao cliente;
- Auth (auth-controller): Gerar token JWT para execução dos Serviços;
- Cliente (cliente-controller): Insere cliente

## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

`-Dspring.profiles.active=dev`


## Deploy

Após importar o projeto em sua IDE de preferencia, realizar o processo no maven de clean install, algumas IDEs tem essa funcionalidade gráfica, mas por exemplo rodando no linux, basta entrar no diretório (pasta) onde está o arquivo pom.xml e executar o comando abaixo

```bash
  mvn clean install
```




## Stack utilizada

**Back-end:** Java 17, Maven 3.9.1, Docker 23.0.5, Docker-compose 1.29.2, Intelijj 2023.1.1 CE


## Observações

A aplicação não possui a implementação de verbo DELETE, PATH e PUT, uma vez que o GET paginado e o POST foi implementado os demais verbos são de simples implementação, ao invez disso preferi dar destaque a componentes de maior relevância, como arquitetura, segurança, documentação, estruturação distribuida (seja ela cloud, docker ou VM).  
A estruturação dentro do docker tem como intuito a inclusão de componententes voltados para observabilidade como uso do Prometheus e Grafana.  
Para o teste, foi gerado um único teste a titulo de demonstração do uso de BDD implementando o padrão Gerkin.


## Autores

- [@dsbrum](https://www.github.com/dsbrum)

