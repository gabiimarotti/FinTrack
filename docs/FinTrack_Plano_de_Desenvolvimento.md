# FinTrack Plano de desenvolvimento e aprendizado

Guia para construir um gerenciador financeiro pessoal local com HTML, CSS, JavaScript, Java e MySQL.

Data: 12 de outubro de 2026.

Os valores e vencimentos dos exemplos financeiros são fictícios. Os dados do contrato pessoal foram substituídos na documentação por solicitação do usuário, mantendo as regras de parcelamento.

## 1 Objetivo e primeiros passos

O FinTrack permitirá organizar receitas, despesas e títulos a pagar ou receber, acompanhar compromissos únicos e recorrentes e analisar a situação financeira de cada mês. O projeto também será um exercício de desenvolvimento fullstack: você acompanhará o caminho de uma informação desde o formulário até o banco de dados, entendendo cada responsabilidade.

A primeira entrega deve ser pequena, mas utilizável: cadastrar uma receita e uma despesa, salvá-las no MySQL, consultar o mês e visualizar os totais. Em seguida, acrescentaremos quitação, recorrências e projeções até completar a primeira versão.

Este documento é uma proposta de escopo e sequência. As tecnologias adicionais e regras sugeridas deverão ser discutidas antes da implementação. O roteiro não pressupõe que você já conheça Spring Boot, APIs ou modelagem relacional.

Começaremos nesta ordem:

1. Descrever um mês fictício com salário, aluguel, mercado, uma receita extra e um título pendente.
2. Confirmar o significado de título, as regras de datas e o que o dashboard deverá mostrar.
3. Selecionar as funcionalidades da primeira versão.
4. Desenhar as telas em papel e identificar os dados necessários.
5. Conferir o ambiente de desenvolvimento e estudar o fluxo entre navegador, Java e MySQL.
6. Construir uma pequena funcionalidade de ponta a ponta, com explicação e exercício em cada parte.

Não é necessário instalar ferramentas ou escrever a aplicação antes de resolver os quatro primeiros itens.

## 2 Como vamos aprender durante o desenvolvimento

**Preferência atual do usuário:** já conhece o básico das tecnologias e deseja aprofundar os conceitos durante a implementação do FinTrack. Atividades isoladas de introdução, como imprimir mensagens ou criar uma página sem relação com o produto, deixam de ser pré-requisitos. A conferência das ferramentas será feita ao executar os próprios incrementos do projeto. Java, SQL, HTML, CSS e JavaScript serão explicados conforme a necessidade de cada funcionalidade.

Cada incremento seguirá o mesmo ciclo:

1. **Explicar o objetivo.** Qual problema do usuário será resolvido e qual conceito será praticado?
2. **Apresentar a lógica.** Usar um exemplo concreto, desenhar o fluxo e mostrar os arquivos que serão envolvidos.
3. **Combinar a implementação.** Você poderá explicar o que entendeu e ajustar a proposta antes de alterarmos o código.
4. **Praticar em pequenos passos.** Você escreve uma parte; eu explico, reviso ou ajudo a encontrar o erro. Quando eu escrever um trecho, explicarei as decisões e seu efeito.
5. **Verificar o resultado.** Executar um cenário correto e um cenário de erro, observando tela, requisição, Java e banco quando aplicável.
6. **Registrar o aprendizado.** Anotar a decisão e fazer uma pequena alteração sem copiar a solução.

Avançaremos quando você conseguir explicar o caminho do dado, a responsabilidade dos arquivos alterados e a regra implementada. Uma funcionalidade funcionando por tentativa e erro não encerra o aprendizado.

Ao final de cada etapa, haverá uma entrega revisável. Alterações de escopo, bibliotecas, migrações e comandos que modifiquem dados serão apresentados antes da execução. Usaremos dados fictícios enquanto as regras e a recuperação dos dados estiverem sendo validadas.

## 3 Referências de produtos semelhantes

A comparação abaixo usa páginas oficiais consultadas em 12 de outubro de 2026. É uma análise de funcionalidades documentadas, sem teste prático dos produtos nem comparação de preços. Os recursos disponíveis podem variar por plano e versão.

| Referência | Recursos observados | Aprendizado para o FinTrack |
| --- | --- | --- |
| Organizze | Categorização, acompanhamento de receitas, gráficos e comparação de períodos [1] | Tornar rápido o cadastro e permitir entender os gastos por categoria e mês. |
| Mobills | Contas, receitas e despesas, recorrências, orçamento por categoria, metas e cartões [2] | Separar compromissos de movimentações realizadas e acrescentar limites de gastos após o núcleo funcionar. |
| Actual Budget | Operação local, relatórios, importação de arquivos e agendamentos únicos ou recorrentes [3][4] | Priorizar autonomia dos dados, previsibilidade e regras explícitas para recorrências. |

As sugestões seguintes são decisões de produto para o FinTrack, inspiradas nessas referências. Não são afirmações de que todos esses sistemas funcionam da mesma forma.

### Funcionalidades que agregam valor

