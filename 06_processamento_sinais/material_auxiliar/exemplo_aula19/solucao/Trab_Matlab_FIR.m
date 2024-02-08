% Exercício de Aquisição de Dados sobre filtros FIR (Aula 19)
% Autor: Tiago Henrique Machado 29/10/2019
%% Preparando os dados e o sistema
clc; clear all; close all;
dado = load('solo.mat');
sinal = dado.y;
Fs = dado.Fs;
clear dado;

%% Tocando o som com o recurso audioplayer]
som_original = audioplayer(sinal,Fs);
play(som_original)

%% Analisando o sinal
% Análise no tempo
figure
plot(sinal) %desta analise o sinal começa em 159500 e termina em 180500
% Análise na frequência
N = length(sinal);
dF = Fs/N;
n = 1:1:N;
F = n.*dF;
X = fft(sinal);
plot(F,X)
X = 1/N*abs(X);
figure
plot(F,X)
axis([0,300,0,0.003])
% Da análise do espectro observa-se que o tom 247 aparece como 231.9
% Assim, é preciso aumentar a frequencia em 1.0647 
% Aproximadamente L=232 M=247 onde L é o expansor/interpolador e M é o paramentro do decimador

%% Identificando a porção temporal onde ocorre o erro (para quem tem o ouvido afinado, consegue identificar que é na sétima nota tocada)

%  NÃO ENTENDI pq ele deixa a última nota de fora da análise
aux = sinal(16000:180000); 
%aux = sinal(66000:76000);%aux = sinal(82000:92000);aux = sinal(97000:108000);
%aux = sinal(112500:125000);aux = sinal(145000:156000);aux = sinal(182000:240000);
N_aux = length(aux);
dF_aux = Fs/N_aux;
n = 0:1:N_aux-1;
F = n.*dF_aux;
Y = fft(aux);
Y = 1/N_aux*abs(Y);
figure
plot(F,Y)
axis([0,300,0,0.003])

%% Trabalhando com o sinal incorreto
x = sinal(160000:180000);
% Analisando o espectro do sinal 
N_x = length(x);
dF_x = Fs/N_x;
n = 0:1:N_x-1;
F = n.*dF_x;
Y = fft(x);
Y = 1/N_x*abs(Y);
figure
plot(F,Y)
axis([0,300,0,0.02])

%% Realizando o expansor
L = 232;
M = 247;
xe = zeros(1,length(x)*L);

for i = 1:length(xe)
    if (mod(i,L)==00)
      xe(i) = x(i/L);
    end
end

% Forma alternativa de realizar a expansão
% for i = 1:length(x)
%    xe(i*L) = x(i);
% end
 
%% Realizando a interpolação
% Gerando o filtro Barlett
%  for k =1:(2*L)
%      inter(k+1) = 1- abs(k-L)/L ;
%  end

% Forma alternativa de gerar o filtro Barlett usando o comando barlett
inter = bartlett(L);
% Fazendo a convolução do filtro com o sinal incorreto
xe2 = filter(inter,1,xe);
% Analisando o espectro do sinal interpolado 
N_xe2 = length(xe2);
dF_xe2 = Fs/N_xe2;
n = 0:1:N_xe2-1;
F = n.*dF_xe2;
Ye = fft(xe2);
Ye = 1/N_xe2*abs(Ye);
figure
plot(F,Ye)
axis([0,10,0,0.009])

%% Filtro Passa-Baixa
% Fazendo o projeto do filtro FIR usando o comando FIR1 e filtrando o sinal xe
fpb=fir1(200,pi/M);
% Fazendo a convolução do filtro com o sinal interpolado
xe3=filter(fpb,1,xe2);
% Analisando o espectro do sinal  xe3
N_xe3 = length(xe3);
dF_xe3  =Fs/N_xe3;
n = 1:1:N_xe3;
F = n.*dF_xe3;
Y = fft(xe3);
Y = 1/N_xe3*abs(Y);
figure
plot(F,Y)
axis([0,300,0,0.009])

%% Realilzando a decimação
for k = 1:(length(xe3)/M)
    x_cor(k) = xe3(k*M);
end

%% Reconstrução do sinal
y = sinal(1:160000-1);
x_cor = x_cor';
y = [y;x_cor];
y = [y;sinal(180000+1:end)];
som_final=audioplayer(y,Fs);
play(som_final)

% Analisando o espectro do sinal  reconstruído
N_y = length(y);
dF_y = Fs/N_y;
n = 1:1:N_y;
F = n.*dF_y;
Y = fft(y);
Y = 1/N_y*abs(Y);
figure
plot(F,Y)
axis([0,300,0,0.003])

