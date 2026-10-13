# FinTrack — Rascunhos de telas e navegação

Data: 12 de outubro de 2026.

Todos os valores e vencimentos usados nos exemplos destas telas são fictícios, incluindo o contrato da academia. Os dados do contrato pessoal foram substituídos para preparar a publicação do projeto.

Etapa 2 do [plano de desenvolvimento](FinTrack_Plano_de_Desenvolvimento.md), baseada nos [requisitos](requisitos.md). O usuário informou que revisou o primeiro mês fictício e deseja avançar. Estes rascunhos são propostas para revisão; não representam uma aplicação funcionando nem aprovação das regras ainda pendentes.

## 1 Objetivo desta etapa

Conseguir simular o cadastro, a consulta e a quitação de uma despesa antes de programar. Cada campo precisa ter uma finalidade e cada ação deve deixar claro seu efeito nos valores previstos e realizados.

A navegação principal terá: **Painel · Lançamentos · Recorrências · Contratos · Cadastros**. Em Cadastros ficarão Contas, Categorias e Feriados. Backup e restauração serão detalhados antes do uso real.

O mês selecionado será preservado entre Painel e Lançamentos. Alterar o mês apenas consulta os registros; não cria ocorrências.

## 2 Painel mensal

Rascunho com os dados fictícios de outubro, consultados em 12/10/2026:

```text
FinTrack     Painel | Lançamentos | Recorrências | Contratos | Cadastros

Painel
[ < ] [ Outubro de 2026 v ] [ > ]
Conta: [Conta corrente v]
[+ Lançamento]

REALIZADO NO MÊS — pela data de pagamento ou recebimento
[Receitas recebidas R$ 3.000,00]
[Despesas pagas R$ 270,00]
[Resultado realizado R$ 2.730,00]

PENDENTE NO MÊS — pela data prevista / vencimento
[A receber R$ 0,00]
[A pagar R$ 20,00]
Resultado mensal previsto atualizado: R$ 2.710,00

CONTA CORRENTE — posição em 12/10/2026
Saldo realizado: R$ 3.730,00
Inclui o saldo inicial e os movimentos confirmados até esta data.

Próximos vencimentos
15/10  Assinatura B      R$ 20,00      Pendente   [Abrir]

Pendências anteriores ao mês
Nenhuma pendência anterior nos dados deste exemplo.

Despesas pagas por categoria
[Ver lançamentos]
Alimentação    R$ 60,00
Assinaturas    R$ 30,00
Compras        R$ 180,00
```

“Compras” é uma categoria sugerida para a compra pontual do exercício; seu nome ainda pode ser ajustado. As categorias do exemplo não são cadastros reais.

- Cada total abre uma lista com os registros que o compõem e explica o filtro aplicado. “Despesas pagas” usa liquidação; “A pagar” usa vencimento e situação pendente.
- O resultado previsto usa os registros existentes: resultado realizado + a receber − a pagar. Não é o saldo da conta.
- O saldo mostra uma data explícita. Proposta: para o mês atual, usar hoje; para meses passados, o último dia do mês. Para mês futuro, mostrar a posição de hoje com essa identificação, sem apresentá-la como projeção.
- Pendências anteriores aparecem separadas por receitas e despesas, sem se misturar aos compromissos do mês.
- Se houver ocorrências mensais ainda não geradas, mostrar um aviso com os meses afetados. A consulta não executa a geração.
- A academia não aparece em outubro. Em novembro, sua primeira parcela aparecerá como R$ 150,00 pendentes, após a geração conforme a política de contratos a definir.

## 3 Listagem de lançamentos

```text
Lançamentos                   [Outubro de 2026 v] [+ Lançamento]
Período por: [Vencimento v]
Tipo: [Todos v]  Situação: [Todas v]  Conta: [Todas v]
Categoria: [Todas v]                        [Limpar filtros]

Descrição             Vencimento  Liquidação  Valor         Situação
Salário de outubro    05/10/2026  05/10/2026  R$ 3.000,00   Recebido [Abrir]
Compra pontual        06/10/2026  06/10/2026  R$   180,00   Pago     [Abrir]
Lanche 1              07/10/2026  07/10/2026  R$    25,00   Pago     [Abrir]
Assinatura A          08/10/2026  08/10/2026  R$    30,00   Pago     [Abrir]
Lanche 2              10/10/2026  10/10/2026  R$    35,00   Pago     [Abrir]
Assinatura B          15/10/2026  —           R$    20,00   Pendente [Abrir]
```

