# -*- coding: utf-8 -*-
"""projetoFINAL(1).ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1k7LGZ1-doxMCvpbNIWxVDybry50BDUWW

# Projeto: Aquisição de Dados

O projeto foi implementado na linguagem Python 3.

# Metodologia

* Subtrair média dos vetores X e Y e subtrair deles
* Montar sinal p = x + jy (complexo)
* Aplicar fft no sinal p/N (N = tamanho do sinal)
* Aplicar fftshift e multiplicar por -j
* Visualizar um padrão superficial
* Limpar o sinal (aplicar filtro)
* Identificar as falhas
"""

####### FUNÇÕES PARA USAR NO PROJETO #######
import numpy as np
import pandas as pd
from scipy import signal as signal
import matplotlib.pyplot as plt
from jupyterthemes import jtplot

# deixando o fundo dos gráficos branco
jtplot.style('grade3')

# função para plotar
def plota_Xreal_Yimag(x, y, titulo, xlim=[0, .15], ylim=[0, .15]):
    fig, (ax1, ax2) = plt.subplots(2)
    fig.suptitle(titulo)
    ax1.plot(tempo, x)
    ax2.plot(tempo, y)
    ax1.set(xlabel='tempo', ylabel='X (real)')
    ax2.set(xlabel='tempo', ylabel='Y (complexo)')
    axes = plt.gca()
    ax1.set_xlim(xlim)
    ax2.set_xlim(ylim)
    
# plota X e Y
def plotarXeY(x, y, titulo, xlim=[0, .15], ylim=[0, .15]):
    plt.plot(x, y)
    plt.title(titulo)
    plt.xlabel('tempo')
    plt.ylabel('valor complexo sinal P')
    axes = plt.gca()
    axes.set_xlim(xlim)
    axes.set_ylim(ylim)

# plota X e Y (V2)
def plotarXeY2(x, y, titulo):
    plt.plot(x, y)
    plt.title(titulo)
    plt.xlabel('frequencia')
    plt.ylabel('valor sinal')
    
# função que subtrai a média do vetor
def subMedia(vetor):
    media = np.mean(vetor)
    return(vetor - media)

# aplica para o par de vetores
def parSubMedia(x, y):
    return(subMedia(x), subMedia(y))

# monta sinal P
def criaSinalP(x, y):
    numComplexos = [ complex(x[i], y[i]) for i in np.arange(len(x)) ]
    numComplexos = np.asarray(numComplexos)
    return( numComplexos )

# função que divide o vetor pelo tamanho dele
def dividePorN(p):
    return( p/len(p) )

# função que gera o vetor frequência de cada
def geraFreqs(fftShifitada):
    maxFreq = len(fftShifitada)
    freqs = np.linspace(-maxFreq/2, maxFreq/2, maxFreq)
    return(freqs)

# função que inverte a fase do sinal
def inverteFaseSinal(sinal):
    return( np.conj(sinal) ) 

# plota fft
def plotaSinalFFT(sinalfft, titulo):
    plt.plot(sinalfft)
    plt.title(titulo)
    plt.xlabel('frequencia')
    plt.ylabel('valor sinal')
    
# plota fft com eixo X
def plotaSinalFFTeixoX(sinalfft, titulo, xlim=[0,10000]):
    plt.plot(sinalfft)
    plt.title(titulo)
    plt.xlabel('frequencia')
    plt.ylabel('valor sinal')
    axes = plt.gca()
    axes.set_xlim(xlim)

# plota fftV2
def plotaSinalFFTeixos(sinalfft, titulo, xlim=[0, .15], ylim=[0, .15]):
    plt.plot(sinalfft)
    plt.title(titulo)
    plt.xlabel('frequencia')
    plt.ylabel('valor sinal')
    axes = plt.gca()
    axes.set_xlim(xlim)
    axes.set_ylim(ylim)

