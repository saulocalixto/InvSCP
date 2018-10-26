# Cliente InvSCP
Software utilizado pelo usuário final para interagir com o sistema InvSCP através do terminal.

## Utilização
Abra o terminal no diretório cliente-invscp e execute os seguintes comandos:

### Para gerar o executável do Cliente InvSCP (Requer: Maven)
- mvn package

### Para iniciar a aplicação do Cliente InvSCP (Requer: Java e Servidor InvSCP funcionando)
- java -jar target/cliente-invscp.jar

### Para realizar os testes de unidade (Requer: Maven)
- mvn test