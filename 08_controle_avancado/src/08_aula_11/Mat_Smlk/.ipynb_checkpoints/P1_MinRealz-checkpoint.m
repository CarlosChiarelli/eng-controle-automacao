A=[2 0 -3; 0 0 0; 3/2 0 5/2];
B=[-1 0 -2]';
C=[1 1 -1];
O=[C; C*A; C*A*A];
rank(O)
P=[B A*B A*A*B]
rank(P)
P1=[P(1:3,1:2) [0;1;0]]
A_bar=inv(P1)*A*P1
B_bar=inv(P1)*B
C_bar=C*P1
O=[C_bar; C_bar*A_bar; C_bar*A_bar^2]
rank(O)

