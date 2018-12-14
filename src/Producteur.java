import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

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
class Producteur extends Thread {
	private Socket socketCli;
	private BufferedReader fluxIn;
	private String pseudo;

	public Producteur(Socket socketCli, String name) {
		super(name);
		this.socketCli = socketCli;
		try {
			fluxIn = new BufferedReader(new InputStreamReader(socketCli.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pseudo = null;
	}

	public String getPseudo() {
		return pseudo;
	}

	public Socket getSocketCli() {
		return socketCli;
	}

	/**
	 * Méthode utilisée lorsque le thread passe à l'état "RUNNING"
	 */
	public void run() {
		//System.out.println(Thread.currentThread().getName()+" is running");
		// tant que le nombre de messages à afficher n'est pas atteint
		String[] data = new String[2];
        data[1] = getName();

        String[] dataAll = new String[2];
        dataAll[0] = "Bienvenue au nouvel utilisateur sur le serveur de Tchat : Veuillez renseigner votre nom : ";
        dataAll[1] = "all";

        Gestionnaire.bal.put(dataAll);

        data[0] = entreeMessage();
        Gestionnaire.bal.put(data);



		do{
			data[0] = entreeMessage();

			if(!data[0].equals("bye")){
				// ecriture d'un message dans la boite aux lettres
				Gestionnaire.bal.put(data);
			}
		}while(!data[0].equals("bye"));

		deconnexion();
		
		//System.out.println(Thread.currentThread().getName()+" a terminé son boulot.");
	}// fin du run

	public void deconnexion(){
		//Ajouter msg : machin s'est déconnecté
		try {
			socketCli.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String entreeMessage(){
		String msg = null;
		try {
			msg = fluxIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

}