| Funcionalidade | Valor para o usuário | Prioridade e esforço relativo |
| --- | --- | --- |
| Lançamentos por mês com filtros | Localizar rapidamente receitas, despesas e pendências | Primeira versão; médio |
| Quitação e indicadores de atraso | Saber o que realmente entrou ou saiu e o que ainda exige ação | Primeira versão; médio |
| Recorrência mensal | Evitar repetir salário, aluguel e assinaturas manualmente | Primeira versão; alto |
| Previsto e realizado separados | Evitar confundir dinheiro esperado com disponível | Primeira versão; médio |
| Categorias e contas | Entender a origem, o destino e a localização do dinheiro | Primeira versão; médio |
| Backup e restauração | Recuperar os dados após falha ou erro | Antes do uso com dados reais; médio |
| Orçamento mensal por categoria | Comparar gastos com um limite definido pelo usuário | Evolução seguinte; médio |
| Contrato com parcelas mensais definidas | Acompanhar a academia até a última parcela | Primeira versão em formato simplificado; alto |
| Transferências entre contas | Movimentar dinheiro sem inflar receitas e despesas | Evolução seguinte; médio |
| Importação CSV com prévia e detecção de duplicatas | Reduzir digitação sem perder controle | Evolução seguinte; alto |
| Metas e projeção dos próximos meses | Planejar reservas e antecipar meses apertados | Evolução seguinte; médio a alto |
| Cartões e faturas | Organizar fechamento, vencimento e parcelas | Avançada; alto |

Os esforços são comparativos, não estimativas de prazo. Cartões ficam para depois porque compras, faturas e pagamentos podem gerar dupla contagem se a modelagem estiver incorreta.

Uma boa característica do FinTrack será permitir abrir os lançamentos que compõem cada indicador. Assim, o dashboard será verificável: ao clicar em “despesas pagas”, você verá os registros que formaram aquele total.

## 4 Escopo da primeira versão

### Premissas do projeto

- Uso pessoal por uma pessoa, em um computador, com moeda BRL.
- Aplicação web acessada pelo navegador, com servidor Java e MySQL na mesma máquina.
- Cadastro manual de dados e funcionamento sem internet depois da instalação das dependências e recursos locais.
- Conforme confirmado na etapa 1, “título” significa um lançamento de conta a pagar ou receber. Investimentos ficam separados, fora da primeira versão.
- Liquidação integral de cada lançamento. Pagamentos parciais, juros, multas e estornos ficam para uma evolução planejada.
- Recorrência mensal inicialmente; outras frequências serão acrescentadas depois.
- Academia como contrato com quantidade definida de parcelas. Conforme solicitado, informar valor integral, quantidade de parcelas e primeiro vencimento; o sistema divide o total e gera as parcelas. A soma deve preservar o valor integral; foi aprovada a distribuição do resto em centavos nas primeiras parcelas. Juros e reajustes automáticos ficam para depois.
- O salário costuma ser recebido entre o 1º e o 5º dia útil. A previsão aprovada é o quinto dia útil, contando segunda a sábado e excluindo domingos e feriados cadastrados no calendário local. Cada ocorrência pode ter sua previsão ajustada individualmente. A data efetiva será registrada ao confirmar o recebimento.
- Integrar Git e GitHub ao desenvolvimento na etapa de organização das pastas, conforme solicitado, explicando as operações antes de executá-las.

### O que precisa existir

1. Cadastro e arquivamento de categorias de receita e despesa.
2. Cadastro de contas manuais, como conta corrente e carteira, com saldo inicial e data de referência.
3. Cadastro, consulta, edição e cancelamento de lançamentos únicos.
4. Registro e reversão de quitação integral, com data de pagamento ou recebimento.
   No cadastro de despesa, “Salvar” abre um pop-up perguntando se deseja confirmar o pagamento. “Sim” encaminha à tela de confirmação com data real; “Não” mantém o lançamento pendente. O saldo só muda na confirmação final. Para receitas, o fluxo equivalente de recebimento está proposto nos requisitos.
5. Geração automática de lançamentos recorrentes mensais, com prevenção de duplicatas e edição individual de valores.
6. Listagem por mês de vencimento, com filtros por tipo, categoria, conta e situação.
7. Dashboard mensal com receitas recebidas, despesas pagas, resultado, valores pendentes e gastos por categoria.
8. Procedimento documentado e testado de backup e restauração do banco.
9. Controle de contrato com parcelas mensais em quantidade definida, incluindo identificação e encerramento após a última parcela.
10. Calendário local de feriados para calcular a previsão do salário e ajuste individual da data prevista sem modificar a recorrência.

Login, múltiplos usuários, integração bancária, nuvem, aplicativo móvel e inteligência artificial ficam fora desta primeira versão. O acesso inicial ficará restrito à própria máquina. Se o acesso pela rede for desejado, autenticação e proteção do serviço entrarão no escopo antes disso.

## 5 Regras financeiras que precisam ser claras

### Título e lançamento

Usaremos inicialmente uma única entidade chamada **Lancamento** para representar uma receita ou despesa prevista ou realizada. Um título a pagar será um lançamento de despesa pendente; um título a receber será um lançamento de receita pendente. Isso evita manter dois registros independentes para a mesma obrigação.

Um lançamento terá descrição, tipo, valor, categoria, conta, vencimento e situação. A situação persistida será PENDENTE, QUITADO ou CANCELADO. Na tela, QUITADO será apresentado como “Pago” para despesa e “Recebido” para receita.

“Atrasado” será calculado: lançamento pendente com vencimento anterior à data atual. Um título que vence hoje ainda não estará atrasado. Lançamentos cancelados não participarão dos totais.

