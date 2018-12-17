# **Algorithmic Composer**: Gerador de métodos e funções algorítmicas para auxílio a composição.

No processo de composição, muitas vezes criamos métodos que podem serem descritos de forma lógica e procedural. Na tentativa de reproduzir a lógica humana no processo de composição, foi desenvolvido um algoritmo com diversos métodos geradores que realizariam parte desse processo.

O algoritmo desenvolvido trabalha em conjunto com o software “Lilypond”, pois sua entrada, realizada em formato de texto, poderia ser obtiva através de uma saída primária de qualquer linguagem de programação, respeitando suas regras e formatação internas.
Com base nas regras internas do Lilypond, se pode criar uma lógica para cada método desejado, utilizando conversão numérica básica, módulo e regras de conversão.

Com base nas regras internas do Lilypond, se pode criar uma lógica para cada método desejado, utilizando conversão numérica básica, módulo e regras de conversão.
&nbsp;
## Geradores

Processos de composição que são descritos de forma lógica foram agrupados com a descrição de geradores, pois com eles, através de sua lógica interna, nasceriam possíveis motivos composicionais. Os geradores são IntervalExpander, RhythmicFractals, Ostinato que é constituído por Simple e Grupeto, NoteExtender e por fim Crossover.

&nbsp;

### 1 - IntervalExpander:
> _https://github.com/tmikulsk1/Algorithmic_Composer/blob/master/src/Composition/IntervalExpander.java_

O expansor intervalar é um método desenvolvido que expande uma nota de acordo com a distância desejada pelo compositor até o limite imposto. Após realizar a sequência base de expansão, e gerar uma lista com as três posições iniciais, é seguida a seguinte lógica: generated_list[i] = generated_list[i - 2] + interval, onde i é a posição atual que inicia em 3 (sendo a lista de 0 a n).

A função possui como parâmetros de entrada a nota – pitch, oitava inicial – initialOctave, intervalo – interval, repetições – repetitions e nota de limite – limitNote.

A nota é definida pela entrada de um número que vai de 0 a 11 e que representa respectivamente as notas de dó a si. A oitava é utilizada para multiplicar a nota e obter um número semelhante a representação da nota MIDI (64, 50, etc.) e que será incrementado pelo intervalo definido. A repetição é um parâmetro de execução que define quantas vezes a lógica será executada que em conjunto com a nota de limite, atém o número máximo de expansão da sequência. 

A saída da função de expansão é uma lista de inteiros que forma um conjunto de notas normalmente representadas pelo protocolo MIDI. Este conjunto é convertido para um objeto que retorna um conjunto de 0 a 11e guarda a informação de sua respectiva oitava. 

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062763-1625b880-0190-11e9-8bd3-05f08c60e171.png">
</p>

&nbsp;

### 2 – RhythmicFractals:
> _https://github.com/tmikulsk1/Algorithmic_Composer/blob/master/src/Composition/Rhythm.java_

Para dar sequência ao expansor e adicionar ritmo, foi desenvolvido o algoritmo que gera durações seguindo a representação de fractais. Duas durações são escolhidas – representadas pelos números 1 para semibreve, 2 para mínima, 4 para semínima, 8 para colcheia, 16 para semicolcheia, 32 para fusa e 64 para semifusa, e que adiciona uma camada complexa que lembra a forma de fractais, onde temos algo semelhante a: A – A – B – A – B – A – A – B – A etc. O gerador retorna uma lista de inteiros que é somada a saída do expansor intervalar, assim, de modo a obter uma saída semelhante a imagem abaixo.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062855-846a7b00-0190-11e9-87d2-de2871aa9909.png">
</p>

Com o uso das estruturas de laço de lógica de programação, é possível inverter o resultado criando o oposto do expansor. 

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062868-951af100-0190-11e9-9a20-a750d13b91cf.png">
</p>

&nbsp;

