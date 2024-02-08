# Faculdade de Engenharia Mecânica (FEM) da UNICAMP

```
2 º Semestre de 2019 – Trabalho Final
```
# Identificação de Falhas em Sistemas Rotativos

Entende-se por máquina rotativa qualquer maquinário que apresente partes girantes. De forma
geral, uma máquina rotativa é constituída de eixos, discos, mancais e acoplamentos. Esse tipo de sistema
rotativo representa a maior e mais importante classe de maquinário, utilizada para o transporte de meios
fluídos, usinagem e conformação de materiais, geração de energia, propulsão naval e aeronáutica, dentre
tantas outras aplicações.

Assim como qualquer outro sistema, estas máquinas estão sujeitas a falhas que podem
comprometer seu correto funcionamento. Desta forma, dentro do novo conceito da Indústria 4.0,
diversos sensores monitoram os diferentes componentes destas máquinas. Dentro dos sensores
utilizados, os sensores que medem a vibração destas máquinas, são os que podem apresentar os melhores
indicativos de algum tipo de falha nos componentes do sistema.

E dentre as diversas falhas que ocorrem em sistemas rotativos, as que ocorrem de maneira mais
recorrente são: o desgaste dos mancais, o empenamento do eixo, o surgimento de trinca no eixo e o
desalinhamento nos acoplamentos. Estes sinais têm características no espectro de frequências que
permitem identificá-los e analisar o seu desenvolvimento de forma a estabelecer critérios para uma
manutenção preventiva antes que um problema mais grave ocorra.

A análise do espectro de frequência de sinais oriundos da vibração de sistemas rotativos tem uma
particularidade, frequências negativas são úteis, uma vez que indicam modos operacionais de vibração
do sistema quando a direção do movimento de precessão acontece em sentido oposto ao movimento de
giro próprio (também conhecido como ‘spin’). Sendo assim, é necessário que se faça uma análise do
espectro completo do sistema (‘também conhecido como ‘fullspectrum’’).

Desta forma, para avaliar o conteúdo em frequência dos sinais de vibração oriundos de sistemas
rotativos, deve-se fazer o seguinte procedimento:

```
✓ montar um vetor girante complexo na forma p = x +jy, onde x é a vibração do sistema na direção
horizontal e y é a vibração do sistema na direção vertical;
```
```
✓ aplicar a transformada de Fourier para o sinal complexo p;
```
```
✓ avaliar as frequências com maior relevância na amplitude do sinal.
```

Um ponto importante a se considerar, é como fazer a transformada de Fourier de maneira correta,
principalmente quando for utilizada diretamente a função fft do software Matlab. Desta forma, deve-se
seguir o seguinte procedimento para se obter o fullspectrum de um dado sinal:

```
✓ para remover os ganhos em frequência 0 dos sinais, subtraia a respectiva média de todos os
pontos dos vetores x e y antes de montar o vetor complexo p;
```
```
✓ utilize a função fft do Matlab para o sinal p/N, onde N é o tamanho do seu vetor p;
```
```
✓ corrija o espectro, uma vez que a resposta da função fft do Matlab é dada de 0 a frequência
final, mas devido às propriedades de simetria da FFT, na verdade esta deve ser de - (frequência
final)/2 a +(frequência final)/ 2. Para fazer esta correção no Matlab, basta usar a função fftshift;
```
```
✓ corrija a fase do sinal, porque a parte negativa do espectro é o complexo conjugado do que
interessa, ou seja, a parte negativa deve ser convertida para seu complexo conjugado.
```
Feito o procedimento descrito anteriormente, a saída será um vetor complexo contendo o
fullspectrum do sinal. Para efeitos práticos, analisa-se o módulo deste espectro.

Tendo as informações descritas anteriormente, o objetivo deste trabalho é identificar, através de
uma metodologia a ser decidida pelo grupo, qual falha está presente em um determinado sinal de
vibração medido e fornecido do sistema. O problema aqui é que estes sinais foram corrompidos por
ruídos de medição e outras influências externas inerentes ao ambiente de funcionamento das máquinas.

A tarefa de cada grupo será, a partir de um conjunto de dados de uma determinada falha, identificar
um padrão próprio da falha, excluindo os outros efeitos não aleatórios e inerentes ao sistema. Para isso,
provavelmente, será necessária aplicação de filtros e outros ajustes no sinal, de forma a eliminar os
conteúdos indesejados, deixando somente as informações úteis para a identificação da falha.