### Datas e seleção do mês

- **Vencimento:** quando esperamos pagar ou receber. Define o mês da listagem de compromissos.
- **Liquidação:** quando o dinheiro efetivamente entrou ou saiu. Define o mês dos indicadores realizados.
- **Competência:** período ao qual uma receita ou despesa se refere. Ficará para uma evolução se houver necessidade de análise por competência.

Exemplo: uma conta vence em 30 de outubro e é paga em 2 de novembro. Ela pertence aos compromissos de outubro e às despesas pagas de novembro. A interface deverá mostrar qual data está sendo usada em cada visão.

### Valores e integridade

O valor será positivo; o tipo RECEITA ou DESPESA determinará seu efeito. No Java, usaremos BigDecimal; no MySQL, DECIMAL(15,2), um tipo de valor exato [7]. Evitaremos float e double para cálculos monetários. A API poderá transmitir valores como texto decimal, por exemplo "150.90", evitando perda de precisão no transporte para JavaScript.

O backend será responsável pelos cálculos financeiros. A tela poderá formatar valores com vírgula e símbolo de real, mas não será a fonte oficial dos saldos. Valores com mais de duas casas serão rejeitados no cadastro; regras de arredondamento para cálculos futuros serão definidas explicitamente.

Regras mínimas:

- Descrição obrigatória, valor maior que zero e referências a conta e categoria existentes.
- Categoria compatível com o tipo do lançamento.
- Data de liquidação obrigatória apenas para lançamento quitado; pagamento ou recebimento realizado não terá data futura.
- Quitação repetida não pode movimentar o saldo duas vezes.
- Para corrigir um lançamento quitado, primeiro reverter a quitação; depois editar e quitar novamente, se necessário.
- Categoria ou conta em uso será arquivada, preservando o histórico e impedindo novos vínculos.

### Recorrência

A recorrência será uma regra que gera lançamentos independentes. “Aluguel todo dia 10” é a regra; “Aluguel de outubro” é uma ocorrência. Gerar uma ocorrência nunca significa marcá-la automaticamente como paga.

Proposta inicial:

1. Informar descrição, tipo, valor previsto, conta, categoria, dia do vencimento, mês inicial e mês final opcional.
2. Gerar automaticamente a ocorrência do mês quando ele começar, se o servidor estiver em execução. Ao iniciar a aplicação, gerar as ocorrências faltantes desde o mês inicial da regra até o mês atual, respeitando o encerramento. Ao cadastrar uma regra iniciada no mês atual, gerar também sua primeira ocorrência. Essa é a proposta de funcionamento local a revisar na etapa 1.
3. Se o dia não existir no mês, usar o último dia: dia 31 em fevereiro vira 28 ou 29. Manter o dia original na regra para março voltar a 31.
4. Impedir duplicatas com uma restrição única para recorrência e mês de referência.
5. Permitir ajustar o valor de uma ocorrência pendente, útil para água e energia, sem mudar todas as outras.
6. Uma edição da regra afeta apenas meses ainda não gerados na primeira versão. Uma ocorrência já criada deve ser editada explicitamente.
7. Encerrar a regra interrompe gerações futuras. Ocorrências existentes continuam visíveis até serem canceladas individualmente.

A geração automática mensal foi solicitada na etapa 1. Um programa local não executa com o computador desligado: por isso, a proposta combina verificação na inicialização e verificação da mudança de mês enquanto o servidor estiver ligado. A verificação apenas cria ocorrências faltantes; não sobrescreve valores editados e não confirma pagamentos ou recebimentos. Uma geração manual para meses futuros poderá ser discutida depois.

Parcelamento é diferente de recorrência contínua: um contrato em seis parcelas termina na sexta. Conforme solicitado, o usuário informa o valor integral e a quantidade, e o sistema calcula e gera todas as parcelas no cadastro do contrato, inicialmente pendentes. O valor integral não cria um lançamento financeiro adicional. A soma das parcelas deve ser igual ao total, sem perda de centavos. A distribuição aprovada coloca os centavos restantes nas primeiras parcelas, mostrando a prévia antes de salvar. Para a academia, R$ 1.800,00 em 12 parcelas resulta em R$ 150,00 por parcela. O modelo de dados e as operações desse contrato serão detalhados antes da implementação; a estrutura de recorrência abaixo não substitui esse detalhamento.

## 6 Dashboard mensal e significado dos números

O seletor de mês controlará o período analisado. Os indicadores realizados usarão a data de liquidação; pendências do mês usarão o vencimento. Um painel separado mostrará pendências antigas. Todos os valores excluirão cancelados.

| Indicador | Regra proposta |
| --- | --- |
| Receitas recebidas no mês | Soma das receitas quitadas com liquidação no mês |
| Despesas pagas no mês | Soma das despesas quitadas com liquidação no mês |
| Resultado realizado | Receitas recebidas menos despesas pagas no mês |
| A receber no mês | Receitas pendentes com vencimento no mês |
| A pagar no mês | Despesas pendentes com vencimento no mês |
| Resultado mensal previsto atualizado | Resultado realizado + a receber no mês − a pagar no mês |
| Pendências anteriores | Receitas e despesas ainda pendentes com vencimento antes do início do mês selecionado, mostradas separadamente |
| Despesas por categoria | Agrupamento das despesas pagas pela categoria, usando liquidação no mês |

