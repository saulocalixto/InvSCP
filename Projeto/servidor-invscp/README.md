# Servidor InvSCP
Software servidor da aplicação InvSCP responsável por receber do cliente as entradas de usuário, tratar as informações, guardá-las ou mandá-las ao cliente.
Os dados são disponibilidades via rest api, os quais o cliente tem acesso mediante autenticação.

## Requisitos
* Maven
* MySQL Server 8
* Java 8

## Utilização

### 1. Para iniciar o banco de dados (necessário para usar o Servidor InvSCP)

Abra o terminal no diretório *InvSCP\Projeto\servidor-invscp\src\main\resources* e execute:
```
$~ mysql -u root -p
```
Insira a senha do usuário root, criado ao instalar o MySQL Server no computador. Em seguida, execute:

```
$~ source schema.sql
$= source data.sql
```

### 2. Para gerar o executável do Servidor InvSCP

Abra o terminal no diretório *InvSCP\Projeto\servidor-invscp* e execute os seguintes comandos:
```
$~ mvn package
```
### 3. Para iniciar a aplicação do Servidor InvSCP

Abra o terminal no diretório *InvSCP\Projeto\servidor-invscp* e execute os seguintes comandos:
```
$~ java -jar target/servidor-invscp-1.0-SNAPSHOT.jar
```
Após esse comando o servidor entrará em ação, sendo possível ter acesso à API criada via [Cliente InvSCP](../cliente-invscp).

### 4. Para realizar os testes de unidade
```
$~ mvn test
```
