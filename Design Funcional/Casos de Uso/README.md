# Casos de Uso

Nessa sessão trataremos sobre os casos de uso que são significativos para os usuários, separando os atores e quais suas necessidades no sistema. É apresentado um diagrama de fácil compreensão para os usuários, além do detalhamento de cada caso de uso.

### Sumário

* [Diagrama](#diagrama-de-caso-de-uso)
* [Chefe do Patrimônio](#chefe-do-patrimônio)
	- [EmInv](#eminv)
	- [BaixPat](#baixpat)
* [Administrador de Departamento](#administrador-de-departamento)
	* Bens Patromoniais:
		- [ManPat](#manpat)
		- [VisHis](#VisHis)
	* Usuários:
		- [ManUsr](#manusr)
	* Localizações:
		- [ManLoc](#manloc)
	* Departamentos:
		- [ManDep](#mandep)
		- [VincSal](#vincsal)
		- [DesvSal](#desvsal)
	* Movimentações:
		- [AceiMov](#aceimov)
		- [CanMov](#canmov)
		- [Emgutransp](#emgutransp)
	* Serviços:
		- [Emrelpat](#emrelpat)
		- [RegOS](#regos)
		- [FechOS](#fechos)
* [Funcionário](#funcionário)
	- [RegMov](#regmov)
* [Público](#publico)
	- [BusBemPat](#busbempat)
	- [ConDadBemPat](#condadbempat)

### Diagrama de Caso de Uso

![Diagrama de casos de uso](./diagramaCasosDeUso.png?raw=true)

## Chefe do Patrimônio

### *EmInv*

**Título:** Emitir inventário

**Descrição resumida:** O chefe do patrimônio precisa emitir o inventário que é na verdade a relação de bens da instituição. No inventário o sistema deverá permitir que o usuário acompanhe a evolução de bem no momento de publicar o balanço patrimonial, além de acompanhamento de depreciação para poder fazer projeções de compras no futuro.

**ator:** Chefe do patrimônio

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Um documento contendo as informações de todos os bens da instituição;

**Sequência típica**
1. Sistema apresenta tela inicial;
2. Usuário entra da funcionalidade de emissão de inventário;
3. Sistema apresenta documento contendo todas as informações de bens patrimoniais cadastrados no sistema;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não há bens patrimoniais cadastrados no sistema.|Sistema informa que não há bens para constar no inventário.|1|

### *BaixPat*

**Título:** Dar baixa em um patrimônio

**Descrição resumida:** Quando o bem patrimonial torna-se desnecessário para a instituição é necessário dar baixa ao mesmo.
Um bem pode ser baixado pelos seguintes motivos:
* Por inservível;
* Por quebra, desgaste ou avaria;
* Por venda em exercícios anteriores;
* Por extravio;
* Por venda direta o leilão;
* Por furto/roubo.

**ator:** Chefe do patrimônio

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. No registro do bem constará a informação da baixa do bem, informando data, ator e motivo;
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

##  Administrador de Departamento

### ManPat
**Título:** Manter patrimônio 
#### Fluxo principal

**Título:** Cadastrar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de cadastrar um novo bem patrimonial que chegou à instituição, podendo informar no ato todas as informações pertinentes a esse novo bem, inclusive localização e departamento ao qual está vinculado. Quando a localização de um bem não é informada, ele é guardado automaticamente na sala de depósito do departamento de patrimônio.

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Um novo patrimônio foi adicionado à base do sistema, podendo agora ser visualizado, editado, movimentado ou ter sua baixa decretada.

**Sequência típica:** cadastrar
1. Sistema apresenta a tela inicial;
2. O usuário atorizado entra na funcionalidade de cadastro de patrimônio;
3. O sistema devolve a ficha de cadastro;
4. O usuário digita todas as informações solicitadas pelo sistema, inclusive o local onde o patrimônio será guardado;
5. O usuário salva as informações;
6. O sistema apresenta a ficha do bem que acabou de ser cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O local indicado para salvar o patrimônio não existe.|O sistema lançará uma exceção informando que o local de lotação não existe no sistema.|3|
|4|O usuário não informa o local onde o bem será guardado.|O sistema avisa que o bem será guardado na sala de depósito do departamento de patrimônio.|5|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

#### Fluxo alternativo I

**Título:** Atualizar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de atualização de um bem patrimonial previamente cadastrado observando as restrições:
* O número do patrimônio não pode ser atualizado;
* O grupo de material do patrimônio não pode ser atualizado;

**ator:** Administrador de departamento

**Pré-condição:**
1. O item atualizado não pode estar dentro do grupo de restrições;

**Pós-condição:**
1. Um patrimônio previamente cadastrado tem seus dados atualizados.

**Sequência típica**
1. O usuário atorizado localiza um patrimônio;
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
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

#### Fluxo alternativo II

**Título:** Remover um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de excluir um patrimônio da base de dados do sistema de forma permanente, desde que seja seguida determinadas regras:
* O patrimônio que se deseja excluir deve ter sido cadastrado há menos de um mês;
* Não pode ter havido a emissão do inventários contendo o bem;
* Ele não pode ter sido movimentado.

**ator:** Administrador de departamento

**Pré-condição:**
1. O item a ser removido deve ter sido cadastrado há menos de um mês;
2. O item não pode ter constado no inventário emitido;
3. O item não pode ter sido movimentado.

**Pós-condição:**
1. O bem removido da base do sistema.

**Sequência típica**
1. O usuário atorizado localiza um patrimônio;
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

#### Fluxo alternativo III*

**Título:** Recuperar um patrimônio

**Descrição resumida:** O sistema possui a funcionalidade de recuperar as informações de um patrimonio.

**ator:** Administrador de departamento.

**Pré-condição:**

Não se aplica

**Pós-condição:**
1. Informações sobre um patrimonio.

**Sequência típica**
1. O usuário  acessa a funcionalidade de recuperar patrimonio;
2. O sistema retorna a lista de bens patrimoniais com opções de filtro;
3. O usuário seleciona o patrimonio;
4. O sistema retorna todas os detalhes do patrimonio;

**Exceções da Sequência alternativa:** recuperar um matrimonio

Não se aplica

### *VisHis*

**Título:** Visualizar histórico de patrimônio

**Descrição resumida:** O sistema deve guardar o histórico de qualquer movimentação feita para um bem patrimonial e o usuário deve poder visualizar esse histórico. Deve conter no histórico:
* Movimentações feitas ao longo de tempo;
* Depreciação ao longo do tempo;
* Os geradas;
* Baixa do bem.

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica

**Pós-condição:**
1. A visualização do histórico do bem.

**Sequência típica**
1. O usuário atorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário aciona o histórico do bem;
4. O sistema lista todas as datas de histórico do referido bem;
5. Usuário escolhe a data desejada;
6. Sistema informa se houve movimentação, Os ou baixa naquela data;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|3|O bem não tem histórico.|O sistema não lista nada.|2|

### ManUsr

**Título:** Manter usuário 

#### *Fluxo principal*

**Título:** Cadastrar um usuário

**Descrição resumida:** O sistema deve provê a funcionalidade do administrador de departamento cadastrar um novo usuário que esteja ligado à seu departamento. No ato do cadastro do usuário o administrador deve informar todos os dados obrigatórios exigidos, além do nível de permissão que determinado usuário terá.

**ator:** Administrador de departamento

**Pré-condição:**
1. Usuário que será cadastrado deverá ser vinculado ao mesmo departamento do administrador que o está cadastrando.

**Pós-condição:**
1. Um novo usuário é adicionado à base.

**Sequência típica**
1. O usuário atorizado entra na funcionalidade de cadastro de usuário;
2. O sistema devolve a ficha de cadastro de usuário;
3. O usuário digita todas as informações solicitadas pelo sistema, inclusive o nível de permissão que o novo usuário terá no sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do usuário recém cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento do novo usuário diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar usuários em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

#### *Fluxo alternativo I*

**Título:** Atualizar um usuário

**Descrição resumida:** O administrador de departamento pode atualizar os dados dos usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**ator:** Administrador de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do administrador que deseja atualizar o dado;

**Pós-condição:**
1. O usuário tem seus dados atualizados no sistema.

**Sequência típica**
1. O usuário atorizado localiza um usuário;
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

#### *Fluxo alternativo II*

**Título:** Remover um usuário

**Descrição resumida:** O administrador de departamento pode remover os usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**ator:** Administrador de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do administrador que deseja atualizar o dado;

**Pós-condição:**
1. O usuário é removido do sistema.

**Sequência típica**
1. O usuário atorizado localiza um usuário;
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

#### Fluxo alternativo III*

**Título:** Recuperar um usuário

**Descrição resumida:** O sistema possui a funcionalidade de recuperar as informações de um usuário.

**ator:** Administrador de departamento.

**Pré-condição:**

Não se aplica

**Pós-condição:**
1. Informações sobre um usuário.

**Sequência típica**
1. O usuário  acessa a funcionalidade de recuperar usuário;
2. O sistema retorna a lista de usuários com opções de filtro;
3. O interessado seleciona o usuário;
4. O sistema retorna todas os detalhes do usuário;

**Exceções da Sequência alternativa:** recuperar usuário

Não se aplica

### ManLoc

**Título:** Manter localização 

#### *Fluxo principal*

**Título:** Cadastrar um local

**Descrição resumida:** O usuário deve poder realizar cadastros de locais onde os bens ficarão guardados. Geralmente um bem é guardado em uma sala que é vinculada à um departamento, prédio e endereço. Portanto ao cadastrar uma localização o usuário deve inserir:
* Sala;
* Prédio;
* Endereço.

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Uma nova localização é adicionado à base.

**Sequência típica**
1. O usuário atorizado entra na funcionalidade de cadastro de localização;
2. O sistema devolve a ficha de cadastro da localização;
3. O usuário digita todas as informações solicitadas pelo sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha de cadastro da localização;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento da nova localização diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar localização em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

#### *Fluxo alternativo I*

**Título:** Atualizar um local

**Descrição resumida:** O usuário deve poder realizar a atualização de localizações que sejam vinculadas ao departamento do qual é administrador. Ele deve observar que ao atualizar uma localização todos os bens que forem vinculados a ela também serão atualizados.

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

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
|7|Usuário desiste da operação.|O sistema volta para a ficha da localização.|3|

#### *Fluxo alternativo II*

**Título:** Remover um local

**Descrição resumida:** O usuário deve poder apagar uma localização previamente cadastrada. Contudo é se houver algum patrimônio ou departamento previamente vinculado a essa localização então a exclusão não será permitida.

**ator:** Administrador de departamento

**Pré-condição:**
1. Usuário deve ser administrador do departamento do qual a localização está vinculada;
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

#### Fluxo alternativo III*

**Título:** Recuperar uma localização

**Descrição resumida:** O sistema possui a funcionalidade de recuperar as informações de uma localização.

**ator:** Administrador de departamento.

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Informações sobre uma localização.

**Sequência típica**
1. O usuário  acessa a funcionalidade de recuperar localização;
2. O sistema retorna a lista de localizações com opções de filtro;
3. O interessado seleciona o localização;
4. O sistema retorna todas os detalhes do localização;

**Exceções da Sequência alternativa:** recuperar localização

Não se aplica.

### ManDep

**Título:** Manter departamento.

#### *Fluxo principal*

**Título:** Cadastrar um departamento

**Descrição resumida:** O sistema possui a funcionalidade de cadastrar um departamento

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Um novo departamento é adicionado à base.

**Sequência típica**
1. O usuário atorizado entra na funcionalidade de cadastro de departamento;
2. O sistema devolve a ficha de cadastro de departamento;
3. O usuário digita todas as informações solicitadas pelo sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do departamento recém cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|1|;
|4|Departamento já existe.|O sistema retorna ao passo 3 alerta o usuario.|2|;

#### *Fluxo alternativo I*

**Título:** Atualizar um departamento

**Descrição resumida:** O usuário pode realizar a operação de atualizar as informações sobre um departamento

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. O departamento selecionado tem seus dados atualizados.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa o departamento;
3. O sistema devolve a ficha com as informações do departamento selecionado;
4. O usuário entra na funcionalidade de edição de dados;
5. O sistema apresenta o formulário de edição dos dados do departamento;
6. O usuário preenche os dados;
7. O usuário confirma a operação;
8. Sistema devolve ficha do departamento com os dados atualizados.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|


#### *Fluxo alternativo II*

**Título:** Remover um departamento

**Descrição resumida:** O usuário pode apagar um departamento previamente cadastrada.

**ator:** Administrador de departamento

**Pré-condição:**
2. O departamento a ser removido não pode ter salas vinculadas.

**Pós-condição:**
1. O departamento será excluída da base de dados do sistema.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa o departamento que deseja buscar;
3. O sistema devolve a ficha com as informações do departamento;
4. O usuário aciona a funcionalidade de exclusão;
5. O sistema apresenta mensagem informando dos riscos do ato;
6. O usuário confirma a operação;
7. Sistema volta para tela inicial.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento possui salas vinculadas|O sistema alerta o usuário e não prossegue|1|
|6|Usuário não confirma a operação.|O sistema volta para a ficha da localização.|2|

#### Fluxo alternativo III*

**Título:** Recuperar um patrimônio

**Descrição resumida:** O sistema possui a funcionalidade de recuperar as informações de um departamento.

**ator:** Administrador de departamento.

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Informações sobre um departamento.

**Sequência típica**
1. O usuário  acessa a funcionalidade de recuperar departamento;
2. O sistema retorna a lista de departamentos com opções de filtro;
3. O usuário seleciona o departamento;
4. O sistema retorna todas os detalhes do departamento;

**Exceções da Sequência alternativa:** recuperar departamento

Não se aplica

### *VincSal*

**Título:** Vincular salas

**Descrição resumida:** O administrador de departamento deve ter a possibilidade de vincular uma sala previamente cadastrada ao departamento de que ele é administrador. Ele só poderá fazer isso se essa sala já não estiver vinculada a um outro departamento.

**ator:** Administrador de departamento

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

### *DesvSal*

**Título:** Desvincular salas

**Descrição resumida:** O administrador de departamento deve ter a possibilidade de desvincular uma sala previamente cadastrada ao departamento de que ele é administrador. Ele só poderá fazer isso se não houver patrimônio vinculado à sala ou ao departamento.

**ator:** Administrador de departamento

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

### *AceiMov*

**Título:** Aceite de entrada de bem movimentado

**Descrição resumida:** O administrador do departamento para onde o bem foi movimentado deve atorizar o recebimento do bem. Só quando essa ação for feita é que o local de lotação do bem é atualizado e o status da movimentação fica como *Finalizada*.

**ator:** Administrador de departamento

**Pré-condição:**
1. O bem deve ter sido movimentado para um departamento diferente da origem.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizada*;
2. O local onde o item é lotado é atualizado no sistema.

**Sequência típica**
1. O administrador de departamento entra na funcionalidade de *Aceite de entrada*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário aceita a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

### *CanMov*

**Título:** Cancelamento de movimentação

**Descrição resumida:** A movimentação pode ser recusada pelo administrador de departamento. Tanto movimentação de entrada, como movimentação de saída. Nesses casos a movimentação fica com o status de *Cancelada*.

**ator:** Administrador de departamento

**Pré-condição:**
1. Deve existir uma movimentação com status de *Aceite de saída* ou *Aceite de entrada* para o departamento do administrador logado no sistema

**Pós-condição:**
1. O bem não tem seu local atualizado;
2. A movimentação fica com o status de *Cancelada*.

**Sequência típica**
1. O administrador de departamento entra na funcionalidade de *Aceite de entrada* ou *Aceite de Saída*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário recusa a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

### *EmGuTransp*

**Título:** Emissão de guia para atorização de transporte

**Descrição resumida:** Quando uma movimentação é feita entre municípios diferentes o administrador de departamento deve emitir um guia para atorização de transporte do bem. Nesse guia deve conter:
* Cidade de origem;
* Cidade de destino;
* Informações do bem movimentado.

**ator:** Administrador de departamento

**Pré-condição:**
1. Deve haver uma movimentação cujo destino seja em cidade diferente da origem.

**Pós-condição:**
1. É emitido a guia de atorização.

**Sequência típica**
1. O administrador de departamento entra na funcionalidade de *Emissão de guia de transporte*;
2. O sistema apresenta a tela com uma lista de movimentações geradas;
3. Usuário deve informar qual a movimentação que originará a guia;
4. Será apresentada a guia com as informações da movimentação;
5. Usuário confirma as informações;
6. Sistema apresenta a guia pronta para ser impressa.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não ter nenhuma movimentação emissão da guia.|O sistema não irá listar nada.|2|
|3|Usuário informa uma movimentação que não tem origem e destino em cidades diferentes.|O sistema informará que a movimentação não exige guia de movimentação.|2|

### *EmRelPat*

**Título:** Emissão de relatório de bens patrimoniais

**Descrição resumida:** O sistema deve prover a funcionalidade de emissão de relatórios dos bens patrimoniais vinculados a um determinado departamento. O relatório deve conter as seguintes regras:
* Bens devem ser agrupados por sala;
* Bens devem ser ordenados por grupo de material e número de tombamento;

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. É emitido relatório com os bens patrimoniais do departamento.

**Sequência típica**
1. O administrador de departamento entra na funcionalidade de *Emissão de relatório patrimonial*;
2. O sistema apresenta a tela o relatório de todos os bens vinculados ao departamento do qual o usuário é administrador .
3. Usuário imprime o relatório.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não há nenhum bem vinculado ao departamento.|O sistema informa que não há insumos para o relatório.|1|

### *RegOS*

**Título:** Registro de ordem de serviço

**Descrição resumida:** O sistema deve prover a funcionalidade de registro de ordem de serviço quando um bem patrimonial precisa ser consertado. O status do da ordem de serviço fica *Em conserto*.

**ator:** Administrador de departamento

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Ordem de serviço é registrada no sistema;
2. A ordem de serviço fica com o status de *Em conserto*.

**Sequência típica**
1. Sistema apresenta tela inicial;
2. O usuário entra na funcionalidade registrar OS;
3. O sistema apresenta formulário com para preenchimento de OS.
4. Usuário informa todas as informações pertinentes, inclusive qual bem que estará indo para conserto;
5. Usuário confirma operação;
6. Sistema informa ao usuário que OS foi registrada com sucesso;
7. A Os fica com o status de *Em conserto*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|5|Usuário desiste da operação.|O sistema volta para a tela inicial.|1|

### *FechOS*

**Título:** Fechar ordem de serviço

**Descrição resumida:** Quando um bem patrimonial sai para conserto é aberto uma Os. Quando esse bem retorna o sistema deve prover a funcionalidade de fechar a OS aberta informando a data de retorno do bem e o valor do serviço. Nesse estado a OS fica com status de *Concluída*.

**ator:** Administrador de departamento

**Pré-condição:**
1. A Os precisa estar com o status de *Em conserto*.

**Pós-condição:**
1. Ordem de serviço fica com o status de *Concluída*.

**Sequência típica**
1. Sistema apresenta tela inicial;
2. O usuário localiza a OS que deseja finalizar;
3. O sistema apresenta a ficha da OS cadastrada;
4. Usuário entra na funcionalidade de fechar OS;
5. Sistema apresenta formulário para fechamento da OS com data de retorno do bem e valor total do serviço;
6. Usuário preenche os dados;
7. Usuário confirma a operação;
8. Sistema apresenta ficha de cadastro da OS com o status de *Concluída*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Os não está com o status de *Em conserto*.|Sistema informa que Os não encontra-se aberta.|1|
|7|Usuário desiste da operação.|O sistema volta para a tela inicial.|2|

## Funcionário

### RegMov 

**Título:** Registrar uma movimentação

**Descrição resumida:** Quando um bem patrimonial tem que ser movido, esta movimentação deve ser registrada, dependendo de quem a resgistrou o restultado pode ser diferente como mostra os fluxos alternativos.

**ator:** Funcionário.

**Pré-condição:**
1. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. A movimentação com o estado de Aceite de saída.;

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. Sistema apresenta ficha de bem com informação pertinente à movimentação recém cadastrada;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|3|O usuário não tem permissão para movimentar o bem|O sistema informa ao usuário que ele não tem permissão para realizar a ação|2|
|5|Usuário desiste da operação|O sistema cancela o registro da movimentação|4|

#### *Fluxo alternativo I*

**Título:** Movimentação de bem patrimonial externo

**Descrição resumida:** Neste caso o Administrador do departamento no qual o bem patrimonial está sendo retirado é o usuário que registra a movimentação

**ator:** Administrador de departamento

**Pré-condição:**
1. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. A movimentação em estado de Aceite de entrada no destino.

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. O sistema cria a movimentação e registra o aceite de saída automaticamente.
8. O sistema apresenta para o usuário a ficha de bem com informação pertinente à movimentação recém cadastrada.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|1|
|6|Usuário desiste da operação|O sistema cancela a criação da movimentação|2|


#### *Fluxo alternativo II*

**Título:** Movimentação de bem patrimonial interno

**Descrição resumida:** Movimentações entre salas do mesmo departamento não precisam do aceite do administrador do departamento, sendo marcadas imediatamente como *Finalizadas*.

**ator:** Administrador de departamento

**Pré-condição:**
1. O bem não pode estar com o status de baixa;
2. A movimentação deve ser entre salas do mesmo departamento.

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
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|1|
|3|O bem patrimonial esta com status de baixa.|O sistema informa ao usuário que o bem está com status de baixa.|2|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|3|

## Público

### *BusBemPat*

**Título:** Buscar bem patrimonial.

**Descrição resumida:** O sistema deve permitir que qualquer pessoa faça uma busca sobre os bens patrimoniais da organização, usando número de tombamento, denominação ou marca como critério de busca(filtro) e retornar um conjunto de bens que condizem com a busca.

**ator:** Qualquer pessoa interessada (não é necessário login)

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Um conjunto de bens patrimoniais resultantes da busca ordenados por denominação e data de aquisição.

**Sequência típica:**
1. A pessoa interessada informa um conjunto de critérios de busca (número de tombamento, denominação ou marca).
2. O sistema faz a busca utilizando os filtros informados.
3. O sistema retorna à pessoa o conjunto (ordenado) de bens patrimoniais que foram identificados.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Nenhum bem condiz aos critérios de busca utilizados|O sistema lançará uma exceção informando que não há bens cadastrados com estes critérios ou o usuário informou algo errado|1|

**Sequência alternativa: Conjunto de todos os bens**

A pessoa não informa nenhum critério de busca.
O sistema emite uma mensagem de confirmação avisando que nenhum critério foi definido então será retornado TODOS os bens patrimoniais.
A pessoa aceita.
O sistema retorna todos os bens.
Sequência alternativa: Pessoa esqueceu de informar os critérios.
A pessoa não informa nenhum critério de busca.
O sistema emite uma mensagem de confirmação avisando que nenhum critério foi definido então será retornado TODOS os bens patrimoniais.
A pessoa recusa.
Retorna para a sequência típica

### ConDadBemPat

**Título:** Consultar dados de um bem patrimonial.

**Descrição resumida:** O sistema deve que qualquer pessoa interessada veja os dados completos de um bem patrimonial.

**ator:** Qualquer pessoa interessada (não é necessário login)

**Pré-condição:**

Não se aplica.

**Pós-condição:**
1. Os dados completos (o número do tombamento, denominação, data de aquisição, número da nota fiscal, grupo de material, vida útil, especificação, garantia, marca, valor de compra, situação e localização) de um bem patrimonial.

**Sequência típica**
1. A pessoa interessada informa um conjunto de critérios de busca (número de tombamento, denominação ou marca).
2. O sistema faz a busca utilizando os filtros informados.
3. O sistema retorna à pessoa o conjunto (ordenado) de bens patrimoniais que foram identificados.
4. A pessoa seleciona o bem que tem interesse em saber os dados.
5. O sistema retorna os dados completos daquele bem que foi selecionado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Nenhum bem condiz aos critérios de busca utilizados|O sistema lançará uma exceção informando que não há bens cadastrados com estes critérios ou o usuário informou algo errado|1|
