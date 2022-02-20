## Transaction Manager

Aplicação em Java utilizando [Micronaut](https://micronaut.io) simulando rotinas de transações em conta.

---

####Requisitos para buildar e executar

- [JDK 17](https://adoptium.net/)
  - Se preferir pode ser utilizado uma ferramenta de gerenciamento de versões como o [skdman](https://sdkman.io/)
- [Docker](https://docs.docker.com/engine/install/)*
- [Docker compose](https://docs.docker.com/compose/cli-command/#installing-compose-v2)*
- [Postgres 14](https://www.postgresql.org/)*

_* Somente se for executar com docker_

---

## Passos para executar localmente

A aplicação utiliza o banco postgres para persistir os dados, com isso algumas variáveis de ambiente são necessárias:
```dotenv
# URL de conexaão do banco
POSTGRES_URL=jdbc:postgresql://host:5432/dbname
# Usuário de acesso ao banco
POSTGRES_USER=postgres
# Senha de acesso ao banco
POSTGRES_PASSWORD=admin
```

#### Executando com gradle
A aplicação possui o gradle configurado e executa na porta 8080, para executar com ele acesse a raiz do diretório que encontra-se a aplicação pelo terminal, 
lembrando que devido utilizar o postgres é necessária o serviço do postgres rodando e setar as variáveis necessárias, e 
com isso executar `./gradlew run`.

```shell
export POSTGRES_URL=jdbc:postgresql://localhost:5432/transaction-manager
export POSTGRES_USER=postgres
export POSTGRES_PASSWORD=admin
./gradlew run
```

#### Executando com docker
Para executar com docker já existe um script com a especificação do docker compose, configurando o postgres junto
não sendo necessário já possuir o mesmo executando, sendo o único requisito é que as portas 8080 e 5432 não estejam em uso.

Para rodar bastar executar no terminal dentro do diretório raiz do projeto:
```shell
bash run-docker.sh
```

Dessa forma será realizado o build da aplicação, gerado o jar com o plugin `shadowJar, 
o download do container do postgres na versão 14 e uma imagem base com jre 17 da Temurin
(antiga Adoptium) da Eclipse, no qual será gerado um container com a aplicação. 
