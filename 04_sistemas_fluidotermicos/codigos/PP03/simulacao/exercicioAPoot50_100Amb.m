%% Exercicio A com temp ambiente
clear
clc
close all
%fixos
b5 = 14.16;
b6 = 10.7; 
b8 = 50009.5;  
b16 = 15; %vario temperaura ambiente 0~40 
b17 = 1;
b44 = 1;
Pfab = 3644;
EffFab = 29.56;
TgasesFab = 559;

b7 = 3.5; % parametro encontrados na otimizacao de topo 
b9 = 800:1:1068; % parametro encontrados na otimizacao de topo
b10 = .85; % parametro encontrados na otimizacao de topo  
b11 = .86; % parametro encontrados na otimizacao de topo 
b12 = .96; % parametro encontrados na otimizacao de topo
PotComp = [];
EffComp     = [];
TgasesComp  = [];
desvioPorComp = [];
desvioEffComp     = [];
desvioTgasesComp  = [];

for val_b9 = b9
    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,val_b9,b10,b11,b12,b16,b17,b44);
    desvioPor = desvioRelFab(PotLiq,Pfab);
    desvioEff = desvioRelFab(Eff,EffFab);
    desvioTgases = desvioRelFab(Tgases,TgasesFab);
    PotComp = [PotComp;PotLiq];
    EffComp = [EffComp;Eff];
    TgasesComp = [TgasesComp;Tgases];
    desvioPorComp = [desvioPorComp;desvioPor];
    desvioEffComp = [desvioEffComp;desvioEff];
    desvioTgasesComp = [desvioTgasesComp;desvioTgases];
end

figure()

subplot(2,3,1)
plot(b9,PotComp)
title('TempMax x Pot')
subplot(2,3,2)
plot(b9,EffComp)
title('TempMax x Eff')
subplot(2,3,3)
plot(b9,TgasesComp)
title('TempMax x Tgases')
subplot(2,3,4)
plot(b9,desvioPorComp)
title('TempMax x DesvioPot')
subplot(2,3,5)
plot(b9,desvioEffComp)
title('TempMax x DesvioEff')
subplot(2,3,6)
plot(b9,desvioTgasesComp)
title('TempMax x DesvioTgases')


function c = desvioRelFab(sim,fab)
    c =((sim-fab)/fab)*100;
end