O filtro padrão usa vencimento. Para permitir abrir os totais realizados do painel, propomos também a opção “Pagamento / recebimento”. Nesse modo, entram apenas lançamentos quitados cuja liquidação pertence ao mês. O modo e os filtros ficam visíveis; “Limpar filtros” retorna à consulta padrão por vencimento.

Uma pendência vencida antes de hoje recebe o texto “Pendente — atrasado”. Vencimento hoje não é atraso. Cor apenas complementa o texto. Cancelados aparecem quando consultados, mas ficam fora dos indicadores.

## 4 Cadastro de lançamento único

```text
Novo lançamento
Tipo*              ( ) Receita  (x) Despesa
Descrição*         [Lanche                            ]
Valor (R$)*        [25,00                             ]
Vencimento*        [13/10/2026                        ]
Conta*             [Conta corrente                   v]
Categoria*         [Alimentação                      v]

Após salvar, você poderá seguir para a confirmação do pagamento.
                                      [Cancelar] [Salvar]
```

| Campo | Finalidade e validação |
| --- | --- |
| Tipo | Define entrada ou saída; obrigatório |
| Descrição | Identifica o compromisso; texto obrigatório após remover espaços externos |
| Valor | Valor positivo, com até duas casas decimais; aceitar a apresentação brasileira |
| Vencimento | Data prevista obrigatória; para salário, o rótulo será “Previsão de recebimento” |
| Conta | Indica onde ocorrerá o movimento; conta ativa obrigatória |
| Categoria | Classifica o movimento; categoria ativa compatível com o tipo |

Trocar o tipo limpa uma categoria incompatível e pede nova seleção. Sem conta ou categoria cadastrada, oferecer acesso ao cadastro correspondente e preservar os campos já preenchidos ao voltar.

**Fluxo solicitado:** ao clicar em “Salvar”, validar os dados e salvar o lançamento como pendente. Após o sucesso, abrir o pop-up:

```text
Lançamento salvo
Deseja confirmar o pagamento?

[Não, manter pendente]    [Sim, confirmar pagamento]
```

- **Sim:** encaminhar à tela “Confirmar pagamento”, com o lançamento salvo e seus dados. Informar a data real e clicar no botão final é o que efetiva a quitação.
- **Não:** concluir o cadastro e abrir o detalhe com situação pendente.
- Fechar o pop-up ou voltar da confirmação mantém o registro salvo como pendente. Mostrar essa informação e não criar outro lançamento ao navegar.
- Se o cadastro falhar, apresentar os erros sem abrir o pop-up.

Para receita, propomos trocar os textos por “Deseja confirmar o recebimento?” e abrir a confirmação de recebimento. O saldo realizado só muda após a confirmação final.

## 5 Detalhe, edição e quitação

O detalhe mostra descrição, tipo, valor, conta, categoria, vencimento, situação e liquidação, quando houver. Para ocorrências, mostra também a recorrência e o mês de referência; para parcelas, o contrato e o número da parcela.

| Situação | Ações propostas |
| --- | --- |
| Pendente | Editar, confirmar pagamento/recebimento, cancelar lançamento |
| Quitado | Consultar e reverter quitação; editar só após reversão, conforme proposta pendente |
| Cancelado | Consultar; edição e reativação não previstas nesta versão |

Na edição de uma ocorrência, usar o título **“Alterar somente este lançamento”** e a mensagem: “Esta alteração não modifica a recorrência nem os outros lançamentos.” Valor e previsão individuais permanecem preservados em verificações automáticas futuras. O mês de referência aparece para consulta, sem edição.

Confirmação de uma despesa:

```text
Confirmar pagamento
Assinatura B — R$ 20,00
Conta: Conta corrente
Data do pagamento* [12/10/2026]

O pagamento integral reduz o saldo realizado em R$ 20,00.
O valor passa a integrar as despesas pagas do mês da data informada.
                               [Voltar] [Confirmar pagamento]
```