O resultado previsto atualizado é uma estimativa baseada nos registros existentes. Ele não inclui automaticamente compromissos que o usuário ainda não cadastrou ou ocorrências recorrentes que ainda não foram geradas. O dashboard deverá avisar sobre recorrências do mês ainda não geradas.

**Resultado do mês não é saldo da conta.** O saldo realizado de uma conta em uma data será seu saldo inicial, na data de referência, mais receitas recebidas e menos despesas pagas depois dessa referência e até a data consultada. Para o MVP, lançamentos anteriores à referência serão recusados; a inclusão de histórico exigirá redefinir o ponto inicial de forma controlada. O saldo inicial representa a posição imediatamente anterior aos movimentos da data de referência.

Exemplo fictício: saldo inicial de R$ 1.000,00 em 1º de outubro; salário recebido de R$ 3.000,00; aluguel pago de R$ 1.200,00; mercado pago de R$ 400,00; energia pendente de R$ 200,00; serviço a receber de R$ 500,00. Sem outros movimentos, o resultado realizado é R$ 1.400,00, o saldo realizado é R$ 2.400,00 e o resultado previsto atualizado é R$ 1.700,00. Esses números respondem a perguntas diferentes.

Começaremos com cartões numéricos, tabela de vencimentos e barras por categoria. Cada gráfico terá um equivalente textual ou em tabela. Comparação com o mês anterior será uma evolução: quando a base anterior for zero, mostraremos a diferença em reais e “sem base percentual”.

## 7 Arquitetura fullstack proposta

Uma aplicação local pode usar tecnologias web. Você abre uma página em localhost; o Java atende às requisições e conversa com o MySQL. O navegador não recebe a senha do banco e não acessa o MySQL diretamente.

```text
Navegador
  HTML organiza a tela
  CSS define a apresentação
  JavaScript trata eventos e usa fetch
              |
              | HTTP com JSON
              v
Servidor Java
  Controller recebe e responde
  Service aplica as regras financeiras
  Model representa o domínio
  Repository lê e grava dados
              |
              | SQL por JDBC
              v
MySQL local
  Tabelas, relacionamentos, restrições e transações
```

O caminho de uma despesa será: preencher formulário → JavaScript montar a requisição → controller receber os dados → service validar as regras → repository gravar no MySQL → resposta retornar → tela atualizar a listagem.

### Tecnologias propostas e motivo

- **HTML, CSS e JavaScript sem framework de interface:** praticar semântica, formulários, layout, DOM, módulos, eventos, promises e fetch.
- **Java:** praticar classes, encapsulamento, enums, interfaces, exceções, coleções, BigDecimal e LocalDate. Java 21 era a base inicial sugerida; a conferência encontrou Microsoft JDK 25.0.4 funcional. Propomos reaproveitá-lo para os exercícios, conforme o [registro do ambiente](ambiente-de-desenvolvimento.md), antes de fixar a configuração final do servidor.
- **Spring Boot com Spring Web:** criar a API HTTP e executar o servidor. Será introduzido depois de um exercício pequeno de orientação a objetos. Conferiremos compatibilidade e fixaremos a versão; a documentação consultada exige Java 17 ou superior [6].
- **Maven e Maven Wrapper:** organizar dependências e repetir a compilação com a versão registrada no projeto.
- **JDBC com SQL explícito inicialmente:** entender consultas, parâmetros, mapeamento de resultados e transações. Podemos usar JdbcTemplate após compreender o JDBC básico. JPA será uma opção posterior, com comparação do SQL que ele substitui.
- **MySQL 8.4 como linha inicial sugerida:** praticar banco relacional. A versão exata será confirmada junto com o ambiente.
- **Git:** registrar mudanças pequenas e acompanhar a evolução do projeto. Só criaremos o repositório após explicar seu funcionamento.
- **GitHub:** conectar o histórico local ao repositório escolhido durante a organização das pastas. Revisaremos o conteúdo antes dos envios; credenciais e dados financeiros reais ficarão fora do versionamento.
- **JUnit:** verificar regras financeiras que podem quebrar silenciosamente, especialmente recorrências e totais.

Essas ferramentas adicionais são propostas para discussão. Não serão instaladas automaticamente por este roteiro. Docker, frameworks de frontend e ferramentas de empacotamento não são necessários para começar.

Para simplificar a execução, o próprio Java poderá servir os arquivos HTML, CSS e JavaScript. Tela e API terão a mesma origem, evitando introduzir configuração de CORS nesta primeira fase. Separar as pastas lógicas continua sendo importante, mesmo com um único servidor.

## 8 Organização de pastas e arquivos

Estrutura alvo sugerida. Ela será criada aos poucos, quando cada parte tiver uma função real; este documento não cria a estrutura da aplicação.

```text
FinTrack/
  README.md
  .gitignore
  docs/
    FinTrack_Plano_de_Desenvolvimento.md
    requisitos.md
    regras-financeiras.md
    modelo-de-dados.md
    api.md
    decisoes.md
    diario-de-aprendizado.md
  database/
    migrations/
      V001__estrutura_inicial.sql
    seeds/
      dados_ficticios.sql
  backend/
    pom.xml
    mvnw
    mvnw.cmd
    .mvn/wrapper/
    src/
      main/
        java/br/com/fintrack/
          FinTrackApplication.java
          controller/
          service/
          repository/
          model/
          dto/
          exception/
          config/
        resources/
          application.properties
          static/
            index.html
            pages/
            css/
              base.css
              components.css
              pages/
            js/
              api/
              pages/
              components/
              utils/
            assets/
      test/
        java/br/com/fintrack/
```