# função que retorna todos dados na frequência para 
# serem visualizados e limpados (aplicação dos filtros)
def seisDadosNaFrequencia(listaX, listaY, transladar=False, w0=0, tempo=[0,1,2]):
  # transladar será para dizer se quero ou não transladar o sinal na frequencia
  # listaX tem as 06 falhas de X
  # listaY tem as 06 falhas de Y

  # tirando a media dos dados
    for i in np.arange(len(listaX)):
        listaX[i], listaY[i] = parSubMedia(listaX[i], listaY[i]) 

  # criando o sinal p = X + jY
    listaP = listaX.copy()
    for i in np.arange(len(listaX)):
        listaP[i] = criaSinalP(listaX[i], listaY[i]) 

  # dividindo sinal P por N
    for i in np.arange(len(listaP)):
        listaP[i] = dividePorN(listaP[i]) 
        
  # aqui vamos transladar no sinal na frequência
  # propriedade da modulação
  # x(n) * exp(j * w0 * n) = X(w - w0)
    if transladar == True:
        for i in np.arange(len(listaP)):
            for j in np.arange(len(listaP[i])):
                listaP[i][j] = listaP[i][j]*np.exp(1j*w0*np.pi*tempo[j]*2)
                
  # aplicando FFT
    listaPfft = listaP.copy()
    for i in np.arange(len(listaP)):
        listaPfft[i] = np.fft.fft(listaP[i])

  # aplicando FFTshift
    listaPfftShift = listaPfft.copy()
    for i in np.arange(len(listaPfft)):
        # ALTERACAO ATENCAO AQUI
        aux = np.abs(listaPfft[i])
        listaPfftShift[i] = np.fft.fftshift(aux) 

  # inverter a fase do sinal
    for i in np.arange(len(listaPfftShift)):
        listaPfftShift[i] = inverteFaseSinal(listaPfftShift[i]) 

  # gera as frequencias do sinal (centralizado em ZERO)
    listaFreqs = listaPfftShift.copy()
    for i in np.arange(len(listaPfftShift)):
        listaFreqs[i] = geraFreqs(listaPfftShift[i]) 

  # retornando lista com sinais shiftados na frequencia e suas respectivas frequencias 
    return(listaFreqs, listaPfftShift, listaP)

# aqui vamos pegar um sinal limpo complexo e retornar ele na frequencia
def seisDadosNaFrequenciaLIMPO(listaP, tempo=[0,1,2]):
  # aplicando FFT
    listaPfft = listaP.copy()
    for i in np.arange(len(listaP)):
        listaPfft[i] = np.fft.fft(listaP[i])

  # aplicando FFTshift
    listaPfftShift = listaPfft.copy()
    for i in np.arange(len(listaPfft)):
        listaPfftShift[i] = np.fft.fftshift(listaPfft[i]) 

  # inverter a fase do sinal
    for i in np.arange(len(listaPfftShift)):
        listaPfftShift[i] = inverteFaseSinal(listaPfftShift[i]) 

  # gera as frequencias do sinal (centralizado em ZERO)
    listaFreqs = listaPfftShift.copy()
    for i in np.arange(len(listaPfftShift)):
        listaFreqs[i] = geraFreqs(listaPfftShift[i]) 

  # retornando lista com sinais shiftados na frequencia e suas respectivas frequencias 
    return(listaFreqs, listaPfftShift, listaP)

# pega uma Lista de sinal complexo e retorna duas listas (parte Real e Imaginaria)
def quebralistaSinalComplexo(listaSinComplexo):
  listaX = listaSinComplexo.copy()
  listaY = listaSinComplexo.copy()
  for i in np.arange(len(listaSinComplexo)):
    listaX[i] = np.real(listaSinComplexo[i])
    listaY[i] = np.imag(listaSinComplexo[i])
  return(listaX, listaY)

# desfaz a trasnlação na frequencia
def desfazTranslacaoFreq(sinalTranslad, w0, tempo):
    sinal = sinalTranslad.copy()
    for i in np.arange(len(sinalTranslad)):
        sinal[i] = sinalTranslad[i]/np.exp(1j*w0*np.pi*tempo[i]*2)
    return(sinal)

# desfaz a trasnlação de uma lista de falhas
def desfazTranslacaoLista(lista, w0, tempo):
    auxLista = lista.copy()
    for i in np.arange(len(auxLista)):
        auxLista[i] = desfazTranslacaoFreq(auxLista, w0, tempo)
    return(auxLista)

