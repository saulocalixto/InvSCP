# Casos de Uso - InvSCP

O presente documento tem como objetivo documentar os casos de uso do sistema InvSCP, salientando os autores de cada ação e de suas necessidades atendidas pelos casos de uso.

### Autores
* Chefe ou substituto de chefe de departamento
* Chefe do departamento de patrimônio
* Funcionário do departamento

### Atalhos para casos de uso
- [CadPat](#cadpat---cadastrar-um-patrimônio)
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
1. O usuário autorizado entra na funcionalidade de cadastro de patrimônio
2. O usuário digita todas as informações solicitadas pelo sistema, inclusive o local onde o patrimônio será guardado
3. O usuário salva as informações, perpetuando o cadastro do patrimônio
4. O sistema persiste o patrimônio em sua base de dados

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O local indicado para salvar o patrimônio não existe.|O sistema lançará uma exceção informando que o local de lotação não existe no sistema.|?|
|3|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|?|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *MovPatEx* - Movimentação de bem patrimonial Externo

**Descrição resumida:** A movimentação de bens entre departamento diferentes deve ser registrada no sistema. Ao criar a movimentação ela não se conclui imediatamente, mas fica com o status de *Aceite de saída*, o que significa que ela está aguardando autorização de origem. Caso seja o próprio chefe do departamento que tenha cadastrado a movimentação, então o *Aceite de Saída* será automático.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem
2. O bem não pode estar com o status de baixa

**Pós-condição:**
1. O item movimentado fica com o status de *Aceite de saída* caso a movimentação não tenha sido feita pelo chefe de departamento.
2. Caso o item tenha sido movimentado pelo chefe do departamento o item fica com o status de *Aceite de entrada no destino*

**Sequencia típica**
1. O usuário localiza o bem no sistema
2. O usuário abre sua ficha de registro
3. Usuário aciona a funcionalidade de movimentação
4. Usuário informa todos as informações exigidas pelo sistema
5. Usuário salva o cadastro de movimentação

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|O usuário não consegue localizar o bem com as informações de pesquisa.|O sistema informará que o bem não foi encontrado.|?|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|?|
|4|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|?|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|?|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *MovPatIn* - Movimentação de bem patrimonial Interno

**Descrição resumida:** Movimentações entre salas do mesmo departamento não precisam do aceite do chefe do departamento, sendo marcadas imediatamente como *Finalizadas*.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem
2. O bem não pode estar com o status de baixa
3. A movimentação deve ser entre salas do mesmo departamento

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizado* imediatamente
2. O local onde o item está guardado é atualizado no sistema 

**Sequencia típica**
1. O usuário localiza o bem no sistema
2. O usuário abre sua ficha de registro
3. Usuário aciona a funcionalidade de movimentação
4. Usuário informa todos as informações exigidas pelo sistema, colocando o destino em uma sala do mesmo departamento de origem
5. Usuário salva o cadastro de movimentação

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|O usuário não consegue localizar o bem com as informações de pesquisa.|O sistema informará que o bem não foi encontrado.|?|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|?|
|4|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|?|
4|Usuário informa o local de destino fora do departamento de origem.|O sistema irá cadastrar a movimentação com o status de *Aceite de Saída*|?|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|?|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *AceiMov* - Aceite de entrada de bem movimentado

**Descrição resumida:** O chefe do departamento para onde o bem foi movimentado deve autorizar o recebimento do bem. Só quando essa ação for feita é que o local de lotação do bem é atualizado e o status da movimentação fica como *Finalizada*.

**Autor:** Chefe da seção

**Pré-condição:**
1. O bem deve ter sido movimentado para o departamento

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizada*
2. O local onde o item é lotado é atualizado no sistema

**Sequencia típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada*
2. Será listado para ele todos as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação
3. Ele deve abrir a lotação que deseja dar o aceite
4. Confirmar a movimentação

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|?|

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
1. O bem não tem seu local atualizado
2. A movimentação fica com o status de *Cancelada*

**Sequencia típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada* ou *Aceite de Saída*
2. Será listado para ele todos as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação
3. Ele deve abrir a lotação que deseja dar o aceite
4. Recusar a movimentação

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|?|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*

## *BaixPat* - Dar baixa em um patrimônio

**Descrição resumida:** Quando o bem patrimonial torna-se desnecessário para a instituição é necessário dar baixa ao mesmo.
Um bem pode ser baixado pelos seguintes motivos:
* Por inservível
* Por quebra, desgaste ou avaria
* Por venda em exercícios anteriores
* Por extravio
* Por venda direta o leilão
* Por furto/roubo

**Autor:** Chefe do departamento de patrimônio

**Pré-condição:**
1. O usuário deve ter autorização para realizar a baixa do bem

**Pós-condição:**
1. No registro do bem constará a informação da baixa do bem, informando data, autor e motivo
2. Bem fica impossibilitado de fazer parte do inventário

**Sequencia típica**
1. O usuário localiza o bem no sistema
2. O usuário abre sua ficha de registro
3. Usuário aciona a funcionalidade de baixa
4. Usuário informa todos as informações exigidas pelo sistema
5. Usuário salva o cadastro de baixa

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|O usuário não consegue localizar o bem com as informações de pesquisa.|O sistema informará que o bem não foi encontrado.|?|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|?|
|4|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|?|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|?|

**Diagrama de classe:**

*Colar aqui o diagrama que representa esse caso de uso*

**Diagrama de Sequência:**

*Colar aqui o diagrama que representa esse caso de uso*
