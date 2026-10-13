# FinTrack

Projeto de aprendizado para construir um gerenciador financeiro pessoal local com HTML, CSS, JavaScript, Java e MySQL.

## Estado atual

Requisitos e rascunhos de telas registrados. Conferência inicial do ambiente iniciada. O aprendizado seguirá os incrementos reais do projeto, partindo do conhecimento básico das tecnologias informado pelo usuário. Ainda não há aplicação, servidor ou banco do FinTrack para executar.

O fluxo aprovado usa o botão **Salvar**, seguido de uma pergunta sobre confirmar o pagamento. A quitação ocorre somente na tela de confirmação, com a data real. No parcelamento, o sistema divide o valor integral pela quantidade de parcelas, preservando o total e distribuindo os centavos restantes nas primeiras parcelas.

## Documentação

- [Plano de desenvolvimento e aprendizado](docs/FinTrack_Plano_de_Desenvolvimento.md)
- [Requisitos e decisões](docs/requisitos.md)
- [Telas, navegação e simulação de uso](docs/telas-e-navegacao.md)
- [Ambiente de desenvolvimento e próximos exercícios](docs/ambiente-de-desenvolvimento.md)

## Organização inicial

`docs/` contém a documentação. O exercício introdutório foi removido; o aprendizado seguirá a implementação das funcionalidades do FinTrack. `.gitignore` prepara as exclusões do versionamento, incluindo `build/`, onde ficam os resultados da compilação. Os passos de execução da aplicação serão registrados conforme seus incrementos forem implementados.

O histórico Git local foi iniciado na branch `main`. O repositório público é [gabiimarotti/FinTrack](https://github.com/gabiimarotti/FinTrack), destinado ao código e aos exemplos fictícios. Os dados do contrato pessoal na documentação foram substituídos por um exemplo fictício. O primeiro registro reúne a documentação, as exclusões de arquivos e o exemplo introdutório opcional; o código do domínio ainda será implementado.

Credenciais, dados financeiros reais, backups e arquivos ativos do MySQL não devem ser versionados. Scripts de estrutura do banco e dados fictícios poderão ser versionados após revisão.

Os registros financeiros de uso real ficarão no MySQL local. Salvar um lançamento não enviará informações ao GitHub. Dados reais também não devem aparecer em documentação, imagens ou exemplos públicos.
