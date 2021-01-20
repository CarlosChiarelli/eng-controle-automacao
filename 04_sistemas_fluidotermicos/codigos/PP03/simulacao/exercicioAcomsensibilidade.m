%% Exercicio A com otimização de topo
clc
clear
close all
%fixos
b5 = 14.16;
b6 = 10.7; 
b7 = 2; % alteravel perda carga do ar na câm. combustão 
b8 = 50009.5;  
b9 = 1067; % alteravel temperatura máxima do ciclo 
b10 = 0.828; % alteravel eficiência isentrópica do compressor 
b11 = 0.856; % alteravel eficiência isentrópica na expansão
b12 = 0.97; % alteravel rendimento eletro-mecânico
b16 = 15;
b17 = 1;
b44 = 1;
Pfab = 3644;
EffFab = 29.56;
TgasesFab = 559;
Pfab = 3644;
EffFab = 29.56;
TgasesFab = 559;

%% b7
sensibilidade = 1:1:5; % alterar sens b7
PotB7       = [];
EffB7       = [];
TgasesB7    = [];

PotComp = [];
EffComp     = [];
TgasesComp  = [];

for sens = sensibilidade
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,sens,b8,b9,b10,b11,b12,b16,b17,b44);
    PotB7 = [PotB7;PotLiq];
    EffB7 = [EffB7;Eff];
    TgasesB7 = [TgasesB7;Tgases];
end
PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];
figure()

subplot(5,3,1)
plot(sensibilidade,PotB7)
title('P.Carga do Ar na Câm. Comb. x Pot')
subplot(5,3,2)
plot(sensibilidade,EffB7)
title('P.Carga do Ar na Câm. Comb. x Eff')
subplot(5,3,3)
plot(sensibilidade,TgasesB7)
title('P.Carga do Ar na Câm. Comb. x Tgases')

%% B9
sensibilidade = 1060:1:1070; % alterar sens b7
PotB7   = [];
EffB7   = [];
TgasesB7  = [];

for sens = sensibilidade
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,sens,b10,b11,b12,b16,b17,b44);
    PotB7 = [PotB7;PotLiq];
    EffB7 = [EffB7;Eff];
    TgasesB7 = [TgasesB7;Tgases];
end

PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];

subplot(5,3,4)
plot(sensibilidade,PotB7)
title('Temperatura máxima do ciclo x Pot')
subplot(5,3,5)
plot(sensibilidade,EffB7)
title('Temperatura máxima do ciclo x Eff')
subplot(5,3,6)
plot(sensibilidade,TgasesB7)
title('Temperatura máxima do ciclo x Tgases')

%% B10
sensibilidade = 0.8:0.001:0.9; % alterar sens b10
PotB7   = [];
EffB7   = [];
TgasesB7  = [];

for sens = sensibilidade
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,b9,sens,b11,b12,b16,b17,b44);
    PotB7 = [PotB7;PotLiq];
    EffB7 = [EffB7;Eff];
    TgasesB7 = [TgasesB7;Tgases];
end

PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];
subplot(5,3,7)
plot(sensibilidade,PotB7)
title('Eficiência Isentrópica do Compressor x Pot')
subplot(5,3,8)
plot(sensibilidade,EffB7)
title('Eficiência Isentrópica do Compressor x Eff')
subplot(5,3,9)
plot(sensibilidade,TgasesB7)
title('Eficiência Isentrópica do Compressor x Tgases')


%% B11
sensibilidade = 0.8:0.001:0.9; % alterar sens b10
PotB7   = [];
EffB7   = [];
TgasesB7  = [];

for sens = sensibilidade
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,b9,b10,sens,b12,b16,b17,b44);
    PotB7 = [PotB7;PotLiq];
    EffB7 = [EffB7;Eff];
    TgasesB7 = [TgasesB7;Tgases];
end

PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];
subplot(5,3,10)
plot(sensibilidade,PotB7)
title('Eficiência Isentrópica na Expansão x Pot')
subplot(5,3,11)
plot(sensibilidade,EffB7)
title('Eficiência Isentrópica na Expansão x Eff')
subplot(5,3,12)
plot(sensibilidade,TgasesB7)
title('Eficiência Isentrópica na Expansão x Tgases')


%% B12
sensibilidade = 0.8:0.01:0.99; % alterar sens b10
PotB7   = [];
EffB7   = [];
TgasesB7  = [];

for sens = sensibilidade
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,b9,b10,sens,b12,b16,b17,b44);
    PotB7 = [PotB7;PotLiq];
    EffB7 = [EffB7;Eff];
    TgasesB7 = [TgasesB7;Tgases];
end

PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];
subplot(5,3,13)
plot(sensibilidade,PotB7)
title('Rendimento Eletro-mecânico x Pot')
subplot(5,3,14)
plot(sensibilidade,EffB7)
title('Rendimento Eletro-mecânico x Eff')
subplot(5,3,15)
plot(sensibilidade,TgasesB7)
title('Rendimento Eletro-mecânico x Tgases')

label_v1 = categorical({'P.Carga do Ar na Câm. Comb. x Pot' 'Temperatura máxima do ciclo x Pot' 'Eficiência Isentrópica do Compressor x Pot' 'Eficiência Isentrópica na Expansão x Pot' 'Rendimento Eletro-mecânico x Pot'});
label_v2 = categorical({'P.Carga do Ar na Câm. Comb. x Eff' 'Temperatura máxima do ciclo x Eff' 'Eficiência Isentrópica do Compressor x Eff' 'Eficiência Isentrópica na Expansão x Eff' 'Rendimento Eletro-mecânico x Eff'});
label_v3 = categorical({'P.Carga do Ar na Câm. Comb. x TGases' 'Temperatura máxima do ciclo x TGases' 'Eficiência Isentrópica do Compressor x TGases' 'Eficiência Isentrópica na Expansão x TGases' 'Rendimento Eletro-mecânico x TGases'});
label_v1 = reordercats(label_v1, {'P.Carga do Ar na Câm. Comb. x Pot' 'Temperatura máxima do ciclo x Pot' 'Eficiência Isentrópica do Compressor x Pot' 'Eficiência Isentrópica na Expansão x Pot' 'Rendimento Eletro-mecânico x Pot'});
label_v2 = reordercats(label_v2, {'P.Carga do Ar na Câm. Comb. x Eff' 'Temperatura máxima do ciclo x Eff' 'Eficiência Isentrópica do Compressor x Eff' 'Eficiência Isentrópica na Expansão x Eff' 'Rendimento Eletro-mecânico x Eff'});
label_v3 = reordercats(label_v3, {'P.Carga do Ar na Câm. Comb. x TGases' 'Temperatura máxima do ciclo x TGases' 'Eficiência Isentrópica do Compressor x TGases' 'Eficiência Isentrópica na Expansão x TGases' 'Rendimento Eletro-mecânico x TGases'});
figure()
title('Relação Parâmetros por Pot.')
bar(label_v1,PotComp)

figure()
title('Relação Parâmetros por Eff.')
bar(label_v2,EffComp)

figure()
title('Relação Parâmetros por TGases.')
bar(label_v3,TgasesComp)

