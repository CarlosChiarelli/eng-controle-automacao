%% função simulação carro


function [] = motorSimulationCosworth(C6,C7,C8,C9,C10,C11,E9,D13,F12,C17,A28,H28)
D12 = 1- D13;
F14 = D12*114+D13*46;
I6 = 10.4*4.1868;
I7 = 6.75*4.1868;
C14 = 8*D12+2*D13;
C15 = 18*D12+D13*6;
C16 = 1*D13;
C21 = C14;
C22 = C15/2;
C23 = (C21*2+C22-C16)/2;
B28 = (C23*C17);
C28 = (C23*C17*3.76);
G28 = ((2*C17*C23)-(C15/2)-(2*C14))/(-2+1-(1/F12));
E28 = (C15/2)-G28;
E28 = G28/F12;
H29 = (B29-B24);
D28 = (C14-E28);





end







