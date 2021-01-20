%% função simulação turbina exercicio A
% b5 = Fluxo de ar no compressor
% b6 = relação de pressões na compressão 
% b7 = perda carga do ar na câm. combustão 
% b8 = PCI do gás natural
% b9 = temperatura máxima do ciclo 
% b10 = eficiência isentrópica do compressor 
% b11 = eficiência isentrópica na expansão
% b12 = rendimento eletro-mecânico
% b16 = temperatura Compressor
% b17 = pressão compressor
% b44 = pressão turbina

function [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,b7,b8,b9,b10,b11,b12,b16,b17,b44)
CelsiusToKelvin = 273.15;
AtmTokPa = 101.325;

d9 = b9+CelsiusToKelvin; %temperatura maxima de ciclo em kelvin

%% Compressor entrada de ar
d16 = b16+CelsiusToKelvin;  %temp de entrada de ar
d17 = b17*AtmTokPa;         %pressao de entrada de ar
[h18,i18] = interpolAirT_h_pr(d16,'T');
b18 = h18;                  %Entalpia
b19 = i18;                  %Pr

%% Compressor saída de ar
b23 = b6*b17; %pressao
b25 = b6*b19; %pr
d23 = b23*AtmTokPa;
[h24,g24] = interpolAirT_h_pr(b25,'pr');
d22 = g24; %temperatura
b22 = d22 - CelsiusToKelvin;
b24 = h24 ;%entalpia
b26 = ((b24-b18)/b10)+b18; %Entalpia (REAL - efic. isoentrópica)
[g28,ni] = interpolAirT_h_pr(b26,'h');
b27 = g28; %temperatura (REAL - efic. isoentrópica)
b30 = (b5*(b24-b18))/b10; %Potência do compressor (mg*(h2s-h1))/h
f30 = b5*(b26-b18);%Potência do compressor (mg*(h2-h1))

%% Câmera de Combustão
b34 = b9; % temp
d34 = b34 + CelsiusToKelvin;
b35 = (1-(b7/100))*b23; % pressao
d35 = b35*AtmTokPa;
[b36,b37] = interpolAirT_h_pr(d34,'T'); %h/pr

b38 = (b5*(b36-b26))/(b8-(b36-b26)); %Fluxo másico de combustível

%% Turbina

d44 = b44 * AtmTokPa;
b46 =(b44/b35)*b37; %pr
[h45,g45] = interpolAirT_h_pr(b46,'pr'); %h/t
d43 = g45;
b43 = d43 - CelsiusToKelvin;
b45 = h45;  %entalpia(isoentrópica)
b47 = b36-(b11*(b36-b45));
[g49,ni] = interpolAirT_h_pr(b47,'h'); %t/pr
d49 = g49; %temp (REAL - efic. isoentrópica)
b48 = d49 - CelsiusToKelvin;

b51 =(b5+b38)*(b36-b45)*b11;%Potência do expansor (mg*(h2s-h1))/h
e51 =(b5+b38)*(b36-b47);%Potência do expansor (mg*(h2-h1))

%% Conclusao
PotLiq = (b51-b30)*b12;
b56 = b5*(b36-b26); %Q fornecido
Eff = (PotLiq/(b38*b8))*100;
Tgases = b48;

end
