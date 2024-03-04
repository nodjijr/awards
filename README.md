----
### Golden Raspberry Awards - API
----
## Descrição
Essa solução fornece os vencedores da categoria Pior Filme do Golden Raspberry Awards.


## EndPoints
Para ver a documentação acesse: 
http://localhost:8081/api/swagger-ui/index.html#/
----

- Obtém os filmes vencedores, informando o ano.

http://localhost:8081/api/movie/{year}

- Obtém os anos com mais de um filme vencedor.

http://localhost:8081/api/movie/years

- Remove um filme especifico pelo seu identificador.

http://localhost:8081/api/movie/{id}

- Obtém os produtores premiados, com maior e menor periodo de anos

http://localhost:8081/api/winners/interval-prizes

- Obtém os estudios premiados

http://localhost:8081/api/studio/winners

----
## Testes
Para executar os testes, acesse a classe desejada, clique em Run -> Run As -> JUnit Test.
Isso fará com que os testes da classe integração sejam executados.
----

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. 
Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/*.