def plotaSEISfalhas(listaFreq, listaPfftSh, titulo):
    fig, ((ax1, ax2), (ax3, ax4), (ax5, ax6)) = plt.subplots(3, 2)
    fig.suptitle(titulo)
    ax1.plot(listaFreq[0], listaPfftSh[0])
    ax2.plot(listaFreq[1], listaPfftSh[1])
    ax3.plot(listaFreq[2], listaPfftSh[2])
    ax4.plot(listaFreq[3], listaPfftSh[3])
    ax5.plot(listaFreq[4], listaPfftSh[4])
    ax6.plot(listaFreq[5], listaPfftSh[5])
    ax1.set(ylabel='FALHA 01')
    ax2.set(ylabel='FALHA 02')
    ax3.set(ylabel='FALHA 03')
    ax4.set(ylabel='FALHA 04')
    ax5.set(ylabel='FALHA 05')
    ax6.set(ylabel='FALHA 06')
    fig.tight_layout(pad=3, w_pad=0.5, h_pad=1.0)

def plotaSEISfalhasZoomEixos(listaFreq, listaPfftSh, titulo, xlim, ylim):
    fig, ((ax1, ax2), (ax3, ax4), (ax5, ax6)) = plt.subplots(3, 2)
    fig.suptitle(titulo)
    ax1.plot(listaFreq[0], listaPfftSh[0])
    ax2.plot(listaFreq[1], listaPfftSh[1])
    ax3.plot(listaFreq[2], listaPfftSh[2])
    ax4.plot(listaFreq[3], listaPfftSh[3])
    ax5.plot(listaFreq[4], listaPfftSh[4])
    ax6.plot(listaFreq[5], listaPfftSh[5])
    ax1.set(ylabel='FALHA 01')
    ax2.set(ylabel='FALHA 02')
    ax3.set(ylabel='FALHA 03')
    ax4.set(ylabel='FALHA 04')
    ax5.set(ylabel='FALHA 05')
    ax6.set(ylabel='FALHA 06')
    axes = plt.gca()
    ax1.set_xlim(xlim)
    ax2.set_xlim(xlim)
    ax3.set_xlim(xlim)
    ax4.set_xlim(xlim)
    ax5.set_xlim(xlim)
    ax6.set_xlim(xlim)
    ax1.set_ylim(ylim)
    ax2.set_ylim(ylim)
    ax3.set_ylim(ylim)
    ax4.set_ylim(ylim)
    ax5.set_ylim(ylim)
    ax6.set_ylim(ylim)
    fig.tight_layout(pad=3, w_pad=0.5, h_pad=1.0)

# compara 3 pares de sinais (sinalSujo, sinalLimpo) 
def plotaComparacaoSujoLimpo(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas):
    sinalSujo = listaSinalSujo.copy()
    sinalLimpo = listaSinalLimpo.copy()
    fig, ((ax1, ax2), (ax3, ax4), (ax5, ax6)) = plt.subplots(3, 2)
    fig.suptitle(titulo+'\n esquerda Sinal Sujo | esquerda Sinal Limpo')
    ax1.plot(eixoXvet, sinalSujo[0])
    ax2.plot(eixoXvet, sinalLimpo[0])
    ax3.plot(eixoXvet, sinalSujo[1])
    ax4.plot(eixoXvet, sinalLimpo[1])
    ax5.plot(eixoXvet, sinalSujo[2])
    ax6.plot(eixoXvet, sinalLimpo[2])
    ax1.set(ylabel='FALHA 0'+str(numFalhas[0]))
    ax2.set(ylabel='FALHA 0'+str(numFalhas[0]))
    ax3.set(ylabel='FALHA 0'+str(numFalhas[1]))
    ax4.set(ylabel='FALHA 0'+str(numFalhas[1]))
    ax5.set(ylabel='FALHA 0'+str(numFalhas[2]))
    ax6.set(ylabel='FALHA 0'+str(numFalhas[2]))
    axes = plt.gca()
    ax1.set_xlim(xlim)
    ax2.set_xlim(xlim)
    ax3.set_xlim(xlim)
    ax4.set_xlim(xlim)
    ax5.set_xlim(xlim)
    ax6.set_xlim(xlim)
    ax1.set_ylim(ylim)
    ax2.set_ylim(ylim)
    ax3.set_ylim(ylim)
    ax4.set_ylim(ylim)
    ax5.set_ylim(ylim)
    ax6.set_ylim(ylim)
    fig.tight_layout(pad=3, w_pad=0.5, h_pad=1.0)
    
