% carrega o audio e dps identifica qual nota está errada

% primra parte: identificar quem está errado

% limpando varaiveis
% clc; clear all; close all;

%dado = load(solo.mat)
%sinal = dado.y;

% fs sinal q foi amostrado
% data eh o sinal no tempo

sinal = data;
Fs = fs;

som_original = audioplayer(sinal, Fs);
play(som_original)

% fft da a transformada do sinal mas nao equlizada
% entao dps de trasnformar e pegar a quantidade de pontos do sinal

% criar um vetor de freqeucnias (tamanho do sinal original)
% para monta-lo comeco com frequencia baixa e vou caminhando de um delta f
% (Fs / numPontos)

% armazenar o tamanho do sinal antes da fft e dps criar o vetor de
% freqaeuncias para plotar o sinal trasnformado

N = length(sinal);
dF = Fs/N;
n = 1:1:N;
F = n.*dF;

% fazer fft
X = fft(sinal);
X = (1/N)*abs(X);

% ver como está o sinal
plot(F,X)
% pela te=abela as frequencias estão aqui
axis([0,300,0,0.003]); % a freq de interesse é até 263, por isso até 300

% plota, tools -> data cursor (clica no pico)
% vemos q Sí está em 232 mas deveria estar em 247 (está errada) !!!

% achar um fator de escala alpha que é quanto preciso andar para corrigir
% uma nota
% (relação do que eu quero para o q eu tenho)
% freq (pico) errado / freq (pico) certo = 1.0647 = alpha

% CORRIGIR NOTA

% plotar sinal de audio
plot(sinal)
% amplitudes maiores eh onde tenho conteudo do sinal

% tenho que dividir o sinal em 8 regioes
% preciso ver na mão (cursor) e ver onde os picos começam
% (fft não segue a ordem no tempo por isso preciso fazer todos)
% quebro em cada bloco para identificar onde está a frequencia

% olhava cada pedaço e fazia fft daquele pedaço

% sinal que eu quero corrigir
x = sinal(160000:180000)

% amplio o sinal: preencho com zero (L zeros) entre os valores do sinal e interpolo
% ele
L = 232;
M = 247;
xe = zeros(1, length(x)*L);

for i = 1:length(x)
    xe(i*L) = x(i);
end

% aplicar um filtro FIR
% posso implementar um filtro em for ou aplico o filtro identificando qual
% eh o filtro (bartlett)
inter = bartlett(L);
% fazendo a convolucao
xe2 = filter(inter,1,xe);
% analisando o espectro do sinal interpolado
N_xe2 = length(xe2);
dF_xe2 = Fs/N_xe2;



% filtro passa baixa
fpb = fir1(200, pi/M);

xe3=filter(fpb,1,xe2);

N_xe3 = length(xe3);








% reamostrar esse sinal



% construir esse sinal expandido






