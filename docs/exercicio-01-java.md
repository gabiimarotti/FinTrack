# Exercício 1 — Editar, compilar e executar Java

**Material opcional:** o usuário prefere aprender durante a implementação do FinTrack e já conhece o básico das tecnologias. Este exercício introdutório não é mais um pré-requisito; o próximo incremento trabalhará um lançamento real do domínio, com dados fictícios.

## Objetivo

Entender o caminho entre um arquivo Java e sua execução. Este primeiro passo verifica o ambiente; as classes do domínio financeiro virão depois. Não precisamos de MySQL, Maven ou Spring Boot aqui.

Abra `exercicios/01-primeira-execucao/PrimeiroPrograma.java` no editor que preferir.

## Leia antes de executar

- `public class PrimeiroPrograma` declara a classe. O arquivo tem o mesmo nome, seguido de `.java`.
- `public static void main(String[] args)` é o ponto de entrada usado neste exercício. `static` permite iniciar sem criar um objeto; `void` indica que o método não retorna um valor; `args` recebe argumentos da execução, que ainda não usaremos.
- `System.out.println(...)` escreve uma linha no console. O texto entre aspas é uma String.

O compilador `javac` lê o arquivo fonte e produz um arquivo `.class`. O comando `java` inicia a JVM, que executa esse resultado. Editar o fonte não altera o `.class` já produzido: é preciso compilar novamente.

## Execute no PowerShell

Abra um terminal na raiz da pasta FinTrack. Estes comandos usam o JDK 25 encontrado nesta máquina sem alterar as configurações globais:

```powershell
$fintrackJdk = Join-Path $env:USERPROFILE '.jdks\ms-25.0.4'
& "$fintrackJdk\bin\java.exe" -version
& "$fintrackJdk\bin\javac.exe" -version
New-Item -ItemType Directory -Force -Path 'build/exercicio-01' | Out-Null
& "$fintrackJdk\bin\javac.exe" -encoding UTF-8 -d 'build/exercicio-01' 'exercicios/01-primeira-execucao/PrimeiroPrograma.java'
& "$fintrackJdk\bin\java.exe" -cp 'build/exercicio-01' PrimeiroPrograma
```

A variável guarda o caminho do JDK apenas neste terminal. `-d` escolhe onde salvar o resultado da compilação; `-cp` informa onde encontrar a classe ao executar. `build/` é uma pasta gerada e já está excluída do versionamento. Em outra máquina, ajustar o caminho para o JDK instalado.

Saída esperada:

```text
FinTrack: primeiro programa Java em execucao.
Dados de aprendizado: use apenas exemplos ficticios.
```

Só avance à execução se a compilação terminar sem erros. Caso contrário, um arquivo compilado antigo poderia ser executado.

## Sua prática

1. Troque o texto da primeira mensagem por uma frase sua, sem dados pessoais.
2. Salve o arquivo, compile novamente e execute. Confira a mudança na saída.
3. Acrescente uma terceira chamada a `System.out.println` com uma mensagem sobre o que o FinTrack fará. Compile e execute novamente.
4. Para observar um erro de compilação, remova temporariamente o ponto e vírgula de uma das chamadas. Compile, leia o erro, restaure o ponto e vírgula e compile de novo.

Para a revisão, compartilhe a saída obtida e explique: por que salvar o `.java` não é suficiente para mudar o programa executado? Não é necessário enviar seu caminho de usuário ou outras informações da máquina.

## Próximo incremento

Após revisar esta prática, executar uma página HTML simples e relacionar seu papel ao navegador. Depois, introduzir o servidor mínimo da etapa 3. O domínio orientado a objetos e a persistência entrarão em incrementos próprios, com explicação e prática em cada um.