A data inicial sugerida é hoje e pode ser ajustada para uma data real anterior, respeitando a referência inicial da conta. Data futura é recusada. Para receita, trocar os textos por recebimento e explicar o aumento do saldo. Uma quitação repetida não produz novo efeito.

Na reversão, explicar: “A confirmação será desfeita e o lançamento voltará a pendente. O valor deixará os realizados da data registrada.” A reversão corrige o registro no FinTrack; não representa uma devolução de dinheiro. O cancelamento também pede confirmação, preserva o registro e o retira dos totais.

## 6 Recorrências e contratos

### Recorrência mensal

Campos: descrição, tipo, valor padrão, conta, categoria, mês inicial, mês final opcional e regra de previsão.

Regras de previsão propostas na tela:

- **Dia fixo:** informar de 1 a 31; explicar o uso do último dia quando o mês não tiver o dia escolhido.
- **Quinto dia útil:** informar que contam segunda a sábado, exceto domingos e feriados cadastrados; usada para o salário.

Antes de salvar, mostrar o período que poderá ser gerado e lembrar que todos os lançamentos começam pendentes. Início em mês futuro não cria ocorrências antecipadas. Os valores e o mês inicial do salário ainda serão informados; não preencher o cadastro real com os R$ 3.000,00 do exercício.

A lista mostra regras ativas e encerradas, período e valor padrão. O detalhe permite consultar ocorrências, alterar a regra para meses ainda não gerados ou encerrar a recorrência. Explicar que alterações da regra não sobrescrevem ocorrências existentes.

A geração na inicialização e a recuperação de meses faltantes continuam como propostas em revisão. Quando definidas, o resultado informará a quantidade criada, sem marcar nada como quitado.

### Contrato parcelado

Campos: descrição, conta, categoria de despesa, **valor integral do título**, **quantidade de parcelas** e primeiro vencimento. O valor de cada parcela será calculado pelo sistema. Antes de salvar, mostrar a prévia com número, valor e vencimento de cada parcela e a soma total.

Exemplo da academia: informar R$ 1.800,00 e 12 parcelas. O sistema calcula 12 parcelas de R$ 150,00, de 15/11/2026 a 15/10/2027. Identificar “Academia 1 de 12” até “Academia 12 de 12”; sem renovação automática.

Ao salvar o contrato, gerar todas as parcelas como pendentes, sem criar um lançamento adicional pelo valor integral. O total nominal não será descontado do saldo. A prévia do calendário não significa que os títulos já existem; a geração só acontece ao salvar com sucesso. Repetir a operação não pode duplicar o contrato nem suas parcelas.

**Distribuição de centavos aprovada:** distribuir o resto da divisão nas primeiras parcelas. R$ 100,00 em 3 parcelas gera R$ 33,34 + R$ 33,33 + R$ 33,33, preservando o total. Recusar total inválido, quantidade não inteira ou não positiva e divisão que produziria uma parcela de valor zero.

A quitação é individual por parcela. Ao abrir uma parcela e confirmar seu pagamento, usar seu valor na tela de confirmação; não apresentar o total do contrato como valor a quitar.

## 7 Cadastros auxiliares

| Tela | Campos propostos | Consulta e ações |
| --- | --- | --- |
| Contas | Nome, saldo inicial, data de referência | Ver saldo e referência; cadastrar e arquivar |
| Categorias | Nome e tipo receita/despesa | Listar por tipo; cadastrar e arquivar |
| Feriados | Data e descrição | Listar por ano; cadastrar, corrigir e excluir |

O saldo inicial representa a posição imediatamente anterior aos movimentos da data de referência. Neste rascunho, não haverá edição dessa posição após existirem lançamentos vinculados; ajustes de histórico exigirão desenho próprio.

Arquivar uma conta ou categoria preserva o histórico e impede novos vínculos. O tratamento de recorrências ativas vinculadas a cadastros arquivados precisa ser definido antes da implementação; a tela deverá apresentar as dependências para revisão.

