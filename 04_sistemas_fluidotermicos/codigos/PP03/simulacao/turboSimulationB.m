%% função simulação turbina exercicio B
% c4 - m_air @ ISSO
% c5 - pres_ratio
% c6 - Thrust_max
% c7 - Thrust_cruise
% c9 - Fuel specific consumption at take-off
% c10 - Fuel specific consumption at cruise
% c16 - T_ISO ºC
% c18 - p_ISO kPa
% h6 - Tamb -> https://www.digitaldutch.com/atmoscalc/index.htm
% h8 -> pamb ->https://www.digitaldutch.com/atmoscalc/index.htm
% h27 T3 -> ???????????????? Onde consigo essa entalpia?
% l6 -> rp comp = c5 
% l8 -> V in O que exatamente é L8 ?
% l12 -> 42800

function [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,l16,l18,mar)
%% Dados Iniciais
CelsiusToKelvin = 273.15;
AtmTokPa = 101.325;
R_ar = 8.314/28.96; %kJ/kg.C
pi;
MtoInches = 39.3701;

h7 = h6 + CelsiusToKelvin; %cruise temp
c17 = c16 + CelsiusToKelvin; %takeoff temp
c19 = c17*R_ar/c18 ; %v_iso PV = RT

[h9,h10] =  interpolAirT_h_pr(h7,'T'); %h/pr ambiente ext cruise

%% Consumo de combustível
c12 = c9*c6/1000; % take off em gramas/s
c13 = c10*c7/1000; %cruise em gramas/s 


%% Dados de Cruise 

l20 = R_ar;
l22 = l20*h7/h8; %v
l23 = 1/l22; % densidade
l10 = c4*c19/l22;
if mar > 0
    l10 = mar;
end
l25 = l10*l22; %ari flow
l27 = l25/l8; %area frontal
l29 = (l27*4/pi)^0.5; %diametro
l30 = l29*MtoInches; %diametros em polegadas


%% %% Propriedades Avião

h15 = h9+(((l8^2)/2)/1000); %h1
[h12,h13]=interpolAirT_h_pr(h15,'h');%T1/pr1 
h14 = h13/h10*h8; %p1

h18 = h13*l6; %h2
[h20,h17]=interpolAirT_h_pr(h18,'pr');%h2s/T2s 
h19 = l6*h14; %p2

h24 = h15+(h20-h15)/l14; % h2
[h22,ni]=interpolAirT_h_pr(h18,'pr');%%T2/pr2 
h23 = h19; %p2

h29 = h23*(1-l18);


[h30,h28]=interpolAirT_h_pr(h27,'T');%%T3/pr3 

%Resultados Compressor e Exaustor
q6 = (l10*(h30-h24))/(l12-h30);
mComb = q6; 
q8 = q6 * 3600;
q10 = l10*(h24-h15);%w compressor
q11 = q10/l10;
q12 = q10; %w expanssor
q13 = q12/(l10+q6);

% Exaustão
h35 = h30-(q12/((l10+q6)*l16)); %h4
[h32,h33]=interpolAirT_h_pr(h35,'h');%%T4s/pr4s
h34 = h29/(h28/h33) ; %p4

h40 = h30-l16*(h30-h35); 
[h37,h38]=interpolAirT_h_pr(h40,'h');%%T4/pr4
h39 = h34;

h44 = h8;  %pressao de saida
h43 = h38/(h39/h44);%AQUI COMEÇA PROPAGACAO DO ERRO

[h42,h45]=interpolAirT_h_pr(h43,'pr');%h5/T5

%% Resultados finais
r14 = (2*(h40-h45)*1000)^0.5;  % ?? tbm nao entendi o q raios é essa formula

q16 = (l10+q6)*r14-(l10*l8);
fEmp = q16;
r18 = q16*0.22481; % ?? tbm nao entendi pq .22481????
q19 = q8/(q16/1000);
q20 = q19/101.973;
r22 = q16/l10;
r23 = (q6*1000)/(q16/1000);
consumComb = r23;
desvComb = (q6/c13);
desvspecComb = (r23/c10)-1;
devioThrust = q16/c7-1;


end







