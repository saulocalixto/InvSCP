# Casos de Uso - InvSCP

O presente documento tem como objetivo documentar os casos de uso do sistema InvSCP, salientando os autores de cada ação e de suas necessidades atendidas pelos casos de uso.

### Sumário

* [Diagrama](#diagrama-de-caso-de-uso)
* [Chefe ou substituto de chefe de departamento](#chefe-de-departamento)
	* Patrimônio
		- [CadPat](#cadpat---cadastrar-um-patrimônio)
		- [UpdPat](#updpat---atualizar-um-patrimônio)
		- [DelPat](#delpat---deletar-um-patrimônio)
	* Usuário 
		- [CadUsr](#cadusr---cadastrar-um-usuário)
		- [UpdUsr](#updusr---atualizar-um-usuário)
		- [DelUsr](#delusr---remover-um-usuário)
	* Localização
		- [CadLoc](#cadloc---cadastrar-uma-localização)
		- [UpdLoc](#updloc---atualizar-uma-localização)
		- [DelLoc](#delloc---deletar-uma-localização)
	* Departamento
		- [VincSal](#vincsal---vincular-salas)
		- [DesvSal](#desvsal---desvincular-salas)
	* Movimentação
		- [MovPatEx](#movpatex---movimentação-de-bem-patrimonial-externo)
		- [MovPatIn](#movpatin---movimentação-de-bem-patrimonial-interno)
		- [AceiMov](#aceimov---aceite-de-entrada-de-bem-movimentado)
		- [CanMov](#canmov---cancelamento-de-movimentação)
* [Chefe ou substituto de chefe de departamento de patrimônio](#chefe-de-departamento-de-patrimônio)
	- [BaixPat](#baixpat---dar-baixa-em-um-patrimônio)

### Diagrama de Caso de Uso

![Diagrama de caso de uso](https://github.com/saulocalixto/InvSCP/blob/dev/Design%20Funcional/Casos%20de%20Uso/Diagrama%20de%20Casos%20de%20Uso(preview).png?raw=true)

## Chefe de departamento

### *CadPat* - Cadastrar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de cadastrar um novo bem patrimonial que chegou à instituição, podendo informar no ato todas as informações pertinentes a esse novo bem, inclusive localização e departamento ao qual está vinculado.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário deve ter autorização para realizar o cadastro.

**Pós-condição:**
1. Um novo patrimônio foi adicionado à base do sistema, podendo agora ser visualizado, editado, movimentado ou ter sua baixa decretada.

**Sequência típica**
1. O usuário autorizado entra na funcionalidade de cadastro de patrimônio;
2. O sistema devolve a ficha de cadastro;
3. O usuário digita todas as informações solicitadas pelo sistema, inclusive o local onde o patrimônio será guardado;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do bem que acabou de ser cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O local indicado para salvar o patrimônio não existe.|O sistema lançará uma exceção informando que o local de lotação não existe no sistema.|2|
|3|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *UpdPat* - Atualizar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de atualização de um bem patrimonial previamente cadastrado observando as restrições:
* O número do patrimônio não pode ser atualizado;
* O grupo de material do patrimônio não pode ser atualizado;

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O patrimônio deve estar previamente cadastrado;
2. O item atualizado não pode estar dentro do grupo de restrições;

**Pós-condição:**
1. Um patrimônio previamente cadastrado tem seus dados atualizados.

**Sequência típica**
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

### *DelPat* - Deletar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de excluir um patrimônio da base de dados do sistema de forma permanente, desde que seja seguida determinadas regras:
* O patrimônio que se deseja excluir deve ter sido cadastrado há menos de um mês;
* Não pode ter havido a emissão do inventários contendo o bem;
* Ele não pode ter sido movimentado.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O patrimônio deve estar previamente cadastrado;
2. O item a ser removido deve ter sido cadastrado há menos de um mês;
3. O item não pode ter constado no inventário emitido;
4. O item não pode ter sido movimentado.

**Pós-condição:**
1. O bem removido da base do sistema.

**Sequência típica**
1. O usuário autorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário aciona a funcionalidade de Remoção do patrimônio;
4. O sistema emite um alerta com as consequências do ato;
5. Usuário confirma o ato;
6. Sistema retorna para tela inicial;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O bem procurado não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|5|Usuário não confirma a ação de remoção do bem.|O sistema volta para a ficha de exibição do bem.|2|

### *CadUsr* - Cadastrar um usuário

**Descrição resumida:** O sistema deve provê a funcionalidade do chefe de departamento cadastrar um novo usuário que esteja ligado à seu departamento. No ato do cadastro do usuário o chefe deve informar todos os dados obrigatórios exigidos, além do nível de permissão que determinado usuário terá.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário que será cadastrado deverá ser vinculado ao mesmo departamento do chefe que o está cadastrando.

**Pós-condição:**
1. Um novo usuário é adicionado à base.

**Sequência típica**
1. O usuário autorizado entra na funcionalidade de cadastro de usuário;
2. O sistema devolve a ficha de cadastro de usuário;
3. O usuário digita todas as informações solicitadas pelo sistema, inclusive o nível de permissão que o novo usuário terá no sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do usuário recém cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento do novo usuário diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar usuários em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *UpdUsr* - Atualizar um usuário

**Descrição resumida:** O chefe de departamento pode atualizar os dados dos usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do chefe que deseja atualizar o dado;

**Pós-condição:**
1. O usuário tem seus dados atualizados no sistema.

**Sequência típica**
1. O usuário autorizado localiza um usuário;
2. O sistema devolve a ficha do usuário;
3. O usuário entra na funcionalidade de atualização;
4. O sistema abre a tela de edição dos dados do usuário;
5. Usuário atualiza os dados;
6. Usuário confirma a operação;
7. Sistema apresenta a ficha do item com os dados atualizados;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O usuário não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|3|Usuário não tem permissão para atualização.|O sistema lança exceção informando que o usuário só pode atualizar outros usuários vinculados ao seu departamento.|2|
|5|Usuário tenta atualizar algum dado indevido.|O sistema lança exceção informando que o dado não pode ser atualizado.|4|
|6|Usuário desiste da operação.|O sistema volta para a ficha do bem sem ter os dados atualizados.|2|

### *DelUsr* - Remover um usuário

**Descrição resumida:** O chefe de departamento pode remover os usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do chefe que deseja atualizar o dado;

**Pós-condição:**
1. O usuário é removido do sistema.

**Sequência típica**
1. O usuário autorizado localiza um usuário;
2. O sistema devolve a ficha do usuário;
3. O usuário entra na funcionalidade de apagar;
4. O sistema lança um alerta informando sobre as consequências do ato;
5. Usuário confirma;
6. Sistema retorna para tela inicial.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O usuário não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|3|Usuário não tem permissão para remover o outro usuário.|O sistema lança exceção informando que o usuário só pode atualizar outros usuários vinculados ao seu departamento.|2|
|5|Usuário não confirma a operação.|O sistema retorna para a ficha do usuário.|3|

### *CadLoc* - Cadastrar uma Localização

**Descrição resumida:** O usuário deve poder realizar cadastros de locais onde os bens ficarão guardados. Geralmente um bem é guardado em uma sala que é vinculada à um departamento, prédio e endereço. Por tanto ao cadastrar uma localização o usuário deve inserir:
* Sala;
* Prédio;
* Endereço.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ter permissão para o ato.

**Pós-condição:**
1. Uma nova localização é adicionado à base.

**Sequência típica**
1. O usuário autorizado entra na funcionalidade de cadastro de localização;
2. O sistema devolve a ficha de cadastro da localização;
3. O usuário digita todas as informações solicitadas pelo sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha de cadastro da localização;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento da nova localização diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar localização em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *UpdLoc* - Atualizar uma Localização

**Descrição resumida:** O usuário deve poder realizar a atualização de localizações que sejam vinculadas ao departamento do qual é chefe. Ele deve observar que ao atualizar uma localização todos os bens que forem vinculados a ela também serão atualizados.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ter permissão para o ato;
2. A localização deve esta cadastrada;

**Pós-condição:**
1. A localização tem seus dados atualizados.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa a localização que deseja buscar;
3. O sistema devolve a ficha com as informações da localização;
4. O usuário entra na funcionalidade de edição de dados;
5. O sistema apresenta o formulário de edição da localização;
6. O usuário preenche os dados;
7. O usuário confirma a operação;
8. Sistema devolve ficha da localização com os dados atualizados.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|6|Usuário deixa alguma informação obrigatória sem preencher.|O sistema lança exceção de dado obrigatório não preenchido.|5|
|7|Usuário desiste da operação.|O sistema volta para a ficha da localização.|3|

### *DelLoc* - Deletar uma Localização

**Descrição resumida:** O usuário deve poder apagar uma localização previamente cadastrada. Contudo é se houver algum patrimônio ou departamento previamente vinculado a essa localização então a exclusão não será permitida.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ser chefe do departamento do qual a localização está vinculada;
2. Não deve haver nenhum patrimônio ou departamento vinculado à localização.

**Pós-condição:**
1. A localização será excluída da base de dados do sistema.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa a localização que deseja buscar;
3. O sistema devolve a ficha com as informações da localização;
4. O usuário aciona a funcionalidade de exclusão;
5. O sistema apresenta mensagem informando dos riscos do ato;
6. O usuário confirma a operação;
7. Sistema volta para tela inicial.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Existe patrimônio ou departamento vinculado à localização.|O sistema informa que não pode ser feita a exclusão por haver patrimônio vinculado à ela.|3|
|6|Usuário não confirma a operação.|O sistema volta para a ficha da localização.|3|

### *VincSal* - Vincular salas

**Descrição resumida:** O chefe de departamento deve ter a possibilidade de vincular uma sala previamente cadastrada ao departamento de que ele é chefe. Ele só poderá fazer isso se essa sala já não estiver vinculada a um outro departamento.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Sala não pode esta  vinculada a outro departamento.

**Pós-condição:**
1. Sala é vinculada ao departamento.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa por uma localização;
3. Sistema devolve a ficha de informações da localização;
4. O usuário entra na funcionalidade de vincular departamento;
5. O sistema lista os departamentos disponíveis de acordo com a permissão do usuário;
6. Usuário confirma operação;
7. Sistema retorna para tela com os dados da localização.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada no sistema.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Localização já está vinculada a um departamento.|O sistema avisa o usuário que não poderá vincular a sala a um outro departamento.|3|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|3|

### *DesvSal* - Desvincular salas

**Descrição resumida:** O chefe de departamento deve ter a possibilidade de desvincular uma sala previamente cadastrada ao departamento de que ele é chefe. Ele só poderá fazer isso se não houver patrimônio vinculado à sala ou ao departamento.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Sala não pode ter patrimônio vinculado a ela;
2. Departamento não pode ser patrimônio vinculado a ele.

**Pós-condição:**
1. Sala é desvinculada ao departamento.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa por uma localização;
3. Sistema devolve a ficha de informações da localização;
4. O usuário aciona a funcionalidade de desvincular departamento;
5. O sistema apresenta uma mensagem informando o usuário das consequências do ato;
6. O usuário confirma a operação;
7. Sistema retorna a ficha da localização;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada no sistema.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Localização não está vinculada ao departamento do usuário.|O sistema avisa o usuário que não poderá desvincular a localização.|3|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|3|

### *MovPatEx* - Movimentação de bem patrimonial Externo

**Descrição resumida:** A movimentação de bens entre departamento diferentes deve ser registrada no sistema. Ao criar a movimentação ela não se conclui imediatamente, mas fica com o status de *Aceite de saída*, o que significa que ela está aguardando autorização de origem. Caso seja o próprio chefe do departamento que tenha cadastrado a movimentação, então o *Aceite de Saída* será automático.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. O item movimentado fica com o status de *Aceite de saída* caso a movimentação não tenha sido feita pelo chefe de departamento;
2. Caso o item tenha sido movimentado pelo chefe do departamento o item fica com o status de *Aceite de entrada no destino*.

**Sequência típica**
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

### *MovPatIn* - Movimentação de bem patrimonial Interno

**Descrição resumida:** Movimentações entre salas do mesmo departamento não precisam do aceite do chefe do departamento, sendo marcadas imediatamente como *Finalizadas*.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa;
3. A movimentação deve ser entre salas do mesmo departamento.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizado* imediatamente;
2. O local onde o item está guardado é atualizado no sistema.

**Sequência típica**
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

### *AceiMov* - Aceite de entrada de bem movimentado

**Descrição resumida:** O chefe do departamento para onde o bem foi movimentado deve autorizar o recebimento do bem. Só quando essa ação for feita é que o local de lotação do bem é atualizado e o status da movimentação fica como *Finalizada*.

**Autor:** Chefe de departamento

**Pré-condição:**
1. O bem deve ter sido movimentado para um departamento diferente da origem.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizada*;
2. O local onde o item é lotado é atualizado no sistema.

**Sequência típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário aceita a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

### *CanMov* - Cancelamento de movimentação

**Descrição resumida:** A movimentação pode ser recusada pelo chefe de departamento. Tanto movimentação de entrada, como movimentação de saída. Nesses casos a movimentação fica com o status de *Cancelada*.

**Autor:** Chefe da seção

**Pré-condição:**
1. Deve existir uma movimentação com status de *Aceite de saída* ou *Aceite de entrada* para o departamento do chefe logado no sistema

**Pós-condição:**
1. O bem não tem seu local atualizado;
2. A movimentação fica com o status de *Cancelada*.

**Sequência típica**
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

## Chefe de departamento de patrimônio

### *BaixPat* - Dar baixa em um patrimônio

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

**Sequência típica**
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