### 3 - Ostinato;
> _https://github.com/tmikulsk1/Algorithmic_Composer/blob/master/src/Composition/Ostinato.java_

Como complemento aos métodos composicionais, foi adicionado aos geradores o ostinato em duas formas, simples e grupeto.
#### A – Simple:
O ostinato simples consiste em duas notas repetidas sequencialmente de acordo com a duração desejada.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062911-cdbaca80-0190-11e9-83f2-792a9a8d236c.png">
</p>

#### B – Grupeto:
Seguindo a lógica de repetição sequencial de notas como a do ostinato simples, a função pega um conjunto de notas definidas pelo compositor e as organiza ascendentemente e decrescentemente para gerar o que em música conhecemos como grupeto.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062919-d57a6f00-0190-11e9-8ad0-3f19c01ef84c.png">
</p>

&nbsp;

### 4 – NoteExtender
> _https://github.com/tmikulsk1/Algorithmic_Composer/blob/master/src/Composition/NoteExtender.kt_

O desenvolvimento de um método que prolonga uma sequência de notas foi um pouco mais complexo e consequentemente dividido em três etapas. A primeira etapa foi a base do método, onde expande as notas na quantidade de linhas – staffs – que o compositor deseja e pela duração desejada. Há um parâmetro que define o mínimo e máximo de repetições – prolongamento – da nota. O extensor já monta uma sequência com ritmo e sua lógica consiste em espalhar pelo número desejado de linhas as notas prolongadas.

O problema do primeiro extensor foi sua impossibilidade de execução por um músico, pois sua métrica não estava bem definida, devido a geração aleatória de durações.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062949-12defc80-0191-11e9-9ae5-4a0c51d9ed8b.png">
<p>

A segunda etapa veio para corrigir este problema e foi desenvolvida em Kotlin, devido a suas funcionalidades e facilidades. Nessa segunda versão foi criado um novo objeto que carregaria as informações das notas, durações e símbolos. Essa segunda etapa utiliza da mesma lógica que a primeira, porém, após a geração das notas e durações, uma função que corrige as durações de acordo com uma métrica pré-definida é chamada, para assim, arranjar de forma legível a saída esperada.

Essa função pega cada duração de cada nota e verifica se ela cabe em um compasso, e, caso não caiba, a nota é subdividida pela sua metade, A verificação ocorre em forma de loop e permite que a função siga somente quando o compasso é fechado, até a última nota da lista inserida.

```
var percent: Float = (denominator / finalStaff.duration[index].duration) / numerator
var compassSize = fullCompass.map { it.percent }.sum()

if (percent > 1 || compassSize + percent > 1f) {
  while (percent > 1 || compassSize + percent > 1f) {
   finalStaff = breakDuration(staff, index)
   percent = (denominator / finalStaff.duration[index].duration) / numerator
  }
}
```

A terceira etapa se deu com a necessidade de agrupar um gerador com o expansor, de forma que a métrica fosse mantida corretamente. Para isso foi criada uma função simples que soma duas linhas e gera pausas para as demais, segundo assim a composição por blocos.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50062990-491c7c00-0191-11e9-9bf5-86fe2f9e1f91.png">
</p>

&nbsp;

### 5 – SimpleCrossover:
> _https://github.com/tmikulsk1/Algorithmic_Composer/blob/master/src/Composition/Crossover.kt_

O método de crossover foi desenvolvido com base em algoritmos genéticos, onde há o cruzamento das informações das quatro linhas. A seleção é feita de modo aleatório e sua saída também passa pela função que ajusta as durações, pois somente assim é possível gerar um cruzamento também legível musicalmente.

<p align="center">
<img src="https://user-images.githubusercontent.com/21376862/50063041-926ccb80-0191-11e9-8d5b-48ccc1b6a332.png">
<p>


Cada vez que o código é executado, a saída será levemente diferente, pois na maioria dos geradores há uma seleção aleatória de notas ou durações.

