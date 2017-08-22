# campanha
Teste
exercicio 1 - start no Runner

- GET - Busca Todas campanhas ativas - http://localhost:8080/campanhas
- POST - Inclui campanha - http://localhost:8080/campanhas - Json exemplo
{
	"nome":"Campanha Teste",
	"idTimeCoracao":"4",
	"dtInicioVigencia":"01/08/2017",
	"dtFimVigencia":"02/08/2017"
}

- Exclui campanha por ID - http://localhost:8080/campanhas/excluir/{idCampanha}
- Atualiza campanha por ID - http://localhost:8080/campanhas/atualizar/{idCampanha}


exercicio 3 - está imbutido no teste - rodar o teste unitario StreamSolverTest 

exercicio 4 - Deadlock é quando ocorre um erro operacional, quando duas ou mais transações/threads tentam utilizar o recurso de uma Thread na outra  e não há controle/gerenciamento desse recurso. Um dos exemplos seria a chamada de um Método de uma Thread 1  que aguarda a resposta do método da Thread 2 e esta aguarda a resposta do Método da Thread 1, assim, gerando o Deadlock. Para solução, seria necessário a anotação de sincronismo no métodos e rodaria em transação com estado salvo, para caso aconteça algum erro abrupto poder retornar como era antes.

exercico 5 - Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos fazer diversas operações de loop, filtros, maps, etc. Porém, existe uma variação bem interessante do Stream que é ParallelStreams. Descreva com suas palavras quando qual é a diferença entre os dois e quando devemos utilizar cada um deles. A principal diferença entre o Stream e o ParallelStream é a execução em paralelo do parallelStream e idependente. O uso ParallelStream deveria ser para questões de manipulação de múltiplas execuções em paralelo, para assim tornar independente  cada processo e receber a resposta mais rápido. Por outro lado, seu uso para questões simples ou execuções de um único processo gasta mais recurso do sistema, desta forma, o uso do Stream seria mais vantajoso.