**Raiz e documentação.** README explica como configurar e executar. docs guarda requisitos, decisões e aprendizados. .gitignore evita incluir senhas, arquivos gerados e dados pessoais. Se adotarmos configuração por variáveis de ambiente, documentaremos os nomes e valores fictícios; um arquivo .env só será usado se configurarmos explicitamente seu carregamento.

**Banco.** migrations contém alterações numeradas do esquema; seeds contém exemplos fictícios. Começaremos entendendo a execução manual e registrando quais scripts foram aplicados. Antes do uso real, adotaremos um processo controlado de migração, manual ou com ferramenta discutida em aula, sem duas fontes concorrentes do esquema. Backups com dados reais ficarão fora do repositório e da pasta sincronizada do código.

**Backend.** controller traduz HTTP; service executa casos de uso; repository contém persistência; model contém objetos e comportamento do domínio; dto define dados de entrada e saída; exception padroniza erros; config configura a aplicação. Não colocaremos a regra de saldo no controller ou no JavaScript.

**Frontend.** static contém os arquivos servidos pelo Java. pages organiza telas; css separa base e componentes; js/api concentra a comunicação com o backend; js/pages coordena cada tela; utils contém funções pequenas, como formatação de moeda. A pasta target, gerada pelo Maven, não será versionada.

Essa estrutura usa um único repositório e um backend monolítico. Não há necessidade de microsserviços para esse escopo. Se o projeto crescer, avaliaremos organizar os pacotes por funcionalidade.

## 9 Modelo de dados inicial

| Entidade | Campos principais | Relações e responsabilidade |
| --- | --- | --- |
| Categoria | id, nome, tipo, ativa | Uma categoria classifica vários lançamentos e recorrências |
| Conta | id, nome, saldo_inicial, data_referencia, ativa | Uma conta recebe vários lançamentos |
| Lancamento | id, descricao, tipo, valor, vencimento, data_liquidacao, situacao, categoria_id, conta_id, recorrencia_id opcional, mes_referencia opcional | Representa uma obrigação e sua quitação integral |
| Recorrencia | id, descricao, tipo, valor_previsto, dia_vencimento, mes_inicio, mes_fim opcional, categoria_id, conta_id | Define como gerar ocorrências mensais |

Valores monetários usarão DECIMAL(15,2), datas civis usarão DATE e identificadores poderão usar BIGINT. O mês de referência da recorrência poderá ser armazenado como o primeiro dia do mês, como 2026-10-01; ele identifica a ocorrência, independentemente do ajuste do dia de vencimento.

Chaves primárias identificam registros; chaves estrangeiras impedem vínculos inexistentes. A combinação recorrencia_id + mes_referencia será única para ocorrências geradas. Lançamentos únicos não terão esses campos preenchidos. Incluiremos restrições de valor e situação coerentes com a validação Java.

O saldo será calculado a partir dos lançamentos, evitando manter dois valores que possam divergir. A consulta mensal usará um intervalo de datas, incluindo o primeiro dia do mês e excluindo o primeiro dia do seguinte. Índices serão definidos para os filtros de vencimento, liquidação e conta conforme as consultas implementadas.

### Orientação a objetos aplicada

Uma classe Lancamento não precisa ter apenas campos e getters. Métodos como quitar(data) e reverterQuitacao() podem proteger suas transições. LancamentoService coordenará a operação e o repositório fará a persistência.

Uma interface LancamentoRepository permitirá começar com dados em memória e depois usar MySQL sem reescrever as regras financeiras. Esse é um exercício concreto de abstração e polimorfismo. Receita e despesa podem usar a mesma classe e um enum TipoLancamento; não criaremos herança apenas para demonstrar um conceito.

## 10 Contrato inicial da API

Uma API é o acordo entre a tela e o servidor: quais endereços existem, quais dados recebem e como respondem. Estes caminhos são uma proposta para documentar durante a implementação.

| Operação | Requisição sugerida | Resultado esperado |
| --- | --- | --- |
| Listar compromissos | GET /api/lancamentos?mes=2026-10 | Lista filtrada pelo mês de vencimento |
| Criar lançamento | POST /api/lancamentos | Registro criado com identificador |
| Editar pendente | PUT /api/lancamentos/{id} | Dados atualizados após validação |
| Quitar | POST /api/lancamentos/{id}/quitacao | Data registrada e situação alterada |
| Reverter quitação | DELETE /api/lancamentos/{id}/quitacao | Retorno à situação pendente |
| Cancelar pendente | POST /api/lancamentos/{id}/cancelamento | Registro preservado e retirado dos totais |
| Consultar dashboard | GET /api/dashboard?mes=2026-10 | Indicadores conforme as regras do capítulo 6 |
| Criar recorrência | POST /api/recorrencias | Regra cadastrada sem quitação automática |
| Gerar ocorrências manualmente se necessário | POST /api/recorrencias/{id}/ocorrencias | Ação complementar futura; a geração mensal automática acontece no servidor |

Exemplo de dados enviados para uma despesa pendente:

