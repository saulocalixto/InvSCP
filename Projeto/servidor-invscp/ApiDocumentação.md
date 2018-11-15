# API InvSCP
O InvSCP expõe seus serviços através de REST API, o presente documento tem o objetivo de mostrar como a api funciona.
## Login

Para poder ter acesso a qualquer serviço do servidor antes é preciso que o usuário se autentique e assim consiga o **token de acesso**.

Para fazê-lo é preciso fazer uma requisição passando o login e a senha:

```
http://localhost:8090/login?email=email@email.com.br,senha=senhaValida
```

O resultado é um json contendo entre outras informações, o token de acesso que deve ser passado via header em cada requisição feita.
## Header
Cada chamada da api deve ter um header contendo o tipo do método e o token de acesso.
O token de acesso deve ser passado da seguinte forma:

```
Autorizacao: tokenDeAcesso
```

## Usuário Api

| Endpoints       | Usage          | Params         | Body         |
|-----------------|----------------|----------------|----------------|
| `GET /usuario` | Pega o usuário com base no email passado.| **email** - [String] email do usuário que se deseja procurar. ||
| `PUT /usuario` | Cadastra um novo usuário na base.| | [Usuario] usuário que deseja salvar. |
| `PUT /usuario/atualize` | Atualiza um usuário na base.| | [Usuario] usuário que deseja atualizar |
| `DELETE /usuario` | Atualiza um usuário na base.| **id** - [String] Id do usuário que deseja apagar da base. |  |
