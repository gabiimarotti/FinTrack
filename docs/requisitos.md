# FinTrack Requisitos da etapa 1

Data de atualização: 12 de outubro de 2026.

Este documento registra o que o FinTrack deve fazer antes de desenharmos as telas ou escrevermos a aplicação. As definições confirmadas pelo usuário estão separadas das propostas que ainda precisam ser revisadas. Ele complementa o plano de desenvolvimento.

**Dados para publicação:** por solicitação do usuário, o contrato usado neste documento é exclusivamente fictício. Valores e vencimentos pessoais foram substituídos. O exemplo não deve ser usado para cadastrar um contrato real; os dados reais serão informados somente no uso local da aplicação.

## 1 Definições confirmadas

- Título é um lançamento de conta a pagar ou receber. Investimentos são separados e não integram a primeira versão.
- O uso inicial será individual e em reais.
- O controle deve abranger salário, gastos pontuais, lanches, academia e assinaturas.
- A academia é um contrato com quantidade definida de parcelas, sem renovação automática presumida.
- O exemplo fictício de contrato da academia terá 12 parcelas mensais de R$ 150,00, com primeiro vencimento em 15/11/2026 e último em 15/10/2027. O total nominal é R$ 1.800,00.
- No cadastro de lançamento, o botão será “Salvar”. Após salvar, um pop-up perguntará se o usuário deseja confirmar o pagamento: “Sim” abre a tela de confirmação; “Não” mantém o título pendente. A quitação só ocorre na confirmação final, com data real informada.
- No parcelamento, o usuário informa o valor integral do título e a quantidade de parcelas. O sistema calcula os valores dividindo o total e gera as parcelas.
- O salário deve ser configurável como recorrência mensal, com geração automática de um título a cada mês.
- O salário não tem dia fixo de recebimento: normalmente entra entre o 1º e o 5º dia útil do mês. A data real será informada na confirmação do recebimento.
- A referência prevista do salário será o 5º dia útil. Contar de segunda a sábado, excluindo domingos e as datas de feriados cadastradas no calendário local.
- Permitir ajustar a data prevista de um lançamento individualmente, sem alterar a regra da recorrência ou os demais lançamentos.
- O valor de uma ocorrência recorrente deve poder ser alterado individualmente, sem obrigar a alteração das demais.
- Na etapa de organização das pastas, integrar o projeto a um repositório GitHub e usar versionamento durante o desenvolvimento, com explicação de cada operação.
- Preferência por repositório GitHub público, com código e exemplos fictícios; dados financeiros reais e credenciais permanecerão fora do repositório. Revisar também os dados pessoais presentes na documentação antes do primeiro envio.

## 2 Premissas adotadas do plano

A primeira versão será uma aplicação web local: navegador, servidor Java e MySQL no mesmo computador. Os cadastros serão manuais; a geração de ocorrências recorrentes será automática. O funcionamento sem internet dependerá de instalação prévia e recursos disponíveis localmente.

Adotaremos quitação integral, recorrência mensal, contas e categorias manuais. Pagamentos parciais, juros, multas, investimentos, cartões e faturas, integração bancária, múltiplos usuários e nuvem ficam fora do escopo inicial. Compras realizadas por cartão exigirão uma decisão posterior sobre como registrar a obrigação; o MVP não calculará faturas.

No exemplo exclusivamente fictício, a academia será tratada como contrato parcelado, com valor integral de R$ 1.800,00 dividido em 12 parcelas mensais de R$ 150,00, vencimento no dia 15, identificação de cada parcela e encerramento após a última. O primeiro vencimento será em 15/11/2026. Conforme solicitado pelo usuário, o cadastro receberá o valor integral e a quantidade de parcelas, e o sistema calculará e gerará as parcelas. Juros e reajustes automáticos continuam fora do escopo. Este recurso é separado da recorrência contínua de salário e assinaturas.

### Critérios propostos para o contrato da academia

1. Identificar os títulos como Academia 1 de 12, Academia 2 de 12 e assim por diante até Academia 12 de 12.
2. Cada parcela terá valor inicial de R$ 150,00 e vencimento no dia 15 de seu mês.
3. Os vencimentos serão mensais consecutivos, a partir do mês inicial informado.
4. Não criar uma parcela 13 nem renovar o contrato automaticamente.
5. Repetir uma geração não criará outra parcela com o mesmo contrato e número.
6. Cada parcela começa pendente e só afeta o saldo realizado quando seu pagamento é confirmado.
7. No cadastro do contrato, gerar todas as parcelas com os valores calculados; o total do contrato não será um lançamento financeiro adicional, evitando dupla contagem.
8. A soma das parcelas deve ser exatamente igual ao valor integral informado. Não gerar valores com mais de duas casas decimais.

