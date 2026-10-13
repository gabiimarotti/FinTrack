# FinTrack — Ambiente de desenvolvimento

Conferência inicial: 12 de outubro de 2026. Corresponde ao início da etapa 3 do [plano](FinTrack_Plano_de_Desenvolvimento.md), após autorização para prosseguir. Nenhuma ferramenta foi instalada e nenhuma configuração global foi alterada nesta conferência.

## 1 O que foi encontrado

| Ferramenta | Evidência local | Situação para o projeto |
| --- | --- | --- |
| Git | `git --version`: 2.53.0.windows.2 | Disponível; histórico local iniciado na branch `main` |
| VS Code | Comando localizado; saída de versão 1.124.2 | Instalado; a consulta apresentou também restrição de acesso ao diretório do editor |
| IntelliJ IDEA | Instalação 2026.2.0.1 registrada no Windows | Disponível como opção de editor; abertura ainda não testada |
| Microsoft JDK | `java -version` e `javac -version` executados pelo caminho completo: 25.0.4 | JDK funcional disponível |
| Java padrão | O comando `java` resolve para o atalho Oracle `java8path` | Não usar esse atalho para os exercícios; a versão efetiva desse comando não foi confirmada |
| Outros JDKs | Pastas de JDK 11 e 26 em `.jdks` | Localizadas, sem necessidade de uso neste momento |
| Maven | Comando `mvn` não localizado no PATH | Não prova ausência em outros locais; Maven Wrapper será tratado ao preparar o servidor |
| MySQL | Comandos `mysql` e `mysqld` não localizados; nenhuma pasta identificada nos locais comuns consultados | Instalação não confirmada; verificar antes de decidir instalar |
| GitHub CLI | Comando `gh` não localizado no PATH | Não é requisito para aprender Git nem para iniciar o histórico local |

A enumeração de serviços do Windows não pôde ser concluída por restrição de acesso. Não foi concluído que o MySQL esteja ausente ou desligado. Nenhum serviço foi iniciado ou encerrado.

## 2 Java para o primeiro exercício

O JDK funcional encontrado está em `.jdks\ms-25.0.4`, dentro da pasta do usuário do Windows. O plano sugeria Java 21, sujeito à conferência do ambiente. Vamos aproveitar este JDK 25 já instalado para o exercício inicial, sem instalar outro ou alterar o Java padrão do Windows.

