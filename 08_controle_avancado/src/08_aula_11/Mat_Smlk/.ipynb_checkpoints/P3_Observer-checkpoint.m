%  %1o Exemplo
%  A=[-1 0; 1 -1];
%  B=[2;0];
%  C=[0 0.5];
%  D=0;
%  L=acker(A',C',[-4 -4])'
%  eig(A-L*C)
 
 %2o Exemplo
 A =[1.8097 -0.8187; 1 0];
 B = [0.5; 0];
 C =[0.1810 -0.1810];
 D = 0;
 L=acker(A',C',[.5 .7])'
 eig(A-L*C)
 
 x=[-1;1]; % initial state
 xhat=[0;0]; % initial estimate
 XX=x;
 XXhat=xhat;
 T=40;
 UU=.1*ones(1,T); % input signal
 for k=0:T-1,
 u=UU(k+1);
 y=C*x+D*u;
 yhat=C*xhat+D*u;
 x=A*x+B*u;
 xhat=A*xhat+B*u+L*(y-yhat);
 XX=[XX,x];
 XXhat=[XXhat,xhat];
 end
 plot(0:T,[XX(1,:);XXhat(1,:)]);
 
     