# plota sinal corrigido na frequencia
def plotaSinalCorrigFreq(sinal, titulo='a', xlim=[-300, 700], ylim=[-.1, .6]):
    fftFalha = np.fft.fft(sinal)
    fftshFalha = np.fft.fftshift(fftFalha)
    fftshFalha = inverteFaseSinal(fftshFalha)
    freqsFalha = geraFreqs(fftshFalha)
    plotarXeY(freqsFalha, fftshFalha, titulo, xlim, ylim)
  
def butter_passaBaixa(Fc=250, Fs=10000, ordemN=5):
    freqNormal = float(Fc) / (Fs/2)
    b, a = signal.butter(ordemN, freqNormal, btype='lowpass')     
    return b, a
  
  # butterwoth passa Alta
def butter_passaAlta(Fc=70, Fs=10000, ordemN=5):
    freqNormal = float(Fc) / (Fs/2)
    b, a = signal.butter(ordemN, freqNormal, btype='highpass') # highpass
    return b, a
  
  # aplicando o filtro
def butter_filtraPassaBaixa(dados, Fc=250, Fs=10000, ordemN=5):
    b, a = butter_passaBaixa(Fc, Fs, ordemN=ordemN)
    y = signal.filtfilt(b, a, dados) 
    return y 
  
def butter_filtraPassaAlta(dados, Fc=70, Fs=10000, ordemN=5):
    b, a = butter_passaAlta(Fc, Fs, ordemN=ordemN)
    y = signal.filtfilt(b, a, dados) 
    return y  

# função que soma energia de uma lista de sinais limpos na frequencia
def somaEnergiaSinalFreq(listaSinal):
  listaTotEnerg = listaSinal.copy()
  for i in np.arange(len(listaSinal)):
    listaTotEnerg[i] = np.sum(np.abs(listaSinal[i]))
  return(listaTotEnerg)

"""# Importação dos dados"""

# vetor de tempo
tempo = np.loadtxt('tempo.txt')
# X sem falha
semFalhax = np.loadtxt('x_original.txt')
# Y sem falha
semFalhay = np.loadtxt('y_original.txt')

# falha A
falhaA_1x = np.loadtxt('x_a1_c.txt')
falhaA_1y = np.loadtxt('y_a1_c.txt')
falhaA_2x = np.loadtxt('x_a2_c.txt')
falhaA_2y = np.loadtxt('y_a2_c.txt')
falhaA_3x = np.loadtxt('x_a3_c.txt')
falhaA_3y = np.loadtxt('y_a3_c.txt')
falhaA_4x = np.loadtxt('x_a4_c.txt')
falhaA_4y = np.loadtxt('y_a4_c.txt')
falhaA_5x = np.loadtxt('x_a5_c.txt')
falhaA_5y = np.loadtxt('y_a5_c.txt')
falhaA_6x = np.loadtxt('x_a6_c.txt')
falhaA_6y = np.loadtxt('y_a6_c.txt')

# falha B
falhaB_1x = np.loadtxt('x_b1_c.txt')
falhaB_1y = np.loadtxt('y_b1_c.txt')
falhaB_2x = np.loadtxt('x_b2_c.txt')
falhaB_2y = np.loadtxt('y_b2_c.txt')
falhaB_3x = np.loadtxt('x_b3_c.txt')
falhaB_3y = np.loadtxt('y_b3_c.txt')
falhaB_4x = np.loadtxt('x_b4_c.txt')
falhaB_4y = np.loadtxt('y_b4_c.txt')
falhaB_5x = np.loadtxt('x_b5_c.txt')
falhaB_5y = np.loadtxt('y_b5_c.txt')
falhaB_6x = np.loadtxt('x_b6_c.txt')
falhaB_6y = np.loadtxt('y_b6_c.txt')

