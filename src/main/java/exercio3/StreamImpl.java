package exercio3;

public class StreamImpl implements Stream {

	private char[] chars;
	
	private int posicao = 0;
	
	
	public StreamImpl(char[] chars) {
		this.chars = chars;
	}
	
	@Override
	public char getNext() {
		char caracter = chars[posicao];
		posicao++;
		return caracter; 
	}

	@Override
	public boolean hasNext() {
		if (posicao >= chars.length || chars[posicao] == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getPosicao() {
		return posicao;
	}

}
