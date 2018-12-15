import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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

	public void run() {
		//System.out.println(Thread.currentThread().getName()+" is running");
		// tant que le nombre de messages à afficher n'est pas atteint
		String[] data = new String[2];
        data[1] = getName();

        String[] dataCli = new String[2];
        dataCli[0] = "Bienvenue sur le serveur de Tchat : Veuillez renseigner votre nom : ";
        dataCli[1] = "f"+getName(); //f+le nom du thread permet d'envoyer le message seulement au thread du même nom (for thread)

        Gestionnaire.bal.put(dataCli);

        data[0] = entreeMessage();
		pseudo = data[0];

		String[] dataAll = new String[2];
		dataAll[0] = " vient de se connecter au serveur de tchat ! Souhaitez-lui la bienvenue :)";
		dataAll[1] = getName();

		Gestionnaire.bal.put(dataAll);


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
		String[] dataAll = new String[2];
		dataAll[0] = " vient de se deconnecter du serveur de tchat !";
		dataAll[1] = getName();

		Gestionnaire.bal.put(dataAll);

		try {
			fluxIn.close();
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