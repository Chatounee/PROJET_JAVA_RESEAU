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
class Consommateur implements Runnable {

	public void run() {
	    
		String tmp;
		//System.out.println(Thread.currentThread().getName()+" is running");
		// tant que l'on a pas atteind le nombre total de message à afficher
		for (int i=0;i<Exo_07.nbrMsg*Exo_07.nbrProd;i++){
			try {
			    Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) { 
			    System.out.println("Erreur : " + e ) ; 
			    System.exit (1) ;
			}

			tmp = Exo_07.bal.get();
			System.out.println(Thread.currentThread().getName()
					+" "+tmp+String.valueOf(Exo_07.nbrMsg*Exo_07.nbrProd)+")");
		}
		
	}// fin du run
}