**Divisão com resto aprovada:** calcular em centavos inteiros e distribuir um centavo adicional nas primeiras parcelas até consumir o resto. Exemplo: R$ 100,00 em 3 parcelas resulta em R$ 33,34, R$ 33,33 e R$ 33,33. A aprovação foi registrada após o usuário confirmar o fluxo e autorizar prosseguir. Validar valor total positivo com até duas casas decimais e quantidade inteira positiva; recusar uma divisão que produziria parcela de valor zero. Mostrar a prévia dos valores e vencimentos antes de salvar.

O total nominal de R$ 1.800,00 representa as 12 parcelas originalmente contratadas, não o total já pago. Para calcular o vencimento de uma parcela, somamos ao primeiro vencimento seu número menos um em meses. A parcela 12 vence 11 meses após a primeira.

### Calendário fictício do contrato da academia

| Parcela | Vencimento | Valor inicial |
| --- | --- | --- |
| 1 de 12 | 15/11/2026 | R$ 150,00 |
| 2 de 12 | 15/12/2026 | R$ 150,00 |
| 3 de 12 | 15/01/2027 | R$ 150,00 |
| 4 de 12 | 15/02/2027 | R$ 150,00 |
| 5 de 12 | 15/03/2027 | R$ 150,00 |
| 6 de 12 | 15/04/2027 | R$ 150,00 |
| 7 de 12 | 15/05/2027 | R$ 150,00 |
| 8 de 12 | 15/06/2027 | R$ 150,00 |
| 9 de 12 | 15/07/2027 | R$ 150,00 |
| 10 de 12 | 15/08/2027 | R$ 150,00 |
| 11 de 12 | 15/09/2027 | R$ 150,00 |
| 12 de 12 | 15/10/2027 | R$ 150,00 |

Esse calendário descreve os vencimentos esperados; não registra pagamentos nem significa que títulos já tenham sido criados na aplicação. No dashboard de compromissos de outubro de 2026, este contrato não terá parcela. Em novembro de 2026, a primeira parcela deverá aparecer como R$ 150,00 a pagar até que seu pagamento seja confirmado. O total do contrato não será descontado de uma vez do saldo realizado.

## 3 Vocabulário com exemplos

| Conceito | Significado no FinTrack | Exemplo |
| --- | --- | --- |
| Receita | Valor que esperamos receber ou já recebemos | Salário |
| Despesa | Valor que esperamos pagar ou já pagamos | Lanche, academia, assinatura |
| Título ou lançamento | Registro individual de uma receita ou despesa | Salário de outubro |
| Recorrência | Regra que cria lançamentos mensais | Salário mensal com recebimento previsto entre o 1º e o 5º dia útil |
| Ocorrência | Um lançamento criado por uma recorrência | Salário de novembro |
| Conta | Lugar em que o dinheiro entra ou sai | Conta corrente |
| Categoria | Classificação para análise | Salário, alimentação, saúde, assinaturas |
| Quitação | Confirmação integral de pagamento ou recebimento | Marcar salário como recebido |

“Recorrente” descreve a frequência, não obriga todos os meses a terem o mesmo valor. A regra guarda um valor padrão; cada ocorrência guarda seu próprio valor.

## 4 História HU01 Salário mensal automático

**História informada pelo usuário:** Como usuário, quero cadastrar o lançamento mensal do meu salário no sistema, para que todo mês esse título seja gerado automaticamente.

### Comportamento proposto

Você cadastra a recorrência uma vez, informando descrição, valor padrão, categoria de receita, conta, previsão de recebimento, mês inicial e mês final opcional. O sistema cria um lançamento pendente para cada mês devido. Receber o salário continua sendo uma confirmação separada. O valor de R$ 3.000,00 usado neste documento é fictício.

O salário será gerado no começo do mês de referência, antes do recebimento. Ao cadastrar uma regra para o mês atual, sua ocorrência será criada imediatamente. Regras futuras só começarão no mês inicial.

**Regra aprovada:** usar o 5º dia útil como data prevista de referência para o salário, mantendo a informação de que o recebimento pode ocorrer entre o 1º e o 5º dia útil. Essa previsão não obriga a data de recebimento: ao confirmar a entrada do dinheiro, você informa a data real. O salário só terá indicação de atraso se continuar pendente após a referência escolhida. Essa data não representa um prazo legal ou garantia de recebimento.