## CONSTANTES ##
# freq amostragem
Fs = tempo[2]-tempo[1]
Fs = 1/Fs
# as frequencias de corte será feita com 10% acima ou baixo do corte exato
Fc240 = 240*1.1
Fc80 = 80*.9
# ordem do filtro
N = 50

# background branco nos graficos
jtplot.style('grade3')

"""# Visualizando os dados no TEMPO"""

plota_Xreal_Yimag(semFalhax, semFalhay, 'Dados SEM FALHAS no tempo')

"""# Montando sinal p

Aqui vamos subtrair os vetores X e Y das médias para sair da vibração no equilíbrio estáático.
"""

semFalhax, semFalhay = parSubMedia(semFalhax, semFalhay)
falhaA_1x, falhaA_1y = parSubMedia(falhaA_1x, falhaA_1y)
falhaB_1x, falhaB_1y = parSubMedia(falhaB_1x, falhaB_1y)

"""Vamos montar o sinal p para o sinal sem falha e com as falhas."""

# criando sinais p
pSemFalha = criaSinalP(semFalhax, semFalhay)
pFalhaA = criaSinalP(falhaA_1x, falhaA_1y)
pFalhaB = criaSinalP(falhaB_1x, falhaB_1y)

pSemFalha = dividePorN(pSemFalha)
pFalhaA = dividePorN(pFalhaA)
pFalhaB = dividePorN(pFalhaB)

"""## Visualizando os sinais complexos"""

plotarXeY(tempo, pSemFalha, 'Sinal complexo SEM FALHAS', [0, .1], [-.0001, .0001])

"""# Aplicar FFT"""

# aplicando FFT
fftSemFalha = np.fft.fft(pSemFalha)
fftFalhaA = np.fft.fft(pFalhaA)
fftFalhaB = np.fft.fft(pFalhaB)

plotaSinalFFT(fftSemFalha, 'Sinal SEM FALHAS na frequencia')

"""# Aplicar FFTSHIFT"""

fftshSemFalha = np.fft.fftshift(fftSemFalha)
fftshFalhaA = np.fft.fftshift(fftFalhaA)
fftshFalhaB = np.fft.fftshift(fftFalhaB)

# invertendo as fases dos sinais
fftshSemFalha = inverteFaseSinal(fftshSemFalha)
fftshFalhaA = inverteFaseSinal(fftshFalhaA)
fftshFalhaB = inverteFaseSinal(fftshFalhaB)

freqsSemFalha = geraFreqs(fftshSemFalha)
freqsFalhaA = geraFreqs(fftshFalhaA)
freqsFalhaB = geraFreqs(fftshFalhaB)

plotarXeY2(freqsSemFalha, fftshSemFalha, 'FFTshifitada Sem Falha')

"""# Aplicação do Filtro (limpeza do Sinal)

Aqui vamos aplicar um filtro FIR para eliminar os ruídos e caracterizar as falhas. 

Será utiliza um filtro FIR pois ele tem resposta de fase linear, ou seja, não distorce o sinal.

Será utilizado filtros de ordem 5 pois foi o que apresentou bons resultados entre filtros de ordem 1 a 10 e este número é bastante usado na prática de acordo com a literatura.

Para as diferentes severidades existe ruídos e dados importantes na mesma em frequências simetricas. Como os filtros aqui usados (passa-baixa e passa-alta) são simétricos, então em certos casos iremos usar a **propriedade de translação na frequência (modulação) da Transformada de Fourier** para quebrar essa simetria e então poder filtrar o sinal alvo. Após filtra-lo desfazemos a translação com o processo reverso.

**Modulação:**

*x(n) * exp(j * w0 * n) = X(w - w0)*

# Falha A

## Visualizando o sinal com ruido

As falhas A escolhidas foram: 1 a 6
"""

# carregando dados da Falha A
listaXa = [falhaA_1x, falhaA_2x, falhaA_3x, falhaA_4x, falhaA_5x, falhaA_6x]
listaYa = [falhaA_1y, falhaA_2y, falhaA_3y, falhaA_4y, falhaA_5y, falhaA_6y]

