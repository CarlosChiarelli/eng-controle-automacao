%% Exercicio A
clc
close all;
b5 = 14.16;
b6 = 10.7; 
b7 = 2; % alteravel
b8 = 50009.5;  
b9 = 1067; % alteravel
b10 = 0.828; % alteravel
b11 = 0.856; % alteravel
b12 = 0.97; % alteravel
b16 = 15;
b17 = 1;
b44 = 1;
Pfab = 3644;
EffFab = 29.56;
TgasesFab = 559;

[PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,b9,b10,b11,b12,b16,b17,b44)

desvioPot = desvioRelFab(PotLiq,Pfab)
desvioEff = desvioRelFab(Eff,EffFab)
desvioTgases = desvioRelFab(Tgases,TgasesFab)


function c = desvioRelFab(sim,fab)
    c =((sim-fab)/fab)*100;
end