%% Exercicio A com otimização de topo
clear
clc
close all

%fixos
c4 = 81.65;
c6 = 53500;
c7 = 15800 ;
c9 = 22.2;
c10 = 25.7;
c16 = 15 ; % TEMP EXT ISO
c18 = 101.325; %P EXT ISO
h6 = -36.35; % SITE TEMP -> 9900 altitude
h8 = 36.1178; %SITE P
l6 = 12.5;
l8 = 260;
l12 =42800;


h27 = 793.9 + 273.15 ; %temp3 Alteravel fixo  Temperatura máxima operacional da turbina em cruzeiro
l14 = .86:.001:.88 ; %alteravel Eficiência isoentrópica do compressor
l16 = .83:.001:.85 ; % alterável Eficiência isoentrópica do expansor
l18 = .02; %alteravel Eficiências isoentrópicas no difusor e no bocal



totalOtimo = 100;
besth27 = 0;
bestl14 = 0;
bestl16 = 0;
bestl18 = 0;
bestB12 = 0;



for val_h27 = h27
   for  val_l14 = l14
        for  val_l16 = l16
            for  val_l18 = l18
                [mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,val_h27,l6,l8,l12,val_l14,val_l16,val_l18,-1);
                sumOtimo = abs(desvComb)+abs(desvspecComb)+abs(devioThrust);
                if(sumOtimo<totalOtimo)
                    totalOtimo = sumOtimo;
                    besth27 = val_h27;
                    bestl14 = val_l14;
                    bestl16 = val_l16;
                    bestl18 = val_l18;
                end
            end
        end
   end
end


[mComb,fEmp,consumComb,desvComb,desvspecComb,devioThrust] = turboSimulationB(c4,c6,c7,c9,c10,c16,c18,h6,h8,besth27,l6,l8,l12,bestl14,bestl16,bestl18,-1)
besth27 %
bestl14 %.87
bestl16 %.84
bestl18 %.02