```
Serão fornecidos os seguintes dados para os grupos:
```
```
✓ dados do sistema sem nenhum tipo de falha e sem estar corrompido por nenhum tipo de
interferência para ser usado como referência. A pasta contendo estes dados está nomeada como
‘Dados sem Falha’. Obs: Os grupos deverão notar que este sinal sem falha deve apresentar um
conteúdo em frequência somente relativo ao desbalanceamento inerente presente em sistemas
rotativos;
```
```
✓ dados do sistema com a falha 1, que está em uma pasta nomeada como ‘Dados Falha 1’;
```
```
✓ dados do sistema com a falha 2 , que está em uma pasta nomeada como ‘Dados Falha 2 ’.
```
Uma observação importante a se fazer aqui: todos os dados foram aquisitados com a mesma
frequência de amostragem e com a mesma quantidade total de tempo. Além disso, a velocidade de
rotação da máquina foi a mesma para todos os casos, _ω_ r = 80Hz ou _ω_ r = 48 00rpm. Isso implica que a
mesma energia estava sendo fornecida para todos os casos.


```
Com os dados fornecidos, cada grupo deverá seguir o seguinte procedimento:
```
```
✓ escolher 6 casos dentre os 10 fornecidos na pasta de cada uma das falhas, ou seja, serão
analisados um total de 12 casos com falhas;
```
```
✓ para cada um do conjunto de 6 casos de uma determinada falha, analisar o espectro do sinal de
forma a identificar o que é o padrão daquela falha e o que é somente ruído ou interferência;
```
```
✓ identificado o padrão, aplicar as técnicas de processamento de sinais vistas durante a disciplina
para ‘limpar’ o sinal, ou seja, retirar todo tipo de ruído e interferência e deixar somente o
conteúdo relativo ao padrão daquela determinada falha.
```
Após fazer este procedimento, o grupo deve identificar qual o tipo da falha presente em cada um
dos dois conjuntos de dados. Para isso, utilize a Tabela 1, que contém as características típicas das
principais falhas em sistemas rotativos no fullspectrum dos sinais de vibração. Vale destacar que, a
maioria das falhas apresentam conteúdos no espectro em frequências que são funções diretas da
velocidade de rotação. Por exemplo, na Tabela 1, ao falar que a falha por desalinhamento apresenta
conteúdos em 2x e 3x, significa que ela vai apresentar conteúdo em 2 vezes a velocidade de rotação e
em três vezes a velocidade de rotação, que para o caso analisado aqui seriam em 160Hz e 240Hz.

```
Tabela 1: Sintomas no Conteúdo em Frequência de Falhas Típicas de Sistemas Rotativos
```
```
Tipo de Falha Sintomas no Conteúdo em Frequência
Desalinhamento Surgimento de Componentes em 2x e 3x
Empenamento Surgimento de Componentes em 0,5x e 1,5x
Desgaste do Mancal Surgimento de Componentes em -1x e -2x
Trinca do Eixo Surgimento de Componentes em 2x e -1x
```
```
Outras informações importantes com relação a este trabalho:
```
```
✓ os trabalhos devem ser desenvolvidos em grupos de até duas pessoas;
```
```
✓ os trabalhos devem ser entregues em uma versão escrita, contendo o detalhamento de tudo que
foi feito, apresentando gráficos, discussões e conclusões acerca do que é pedido. Além disso,
em anexo, deve ser colocado o código em Matlab, ou software semelhante, utilizado para gerar
os resultados;
```
```
✓ os trabalhos também serão brevemente apresentados pelos grupos nos dias 21/11 ou 26/ 11
(apresentações de cerca de 10 minutos). As datas de apresentação de cada grupo serão definidas
em sorteio realizado na sala de aula;
```

```
✓ será valorizada a capacidade criativa dos grupos para resolver o problema e forma detalhada
como o procedimento for realizado e detalhado tanto na versão escrita como na apresentação;
```
```
✓ como cada grupo utilizará somente 12 dos 20 dados com falha fornecidos, e também, a forma
de se chegar as conclusões corretas pode ser bem diversa, estatisticamente é praticamente
impossível que se tenham dois trabalhos iguais simplesmente por ‘coincidência’.
```
# Desenvolvimento

Este projeto foi desenvolvido em **Python 3**. 

O arquivo principal se encontra no jupyter notebook **projetoFINAL.ipynb**