```json
{
  "descricao": "Conta de energia",
  "tipo": "DESPESA",
  "valor": "200.00",
  "vencimento": "2026-10-20",
  "categoriaId": 2,
  "contaId": 1
}
```

Você não precisará informar um saldo novo. O servidor validará os dados, atribuirá o identificador e a situação inicial e recalculará as consultas a partir dos registros.

Estudaremos códigos HTTP no contexto: 201 para criação, 200 para consulta ou atualização com resposta, 400 para dados inválidos, 404 para registro inexistente e 409 para conflito de estado. A resposta de erro terá mensagem compreensível e campos inválidos, sem expor senha, detalhes internos ou consultas SQL.

GET não criará lançamentos recorrentes: uma consulta deve apenas consultar. A geração automática será coordenada pelo servidor, com operação transacional e proteção contra repetição. Se oferecermos geração manual complementar, ela usará POST e reutilizará as mesmas regras.

## 11 Etapas de desenvolvimento e critérios de conclusão

As etapas seguem dependências técnicas e de aprendizado. Não há prazo fixo: o ritmo será definido pelo tempo disponível e pela compreensão demonstrada.

### Etapa 1 Definir o problema e o vocabulário

**Objetivo:** transformar a ideia em regras verificáveis.

1. Montar de cinco a dez lançamentos fictícios de um mês.
2. Separar receita, despesa, conta, categoria, título, recorrência e quitação.
3. Confirmar as premissas do capítulo 4, especialmente o significado de títulos.
4. Escrever histórias, por exemplo: “Como usuário, quero marcar uma despesa como paga para atualizar meu saldo realizado”.
5. Registrar regras e funcionalidades prioritárias.

**Entrega:** requisitos e regras financeiras revisados.

**Prática:** explicar por que um salário previsto não aumenta o saldo realizado.

**Concluída quando:** você conseguir calcular manualmente os indicadores de um mês e justificar a inclusão de cada registro.

### Etapa 2 Desenhar telas e navegação

**Objetivo:** entender a experiência antes de programar.

1. Desenhar dashboard, listagem, formulário e cadastros de categorias e contas.
2. Posicionar o seletor de mês e os filtros.
3. Definir campos obrigatórios, mensagens de erro e confirmação de quitação.
4. Desenhar estados sem dados, carregando e com falha de comunicação.
5. Conferir navegação por teclado, rótulos e uso em uma tela menor.

**Entrega:** rascunhos de telas e sequência de uso.

**Prática:** simular o cadastro e a quitação de uma despesa usando apenas os rascunhos.

**Concluída quando:** for possível realizar o fluxo sem ambiguidades e cada campo tiver uma finalidade.

### Etapa 3 Preparar o ambiente e compreender a arquitetura

**Objetivo:** executar as ferramentas entendendo o papel de cada uma.

1. Conferir o que já existe na máquina: editor, JDK, Git e MySQL.
2. Escolher e registrar versões compatíveis; instalar somente o necessário, após explicação.
3. Explicar JVM, compilação, dependências Maven, servidor HTTP e serviço do MySQL.
4. Criar a estrutura mínima e um README com passos de execução.
5. Verificar compilação e execução com o primeiro incremento real do FinTrack: representar um lançamento em Java. Introduzir a página e o servidor mínimo ao construir o fluxo de cadastro, explicando cada responsabilidade durante a implementação.
6. Explicar Git e GitHub, verificar repositório existente, preparar .gitignore e revisar o primeiro commit. Definir endereço e visibilidade do repositório com o usuário, conectar e enviar código e documentação. Integrar pequenos commits às próximas etapas, sem enviar senhas, dados reais ou backups.

**Entrega:** ambiente reproduzível e aplicação mínima executável.

**Prática:** explicar a diferença entre navegador, servidor da aplicação e servidor do banco.

**Concluída quando:** você conseguir iniciar e encerrar cada serviço e reconhecer um erro de porta ocupada ou de conexão.

### Etapa 4 Praticar o domínio em Java

**Objetivo:** aprender orientação a objetos com as regras do FinTrack.

1. Criar Lancamento, Conta, Categoria e os enums necessários, sem depender de HTTP ou MySQL.
2. Usar construtores e encapsulamento para impedir estados inválidos.
3. Implementar quitação e reversão, com BigDecimal e LocalDate.
4. Criar a interface de repositório e uma implementação em memória.
5. Escrever testes para valor inválido, quitação repetida e soma de receitas e despesas.

**Entrega:** regras funcionando em testes ou em um pequeno exercício de console.

**Prática:** criar uma nova validação e explicar em qual classe ela pertence.

**Concluída quando:** você conseguir explicar classe, objeto, interface e responsabilidade usando o código que escreveu.

### Etapa 5 Modelar e consultar o MySQL

**Objetivo:** transformar o domínio em dados relacionais.

1. Desenhar entidades e cardinalidades, começando por conta, categoria e lançamento.
2. Criar tabelas, chaves e restrições com um script numerado.
3. Inserir os dados fictícios manualmente.
4. Praticar INSERT, SELECT, UPDATE, JOIN, SUM e GROUP BY.
5. Consultar lançamentos de um mês usando limites de datas.
6. Entender transação, commit e rollback com um exemplo pequeno.

**Entrega:** modelo documentado e consultas conferidas manualmente.

**Prática:** obter despesas pagas por categoria e explicar o JOIN.

