# Servidor InvSCP
Software servidor da aplicação InvSCP responsável por receber do cliente as entradas de usuário, tratar as informações, guardá-las ou mandá-las ao cliente.
Os dados são disponibilidades via rest api, os quais o cliente tem acesso mediante autenticação.

## Utilização
Abra o terminal no diretório cliente-invscp e execute os seguintes comandos:

### Para gerar o executável do Servidor InvSCP (Requer: Maven)
```
$~ mvn package
```
### Para iniciar a aplicação do Servidor InvSCP

```
$~ java -jar target/servidor-invscp-1.0-SNAPSHOT.jar
```
Após esse comando o servidor entrará em ação, sendo possível ter acesso à API criada via [Cliente InvSCP](../cliente-invscp).

### Para realizar os testes de unidade (Requer: Maven)
```
$~ mvn test
```
