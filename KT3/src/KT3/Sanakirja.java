package KT3;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Scanner;

public class Sanakirja {
	
	private static void serializeToXML(HashMap <String, String> sanaparit) throws Exception  {
		FileOutputStream fos = new FileOutputStream("Sanat.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(sanaparit);
		encoder.close();
		fos.close();
	}

		static HashMap <String, String> deserializeFromXML(HashMap <String, String> sanaparit) throws Exception {
		FileInputStream fis = new FileInputStream("Sanat.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		HashMap <String, String> decodedSanat = (HashMap <String, String>) decoder.readObject();
		decoder.close();
		fis.close();
		return decodedSanat;
}
	
	public static void main(String[] args) throws Exception {

		String suomi;
		String englanti;
		
		Sanat a = new Sanat();
		
		HashMap <String, String> sanaparit = new HashMap<>();
		
		sanaparit = deserializeFromXML(sanaparit);
		
		for (int i = 0; i < a.englanniksi.length; i++) {

			sanaparit.put(a.suomeksi[i], a.englanniksi[i]);
			
		}
		
		Iterator<Entry<String, String>> itr = sanaparit.entrySet().iterator(); 

		System.out.println("Sanakirjan sis�lt�:\n");
		
		while (itr.hasNext()) {
			HashMap.Entry<String,String> alkio = (HashMap.Entry<String,String>) itr.next();
			System.out.print( alkio.getKey() + "=" + alkio.getValue() + "\n");
		}
		
		System.out.print("\n");
		
		Scanner lukija = new Scanner(System.in);
		
		do {
			System.out.print("\nMink� sanan k��nn�ksen haluat tiet��? (Tyhj� sana lopettaa)");		
			suomi = lukija.nextLine();
			 
			if (suomi.equals("")) {
				System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!");
			        break;
			}
			      
			if (!sanaparit.containsKey(suomi)) {
				System.out.println("Sanan \"" + suomi +"\" k��nn�s on tuntematon!");
			}
			
			else
			    System.out.println("Sanan \"" + suomi + "\" k��nn�s on \"" + sanaparit.get(suomi) +"\"");
		}
		
		while (true); {
		
		do {
		      System.out.print("\nSana alkukielell�? (Tyhj� sana lopettaa) ");
		      suomi = lukija.nextLine();
		      if (suomi.equals("")) {
		    	System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!");
		        break;
		      }

		      if (sanaparit.containsKey(suomi)) {
		        System.out.println("Sana \"" + suomi + "\" l�ytyy jo!");
		        System.out.println("Vanha k��nn�s " + "\"" +sanaparit.get(suomi) + "\" j�� voimaan!");
		        continue;
		      }

		      System.out.print("Sana k��nnettyn�? Tyhj� sana lopettaa)");
		      englanti = lukija.nextLine();
		      
		      if (englanti.equals("")) {
		    	  System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!\n");
		        break;
		      }
		      sanaparit.put(suomi, englanti);
		      serializeToXML(sanaparit);

		    }
		
			while (true); 
		

		    System.out.println(sanaparit);
	}
}	
}
			