**Calendário aprovado:** contar de segunda a sábado, excluindo domingos e qualquer data cadastrada como feriado no calendário local. Um sábado cadastrado como feriado também será excluído. Um feriado que caia no domingo não exclui automaticamente a segunda-feira. O calendário não consulta nem importa feriados da internet: datas não cadastradas seguem a regra semanal.

Para encontrar a previsão, percorrer as datas a partir do primeiro dia do mês, contar somente as elegíveis e parar na quinta. A modelagem de datas do plano será revista para representar essa regra; um único campo de dia fixo do mês não a atende.

Exemplo hipotético: se um mês começar na segunda-feira e não houver feriados na primeira semana, o quinto dia útil será sexta-feira, dia 5. Se quarta-feira, dia 3, estiver cadastrada como feriado, a previsão passará para sábado, dia 6. Isso demonstra a regra do FinTrack, sem definir um calendário legal de pagamento.

### Critérios de aceitação

1. Ao cadastrar a recorrência “Salário”, com início no mês atual, o sistema cria a ocorrência desse mês como receita pendente, usando o quinto dia útil segundo o calendário aprovado.
2. No início do mês seguinte, com o servidor em execução, o sistema cria uma nova ocorrência e calcula a previsão daquele mês, sem presumir um dia fixo do calendário.
3. Reabrir ou reiniciar o sistema no mesmo mês não cria outra ocorrência para a mesma regra e mês.
4. Se o servidor ficar desligado durante a mudança do mês, a ocorrência faltante é criada ao iniciar novamente.
5. Se existirem vários meses faltantes, a proposta é recuperá-los desde o mês inicial até o atual, respeitando o mês final. O sistema informa quantos títulos foram criados; não marca os atrasados como recebidos.
6. A geração não aumenta o saldo realizado. O saldo só muda quando a receita é confirmada como recebida.
7. Para recorrências configuradas por dia fixo, se esse dia não existir no mês, usar o último dia, preservando o dia original na regra. Para o salário, contar segunda a sábado e excluir domingos e feriados locais cadastrados.
8. Se a recorrência começar em um mês futuro, não criar ocorrências anteriores a esse mês.
9. O mês final é inclusivo: regra com término em dezembro gera dezembro, mas não janeiro seguinte.
10. A verificação automática não altera ocorrências existentes, inclusive canceladas ou editadas. Uma ocorrência cancelada não é recriada automaticamente.
11. Ao confirmar o salário como recebido, registrar a data real, mesmo que diferente da previsão. Os indicadores realizados usarão a data real.
12. Um sábado sem feriado conta como dia útil; um sábado com feriado cadastrado não conta. Domingos nunca contam e datas elegíveis sem feriado cadastrado contam normalmente.

**Limite da execução local:** não há geração enquanto computador e servidor estão desligados. A recuperação ao iniciar mantém o controle mensal sem exigir um serviço em nuvem. Essa proposta operacional deve ser revisada com você antes da implementação.

## 5 História HU02 Alteração individual do valor

**História informada pelo usuário:** Como usuário, quero poder alterar o valor de um lançamento recorrente individualmente, para que isso não me limite.

### Comportamento proposto

Ao abrir uma ocorrência pendente e editar seu valor, a ação será apresentada como “Alterar somente este lançamento”. O valor padrão da recorrência permanecerá igual. A geração dos meses seguintes usará esse padrão, sem sobrescrever o mês editado.

### Critérios de aceitação

1. Dada uma recorrência de salário de R$ 3.000,00, alterar outubro para R$ 3.200,00 modifica apenas outubro.
2. A regra continua com valor padrão de R$ 3.000,00 e novembro continua com esse valor, já esteja gerado ou seja gerado depois.
3. Reiniciar o sistema ou executar outra verificação de recorrências preserva os R$ 3.200,00 de outubro.
4. O título editado mantém seu vínculo com a recorrência e o mês de referência, impedindo duplicatas.
5. O novo valor deve ser maior que zero e ter até duas casas decimais. Entrada inválida é recusada sem modificar o registro.
6. Enquanto o título permanecer pendente, a edição altera os totais previstos correspondentes, mas não o saldo realizado.
7. Para editar um título já pago ou recebido, a proposta inicial é reverter sua quitação, editar e confirmar novamente. Essa regra preserva a coerência entre o registro e os valores realizados.
8. Títulos cancelados não podem ter seu valor editado enquanto estiverem cancelados. Reativação será discutida se necessária.

