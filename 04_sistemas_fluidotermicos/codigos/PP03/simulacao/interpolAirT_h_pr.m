%% Função interpoladora do ar

function [a,b] = interpolAirT_h_pr(val,param)
    switch param
        case 'T'
            [a,b] = param_T(val); %h/pr
        case 'h'
            [a,b] = param_h(val); %T/pr
        case 'pr'
            [a,b] = param_pr(val); %h/T
        otherwise
            a = -1;
            b = -1;
    end
end

%%
function [h,pr] = param_T(val)
%     disp('param_T')
    a           = open('airtable.mat');
    aircolumnT  = a.airtable(:,1);
    aircolumnh  = a.airtable(:,2);
    aircolumnpr = a.airtable(:,3);
    i           = findIndex(val,aircolumnT); %x/y
    h           = linearInterpol(aircolumnT(i),val,aircolumnT(i+1),aircolumnh(i),aircolumnh(i+1));
    pr          = linearInterpol(aircolumnT(i),val,aircolumnT(i+1),aircolumnpr(i),aircolumnpr(i+1));
end

%%
function [T,pr] = param_h(val)
%     disp('param_h')
    a = open('airtable.mat');
  	aircolumnT  = a.airtable(:,1);
    aircolumnh  = a.airtable(:,2);
    aircolumnpr = a.airtable(:,3);
    i           = findIndex(val,aircolumnh); %x/y
    T           = linearInterpol(aircolumnh(i),val,aircolumnh(i+1),aircolumnT(i),aircolumnT(i+1));
    pr          = linearInterpol(aircolumnh(i),val,aircolumnh(i+1),aircolumnpr(i),aircolumnpr(i+1));

end

%%
function [h,T] = param_pr(val)
% 	disp('param_pr')
    a = open('airtable.mat');
    aircolumnT  = a.airtable(:,1);
    aircolumnh  = a.airtable(:,2);
    aircolumnpr = a.airtable(:,3);
    i           = findIndex(val,aircolumnpr); %x/y
    h           = linearInterpol(aircolumnpr(i),val,aircolumnpr(i+1),aircolumnh(i),aircolumnh(i+1));
    T          = linearInterpol(aircolumnpr(i),val,aircolumnpr(i+1),aircolumnT(i),aircolumnT(i+1));
end

%%
function i = findIndex(val,vetor)
    i = -1;
    for valVetor = vetor'
        i = i + 1;
        if val<valVetor
            break
        end
    end
end

%%
% x  => y
% xi => yi (yi -> retorno)
% xo => yo

function yi = linearInterpol(x,xi,xo,y,yo)

    yi = y - (((x-xi)*(y-yo))/(x-xo));

end
