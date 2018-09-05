# Casos de Uso - InvSCP

O presente documento tem como objetivo documentar os casos de uso do sistema InvSCP, salientando os autores de cada ação e de suas necessidades atendidas pelos casos de uso.

### Autores
* Chefe ou substituto de chefe de departamento
* Chefe do departamento de patrimônio
* Funcionário do departamento

### Atalhos para casos de uso
- [CadPat](#cadpat---cadastrar-um-patrimônio)
- [UpdPat](#updpat---atualizar-um-patrimônio)
- [MovPatEx](#movpatex---movimentação-de-bem-patrimonial-externo)
- [MovPatIn](#movpatin---movimentação-de-bem-patrimonial-interno)
- [AceiMov](#aceimov---aceite-de-entrada-de-bem-movimentado)
- [CanMov](#canmov---cancelamento-de-movimentação)
- [BaixPat](#baixpat---dar-baixa-em-um-patrimônio)

## *CadPat* - Cadastrar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de cadastrar um novo bem patrimonial que chegou à instituição, podendo informar no ato todas as informações pertinentes a esse novo bem.

**Autor:** Chefe e Substituto de chefe da Seção

**Pré-condição:**
1. O usuário deve ter autorização para realizar o cadastro.

**Pós-condição:**
1. Um novo patrimônio foi adicionado à base do sistema, podendo agora ser visualizado, editado, movimentado ou ter sua baixa decretada.

**Sequencia típica**
1. O usuário autorizado entra na funcionalidade de cadastro de patrimônio;
2. O sistema devolver a ficha de cadastro;
3. O usuário digita todas as informações solicitadas pelo sistema, inclusive o local onde o patrimônio será guardado;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do bem que acabou de ser cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O local indicado para salvar o patrimônio não existe.|O sistema lançará uma exceção informando que o local de lotação não existe no sistema.|2|
|3|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *UpdPat* - Atualizar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de atualização de um bem patrimonial previamente cadastrado observando as restrições:
* O número do patrimônio não pode ser atualizado;
* O grupo de material do patrimônio não pode ser atualizado;

**Autor:** Chefe e Substituto de chefe da Seção

**Pré-condição:**
1. O patrimônio deve estar previamente cadastrado;
2. O item atualizado não pode estar dentro do grupo de restrições;

**Pós-condição:**
1. Um patrimônio previamente cadastrado tem seus dados atualizados.

**Sequencia típica**
1. O usuário autorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário entra na funcionalidade de atualização;
4. O sistema abre a tela de edição do patrimônio;
5. Usuário atualiza os dados;
6. Usuário confirma a operação;
7. Sistema apresenta a ficha do item com os dados atualizados;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O bem procurado não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|5|Usuário tenta atualizar algum dado indevido.|O sistema lança exceção informando que o dado não pode ser atualizado.|4|
|6|Usuário desiste da operação.|O sistema volta para a ficha do bem sem ter os dados atualizados.|2|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *MovPatEx* - Movimentação de bem patrimonial Externo

**Descrição resumida:** A movimentação de bens entre departamento diferentes deve ser registrada no sistema. Ao criar a movimentação ela não se conclui imediatamente, mas fica com o status de *Aceite de saída*, o que significa que ela está aguardando autorização de origem. Caso seja o próprio chefe do departamento que tenha cadastrado a movimentação, então o *Aceite de Saída* será automático.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. O item movimentado fica com o status de *Aceite de saída* caso a movimentação não tenha sido feita pelo chefe de departamento;
2. Caso o item tenha sido movimentado pelo chefe do departamento o item fica com o status de *Aceite de entrada no destino*.

**Sequencia típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. Sistema apresenta ficha de bem com informação pertinente à movimentação recém cadastrada.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Bem pesquisado não foi localizado|O sistema informará que o bem não foi encontrado.|1|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|2|
|5|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|4|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *MovPatIn* - Movimentação de bem patrimonial Interno

**Descrição resumida:** Movimentações entre salas do mesmo departamento não precisam do aceite do chefe do departamento, sendo marcadas imediatamente como *Finalizadas*.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa;
3. A movimentação deve ser entre salas do mesmo departamento.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizado* imediatamente;
2. O local onde o item está guardado é atualizado no sistema.


**Sequencia típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. Sistema apresenta ficha de bem com informação pertinente à movimentação recém cadastrada.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Bem pesquisado não foi localizado|O sistema informará que o bem não foi encontrado.|1|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|2|
|5|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|4|
|5|Usuário informa uma localização fora do departamento no qual o bem está lotado.|O sistema cadastra a movimentação mas com o status de *Aceite de Saída*.|7|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *AceiMov* - Aceite de entrada de bem movimentado

**Descrição resumida:** O chefe do departamento para onde o bem foi movimentado deve autorizar o recebimento do bem. Só quando essa ação for feita é que o local de lotação do bem é atualizado e o status da movimentação fica como *Finalizada*.

**Autor:** Chefe de departamento

**Pré-condição:**
1. O bem deve ter sido movimentado para um departamento diferente da origem.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizada*;
2. O local onde o item é lotado é atualizado no sistema.

**Sequencia típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário aceita a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *CanMov* - Cancelamento de movimentação

**Descrição resumida:** A movimentação pode ser recusada pelo chefe de departamento. Tanto movimentação de entrada, como movimentação de saída. Nesses casos a movimentação fica com o status de *Cancelada*.

**Autor:** Chefe da seção

**Pré-condição:**
1. Deve existir uma movimentação com status de *Aceite de saída* ou *Aceite de entrada* para o departamento do chefe logado no sistema

**Pós-condição:**
1. O bem não tem seu local atualizado;
2. A movimentação fica com o status de *Cancelada*.

**Sequencia típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada* ou *Aceite de Saída*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário recusa a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *BaixPat* - Dar baixa em um patrimônio

**Descrição resumida:** Quando o bem patrimonial torna-se desnecessário para a instituição é necessário dar baixa ao mesmo.
Um bem pode ser baixado pelos seguintes motivos:
* Por inservível;
* Por quebra, desgaste ou avaria;
* Por venda em exercícios anteriores;
* Por extravio;
* Por venda direta o leilão;
* Por furto/roubo.

**Autor:** Chefe do departamento de patrimônio

**Pré-condição:**
1. O usuário deve ter autorização para realizar a baixa do bem.

**Pós-condição:**
1. No registro do bem constará a informação da baixa do bem, informando data, autor e motivo;
2. Bem fica impossibilitado de fazer parte do inventário.

**Sequencia típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha de registro do bem;
3. Usuário aciona a funcionalidade de baixa;
4. Sistema abre tela de cadastro de baixa;
5. Usuário informa todos as informações exigidas pelo sistema;
6. Usuário salva o cadastro de baixa;
7. Sistema apresenta ficha do bem com o status de *Baixa*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|O usuário não consegue localizar o bem com as informações de pesquisa.|O sistema informará que o bem não foi encontrado.|1|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|2|
|4|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|4|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|1|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*
