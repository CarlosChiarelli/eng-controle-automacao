%% Exercicio A com otimização de topo
clear
clc
close all
%fixos
b5 = 14.16;
b6 = 10.7; 
b8 = 50009.5;  
b16 = 15;
b17 = 1;
b44 = 1;
Pfab = 3644;
EffFab = 29.56;
TgasesFab = 559;

b7 = 3.5; % alteravel perda carga do ar na câm. combustão 8
b9 = 1068; % alteravel temperatura máxima do ciclo 10
b10 = 0.84:0.001:0.86; % alteravel eficiência isentrópica do compressor  1
b11 = 0.85:0.001:0.87; % alteravel eficiência isentrópica na expansão 1
b12 = 0.96:0.01:0.99; % alteravel rendimento eletro-mecânico 4

totalOtimo = 100;
bestB7 = 0;
bestB9 = 0;
bestB10 = 0;
bestB11 = 0;
bestB12 = 0;

for val_b7 = b7
    ok = 'ok'
   for  val_b9 = b9
        for  val_b10 = b10
            for  val_b11 = b11
                for  val_b12 = b12
                    [PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,val_b7,b8,val_b9,val_b10,val_b11,val_b12,b16,b17,b44);
                    desvioPor = desvioRelFab(PotLiq,Pfab);
                    desvioEff = desvioRelFab(Eff,EffFab);
                    desvioTgases = desvioRelFab(Tgases,TgasesFab);
                    sumOtimo = abs(desvioPor)+abs(desvioEff)+abs(desvioTgases);
                    if(sumOtimo<totalOtimo)
                       totalOtimo = sumOtimo;
                       bestB7 = val_b7;
                       bestB9 = val_b9;
                       bestB10 = val_b10;
                       bestB11 = val_b11;
                       bestB12 = val_b12;
                    end
                end
            end
        end
   end
end

[PotLiq,Eff,Tgases] = turboSimulationA(b5,b6,bestB7,b8,bestB9,bestB10,bestB11,bestB12,b16,b17,b44)

desvioPot = desvioRelFab(PotLiq,Pfab)
desvioEff = desvioRelFab(Eff,EffFab)
desvioTgases = desvioRelFab(Tgases,TgasesFab)
perda_carga_do_ar_na_cam_combustao = bestB7 %3.5
temperatura_maxima_do_ciclo = bestB9 %1068
eficiencia_isentropica_do_compressor = bestB10 %.85
eficiencia_isentropica_do_expansor = bestB11 %.86
eficiencia_eletro_mecanica = bestB12 %.96


function c = desvioRelFab(sim,fab)
    c =((sim-fab)/fab)*100;
end