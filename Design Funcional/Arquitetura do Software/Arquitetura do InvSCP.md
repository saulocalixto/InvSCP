# Arquitetura do InvSCP

![Imagem da Arquitetura](./ArquiteturaInvSCP.png?raw=true)

## Cliente InvSCP

O Cliente InvSCP é o software utilizado pelo usuário final (gerentes, funcionários da instituição, etc.). Através dele o usuário poderá fazer as operações que desejar e o Cliente InvSCP mostrará seus resultados após receber a resposta das requisições. As requisições enviadas e recebidas do InvSCP (presente no Servidor InvSCP) são transmitidas pela internet.

## InvSCP

Este é o principal módulo do sistema, composto pelos componentes abaixo.

### Autenticação

O componente de Autenticação é a porta de entrada para o InvSCP. Ele é responsável por autorizar as demandas recebidas de acordo com o protocolo OAuth2 e com as regras de acesso definidas para cada usuário/serviço.

### Lógica de Negócios

O componente de Lógica de Negócios é responsável por realizar as operações que envolvem o domínio da aplicação, como por exemplo cadastrar um novo bem, realizar movimentações, entre outros. Essas operações só podem ser realizadas após sua devida autorização.

### Dados

O componente de dados é responsável por administrar todos os dados do sistema e sua persistência e acesso no banco de dados. Estes dados incluem os registros de bens, movimentações, locais, usuários e suas permissões de acesso, entre outros.
