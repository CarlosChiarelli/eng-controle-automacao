%
% File demo.m
% Demo for the three simulation programs with visualization 
% and joystick input.
% Created by Marc Bodson. Last revised: 3/28/13.
%
pi=3.14159265358979;alldone=0;
while (alldone==0);
   fprintf('Type 1 for manual control of ball and beam \n');
   fprintf('Type 2 for automatic control of ball and beam\n');
   fprintf('Type 3 for manual control of inverted pendulum\n');
   fprintf('Type 4 for automatic control of inverted pendulum\n');
   fprintf('Type 5 for manual control of flexible beam\n');
   fprintf('Type 6 for automatic control of flexible beam\n');
   testcase=input('Type ctrl-C to stop the demo.\n');
   switch testcase;
      case 1;system=1;mode=1;tmax=30;
      case 2;system=1;mode=2;tmax=14;
      case 3;system=2;mode=1;tmax=30;
      case 4;system=2;mode=2;tmax=30;
      case 5;system=3;mode=1;tmax=30/10;
      case 6;system=3;mode=2;tmax=33/10;
      case 7;alldone=1;
   end;
switch system;
case 1;
%
% Initialization - Ball and beam model
%
lbeam=0.8;       % beam length (m)
xmax=lbeam/2;    % max. ball position (m)
thmax=pi/36;     % max. beam angle (rad)
xball=0;         % initial ball position (m) 
vball=0;         % initial ball velocity (m/s)
dt=0.05;         % sampling period (s)
g=9.81;g57=g*5/7;
%
% Initialization - Controller
%
switch mode;
   case 1;gmanual=thmax;         % gain of manual controller
   case 2;kp=-1.5857;kv=-0.9514; % initialization of automatic controller
end;
%
% Initialization - Visualization
% Adjust the position & size of the simulation window below 
% to fit the screen and to square the axes (if needed)
% Format: [position from left, position from bottom, width, height]
%
figure(1);
wbeam=0.012;rball=0.008;npb=20;ipb=0:npb;vgap=0.2*rball;
axis([-0.6*lbeam 0.6*lbeam -0.2*lbeam 0.2*lbeam]);clf
axis([-0.6*lbeam 0.6*lbeam -0.2*lbeam 0.2*lbeam]);
set(1,'pos',[20 350 940 300]);hold on % position/size of sim. window
xdbeam=[lbeam/2;lbeam/2;-lbeam/2;-lbeam/2];
ydbeam=[-wbeam/2;wbeam/2;wbeam/2;-wbeam/2];
pbeam=fill(xdbeam,ydbeam,'green','EraseMode','background');   
xdball=rball*cos(2*pi*ipb/npb);
ydball=rball*sin(2*pi*ipb/npb)+wbeam/2+rball+vgap;
pball=fill(xdball,ydball,'red','EraseMode','background');
xline1=[-0.375*lbeam;-0.375*lbeam];yline1=[-wbeam/2;wbeam/2];
pline1=plot(xline1,yline1,'black','EraseMode','background');
xline2=[0.375*lbeam;0.375*lbeam];yline2=[-wbeam/2;wbeam/2];
pline2=plot(xline2,yline2,'black','EraseMode','background');
%
% Real-time simulation
%
t=0;tm=0;nt=0;done=0;tic;
while (done==0);
   while tm<nt;tm=toc;end;t=nt;nt=t+dt;
%
% Controller
% Control signal:   theta, beam angle (rad)
% Useful variables: xball, ball position (m)
%                   vball, ball velocity (m/s)
%                   t, time (s)
%
switch mode;
   case 1;joyin=jst;theta=-gmanual*joyin(2);  % manual control 
   case 2;xref=0.3*sign(sin(pi*t/5));
          theta=kp*(xref-xball)-kv*vball; % automatic control
