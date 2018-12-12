import java.io.IOException;
import java.net.Socket;

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
class Producteur implements Runnable {
	private Socket socketCli;

	public Producteur(Socket socketCli) {
		this.socketCli = socketCli;
	}

	/**
	 * Méthode utilisée lorsque le thread passe à l'état "RUNNING"
	 */
	public void run() {
		//System.out.println(Thread.currentThread().getName()+" is running");
		// tant que le nombre de messages à afficher n'est pas atteint
		
		do{
			// ecriture d'un message dans la boite aux lettres
			Exo_07.bal.put("Msg du client");
			//System.out.println(Thread.currentThread().getName()+": j'ai déposé un message");
		}while(true);


		
		//System.out.println(Thread.currentThread().getName()+" a terminé son boulot.");
	}// fin du run

	public void deconnexion(){
		try {
			socketCli.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}