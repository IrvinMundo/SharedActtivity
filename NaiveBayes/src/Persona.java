
public class Persona {
	String sexo; double altura; int peso; int tamano;
	public Persona(String sexo, double altura, int peso, int tamano) {
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
		this.tamano = tamano;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;	
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;	
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;	
	}
	public double getTamano() {
		return tamano;
	}
	public void setTamano(int tamano ) {
		this.tamano = tamano;	
	}
	public String toString() {
		return "|\t"+ sexo + "\t|\t\t" + altura + "\t|\t\t "+ peso + "\t|\t\t " + tamano +"\t|";
	}
}