**Exemplo:** uma assinatura de R$ 30,00 recebe desconto em outubro e custa R$ 20,00. Alterar a ocorrência de outubro não transforma o desconto em uma mudança permanente da assinatura.

Se o salário aumentar permanentemente, será outra ação: alterar o valor padrão da recorrência para os meses ainda não gerados. A edição individual não fará essa alteração implicitamente.

## 6 História HU03 Ajuste individual da previsão

**História derivada da regra aprovada pelo usuário:** Como usuário, quero ajustar a data prevista de um lançamento individualmente, para representar uma previsão diferente naquele mês sem alterar os demais.

### Critérios de aceitação

1. Ao editar a previsão de uma ocorrência pendente, alterar somente sua data prevista, mantendo o vínculo e o mês de referência da ocorrência.
2. Não alterar a regra nem as datas dos outros lançamentos; os próximos meses continuam usando o quinto dia útil para o salário.
3. Uma nova verificação automática não sobrescreve a data ajustada e não cria uma ocorrência duplicada.
4. A edição da previsão não confirma recebimento ou pagamento e não altera o saldo realizado.
5. A indicação de atraso e a listagem por vencimento usam a previsão ajustada. Se a nova data estiver em outro mês, o compromisso muda de mês nessa listagem, mas preserva seu mês de origem para evitar duplicatas.
6. A data real de recebimento continua sendo informada separadamente, na quitação.

**Proposta para mudanças no calendário:** feriados cadastrados ou corrigidos afetarão novos cálculos, preservando as datas de ocorrências já criadas. Se necessário, o usuário ajustará essas ocorrências individualmente. Essa política ainda será revisada antes da implementação.

## 7 Outros requisitos derivados das situações de uso

Estes requisitos são propostas baseadas nos exemplos e no escopo do plano; ainda não são novas histórias aprovadas individualmente.

| Código | Necessidade | Comportamento inicial proposto |
| --- | --- | --- |
| RF03 | Registrar gastos pontuais | Cadastrar despesa única, sem repetição automática |
| RF04 | Registrar lanches | Cadastrar cada gasto separadamente na categoria Alimentação |
| RF05 | Controlar academia | Cadastrar valor integral, quantidade de parcelas mensais e primeiro vencimento; calcular e gerar parcelas, identificá-las e encerrar após a última |
| RF06 | Controlar assinaturas | Criar uma recorrência mensal por assinatura e permitir encerramento |
| RF07 | Confirmar pagamentos e recebimentos | Registrar quitação integral e data; permitir reversão |
| RF08 | Consultar títulos do mês | Filtrar por vencimento, tipo, situação, conta e categoria |
| RF09 | Analisar o mês | Separar previsto, realizado, pendências e saldo |
| RF10 | Preservar os dados | Persistir no MySQL e testar backup e restauração antes do uso real |
| RF11 | Manter calendário local | Cadastrar feriados por data e descrição e usá-los no cálculo do quinto dia útil do salário |

### Versionamento integrado ao aprendizado

Na etapa de organização das pastas, explicar Git como histórico local e GitHub como repositório remoto. Conferir se já existe um repositório, preparar o .gitignore, revisar os arquivos, registrar o primeiro commit e conectar ao repositório escolhido. O endereço e a visibilidade do repositório serão definidos nessa etapa, antes do primeiro envio.

Versionar código, documentação, scripts de estrutura do banco e dados fictícios. Credenciais, dados financeiros reais, backups, diretório ativo do MySQL e arquivos gerados não serão enviados. Cada incremento poderá ter um commit pequeno após revisão, para acompanhar o aprendizado. Esta solicitação é para integrar o fluxo na etapa indicada; nenhum repositório foi criado ou publicado agora.

**Fluxo solicitado para salvar:** validar e salvar o lançamento como pendente, então apresentar o pop-up “Deseja confirmar o pagamento?”. “Sim, confirmar pagamento” encaminha à tela de confirmação da despesa, onde será informada a data real e confirmada a quitação integral. “Não, manter pendente” conclui o cadastro sem quitação. Fechar o pop-up ou voltar da tela de confirmação também mantém o lançamento salvo como pendente; não repetir o cadastro ao navegar. Para receitas, propomos o mesmo fluxo com o texto “recebimento”. No contrato parcelado, a geração não quita parcelas; qualquer confirmação se refere a uma parcela individual, nunca automaticamente ao total do contrato.