# obtendo sinais da falha A com ruído 
listaFreq, listaPfftSh, listaP = seisDadosNaFrequencia(listaXa, listaYa)

"""Visualizando Falhas A com ruído no tempo e na frequência."""

aux = [tempo]*6
plotaSEISfalhasZoomEixos(aux, listaP, "Falhas A no tempo", [0,.05], [-.00015,.00025])

plotaSEISfalhasZoomEixos(listaFreq, listaPfftSh, 'Falhas A observadas na frequencia', [-400,450], [-.01,.7])

"""Obervamos 03 picos característicos na frequencia. Eles aparecem em 80Hz (força de excitação), 160Hz e 240Hz. 160Hz e 240Hz (2x e 3x) caracterizam a **falha por desalinhamento**. Logo queremos manter esses 3 picos caracteristicos e remover os demais com aplicacao de filtros.

A frequência de corte na aplicação do filtro estará 10Hz acima ou abaixo da frequencia verdadeira, isso porque nosso filtro não é ideal, assim evitando corta algum sinal de interesse.

Algumas falhas possuem espectros em comum na frequencia, logo algumas serão agrupadas no processamento destes sinais.

## Processando o sinal (aplicação dos filtros)

* Falha 01: filtro passa-baixa em 250Hz.

* Falhas 02, 03 e 04: translação em frequência (-40Hz) para remover simetria do pico alvo (240Hz que se torna 200Hz) e ruido (-240Hz que se torna -280Hz)); filtro passa-baixa em 210Hz e destranslação em frequência (-40Hz).

* Falha 05 e 06: filtro passa-alta em 70Hz.
"""

# posicoes no vetor de cada falha
num1 = [0]
num2e3e4 = [1,2,3]
num5e6 = [4,5]

# vetor para guardar falhas sem ruido
listaFalhaAcorrig = listaP.copy()

# processando A1
listaFalhaAcorrig[0] =  butter_filtraPassaBaixa(listaFalhaAcorrig[0])

# processando A2, A3 e A4
listaFreq, listaPfftSh, listaP = seisDadosNaFrequencia(
    listaXa, listaYa, transladar=True, w0=-40, tempo=tempo)

listaFalhaAcorrig[1] = listaP[1].copy()
listaFalhaAcorrig[2] = listaP[2].copy()
listaFalhaAcorrig[3] = listaP[3].copy()

listaFalhaAcorrig[1] =  butter_filtraPassaBaixa(listaFalhaAcorrig[1], Fc=210)
listaFalhaAcorrig[2] =  butter_filtraPassaBaixa(listaFalhaAcorrig[2], Fc=210)
listaFalhaAcorrig[3] =  butter_filtraPassaBaixa(listaFalhaAcorrig[3], Fc=210)

# desfazTranslacaoFreq
# listaFalhaAcorrig[1] = desfazTranslacaoLista(listaFalhaAcorrig[1], w0=-40, tempo=tempo)
# listaFalhaAcorrig[2] = desfazTranslacaoLista(listaFalhaAcorrig[2], w0=-40, tempo=tempo)
# listaFalhaAcorrig[3] = desfazTranslacaoLista(listaFalhaAcorrig[3], w0=-40, tempo=tempo)


listaFalhaAcorrig[1] = desfazTranslacaoFreq(listaFalhaAcorrig[1], w0=-40, tempo=tempo)
listaFalhaAcorrig[2] = desfazTranslacaoFreq(listaFalhaAcorrig[2], w0=-40, tempo=tempo)
listaFalhaAcorrig[3] = desfazTranslacaoFreq(listaFalhaAcorrig[3], w0=-40, tempo=tempo)

# processando A5, A6
listaFalhaAcorrig[4] =  butter_filtraPassaAlta(listaFalhaAcorrig[4], Fc=70)
listaFalhaAcorrig[5] =  butter_filtraPassaAlta(listaFalhaAcorrig[5], Fc=70)

plotaSEISfalhasZoomEixos(aux, listaFalhaAcorrig, "Falhas A no tempo (LIMPAS)", [0,.05], [-.00015,.00025])

