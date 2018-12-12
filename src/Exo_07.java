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
class Exo_07 {

    protected static int nbrProd=5;
	protected static int nbrMsg=1;
	protected static BaL bal=new BaL();
    	

	public static void main (String args[]) {
		
		//création du thread consommateur
		Thread conso = new Thread(new Consommateur(),"Conso");
		
		//création des nbrProd  threads producteur et démarrage
		Thread tprod;
		for (int i=1;i<=Exo_07.nbrProd;i++){
			//tprod=new Thread(new Producteur(),"Prod-"+String.valueOf(i));
			//tprod.start();
		}
		//démarrage du thread consommateur
		conso.start();
	}						
}