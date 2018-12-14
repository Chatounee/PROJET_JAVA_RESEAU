import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;

/**
 * 
 * <b>TD M3102 Java : Exercice 07</b><br/>
 * 
 * Lance X threads producteurs et un thread consommateur. Les threads communiquent
 * entre eux par l'intermédiaire d'une instance de la classe BaL. Les producteurs écrivent 
 * un message que le Thread Consommateur affiche.
 * 
 * Écrire un programme Java qui démarre X threads déposant des messages dans une boîte aux lettres. 
 * Un thread récupère le message de la boîte aux lettres et l'affiche.
 * Classes : Exo_05, BAL, Consommateur (thread qui affiche le message), Producteur (thread qui envoie le message)
 * Utilisation de sleep(), wait() et synchronized
 * La classe BAL possède deux méthodes et au moins une variable de classe pour stocker le message :
 * put : écrit le message d'un thread producteur dans la variable
 * get : récupère la valeur de la variable
 * Les threads producteurs écrivent un message dans la boîte aux lettres lorsque la variable est vide.
 * Le thread consommateur affiche le contenu de la variable puis la vide.
 *
 * @author N. Ménard
 * @version 20161104-f
 * 
 **/
class Consommateur implements Runnable {
	private BufferedWriter fluxOut;

	public Consommateur() {
		fluxOut = null;
	}

	public void run() {
	    
		String[] tmp;
		//System.out.println(Thread.currentThread().getName()+" is running");
		// tant que l'on a pas atteind le nombre total de message à afficher
		while(true){
			tmp = Gestionnaire.bal.get();

			for (Thread threadCli: Gestionnaire.threads) {
				if(((Producteur)threadCli).getPseudo() != null){
					if(tmp[1].equals("all")){
						try {
							fluxOut = new BufferedWriter(new OutputStreamWriter(((Producteur) threadCli).getSocketCli().getOutputStream()));
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
					else if(!tmp[1].equals(threadCli.getName())){
						//ENVOI MESSAGE A TOUT LE MONDE SAUF L'ENVOYEUR
						try {
							fluxOut = new BufferedWriter(new OutputStreamWriter(((Producteur) threadCli).getSocketCli().getOutputStream()));
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}

			}
		}
		
	}// fin du run
}