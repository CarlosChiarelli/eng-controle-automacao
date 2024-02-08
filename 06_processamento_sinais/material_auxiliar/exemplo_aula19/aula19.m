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
axis([0,300,0,0.003]); % a freq de interesse é até 263, por isso até 300