A documentação oficial do [Spring Boot 4.0 — requisitos de sistema](https://docs.spring.io/spring-boot/4.0/system-requirements.html), consultada nesta conferência, informa compatibilidade com Java 25. Isso sustenta a possibilidade de reaproveitamento quando chegarmos ao servidor; a versão exata de Spring Boot e do Maven Wrapper será escolhida e registrada antes dessa implementação.

Para conferir o JDK no PowerShell:

```powershell
$fintrackJdk = Join-Path $env:USERPROFILE '.jdks\ms-25.0.4'
& "$fintrackJdk\bin\java.exe" -version
& "$fintrackJdk\bin\javac.exe" -version
```

O caminho é específico desta máquina. Uma futura rotina de execução deverá permitir selecionar o JDK sem gravar esse caminho no código Java.

## 3 Papel de cada ferramenta

- **Editor:** onde escrevemos e revisamos os arquivos.
- **JDK:** contém o compilador `javac`, que transforma código Java em arquivos executáveis pela JVM, e o comando `java`, que inicia a execução.
- **JVM:** executa o programa Java compilado.
- **Maven:** organiza dependências e a compilação. O Wrapper permite registrar no projeto a versão usada por todos.
- **Navegador:** apresenta HTML e CSS e executa JavaScript; enviará pedidos ao servidor da aplicação.
- **Servidor Java:** recebe os pedidos, valida as regras e acessa o banco. Precisa estar em execução para responder.
- **MySQL:** armazena os dados de forma persistente; é um serviço separado do servidor Java. A senha do banco não pertence ao navegador.
- **Git:** guarda versões locais revisadas dos arquivos.
- **GitHub:** hospeda o repositório remoto para receber o histórico enviado pelo Git.

## 4 Preparação do versionamento

Foi criado um `.gitignore` na raiz. Ele exclui resultados de compilação, configurações locais, credenciais e pastas reservadas para dados reais e backups. Scripts SQL e dados fictícios não foram excluídos genericamente, pois fazem parte das entregas de aprendizado.

Sequência que será explicada e executada na organização do histórico:

1. `git init`: cria o histórico local; não publica arquivos.
2. `git status`: mostra os arquivos candidatos ao primeiro registro.
3. `git add` com caminhos revisados: seleciona o conteúdo do registro.
4. `git diff --cached`: permite conferir exatamente o que será registrado.
5. `git commit`: registra uma versão local com uma descrição do incremento; depende de identidade Git configurada.
6. Após definir o repositório e sua visibilidade, conectar o remoto e enviar o histórico. A autenticação será feita pelo próprio usuário quando necessária.

O histórico local foi iniciado na branch `main`, e o repositório público [gabiimarotti/FinTrack](https://github.com/gabiimarotti/FinTrack) foi criado. A configuração local de autoria usa o endereço `noreply` do GitHub, evitando expor o e-mail pessoal nos commits deste projeto. O primeiro registro reúne os arquivos revisados da documentação, o `.gitignore` e o exemplo introdutório opcional. O `.gitignore` ajuda a selecionar arquivos, mas a revisão do conteúdo continua necessária antes de registrar e enviar cada incremento.

Para acompanhar as próximas mudanças: consultar `git status`, selecionar arquivos específicos com `git add`, revisar `git diff --cached`, registrar com `git commit` e enviar com `git push`. Salvar um arquivo no editor não executa essas operações automaticamente.

## 5 Aprendizado durante a implementação

O usuário esclareceu que já conhece o básico de cada tecnologia e prefere aprender os conceitos durante a implementação do projeto. O programa isolado de mensagens anteriormente preparado deixa de ser atividade obrigatória. A execução do primeiro código do domínio servirá também para conferir o ambiente Java.

Primeiro incremento: representar um lançamento do FinTrack em Java, começando por descrição, tipo, valor, vencimento e situação pendente. Trabalhar validação de valor positivo, quitação com data real e prevenção de quitação repetida. Contas e categorias serão incorporadas antes do cadastro completo; este incremento não pretende concluir todos os requisitos de um lançamento.

Explicar encapsulamento ao proteger os campos e transições, `BigDecimal` ao representar dinheiro, `LocalDate` ao trabalhar datas e enums ao restringir tipo e situação. Usar cenários fictícios de uma despesa válida e de um valor inválido. Ao introduzir quitação, revisar primeiro a proposta pendente de reversão antes da edição; não presumir aprovação das regras restantes.

Nos incrementos seguintes, ligar cada conceito a uma necessidade: encapsulamento para proteger o estado de um lançamento, abstração para separar regras e persistência, e interfaces para alternar entre memória e banco. Herança não será introduzida sem necessidade do domínio. Em SQL, começar por tabelas, INSERT e SELECT com dados fictícios; acrescentar UPDATE, relacionamentos, agregações e transações quando os fluxos exigirem. Cada incremento terá explicação, prática do usuário e revisão, conforme o ciclo do plano.

## Preferência de publicação e privacidade

O usuário escolheu um repositório GitHub público, mantendo seus dados financeiros fora dele: [gabiimarotti/FinTrack](https://github.com/gabiimarotti/FinTrack). O repositório conterá código, instruções, estrutura do banco e exemplos fictícios; os registros de uso real ficarão no MySQL local, fora da pasta sincronizada do projeto.

Salvar um lançamento no sistema não executará Git nem enviará dados ao GitHub. Credenciais serão configurações locais excluídas do versionamento. Exportações e backups com dados reais terão destino fora do repositório. O `.gitignore` é uma ajuda de seleção, não uma proteção contra conteúdo sensível colocado em arquivos versionados ou já presente no histórico.

Por solicitação do usuário, os valores e vencimentos do contrato pessoal foram substituídos na documentação por um exemplo exclusivamente fictício: total de R$ 1.800,00 em 12 parcelas de R$ 150,00, de 15/11/2026 a 15/10/2027. Os caminhos locais também passaram a usar a pasta do usuário obtida pelo ambiente, sem identificar a conta do Windows. Não foi criada uma cópia dos dados pessoais no projeto.

Antes do primeiro envio público, revisar também documentação, capturas de tela e dados de teste. Não basta proteger apenas os registros futuros do banco. Revisar o conteúdo do histórico antes de publicar, pois apagar um dado do arquivo atual não o remove de versões anteriores.

## 6 Pendências desta etapa

- Implementar o primeiro incremento real do domínio com explicação e participação do usuário.
- Manter pequenos commits revisados durante a implementação e conferir cada envio ao repositório público.
- Confirmar o ambiente MySQL antes de instalar ou configurar qualquer banco.
- Registrar as versões finais e os passos de execução conforme cada componente for introduzido.
- Manter identificadas as propostas financeiras ainda pendentes nos requisitos; a conferência do ambiente não equivale à aprovação dessas regras.

A etapa 3 ainda não está concluída: faltam o primeiro incremento executável do projeto, o versionamento e a preparação dos serviços. A verificação do ambiente e o início do domínio poderão avançar juntos, conforme a preferência de aprendizado atualizada.
