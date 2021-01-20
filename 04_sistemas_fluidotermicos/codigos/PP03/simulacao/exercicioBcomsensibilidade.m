%% Exercicio A com otimização de topo
clc
clear
close all
%fixos
c4 = 81.65;
c6 = 53500;
c7 = 15800 ;
c9 = 22.2;
c10 = 25.7;
c16 = 15 ; % TEMP EXT ISO
c18 = 101.325; %P EXT ISO
h6 = -43.15; % SITE TEMP
h8 = 31.2; %SITE P
l6 = 12.5;
l8 = 260;
l12 =42800;


h27 = 1067 ; %temp3 Alteravel fixo  Temperatura máxima operacional da turbina em cruzeiro
l14 = .87 ; %alteravel Eficiência isoentrópica do compressor
l16 = .84 ; % alterável Eficiência isoentrópica do expansor
l18 = .02; %alteravel Eficiências isoentrópicas no difusor e no bocal


%% h27
sensibilidade = 1060:1:1070; % alterar sens h27
PotB7       = [];
EffB7       = [];
TgasesB7    = [];

PotComp = [];
EffComp     = [];
TgasesComp  = [];

for sens = sensibilidade
    [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,sens,l6,l8,l12,l14,l16,l18);
    PotB7 = [PotB7;mComb];
    EffB7 = [EffB7;fEmp];
    TgasesB7 = [TgasesB7;consumComb];
end
PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];
figure()

subplot(4,3,1)
plot(sensibilidade,PotB7)
title('Temp. Max. Op Turb x mComb')
subplot(4,3,2)
plot(sensibilidade,EffB7)
title('Temp. Max. Op Turb x fEmpuxo')
subplot(4,3,3)
plot(sensibilidade,TgasesB7)
title('Temp. Max. Op Turb x consumComb')


%% l14
sensibilidade = .8:.01:.9; % alterar sens l14
PotB7       = [];
EffB7       = [];
TgasesB7    = [];



for sens = sensibilidade
    [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,sens,l16,l18);
    PotB7 = [PotB7;mComb];
    EffB7 = [EffB7;fEmp];
    TgasesB7 = [TgasesB7;consumComb];
end
PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];

subplot(4,3,4)
plot(sensibilidade,PotB7)
title('Eficiência isoentrópica do compressor x mComb')
subplot(4,3,5)
plot(sensibilidade,EffB7)
title('Eficiência isoentrópica do compressor x fEmpuxo')
subplot(4,3,6)
plot(sensibilidade,TgasesB7)
title('Eficiência isoentrópica do compressor x consumComb')



%% l16
sensibilidade = .8:.01:.9; % alterar sens l16
PotB7       = [];
EffB7       = [];
TgasesB7    = [];



for sens = sensibilidade
    [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,sens,l18);
    PotB7 = [PotB7;mComb];
    EffB7 = [EffB7;fEmp];
    TgasesB7 = [TgasesB7;consumComb];
end
PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];

subplot(4,3,7)
plot(sensibilidade,PotB7)
title(' Eficiência isoentrópica do expansor x mComb')
subplot(4,3,8)
plot(sensibilidade,EffB7)
title(' Eficiência isoentrópica do expansor x fEmpuxo')
subplot(4,3,9)
plot(sensibilidade,TgasesB7)
title(' Eficiência isoentrópica do expansor x consumComb')



%% l18
sensibilidade = .01:.001:.03; % alterar sens l18
PotB7       = [];
EffB7       = [];
TgasesB7    = [];

for sens = sensibilidade
    [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,l16,sens,-1);
    PotB7 = [PotB7;mComb];
    EffB7 = [EffB7;fEmp];
    TgasesB7 = [TgasesB7;consumComb];
end
PotComp = [PotComp (abs(PotB7(1)-PotB7(end)))];
EffComp = [EffComp (abs(EffB7(1)-EffB7(end)))];
TgasesComp = [TgasesComp (abs(TgasesB7(1)-TgasesB7(end)))];

subplot(4,3,10)
plot(sensibilidade,PotB7)
title(' Eficiências isoentrópicas no difusor e no bocal x mComb')
subplot(4,3,11)
plot(sensibilidade,EffB7)
title(' Eficiências isoentrópicas no difusor e no bocal x fEmpuxo')
subplot(4,3,12)
plot(sensibilidade,TgasesB7)
title(' Eficiências isoentrópicas no difusor e no bocal x consumComb')


label_v1 = categorical({'Temp. Max. Op Turb x mComb' 'Eficiência isoentrópica do compressor x mComb' ' Eficiência isoentrópica do expansor x mComb' ' Eficiências isoentrópicas no difusor e no bocal x mComb'});
label_v2 = categorical({'Temp. Max. Op Turb x F.Empuxo' 'Eficiência isoentrópica do compressor x F.Empuxo' ' Eficiência isoentrópica do expansor x F.Empuxo' ' Eficiências isoentrópicas no difusor e no bocal x F.Empuxo'});
label_v3 = categorical({'Temp. Max. Op Turb x consumComb' 'Eficiência isoentrópica do compressor x consumComb' ' Eficiência isoentrópica do expansor x consumComb' ' Eficiências isoentrópicas no difusor e no bocal x consumComb'});
label_v1 = reordercats(label_v1 ,{'Temp. Max. Op Turb x mComb' 'Eficiência isoentrópica do compressor x mComb' ' Eficiência isoentrópica do expansor x mComb' ' Eficiências isoentrópicas no difusor e no bocal x mComb' });
label_v2 = reordercats(label_v2 ,{'Temp. Max. Op Turb x F.Empuxo' 'Eficiência isoentrópica do compressor x F.Empuxo' ' Eficiência isoentrópica do expansor x F.Empuxo' ' Eficiências isoentrópicas no difusor e no bocal x F.Empuxo' });
label_v3 = reordercats(label_v3 ,{'Temp. Max. Op Turb x consumComb' 'Eficiência isoentrópica do compressor x consumComb' ' Eficiência isoentrópica do expansor x consumComb' ' Eficiências isoentrópicas no difusor e no bocal x consumComb' });

figure()
title('Relação Parâmetros por M.Comb.')
bar(label_v1,PotComp)

figure()
title('Relação Parâmetros por fEmpuxo.')
bar(label_v2,EffComp)

figure()
title('Relação Parâmetros por consumComb.')
bar(label_v3,TgasesComp)