# quebrando o sinal corrigido
# pega uma Lista de sinal complexo e retorna duas listas (parte Real e Imaginaria)
#sinalCorrigX, sinalCorrigY = quebralistaSinalComplexo(listaFalhaAcorrig)
#listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequencia(sinalCorrigX, sinalCorrigY) 

listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequenciaLIMPO(listaFalhaAcorrig)

plotaSEISfalhasZoomEixos(listaFreqLimpo, listaPfftShLimpo, 'Falhas A Limpas observadas na frequencia', [-400,450], [-.01,.7])
#plotaSEISfalhasZoomEixos(aux, listaFalhaAcorrig, "Falhas A no tempo (LIMPAS)", [0,.05], [-.00015,.00025])

listaFreq, listaPfftSh, listaP = seisDadosNaFrequencia(listaXa, listaYa)

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    tempo, listaP[0:3], listaFalhaAcorrig[0:3], 'Comparacao sinais A1, A2 e A3 no tempo',
    [0,.05], [-.00015,.00025], [1,2,3])

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    tempo, listaP[3:7], listaFalhaAcorrig[3:7], 'Comparacao sinais A4, A5 e A6 no tempo',
    [0,.05], [-.00015,.00025], [4,5,6])

#def seisDadosNaFrequenciaLIMPO(listaP, tempo=[0,1,2]):
listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequenciaLIMPO(listaFalhaAcorrig, tempo)

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    listaFreq[0], listaPfftSh[0:3], listaPfftShLimpo[0:3], 'Comparacao sinais A1, A2 e A3 na frequencia',
    [-400,450], [-.01,.7], [1,2,3])

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    listaFreq[0], listaPfftSh[3:7], listaPfftShLimpo[3:7], 'Comparacao sinais A4, A5 e A6 na frequencia',
    [-400,450], [-.01,.7], [4,5,6])

"""## Verificando resultado A

Para verificar o resultado a energia total do sinal sem falha deve ser igual a energia total do sinal com falha filtrado.
"""

print('\nSoma das energias CASO A: ',somaEnergiaSinalFreq(listaPfftShLimpo))
# AQUI ATENCAO

"""Acima notamos que não obtvemos o resultado esperado ao analisar a energia total dos sinais filtrados. Contudo, visualmente os picos dos gráficos aparentam uma soma total igual a 1, como acontece no sinal sem falha. Numericamente as energias dos sinais filtrados deram o dobro do esperado.

# Falha B

# Visualizando o sinal com ruido
As falhas B escolhidas foram: 1 a 6
"""

# carregando dados da Falha B
listaXb = [falhaB_1x, falhaB_2x, falhaB_3x, falhaB_4x, falhaB_5x, falhaB_6x]
listaYb = [falhaB_1y, falhaB_2y, falhaB_3y, falhaB_4y, falhaB_5y, falhaB_6y]

# obtendo sinais da falha B com ruído 
listaFreq, listaPfftSh, listaP = seisDadosNaFrequencia(listaXb, listaYb)

"""Visualizando Falhas B com ruído no tempo e na frequência."""

aux = [tempo]*6
plotaSEISfalhasZoomEixos(aux, listaP, "Falhas B no tempo", [0,.05], [-.00015,.00025])

plotaSEISfalhasZoomEixos(listaFreq, listaPfftSh, 'Falhas B observadas na frequencia', [-400,450], [-.01,.7])

"""Obervamos 03 picos característicos na frequencia. Eles aparecem em 80Hz (força de excitação), 160Hz e 240Hz. -80Hz e -160Hz (-1x e -2x) caracterizam a **falha por desgaste de mancal**. Logo queremos manter esses 3 picos caracteristicos e remover os demais com aplicacao de filtros.

A frequência de corte na aplicação do filtro estará 10Hz acima ou abaixo da frequencia verdadeira, isso porque nosso filtro não é ideal, assim evitando corta algum sinal de interesse.

Algumas falhas possuem espectros em comum na frequencia, logo algumas serão agrupadas no processamento destes sinais.

# Processando o sinal (aplicação dos filtros)

* Falha 01 e 06: filtro passa-alta em 70Hz.

* Falhas 02, 05: filtro passa-baixa em 170Hz.

* Falha 03 e 04: filtro passa-alta em 70Hz e passa-baixa em 170Hz.
"""

