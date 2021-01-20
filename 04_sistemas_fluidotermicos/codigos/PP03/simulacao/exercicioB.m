%% Exercicio A

c4 = 81.65;
c6 = 53500;
c7 = 15800 ;
c9 = 22.2;
c10 = 25.7;
c16 = 15 ; % TEMP EXT ISO
c18 = 101.325; %P EXT ISO 
h6 = -49.35; % SITE TEMP -> 8977 al
h8 = 26.8435; %SITE P
h27 = 793.9 + 273.15 ; %temp3
l6 = 12.5;
l8 = 260;
l12 =42800;
l14 = .87 ; %alteravel
l16 = .87 ; % alterável
l18 = 2.5/100; %alteravel
[mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,l16,l18,-1);



%% qual o impacto sobre o consumo de combustível, para o voo a altitude, por exemplo, 2.000 m abaixo
temp2000m = 35.0750
pressao2000m = 77.0585
c7= 53500;
c10 = 22.2;
m_ar = 81.65;

[mCombMexico,fEmpMexico,consumCombMexico,desvCombMexico,desvspecCombMexico,devioThrustMexico] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,l16,l18,m_ar)

temp1 = 15
pressao1 = 101.325

[mCombNivelMar,fEmpNivelMar,consumCombNivelMar,desvCombNivelMar,desvspecCombNivelMar,devioThrustNivelMar] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,h27,l6,l8,l12,l14,l16,l18,m_ar)