end;
%
% Ball and beam model
%
if (theta>thmax);theta=thmax;end;
if (theta<-thmax);theta=-thmax;end;
vball=vball-dt*g57*sin(theta);
if and((abs(theta)<0.02),(abs(vball)<0.002));
   vball=0;end;  % pseudo-friction
xball=xball+dt*vball;
if (xball>xmax);xball=xmax;vball=0;end
if (xball<-xmax);xball=-xmax;vball=0;end
%
% Visualization
%
u=[cos(theta) -sin(theta);sin(theta) cos(theta)];
dball=[xdball'+xball ydball']*u';xpball=dball(:,1);ypball=dball(:,2);
set(pball,'Xdata',xpball,'Ydata',ypball);
dbeam=[xdbeam ydbeam]*u';xpbeam=dbeam(:,1);ypbeam=dbeam(:,2);
set(pbeam,'Xdata',xpbeam,'Ydata',ypbeam);
dline1=[xline1 yline1]*u';xpline1=dline1(:,1);ypline1=dline1(:,2);
set(pline1,'Xdata',xpline1+1e-6*rand,'Ydata',ypline1);% rand to avoid erase
dline2=[xline2 yline2]*u';xpline2=dline2(:,1);ypline2=dline2(:,2);
set(pline2,'Xdata',xpline2+1e-6*rand,'Ydata',ypline2);
drawnow;
%
if (t>=tmax);done=1;end;
end;
hold off;
% End of ball and beam simulation
case 2;
%
% Initialization - Inverted pendulum model
% Set the "cheat" variable to a value greater than 1
% in order to make the manual control problem easier
%
cheat=1;
if (mode==1);cheat=6;end;
g=9.81/cheat;          % acceleration of gravity (m/s^2)
lcg=1;lbeam=lcg*2;     % beam length (m)
thmax=pi/6;xmax=lbeam; % max. beam angle (rad) and cart position (m)
theta=0;xcart=0;       % initial beam angle (rad) and cart position (m)
% theta=5*pi/180;      % use to set an initial beam angle of 5 deg
dt=0.05;               % sampling period (s)
a2=3*g/(4*lcg);nump=[-a2/g 0 0];denp=[1 0 -a2];sysp=tf(nump,denp);
[ap,bp,cp,dp]=ssdata(c2d(sysp,dt,'zoh'));f=5;
icca=inv([cp;cp*ap]);iccb=icca*([dp;cp*bp+dp]); % zero velocity initialization
xp=icca*[theta;theta]-iccb*xcart;xss=icca*[thmax;thmax];
% 
% Initialization - Controller
%
switch mode;
   case 1;gmanual=xmax; % gain of manual controller
   case 2;c=0.3391;ac =[2.1284 -0.563;2 0];bc=[1;0];cc=[-1.3536 0.6299];
          dc=-2.4412;xc=[0;0];xreff=0;
      % initialization of automatic controller
end;
%
% Initialization - Visualization
% Adjust the position & size of the simulation window below 
% to fit the screen and to square the axes (if needed)
% Format: [position from left, position from bottom, width, height]
%
figure(1);
wbeam=0.016*lbeam;vgap=0.005*lbeam;wtrack=2.12*lbeam;
htrack=0.03*lbeam;wcart=0.12*lbeam;hcart=0.05*lbeam;
axis([-0.75*wtrack 0.75*wtrack 0 htrack+hcart+1.2*lbeam]);clf
axis([-0.75*wtrack 0.75*wtrack 0 htrack+hcart+1.2*lbeam]);
set(1,'pos',[20 300 970 393]);hold on % position/size of sim. window
xdtrack=[wtrack/2;wtrack/2;-wtrack/2;-wtrack/2];
ydtrack=[0;htrack;htrack;0];
ptrack=fill(xdtrack,ydtrack,'green'); 
xline1=[-lcg,-lcg];yline1=[0,htrack];
pline1=plot(xline1,yline1,'black','EraseMode','background');
xline2=[lcg,lcg];yline2=[0,htrack];
pline2=plot(xline2,yline2,'black','EraseMode','background');
xdcart=[wcart/2;wcart/2;-wcart/2;-wcart/2];
ydcart=[0;hcart;hcart;0];
pcart=fill(xdcart,ydcart+htrack+vgap,'blue','EraseMode','background'); 
xdbeam=[wbeam/2;wbeam/2;-wbeam/2;-wbeam/2];
ydbeam=[0;lbeam;lbeam;0];
pbeam=fill(xdbeam,ydbeam+htrack+vgap+vgap+hcart,'red','EraseMode','background'); 
%
% Real-time simulation
%
t=0;tm=0;nt=0;done=0;tic;
while (done==0);
   while tm<nt;tm=toc;end;t=nt;nt=t+dt;