# posicoes no vetor de cada falha
num1e6 = [0,5]
num2e5 = [1,4]
num3e4 = [2,3]

# vetor para guardar falhas sem ruido
listaFalhaBcorrig = listaP.copy()

# processando B1 e B6
listaFalhaBcorrig[0] =  butter_filtraPassaAlta(listaFalhaBcorrig[0], Fc=70)
listaFalhaBcorrig[5] =  butter_filtraPassaAlta(listaFalhaBcorrig[5], Fc=70)

# porcessando B2 e B5
listaFalhaBcorrig[1] =  butter_filtraPassaBaixa(listaFalhaBcorrig[1], Fc=170)
listaFalhaBcorrig[4] =  butter_filtraPassaBaixa(listaFalhaBcorrig[4], Fc=170)

# porcessando B3 e B4
listaFalhaBcorrig[2] =  butter_filtraPassaBaixa(listaFalhaBcorrig[2], Fc=170)
listaFalhaBcorrig[3] =  butter_filtraPassaBaixa(listaFalhaBcorrig[3], Fc=170)
listaFalhaBcorrig[2] =  butter_filtraPassaAlta(listaFalhaBcorrig[2], Fc=70)
listaFalhaBcorrig[3] =  butter_filtraPassaAlta(listaFalhaBcorrig[3], Fc=70)

listaFreq, listaPfftSh, listaP = seisDadosNaFrequencia(listaXb, listaYb)

plotaSEISfalhasZoomEixos(aux, listaFalhaBcorrig, "Falhas B no tempo (LIMPAS)", [0,.05], [-.00015,.00025])

# quebrando o sinal corrigido
# pega uma Lista de sinal complexo e retorna duas listas (parte Real e Imaginaria)
#sinalCorrigX, sinalCorrigY = quebralistaSinalComplexo(listaFalhaAcorrig)
#listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequencia(sinalCorrigX, sinalCorrigY) 

listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequenciaLIMPO(listaFalhaBcorrig)

plotaSEISfalhasZoomEixos(listaFreqLimpo, listaPfftShLimpo, 'Falhas B Limpas observadas na frequencia', [-400,450], [-.01,.7])
#plotaSEISfalhasZoomEixos(aux, listaFalhaAcorrig, "Falhas A no tempo (LIMPAS)", [0,.05], [-.00015,.00025])

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    tempo, listaP[0:3], listaFalhaBcorrig[0:3], 'Comparacao sinais B1, B2 e B3 no tempo',
    [0,.05], [-.00015,.00025], [1,2,3])

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    tempo, listaP[3:7], listaFalhaBcorrig[3:7], 'Comparacao sinais B4, B5 e B6 no tempo',
    [0,.05], [-.00015,.00025], [4,5,6])

#def seisDadosNaFrequenciaLIMPO(listaP, tempo=[0,1,2]):
listaFreqLimpo, listaPfftShLimpo, listaPLimpo = seisDadosNaFrequenciaLIMPO(listaFalhaBcorrig, tempo)

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    listaFreq[0], listaPfftSh[0:3], listaPfftShLimpo[0:3], 'Comparacao sinais B1, B2 e B3 na frequencia',
    [-400,450], [-.01,.7], [1,2,3])

#(eixoXvet, listaSinalSujo, listaSinalLimpo, titulo, xlim, ylim, numFalhas
plotaComparacaoSujoLimpo(
    listaFreq[0], listaPfftSh[3:7], listaPfftShLimpo[3:7], 'Comparacao sinais B4, B5 e B6 na frequencia',
    [-400,450], [-.01,.7], [4,5,6])

"""## Verificando resultado B"""

print('\nSoma das energias CASO B: ',somaEnergiaSinalFreq(listaPfftShLimpo),'\n')
# AQUI ATENCAO

"""Acima notamos que não obtvemos o resultado esperado ao analisar a energia total dos sinais filtrados. Contudo, visualmente os picos dos gráficos aparentam uma soma total igual a 1, como acontece no sinal sem falha. Numericamente as energias dos sinais filtrados deram o dobro do esperado."""
