import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList <Persona>personas = new ArrayList<Persona>();
	
	public static void imprimir() {
		System.out.println("|\t sexo \t|\t altura (in) \t|\t peso (lb)\t|\t tamano (ft)\t|");

		for(int i = 0; i < personas.size();i++) {
			System.out.println(personas.get(i).toString());
		}
	}
	
	static int cuentaSexo(String sexo) {
		
		int cuenta = 0;
		for(int i = 0; i < personas.size();i++) {
			if(personas.get(i).getSexo().equals(sexo)) cuenta++;
		} return cuenta;
		
	}
	
	static Persona medias(String sexo) {
		
		double mediaAltura=0;
		int mediaPeso=0, mediaTamano=0;
		
		for(int i = 0; i<personas.size();i++) {
			if(personas.get(i).getSexo().equals(sexo)) {
				mediaAltura += personas.get(i).getAltura();
				mediaPeso += personas.get(i).getPeso();
				mediaTamano += personas.get(i).getTamano();
			}
		}
		
		mediaAltura/= ((double)cuentaSexo(sexo));
		mediaPeso/= cuentaSexo(sexo);
		mediaTamano/= cuentaSexo(sexo);
		
		return new Persona(sexo, mediaAltura, mediaPeso, mediaTamano);
	}
	
	static Persona varianzas(String sexo, Persona personaMedia) {
		
		double mediaAltura=0;
		int mediaPeso=0, mediaTamano=0;
		
		for(int i = 0; i<personas.size();i++) {
			if(personas.get(i).getSexo().equals(sexo)) {
				mediaAltura += Math.pow(personas.get(i).getAltura(), 2);
				mediaPeso += Math.pow(personas.get(i).getPeso(),2);
				mediaTamano += Math.pow(personas.get(i).getTamano(),2);
			}
		}
		
		mediaAltura-= Math.pow(personaMedia.getAltura(), 2);
		mediaPeso-= Math.pow(personaMedia.getPeso(), 2);
		mediaTamano-= Math.pow(personaMedia.getTamano(), 2);
		
		return new Persona(sexo, mediaAltura, mediaPeso, mediaTamano);
	}
	
	static void p_x_dado_y(Persona persona, Persona personaMedia, Persona personaVar){
		
		double alturaArriba = Math.exp(-1*Math.pow(persona.getAltura()-personaMedia.getAltura(),2)/(2*Math.pow(personaVar.getAltura(),2)));
		double alturaAbajo = personaVar.getAltura()*(Math.sqrt(2*Math.PI));
		double altura = alturaArriba/alturaAbajo;

		double pesoArriba = Math.exp(-1*Math.pow(persona.getPeso()-personaMedia.getPeso(),2)/(2*Math.pow(personaVar.getPeso(),2)));
		double pesoAbajo = personaVar.getPeso()*(Math.sqrt(2*Math.PI));
		double peso = pesoArriba/pesoAbajo;

		double tamanoArriba = Math.exp(-1*Math.pow(persona.getTamano()-personaMedia.getTamano(),2)/(2*Math.pow(personaVar.getTamano(),2)));
		double tamanoAbajo = personaVar.getTamano()*(Math.sqrt(2*Math.PI));
		double tamano = tamanoArriba/tamanoAbajo;
		System.out.println("|\t"+ persona.getSexo() + "\t|\t\t" + altura + "\t|\t\t "+ peso + "\t|\t\t " + tamano +"\t|");
		
	}
	
	public static void main(String [] args) {
		File file = new File("personas.txt");
		
		try {
			
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				
				String [] linea = sc.nextLine().split(",");
				System.out.println("entro");
				personas.add(new Persona(

						linea[0],
						Double.parseDouble(linea[1]), 
						Integer.parseInt(linea[2]), 
						Integer.parseInt(linea[3])
						));
				
			}
			sc.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		imprimir();
		
		//Probabilidades
		double probabilidadHombre = ((double)cuentaSexo("hombre"))/((double)  personas.size());
		double probabilidadMujer = ((double)cuentaSexo("mujer"))/((double)  personas.size());
		System.out.println("Hombre: "+ probabilidadHombre);
		System.out.println("Mujer: "+ probabilidadMujer);
		
		//Medias
		Persona mediaHombres = medias("hombre");
		Persona mediaMujeres = medias("mujer");
		System.out.println(mediaHombres.toString());
		System.out.println(mediaMujeres.toString());
		
		//Varianzas
		Persona varHombres = varianzas("hombre", mediaHombres);
		Persona varMujeres = varianzas("mujer", mediaMujeres);
		System.out.println(varHombres.toString());
		System.out.println(varMujeres.toString());
		
		//P_de_X_dado_Y
		p_x_dado_y(new Persona("hombre", 6.3, 180, 10), mediaHombres, varHombres);
		p_x_dado_y(new Persona("mujer", 5.3, 150, 8), mediaMujeres, varMujeres);
	}
}