## 8 Mês fictício para conferir as regras

Valores exclusivamente didáticos. Referência da análise: 12 de outubro de 2026. Conta única: Conta corrente, com saldo inicial de R$ 1.000,00 imediatamente antes dos movimentos de 01/10/2026.

O contrato fictício da academia começa em novembro de 2026 e, portanto, não participa dos compromissos de outubro. Os demais valores abaixo continuam exclusivamente fictícios.

| Lançamento | Tipo e frequência | Vencimento | Situação e liquidação | Valor |
| --- | --- | --- | --- | --- |
| Salário de outubro | Receita recorrente | 05/10/2026 | Recebido em 05/10/2026 | R$ 3.000,00 |
| Compra pontual | Despesa única | 06/10/2026 | Pago em 06/10/2026 | R$ 180,00 |
| Lanche 1 | Despesa única | 07/10/2026 | Pago em 07/10/2026 | R$ 25,00 |
| Assinatura A | Despesa recorrente | 08/10/2026 | Pago em 08/10/2026 | R$ 30,00 |
| Lanche 2 | Despesa única | 10/10/2026 | Pago em 10/10/2026 | R$ 35,00 |
| Assinatura B | Despesa recorrente | 15/10/2026 | Pendente | R$ 20,00 |

### Conferência manual

- Receitas recebidas: **R$ 3.000,00**.
- Despesas pagas: 180 + 25 + 30 + 35 = **R$ 270,00**.
- A pagar no mês: somente a assinatura B, **R$ 20,00**.
- A receber no mês: **R$ 0,00**, pois o salário já foi recebido.
- Resultado realizado: 3.000 − 270 = **R$ 2.730,00**.
- Resultado mensal previsto atualizado: 2.730 + 0 − 20 = **R$ 2.710,00**.
- Saldo realizado da conta: 1.000 + 3.000 − 270 = **R$ 3.730,00**.

O resultado realizado mostra quanto entrou menos quanto saiu no mês. O saldo também inclui o dinheiro que já existia antes. Pendências ainda não são saídas realizadas.

### Exercício de compreensão

Vamos usar uma assinatura para separar este exercício das regras de parcelamento. Imagine uma assinatura de R$ 30,00 por mês. A cobrança de outubro ainda não foi paga, mas você recebe um desconto e altera somente outubro para R$ 20,00.

Antes da edição, há R$ 30,00 a pagar. Depois, há R$ 20,00 a pagar. O dinheiro disponível na conta não muda nessa edição, pois nenhum pagamento ocorreu. Quando você pagar os R$ 20,00 e confirmar o pagamento, o saldo diminuirá em R$ 20,00.

A cobrança de novembro continuará em R$ 30,00, porque o desconto foi aplicado apenas ao lançamento de outubro. Essa é a diferença entre alterar uma ocorrência e alterar o valor padrão da recorrência. Este exemplo usa valores fictícios e não modifica o mês da tabela anterior.

## 9 Decisões para a próxima revisão

1. Revisar os critérios restantes do contrato da academia. O cadastro por valor integral e quantidade e a distribuição do resto nas primeiras parcelas estão aprovados; o calendário continua de 15/11/2026 a 15/10/2027, em 12 parcelas de R$ 150,00, calculadas a partir de R$ 1.800,00.
2. Revisar a proposta de geração automática no começo do mês e recuperação de meses faltantes ao iniciar.
3. Revisar a regra de reversão de quitação antes de editar um título realizado.
4. Conferir o mês fictício e explicar o efeito da edição individual no exercício.
5. Revisar o efeito de alterações no calendário sobre ocorrências já criadas. A contagem de segunda a sábado, excluindo domingos e feriados cadastrados, e o ajuste individual de previsão estão aprovados. O valor e o mês inicial da recorrência ainda serão definidos; os valores e datas de recebimento do exercício são fictícios.

As três histórias principais estão registradas. Em 12/10/2026, o usuário informou que revisou o primeiro mês fictício e deseja prosseguir. A revisão do exemplo foi registrada; as demais propostas acima não foram consideradas aprovadas implicitamente.

Iniciamos os [rascunhos de telas e navegação](telas-e-navegacao.md) como próximo passo do plano, mantendo as decisões pendentes identificadas para revisão antes da implementação. A implementação da aplicação ainda não começou.