**Concluída quando:** as consultas coincidirem com os valores calculados na etapa 1 e referências inválidas forem recusadas.

### Etapa 6 Construir a primeira API com persistência

**Objetivo:** cadastrar e consultar lançamentos no banco através do Java.

1. Introduzir Spring Boot e explicar suas configurações e anotações utilizadas.
2. Criar DTOs e endpoints mínimos de criação e consulta.
3. Conectar service à implementação JDBC do repositório.
4. Usar consultas parametrizadas e tratamento consistente de erros.
5. Configurar credenciais fora do código e usar um usuário de banco próprio da aplicação.
6. Exercitar requisições válidas e inválidas e conferir os dados no MySQL.

**Entrega:** API que cria e lista dados persistentes.

**Prática:** seguir uma requisição em modo de depuração e explicar cada camada.

**Concluída quando:** os registros continuarem disponíveis após reiniciar o Java e dados inválidos não forem gravados.

### Etapa 7 Construir e integrar o frontend

**Objetivo:** completar a primeira funcionalidade de ponta a ponta.

1. Criar HTML semântico para o formulário e a listagem.
2. Aplicar CSS responsivo e estados visuais de erro e sucesso.
3. Usar JavaScript para ler campos, tratar eventos e montar JSON.
4. Implementar fetch com async e await, verificando erros de rede e respostas HTTP.
5. Evitar envios repetidos enquanto a requisição estiver em andamento.
6. Atualizar a lista após sucesso e preservar campos quando houver erro.

**Entrega:** cadastro e consulta de receitas e despesas pelo navegador.

**Prática:** adicionar um filtro de tipo e explicar como o evento chega à API.

**Concluída quando:** o fluxo funcionar na tela, os dados persistirem e uma falha no backend produzir uma mensagem útil.

### Etapa 8 Implementar o ciclo de vida dos títulos

**Objetivo:** gerenciar pendências, quitações e cancelamentos sem inconsistência.

1. Implementar edição de pendentes, quitação integral, reversão e cancelamento.
2. Validar transições no domínio e no service.
3. Calcular o indicador de atraso usando a data atual.
4. Atualizar listagem e saldos após cada ação.
5. Testar repetição de quitação, vencimento hoje e pagamento em outro mês.

**Entrega:** fluxo completo de contas a pagar e receber.

**Prática:** explicar por que cancelar e apagar um registro têm efeitos diferentes sobre o histórico.

**Concluída quando:** cada ação tiver efeito único e os registros cancelados não aparecerem nos totais.

### Etapa 9 Implementar recorrências mensais

**Objetivo:** automatizar o cadastro mantendo controle sobre cada ocorrência.

1. Modelar Recorrencia e sua relação com Lancamento.
2. Implementar cálculo do vencimento e geração para intervalo limitado.
3. Executar a geração automaticamente na inicialização e na mudança de mês, explicando a abrangência antes de ativar a regra.
4. Garantir unicidade por recorrência e mês no banco, além da verificação no Java.
5. Testar fevereiro, ano bissexto, meses com 30 dias, encerramento e repetição da geração.
6. Explicar edição da ocorrência versus edição da regra.
7. Detalhar e implementar o contrato parcelado da academia a partir do valor integral e da quantidade, identificando cada parcela e impedindo geração além da quantidade contratada. Conferir a soma exata dos valores, a divisão com resto em centavos e a prevenção de duplicatas. Validar sua modelagem separadamente da recorrência contínua.

**Entrega:** receitas e despesas mensais recorrentes sem duplicatas.

**Prática:** prever o resultado de uma regra do dia 31 em fevereiro e março antes de executá-la.

**Concluída quando:** iniciar novamente o sistema e verificar o mesmo mês não criar registros adicionais, os meses faltantes serem recuperados e nenhuma ocorrência ser quitada automaticamente ou ter seu valor individual sobrescrito.

### Etapa 10 Construir o dashboard mensal

**Objetivo:** transformar os dados em uma leitura financeira confiável.

1. Implementar consultas agregadas no backend conforme o capítulo 6.
2. Conferir cada indicador com um conjunto pequeno de dados fictícios.
3. Criar cartões, barras por categoria e lista de vencimentos.
4. Permitir selecionar o mês e abrir os registros de cada total.
5. Mostrar mês sem dados, pendências anteriores e recorrências ainda não geradas.
6. Tratar respostas fora de ordem quando o usuário trocar rapidamente de mês.

**Entrega:** dashboard rastreável e coerente com os lançamentos.

**Prática:** explicar por que uma despesa vencida em outubro e paga em novembro entra em painéis diferentes.

**Concluída quando:** os indicadores baterem com SQL e cálculo manual, sem mistura entre realizado, previsto e saldo.

### Etapa 11 Preparar o uso local com dados reais

**Objetivo:** conseguir operar e recuperar o sistema.

1. Configurar aplicação e banco para acesso local e manter segredos fora do repositório.
2. Revisar logs, mensagens e uso de consultas parametrizadas.
3. Documentar inicialização, encerramento e atualização do esquema.
4. Fazer backup e restaurá-lo em um banco de teste, conferindo registros e totais.
5. Executar os cenários essenciais de ponta a ponta.
6. Registrar limitações conhecidas e concluir o README.

**Entrega:** primeira versão utilizável e procedimento de recuperação comprovado.