Na manutenção de feriados, sinalizar a proposta ainda pendente: alterações afetam novos cálculos e preservam previsões de ocorrências existentes, que podem ser ajustadas individualmente.

## 8 Estados das telas e acessibilidade

| Estado | Comportamento proposto |
| --- | --- |
| Sem registros | “Nenhum lançamento neste período” e acesso a novo lançamento |
| Filtro sem resultados | Explicar que não há resultados para os filtros e oferecer limpeza |
| Carregando | Texto de carregamento; impedir envio repetido enquanto salva |
| Campo inválido | Mensagem junto ao campo, resumo de erros e foco no primeiro campo inválido |
| Falha de consulta | “Não foi possível carregar os dados. Verifique se o servidor está em execução.” e “Tentar novamente” |
| Falha ao salvar | Preservar os campos; se a resposta se perder, consultar o resultado antes de orientar outro envio |
| Sucesso | Informar o que foi salvo ou confirmado e atualizar os dados exibidos |

Todos os campos terão rótulos permanentes. A navegação funcionará por teclado, com foco visível e ordem lógica. Janelas de confirmação manterão o foco em seus controles e devolverão o foco ao botão de origem ao fechar. Em tela menor, filtros ficam empilhados e lançamentos aparecem em cartões com os mesmos dados e ações da tabela.

## 9 Simulação de uso para revisão

Use uma cópia imaginária do mês fictício, sem modificar sua tabela de referência:

1. Abra Lançamentos em outubro e escolha “Novo lançamento”.
2. Cadastre uma despesa “Lanche de teste”, de R$ 25,00, vencimento em 13/10/2026, Conta corrente e Alimentação.
3. Clique em “Salvar”. No pop-up, escolha “Sim, confirmar pagamento”. O lançamento já está salvo como pendente: a pagar passa de R$ 20,00 para R$ 45,00; o saldo realizado permanece R$ 3.730,00. O resultado previsto passa para R$ 2.685,00.
4. Na tela de confirmação, informe a data real de 12/10/2026 e confirme o pagamento. As despesas pagas passam a R$ 295,00; o resultado realizado, a R$ 2.705,00; o saldo realizado, a R$ 3.705,00. O total a pagar volta a R$ 20,00 e o resultado previsto permanece R$ 2.685,00.
5. Confira que o vencimento continua em 13/10 e a liquidação ficou em 12/10. Não é preciso mudar o vencimento para confirmar o pagamento.
6. Experimente informar valor zero no formulário: a tela deve explicar o erro e manter os outros campos.
7. Em uma simulação separada, escolha “Não, manter pendente” no pop-up: o título deve permanecer pendente, sem mudar o saldo. Confira também o mesmo comportamento ao fechar o pop-up ou voltar da confirmação.
8. Simule o contrato da academia com total de R$ 1.800,00 e 12 parcelas; confira a prévia de R$ 150,00 por parcela e a geração sem quitação. Para revisar o tratamento de centavos, simule também R$ 100,00 em 3 parcelas.

Prática: explicar em qual ação o saldo mudou e por que o resultado previsto permaneceu igual entre os passos 3 e 4.

## 10 Pontos para encerrar a revisão

- Revisar se o fluxo de cadastro e quitação está claro e se os campos são suficientes.
- Revisar o filtro por liquidação e a data de consulta do saldo propostos neste documento.
- Concluir as decisões restantes da seção 9 dos requisitos: contrato da academia, geração e recuperação mensal, reversão antes da edição e efeito de mudanças em feriados.
- Revisar o tratamento de cadastros arquivados com recorrências ativas. A distribuição de centavos foi aprovada.

Depois de revisar estes rascunhos e simular o fluxo, avançaremos à preparação do ambiente e organização das pastas. A integração com Git e GitHub pertence a essa etapa seguinte, com explicação das operações e definição do repositório antes do primeiro envio.

O usuário autorizou prosseguir após a revisão do fluxo de salvamento e do parcelamento. Iniciamos a conferência do ambiente, registrada em [ambiente de desenvolvimento](ambiente-de-desenvolvimento.md). Essa conferência não encerra automaticamente a revisão das demais propostas nem comprova a prática de aprendizado acima.
