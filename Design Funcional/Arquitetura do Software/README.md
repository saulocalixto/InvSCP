# Arquitetura do InvSCP

## Visão Geral

![Imagem da Arquitetura](./ArquiteturaInvSCP.png?raw=true)

O InvSCP é um sistema essencialmente baseado no estilo arquitetural cliente-servidor. Contudo não se limita apenas a este. O sistema também lança de mão do estilo arquitetural de serviço, multicamadas e rest.

Por ser uma arquitetura orientada a serviços nós temos um servidor que tem a função de expor os serviços para que sejam consumidos pelo cliente. A comunicação entre cliente e o servidor se dá por meio de rests apis, que trafegam dados em json. As apis são protegidas por um token de acesso que é conseguido por meio de autenticação. Para a atuenticação utlizamos o protocolo OAuth 2.0, que resumidamente valida um login e passa para o cliente um token de acesso que será usado para acessar as apis.

## Cliente InvSCP

O Cliente InvSCP é o software utilizado pelo usuário final (gerentes, funcionários da instituição, etc.). Através dele o usuário poderá fazer as operações que desejar e o Cliente InvSCP mostrará seus resultados após receber a resposta das requisições. As requisições enviadas e recebidas do InvSCP (presente no Servidor InvSCP) são transmitidas pelo servidor através de rest api's.
O cliente é todo feito em Java, não implementa interface gráfica e toda iteração se dá por meio de comandos do console.

## Servidor InvSCP

![Servidor](./ModelagemServidor.png?raw=true)

Este é o principal módulo do sistema, que, como podemos ver, é divido em multicamadas. Cada camada tem uma função bem específica?

### Controller

Essa camada tem o papel de expor o servidor para o cliente. É nela que é implementada as apis para cada serviço do sistema, permitindo assim que o sistema se comunique com outros sistemas. O Controller é importante porque traz ao sistema uma maior modularidade e interoperabilidade, já que facilita o uso do servidor, bastando ter o autorização de acesso, pois o formato trafegado, json, é universalmente conhecido, independente da linguagem usada pelo cliente.

### Serviço

O serviço, como todo servidor, é implementado em Java. Nele há toda a lógica de negócio. Ele é responsável por chamar o repositório e com os dados conseguidos fazer o tratamento devido para que sejam passados para o controller. O serviço também tem o papel de receber do controller dados que precisaram ser tratados e persistidos.
No serviço encontram-se as validações de negócio, que são pré requisitos para que o usuário possa fazer alguma alteração na base de dados. Por exemplo, verificar se um objeto passado pelo cliente é válido, ou mesmo validar a autorização que o usuário tem para efetuar determinada ação.

### Repositório

O repositório abstrai toda a lógica para conexão no banco. O serviço não tem conhecimento de como os dados serão persistidos, ele tem o papel apenas de passar para o repositório um dado para ser pesistido ou consultado. Então é papel do repositório fazer a conexão com o banco de dados. Para a conexão com a base de dados foi usado JDBC e consultas em sql, não foi utilizado nenhum framework para tal.

### Banco de dados

O banco de dados é o MySql e é divido em várias tabelas. Em suma todo conceito tem sua própria tabela, que pode ou não ter relação com outras tabela. Ele é acessado por meio do repositório.

### Autenticação

![Servidor de autenticação](./servidorAutenticacao.png?raw=true)

O componente de Autenticação é a porta de entrada para o InvSCP. Ele é responsável por autorizar as demandas recebidas de acordo com o protocolo OAuth2 e com as regras de acesso definidas para cada usuário/serviço. A autenticação nada mais é do que um serviço. Antes de acessar qualquer api é necessário que o usuário faça login no sistema. Ao enviar o login e a senha para o servidor, este irá validar esses dados, se estiverem corretos o servidor irá enviar um token de acesso, por meio do qual o cliente terá acesso as outras apis do sistema.