**Prática:** restaurar uma cópia e confirmar o dashboard do mês fictício.

**Concluída quando:** você conseguir executar o sistema seguindo o README e recuperar os dados sem depender da base original.

### Etapa 12 Evoluir a partir do uso

**Objetivo:** escolher melhorias com base em necessidades observadas.

1. Usar a primeira versão e anotar tarefas repetitivas ou informações faltantes.
2. Priorizar orçamento por categoria, cálculos avançados de parcelamento, transferências ou importação CSV.
3. Para cada recurso, escrever a regra, alterar o modelo, implementar e verificar.
4. Comparar JDBC com JPA somente quando as consultas e responsabilidades estiverem claras.
5. Registrar o que aprendeu e quais decisões mudaria em um novo projeto.

**Entrega:** evolução pequena e justificável por necessidade real.

**Prática:** defender a próxima funcionalidade com um exemplo de uso e seus impactos nos dados e indicadores.

**Concluída quando:** a melhoria funcionar sem alterar indevidamente os resultados anteriores.

## 12 Verificações essenciais

| Cenário | Resultado esperado |
| --- | --- |
| Salvar lançamento válido e reiniciar | Dados continuam disponíveis |
| Enviar valor zero, negativo ou referência inexistente | Servidor recusa e banco permanece íntegro |
| Quitar o mesmo registro duas vezes | Efeito financeiro ocorre uma vez |
| Reverter quitação | Valor sai dos realizados e volta às pendências |
| Cancelar lançamento pendente | Histórico permanece; totais o excluem |
| Vencimento hoje | Pendente, sem indicação de atraso |
| Pagamento em mês diferente do vencimento | Listagem e realizados usam suas respectivas datas |
| Gerar recorrência duas vezes | Não há duplicatas |
| Dia 31 em fevereiro e março | Ajuste para fim de fevereiro; retorno ao dia 31 em março |
| Mês sem movimentação | Indicadores zerados e mensagens claras |
| Categoria ou conta arquivada | Histórico preservado; novos vínculos impedidos |
| Restaurar backup em base de teste | Registros e totais conferem com a origem |

Testes unitários cobrirão regras isoladas; testes de integração verificarão persistência e restrições; cenários manuais confirmarão a experiência da tela. Não é necessário testar cada detalhe visual automaticamente. O foco será dinheiro, datas, estados e preservação dos dados.

## 13 Cuidados com execução e armazenamento local

O sistema não depende de hospedagem externa, mas precisará do servidor Java e do MySQL em execução. O funcionamento sem internet requer que fontes, scripts e outros recursos usados pela tela também estejam disponíveis localmente.

O código deste projeto está em uma pasta do OneDrive. O diretório de dados ativo do MySQL deve permanecer no local administrado pelo próprio MySQL, fora da pasta sincronizada do projeto. Backups deverão ter destino deliberado e acesso controlado; “local” não implica que um arquivo dentro do OneDrive deixe de ser sincronizado.

Não incluiremos credenciais de bancos financeiros nem faremos movimentações bancárias. O FinTrack registrará informações fornecidas pelo usuário. Exportação CSV facilitará análise, mas não substituirá um backup completo capaz de restaurar tabelas, relacionamentos e dados.

## 14 Checklist da primeira sessão de trabalho

- [ ] Confirmar se títulos são contas a pagar e receber ou investimentos.
- [ ] Confirmar uso individual, BRL e acesso somente na própria máquina.
- [ ] Criar um mês fictício e calcular previsto, realizado e saldo.
- [ ] Revisar o escopo da primeira versão.
- [ ] Confirmar as regras de recorrência e liquidação integral.
- [ ] Desenhar listagem, formulário e dashboard.
- [ ] Informar quais conhecimentos de Java e SQL você já praticou.
- [ ] Conferir ferramentas instaladas antes de escolher versões e instalar algo.
- [ ] Combinar o primeiro exercício pequeno de Java e sua forma de revisão.

A primeira atividade recomendada é definir o mês fictício e suas regras. Isso dará uma referência para verificar todas as telas, consultas e indicadores que construiremos depois.

## 15 Fontes e material de estudo

As fontes de produto sustentam a comparação do capítulo 3. As fontes técnicas apoiam a escolha e o estudo das ferramentas; o escopo e a ordem de desenvolvimento são recomendações para este projeto.

1. [Organizze — Como fazer um controle financeiro e organizar as finanças](https://www.organizze.com.br/blog/gestao-financeira/como-fazer-controle-financeiro).
2. [Mobills — Como utilizar o aplicativo de controle financeiro](https://www.mobills.com.br/blog/mobills/como-utilizar-o-mobills/).
3. [Actual Budget — Funcionalidades e funcionamento local](https://actualbudget.org/).
4. [Actual Budget — Agendamentos e recorrências](https://actualbudget.org/docs/schedules/).
5. [Dev Java — Fundamentos, orientação a objetos e APIs Java](https://dev.java/learn/).
6. [Spring Boot — Requisitos de sistema e compatibilidade](https://docs.spring.io/spring-boot/system-requirements.html).
7. [MySQL 8.4 — Tipos de valor exato DECIMAL e NUMERIC](https://dev.mysql.com/doc/refman/8.4/en/fixed-point-types.html).

Fontes consultadas em 12 de outubro de 2026. Versões finais das ferramentas serão registradas no projeto quando prepararmos o ambiente.
