class BaL {
	
    private String[] data; // boite aux lettres. Variable privée
    					//qui est accédée par les méthodes put et get ci dessous

    public synchronized void put(String[] put_data) {
        
    	// si un thread a écrit un message on attend. 
    	// Lorsque la méthode get aura vidé
        // la variable data et que le thread sera réveillé, 
    	// l'exécution pourra reprendre
        while (data != null) {
                
    		try {
        	    //System.out.println("BaL(put):" +Thread.currentThread().getName() +" passe à l'état waiting");
				wait();
				//System.out.println("BaL(put):" +Thread.currentThread().getName() +" sort de l'état waiting");
            } catch (InterruptedException e) {}
        }
	    
	    //System.out.println("BaL(put):" +Thread.currentThread().getName() +" écrit un message");
	    
	    data = put_data;

	    // Une fois la donnée déposée on réveille les threads en attente
	    notifyAll();
	    return;

    }//fun de la méthode put    

    public synchronized String[] get() {
    	// si aucun message n'a été envoyé, 
    	// l'exécution du thread est suspendue, dès qu'un 
    	// thread producteur aura écrit un message et 
    	// que le thread sera réveillé l'exécution pourra reprendre
    	while (data == null) {
    		
    		try {
    			//System.out.println("BaL(get):" +Thread.currentThread().getName() +" passe à l'état waiting");
    			
    			wait();

    			//System.out.println("BaL(get):" +Thread.currentThread().getName() +" sort de l'état waiting");
    			
    		} catch (InterruptedException e) { }
    	}
   
    	//System.out.println("BaL(get):" +Thread.currentThread().getName() +" récupère un message");
    	
        String[] get_data = data ;
        data = null ;
        
        // Une fois la boite aux lettres vide on réveille les threads en attente
        notifyAll();
        return get_data;
            
    }// fin de la méthode get

}