%
% Controller
% Control signal:   xref, reference cart position (m)
% Useful variables: xcart, cart position (m)
%                   theta, beam angle (rad)
%                   t, time (s)
%
switch mode;
   case 1;joyin=jst;xcom=gmanual*joyin(2); % manual control
   case 2;xref=sign(sin(pi*t/10));xreff=xreff+dt*c*(xref-xreff);
          xcom=xreff-(cc*xc+dc*theta);xc=ac*xc+bc*theta; % automatic control
end;
%
% Inverted pendulum model
%
xcart=xcart+dt*f*(xcom-xcart);
if (xcart>xmax);xcart=xmax;end;
if (xcart<-xmax);xcart=-xmax;end;
theta=cp*xp+dp*xcart;xp=ap*xp+bp*xcart;
if (theta>thmax);theta=thmax;xp=xss-iccb*xcart;end;
if (theta<-thmax);theta=-thmax;xp=-xss-iccb*xcart;end;
%
% Visualization
%
u=[cos(theta) sin(theta);-sin(theta) cos(theta)];
xpcart=xdcart+xcart;ypcart=htrack+vgap+ydcart;
dbeam=[xdbeam ydbeam]*u';
xpbeam=xcart+dbeam(:,1);ypbeam=htrack+hcart+vgap+vgap+dbeam(:,2);
set(pcart,'Xdata',xpcart,'Ydata',ypcart);
set(pbeam,'Xdata',xpbeam,'Ydata',ypbeam);
drawnow;
%
if (t>=tmax);done=1;end;
end;
hold off
% End of inverted pendulum simulation
case 3;
%
% Initialization - Flexible beam model
%
imax=6;       % max. motor current (A)
dphimax=pi/6; % max. tip angle - shaft angle in visualization (rad)
dt=0.005;     % sampl. period (s) 
dtsim=0.05;   % sampl. period for visualization (s)
kp=5.5;       % DC gain from motor to angular acceleration (rad/(s^2.A))
z11=-.07 + 18i;z12=-0.07 + 180i;z21=-300;z22=200;
z23=-120;z24=100;p1=-3+74i;p2=-3+215i;
den1=[1 -2*real(p1) abs(p1)^2];
den2=[1 -2*real(p2) abs(p2)^2];den=conv(den1,den2);
num11=[1 -2*real(z11) abs(z11)^2 ];num12=[1 -2*real(z12) abs(z12)^2 ];
num1=conv(num11,num12);num1=num1*kp*den(5)/num1(5);
num21=[1 -(z21+z22) (z21*z22)];num22=[1 -(z23+z24) (z23*z24)];
num2=conv(num21,num22);num2=num2*kp*den(5)/num2(5);den=[den 0 0];
sysp1=tf(num1,den);sysd1=c2d(sysp1,dt,'zoh');
[dnum1,dden]=tfdata(sysd1,'v');dim=length(dden)-1;
ap=[zeros(dim-1,1) eye(dim-1);-fliplr(dden(2:dim+1))];
bp=[zeros(dim-1,1);1];cp1=fliplr(dnum1(2:dim+1));
sysp2=tf(num2,den);sysd2=c2d(sysp2,dt,'zoh');
[dnum2,dden]=tfdata(sysd2,'v');cp2=fliplr(dnum2(2:dim+1));
xp=zeros(6,1);theta=cp1*xp;phi=cp2*xp;
%
% Initialization - Controller
%
switch mode;
   case 1;gmanual=imax; % gain of manual controller
   case 2;af=0.9809;bf=0.1250;cf=0.1529;df=0;
      ac=[2.1804 -0.3952 0.0476;4 0 0;0 2 0];
      bc=[8;0;0];cc=[-7.8388 3.5483 -0.8215];dc=97.7119;
      xf=0;xc=[0;0;0];
      % initialization of automatic controller
