# MiniBanco

Este projeto tem como objetivo simular o funcionamento básico de uma conta bancária digital. Ele permite que o usuário realize operações essenciais como **criar uma conta**, **consultar saldo**, **realizar crédito**, **realizar débito** e **transferências**.

## Integrantes
- Gabriele Queiroz Rêgo, github `@gabrielerego123`
- Ana Maria Gomes Holanda, github `@anamgholandadev`

## Linguagem de Programação
No projeto, é utilizado a linguagem de programação Java com Spring Boot.

### Requisitos
- Java 19+.

### Executar projeto via terminal

1. Abra o terminal e execute os comandos abaixos:

```
    cd minibanco-gcm/src && javac -d ../bin $(find . -name "*.java") && cd ..
```

2. Agora execute o comando para criar o .jar

```
    jar cfm minibanco.jar src/META-INF/MANIFEST.MF -C bin/ .
```

3. Execute o .jar

```
    java -jar minibanco.jar
```

### Executar projeto via Intellij IDEA

1. Abra o IntelliJ e clique em `File > Open`, depois selecione a pasta do projeto.
2. Clique com o botão direito na classe **Main.java** que está em `src/edu/ufrn/gcm`, e selecione `Run 'main'`.
3. A saída aparecerá no terminal inferior da IDE.

### Executar projeto via Eclipse

1. Abra o Eclipse.
2. Vá em `File > Import > Existing Projects into Workspace`.
3. Selecione a pasta do projeto.
4. No Package Explorer, clique com o botão direito na classe **Main.java** que está em `src/edu/ufrn/gcm`
   e escolha `Run As > Java Application`.
