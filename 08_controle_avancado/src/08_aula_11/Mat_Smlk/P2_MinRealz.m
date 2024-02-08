A=[-1 -3 0;0 1 1; 0 2 0];
B=[ 1 2/3; 0 1/3; 0 1/3];
C=[1 -1 2;0  6 3];
D=[0 -1/3; 0 0];
% test controlability, observability
rank(ctrb(A,B))
rank(obsv(A,C))
eig(A);
for  s=[-1,2]
rank([s*eye(3)-A B])
end
%minimal realization
T=[1 -2/3 -1/3;0 -1/3 1/3; 0 2/3 1/3];
A_bar=T^-1*A*T;
B_bar=T^-1*B;
C_bar=C*T;
D_bar=D;
%sysT_ss<=[Amin,Bmin,Cmin,Dmin];
sysMin=minreal(ss(A_bar,B_bar,C_bar,D_bar))
%transfer function
G=tf(ss(A,B,C,D)) %numerical difficulties
Gmin=tf(ss(sysMin))
tzero(sysMin);
tzero(G(1,2));
tzero(Gmin(1,2)); %choose G(i,j)
pole(Gmin(2,2));