end;
%
% Initialization - Visualization
% Adjust the position & size of the simulation window below 
% to fit the screen and to square the axes (if needed)
% Format: [position from left, position from bottom, width, height]
%
figure(1);
lbeam=1.;npb=20;ipb=0:npb;wbeam=0.02;wbeam2=wbeam/2;
axis([-1.05*lbeam 1.05*lbeam -0.05*lbeam 1.05*lbeam]);clf
axis([-1.05*lbeam 1.05*lbeam -0.05*lbeam 1.05*lbeam]);
set(1,'pos',[30 280 810 400]);hold on % position/size of sim. window
al=ipb/npb;al2=al.^2;all=al*lbeam;
xdbeam=zeros(1,2*npb+2);ydbeam=zeros(1,2*npb+2);
pbeam=fill(xdbeam,ydbeam,'red','EraseMode','background');
xl=[-lbeam,0,lbeam];yl=[lbeam 0 lbeam];
pline=plot(xl,yl,'blue','EraseMode','background');
%
% Real-time simulation
%
tm=0;t=0;nt=0;tsim=0;ntsim=0;tm;done=0;tic;
while (done==0);
   while tm<ntsim;tm=toc;end;t=nt;nt=t+dt;tsim=ntsim;ntsim=tsim+dtsim;
%
% Controller
% Control signal:   imotor, motor current (A)
% Useful variables: theta, angle of the motor shaft (rad) 
%                   phi, angle of the tip of the beam (rad)
%
switch mode;
   case 1;joyin=jst;imotor=-gmanual*joyin(2); % manual control
   case 2;phiref=(pi/4)*sign(sin(pi*t/1.1));
          phireff=cf*xf+df*phiref;xf=af*xf+bf*phiref;
          imotor=cc*xc+dc*(phireff-phi);xc=ac*xc+bc*(phireff-phi);
          % automatic control
end;
%
% Flexible beam model
% 
xp=ap*xp+bp*imotor;theta=cp1*xp;phi=cp2*xp;
%
% Visualization
%
if (imotor>imax);imotor=imax;end;
if (imotor<-imax);imotor=-imax;end;
dphi=phi-theta;
if (dphi>dphimax);dphi=dphimax;end;
if (dphi<-dphimax);dphi=-dphimax;end;
set(pline,'Xdata',xl+1e-6*rand,'Ydata',yl); % rand to avoid erase
xcb=all-(2*dphi^2/3)*all.*al2;ycb=dphi*all.*al;
scup=(1-wbeam*dphi/lbeam);sclo=(1+wbeam*dphi/lbeam);
xup=xcb*scup;yup=ycb*scup+wbeam2;xlo=xcb*sclo;ylo=ycb*sclo-wbeam2;
xdbeam=[xlo fliplr(xup)];ydbeam=[ylo fliplr(yup)];
u=[-sin(theta) -cos(theta);cos(theta) -sin(theta)];
dbeam=[xdbeam' ydbeam']*u';xpbeam=dbeam(:,1);ypbeam=dbeam(:,2);
set(pbeam,'Xdata',xpbeam,'Ydata',ypbeam);drawnow;
%
if (t>=tmax);done=1;end;
end;
hold off;
% End of flexible beam system
end; % End for choice of system
end; % End for alldone loop