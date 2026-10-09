package ENG.Sprint;


public class Network {

    CellTower[] net;
    CellTower proxy;

    public Network(){
        init(20);
        // fill net with proxies with all the cell towers in the network.


    }

    private  void init(int size){
        proxy = new CellTower();
        net = new CellTower[size];
        for(int i = 0; i < net.length; i++){
            net[i] = new CellTower();
            // proxy = net[i];
        }
        proxy = net[0];
    };

    public Network(int numTowers){
        int size = (numTowers>0) ? numTowers : 20;

        init(size);
        

    }

    /**
     * 
     * @return active cell tower, null if none available
     */
    public CellTower getAvailTower(){
        for(int i = 0; i < net.length; i++){
            if(net[i] == null) {    
            System.out.println("Index " + i + " is NULL!"); 
            continue;
        }
        if(net[i].avail()){
            proxy = net[i];
            return proxy;
        }            

        // }
        // for(CellTower n : net){
        //     if(n != null && n.avail()){
        //         proxy = n;
        //         return n;
        //     }
        // }
        }
        return null;
    };

    /**
     * The last used cell tower. I don't really care to implement this further
     * @return the last used cell tower (proxy)
     */
    public CellTower getActiveTower(){
        return proxy;
    }


    
    public CellTower findTowerForPhone(CellPhone phone){
        for(CellTower n : net){
            if(n != null && n.avail()){
                n.connectPhone(phone);
                proxy = n;                
                return n;
                
            }
        }

        return null;
    }


}
