package com.campanha.exercicio3;

import org.junit.Test;

import exercio3.StreamImpl;

public class StreamSolverTest {

	char[] vogais = new char[] { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
	
	private char caracterUnico;

	char[] ocorrencia = new char[100];
	
	char[] semOcorrencia = new char[100];
	
	@Test
	public void test() {

		String texto = "aAbBABacafe";
		

		StreamImpl str = new StreamImpl(texto.toCharArray());

		resultado(str, '0', 0, '0', 0, '0', 0);
		System.out.println(caracterUnico);
	}

	private void resultado(StreamImpl str, char caracter1, int pos1, char caracter2, int pos2, char caracter3, int pos3) {
		char caracterAtual;
		if (str.hasNext()) {
			caracterAtual = str.getNext();
			
			if(isVogal(caracterAtual)) {
				if (!ocorrencia(caracterAtual)) {
					resultado(str, caracter1, pos1, caracter2, pos2, caracterAtual, str.getPosicao());
				} else {
					resultado(str, caracterAtual, str.getPosicao(), caracter2, pos2, caracter3, pos3);
				}
				
			} else {
				if (!(((pos1+1) == pos2 && (pos2+1) == pos3))) {
					resultado(str, caracter1, pos1, caracterAtual, str.getPosicao(), caracter3, pos3);
				} else {
					resultado(str, caracter1, pos1, caracter2, pos2, caracter3, pos3);
				}
			}
			
		} else {
			if (((pos1+1) == pos2 && (pos2+1) == pos3)) {
				caracterUnico = caracter3;
			}
		}
	}

	private boolean isVogal(char caracter) {
		for (char c : vogais) {
			if (c == caracter) {
				return true;
			}
		}
		return false;
	}
	
	private boolean ocorrencia(char caracter) {
		int counter = 0;
		int counterS = 0;
		for (char c : ocorrencia) {
			if (c == caracter) {
				return true;
			} else if (ocorrencia[counter] != 0) {
				counter++;
			}
		}
		
		for (char s : semOcorrencia) {
			if (s == caracter) {
				ocorrencia[counter] = caracter;
				semOcorrencia[counterS] = 0;
				return true;
			} else if (semOcorrencia[counterS] != 0) {
				counterS++;
			}
		}
		
		semOcorrencia[counterS] = caracter;
		
		return false;
	}

}
