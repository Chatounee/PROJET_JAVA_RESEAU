import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Gestionnaire {

    protected static BaL bal=new BaL();

    public static ServerSocket sockServ;

    public static void main (String args[]) {

        try{
            sockServ = new ServerSocket(2018);
        }catch (IOException e) {
            e.printStackTrace();
        }

        //création du thread consommateur
        Thread conso = new Thread(new Consommateur(),"Conso");

        //démarrage du thread consommateur
        conso.start();

        Gestionnaire.connection();
    }

    public static void connection(){
        Thread tprod;
        Socket connexionSocket ;
        int i =0;
        while(true){
            try{
                connexionSocket = sockServ.accept();
                //Démarrage des threads producteur
                tprod=new Thread(new Producteur(connexionSocket),"Prod-"+String.valueOf(i));
                tprod.start();
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
