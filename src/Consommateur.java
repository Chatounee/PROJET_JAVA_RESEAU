import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

            
            if(tmp[1].equals("all") || tmp[1].charAt(0) == 'f'){
                tmp[0] = "Serveur -> "+tmp[0];
            }else if (tmp[1].charAt(0) != 'f'){
            	int i=0, trouve=0;
            	do {
            		if(tmp[1].equals(Gestionnaire.threads.get(i).getName())){
                		tmp[0] = ((Producteur)Gestionnaire.threads.get(i)).getPseudo()+" -> "+tmp[0];
                		trouve = 1;
            		}
            		i++;
            	}while(i<Gestionnaire.threads.size() || trouve==0);
            }

            //System.out.println("Conso : Message à envoyer : "+tmp[1]+" : "+tmp[0]);
            
			for (Thread threadCli: Gestionnaire.threads) {
				//Message serveur envoyé à tout le monde
                if(tmp[1].equals("all")){
                	envoie(tmp[0], threadCli);

                }

                //message délivré à un seul client
                else if(tmp[1].charAt(0) == 'f'){
					if(tmp[1].substring(1).equals(threadCli.getName())){
						envoie(tmp[0], threadCli);
					}
				}

                //Envoie message à tous les clients sauf l'envoyeur
                else if(((Producteur)threadCli).getPseudo() != null){

					if(!tmp[1].equals(threadCli.getName())){
						envoie(tmp[0], threadCli);
					}
				}
			}
		}
		
	}// fin du run


	public void envoie(String msg, Thread threadCli){
		try {
			fluxOut = new BufferedWriter(new OutputStreamWriter(((Producteur) threadCli).getSocketCli().getOutputStream()));
			fluxOut.write(msg);
			fluxOut.newLine();
			fluxOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}