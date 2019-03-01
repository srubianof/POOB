package conquerWorld;

import shapes.*;

import java.util.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Manage all the nations and also ConquerWorld the canvas .
 *
 * @author  (Yeisson Gualdron y Santiago Rubiano)
 * @version (9.3)
 */

public class ConquerWorld
{
    // instance variables - replace the example below with your own
    private shapes.Canvas mundo;
    private Nation nation;
    private Cash cash;
    private Route elimina;    
    private Route route;        
    private ArrayList<Nation> arrayNations;
    private ArrayList<String> colorNations;
    private ArrayList<Route> routes;
    private HashMap<String,Integer> visited; //tabla de hash para saber si un una nacion esta visitado
    private Map<String,ArrayList<String>> adjList; //lista de adyacencia para los nodos del grafo           
    private boolean isVisible;
    private boolean aCicle;
    private boolean okR;
    private int maxX;
    private int nNations;
    
    /**
    /**
     * Constructor de objetos de la clase conquerWorld, solicitamos memoria para los contenedores
     * de la clase conquerWorld y para crear otros objetos
     */
    public ConquerWorld(int maxX,int maxY){
        this.maxX = maxX;
        this.mundo = mundo.getCanvas(maxX,maxY);
        
        arrayNations = new ArrayList<Nation>();
        colorNations = new ArrayList<String>();
        routes = new ArrayList<Route>();        
        fondoCash f = new fondoCash(maxX);
        cash = new Cash(0,maxX);
        colorNations = new ArrayList<String>();
        visited = new HashMap<String,Integer>();
        adjList = new HashMap<>();
        isVisible = false;
        
    }   
    /** Anadir efectivo al presupuesto de batalla, ademas guardamos el antigua cash le sumamos el valor de cash a anadir 
     * y comparamos con el nuevo valor 
	 *hola
     *  
     *
     * @param  dinero a adicionar
     */
    public void addCash(int c){
        long bCash=cash.getCash();
        cash.addCash(c);
        okR = ((cash.getCash()==(bCash+c))? true : false);        
    }
    /**
     * Anade una nueva nacion a la batalla, para esto revisa que no exista una nacion en esta posicion 
     *
     * @param  La forma,el color, el area, la posicion y las unidades necesarias para esta nueva nacion
     */
    public void addNation(String shape, int area,String color,int[] position, int[] armies){
        boolean put=true;
        int side;
        int xPos=position[0],yPos=position[1];
        nNations=colorNations.size();
        int[] aux= new int[2];
        //revisamos si existen naciones anteriormente creadas
        if(nNations>0){
            if(shape.equals("pentagon")){
                side = (int)Math.sqrt(2*area);
                put = put && canPut(position); //1
                position[0]+=side;
                put = put && canPut(position); //2
                position[1]-=side;
                put = put && canPut(position); //3
                position[0]-=side*2;
                put= put&&canPut(position);  //4
                position[1] +=side;
                put = put && canPut(position); //5
                position[0]+=side;
                position[1]+=side;
                put = put&&canPut(position); //6
                position[1]-=side;
            }
            else if(shape.equals("triangle")){
                side = (int)Math.sqrt(2*area);
                put = put&&canPut(position);
                position[0]+=(int)side/2;position[1]+=side;
                put = put&&canPut(position);
                position[0]-=side;
                put = put&&canPut(position);
                position[0]+=(int)side/2;position[1]-=side;
            }
            else if(shape.equals("circle")){
                side = (int)(Math.sqrt(area/Nation.PI))*2;
                put = put && canPut(position);
                position[0]+=side;
                put = put && canPut(position);
                position[1]+=side;
                put = put && canPut(position);
                position[0]-=side;
                put = put && canPut(position);
                position[1]-=side;
            }
            else if(shape.equals("square")){
                side = (int)Math.sqrt(area);
                put = put && canPut(position);                
                position[0]+=side;
                put = put && canPut(position);                
                position[1]+=side;
                put = put && canPut(position);
                position[0]-=side;
                put = put && canPut(position);
                
            }
            else{
                side = (int)Math.sqrt(2*area);
                put = put && canPut(position);                 
                position[0]+=(int)side/2;
                put = put && canPut(position);
                position[1]+=side;
                put = put && canPut(position);
                position[0]-=(int)side/2;
                put = put && canPut(position);
                position[1]-=side;
            }
        }        
        if(put &&  !(colorNations.contains(color))){
            aux[0]=xPos;aux[1]=yPos;
            nation = new NormalN(shape,area,color,aux,armies,this.mundo);
            arrayNations.add(nation);
            colorNations.add(color);
            adjList.put(color,new ArrayList<String>());
            okR=true;
        }
        else{
            //JOptionPane.showMessageDialog(null,"El lugar esta ocupado por otra nacion o nacion existente");
            okR=false;
        }
    }        
    /**
     * Remueve una nacion de la batalla, remueve la nacion si no tiene rutas a otras naciones 
     *
     * @param  Nombre/Color de la nacion
     * 
     */
    public void removeNation(String removeColor){        
        String colorob;
        okR=false;
        boolean okTempt = true;    
        for (Nation n:arrayNations){
            if (n.getColor().equals(removeColor)&& n.canRemoveN()){
                if(colorNations.contains(removeColor)){ 
                    for(int i=0;i<arrayNations.size();i++){
                        colorob= arrayNations.get(i).getColor();
                        if(arrayNations.get(i).getColor().equals(removeColor)){
                            for(String nodo: colorNations){
                                 for(int k =0;k<adjList.get(nodo).size();k++){
                                     if(adjList.get(nodo).get(k).equals(removeColor)){
                                        okTempt = false;                                
                                     }
                                 }
                            }
                            //okTemp revisa otra nacion existente tiene una ruta creada hacia removecolor(nacion a remover)
                            if(okTempt){
                                 arrayNations.get(i).removeNationF(removeColor);
                                 arrayNations.remove(i);
                                 colorNations.remove(i);
                                 adjList.remove(removeColor);
                                 okR = true;
                            }
                        }                    
                    }         
                }
            }
        }
    }
    /**
     * Anade una nueva ruta entre dos naciones a la batalla. para esto revisamos si las naciones existen,
     * si no existe una ruta entre la nacionA y la nacionB si lo anterior se cumple creamos y graficamos la ruta 
     *
     * @param  Las naciones con ruta y su costo
     */
    public void addRoute(String[] nations,int cost){
        int[] aPosition={0,0},bPosition={0,0};        
        for(Nation ob : arrayNations){            
            if(ob.getColor()== nations[0]){
                aPosition = ob.getPosition();                
            }
            else if( ob.getColor()==nations[1]){
                bPosition = ob.getPosition();
            }
        }                       
        if (colorNations.contains(nations[0]) && colorNations.contains(nations[1]) ){
            if(okRoute(nations)){
                Route route = new Route(aPosition,bPosition,cost,nations[0],nations[1]); 
                route.makeVisible();
                routes.add(route);    
                adjList.get(nations[0]).add(nations[1]);
                adjList.get(nations[1]).add(nations[0]);
                okR=true;
            }else okR=false;
        }
        else{
            okR=false;
        }
    }       
    /**
     * Remueve una ruta entre dos naciones de la batalla, para esto reviamos que las naciones existan,
     * y eliminamos las apariciones de las naciones en la lista de adyacencia
     *
	 *hola
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void removeRoute(String[] nations)
    {
        int[] aPosition={0,0},bPosition={0,0};
        int posRouteToRemove = 0;
        okR=false;
        boolean puede=true;
        for(Nation n : arrayNations){
            puede = puede&&n.canRemoveR();
        }
        
        for(Route r : routes){
            if((r.getFrom()==nations[0] && r.getTo()==nations[1]) ||( 
            r.getFrom()==nations[1] && r.getTo()==nations[0])&& puede){
                elimina = r;
                for(Nation obNation : arrayNations){
                    if(obNation.getColor()== nations[0]){
                        aPosition = obNation.getPosition();
                        for(int k =0;k<adjList.get(nations[0]).size();k++){
                            if(adjList.get(nations[0]).get(k).equals(nations[1])){
                                adjList.get(nations[0]).remove(k);
                            }
                        }
                    }
                    else if( obNation.getColor()==nations[1]){
                        bPosition = obNation.getPosition();
                        for(int l =0;l<adjList.get(nations[1]).size();l++){
                            if(adjList.get(nations[1]).get(l).equals(nations[0])){
                                adjList.get(nations[1]).remove(l);
                            }
                        }
                    }
                }
                r.removeR(aPosition,bPosition);
                r.makeInvisible();
                okR=true;                
            }            
            if(!okR) posRouteToRemove++;
        }
        if(okR){
            routes.remove(posRouteToRemove);
        }
    }    
    /**
     * Limpia todo el tablero
     */
    public void erase(){
        
        mundo.erase();
        
    }                 
    /**
     * Hace visible todas las naciones y rutas
     */
    public void makeVisible(){
        for (Nation c:arrayNations)
        {
            c.makeVisible();
            okR=true;
        }
        for (Route r:routes){
            r.makeVisible();
            okR=true;
        }
        isVisible = true;
    }      
     /**
     * Hace invisible todas las naciones y rutas.god
     */
    public void makeInvisible(){
        for (Nation c:arrayNations)
        {
            c.makeInvisible();
            okR=true;
        }
        for (Route r:routes){
            r.makeInvisible();
            okR=true;
        }
        isVisible = false;
    }
     /**
     * Anade armamento a una nacion, buscamos la nacion en el arrayList de naciones,
     * obteniendo su color y comparandolo con el color de la nacion a la cual anadiremos 
     * army 
	 *
     * @param  Nacion a agregar armamento
     */
    public void addArmy(String nation){
    
        if (colorNations.contains(nation)){
            for(Nation n : arrayNations){
                if(n.getColor().equals(nation)){
                    n.setArmy(10);
                    okR=true;
                }
            }        
        }
        else okR=false;
    }
    /**
     * Anadimos army a una lista(arreglo estatico de colores) de naciones iterando la lista 
     * y llamando al metodo addArmy con cada uno de los elementos de la lista
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addArmies(String[] nations){
        okR=false;
        boolean conjun=true;
        for(int j=0;j<nations.length;j++){
            addArmy(nations[j]);
            conjun=conjun&&ok();
        }
        okR=conjun;
    }
    /**
     * Quita armamento a una nacion,buscamos la nacion en el arrayList de naciones
     * y revismaos si tiene un army a remover.
	 *
     *
     * @param  Nacion a quitar armamento
     */
    public void removeArmies(String nation){
        okR=false;
        for(Nation n : arrayNations){
            if(n.getColor().equals(nation)){
                if(n.getArmyH()>0){
                    n.setArmy();
                    okR=true;
                }
            }
        }
    } 
    /**
     * Mover armamento entre naciones, para esto revisamos si la ruta entre la nacion A y B existe
     * , ademas reviamos si el cash es suficiente para mover el army por esta ruta
     *
     * @param  Nacion origen y nacion destino
     */
    public void moveArmy(String fromNation,String toNation)
    {
        
        okR = false;
        Nation object1 = getNation(fromNation),object2=getNation(toNation);
        for(Route rout: routes){
            if(((rout.getFrom().equals(fromNation) && rout.getTo().equals(toNation))
            || (rout.getFrom().equals(toNation) && rout.getTo().equals(fromNation)))
            && adjList.get(fromNation).contains(toNation) && adjList.get(toNation).contains(fromNation)
            ){
                if(object1.getArmyH()>0 && cash.getCash()>0 && cash.getCash() > rout.getCost() &&
                    (object1.canRemoveA() && object2.canRemoveA() )
                    ){
                    if( !(object1.getArmy() instanceof Stingy) || (object1.getArmy() instanceof Stingy && 
                    object1.getArmy().canRemoveB(routes,fromNation,toNation,rout) &&
                    object2.getArmy() instanceof Stingy && 
                    object2.getArmy().canRemoveB(routes,fromNation,toNation,rout))  ){
                        
                        System.out.println("mal");
                        erase();
                        int n = object1.getArmyH()-object1.getArmyN();
                        int m = object2.getArmyN()-object2.getArmyH();
                        soloMover(n,m,object1,object2);                                                      
                        cash.addCash(cash.getCash()-rout.getCost());
                        makeVisible();
                        okR=true; break;
                    }
                    else okR = false;
                }
                else okR=false;
            }
            else okR=false;
        }
        System.out.println(okR);
    }
    /*
     *mover un army con las restricciones previamente evaluadas 
     *
    */
    private void soloMover(int n,int m,Nation object1,Nation object2){
        if (n>m){
            object2.setArmy(object2.getArmyN());
            object1.setArmy(-m);
        }
        else {
            object2.setArmy(n);
            object1.setArmy(-n);
        }
    }
    public void moveArmyNaive(String fromNation,String toNation,int value){
        erase();
        Nation object1 = getNation(fromNation),object2=getNation(toNation);
        object1.setArmy(-value);
        object2.setArmy(value);
        makeVisible();
    }
    /**
     * Obtiene la nacion a partir del Nombre/Color, para esto iteramos el arrayList de naciones 
     *  
     * @param  Nombre/Color de nacion
     * @return     Nacion
     */
    private Nation getNation(String nationName){
        int[] pos={1,1};
        Nation x = new NormalN("triangle",1,"blue",pos,pos,this.mundo);
        
        for(Nation n: arrayNations){    
            if(n.getColor().equals(nationName)){ 
                okR=true;
                return n;
            }
        }       
        return x;
    }        
    /**
     * Puedo poner, este metodo toma posiciones en el eje x, y revisa si cualquier nacion anteriormente creada 
     * esta dibujada sobre los puntos x,y anteriores, para esto iteramos el arraylist de naciones y 
     * nos apoyamos en el metodo isFigure 
     *
     * @param arreglo estatico de enteros de dos elementos en su primera posicion la coordenada X,
     * y en el siguiente la coordenada Y
     * 
     * @return false si existe una nacion dibujada sobre los puntos x,y. true de lo contrario
     */
    private boolean canPut(int[] positions){
        boolean aux=true;
        //System.out.println(positions[0]+" "+positions[1] );
        for(Nation nation: arrayNations){
            aux = isFigure(positions[0],positions[1],nation);            
            if(aux) return false;
        }
        return true;
    }
    /**
     * Revisa si una nacion en especifico esta dibujada sobre  posicion x,y  
     * 
     * @Param    int xPoint,int yPoint,Nation toNation
     * @return true si existe una figura dibujada sobre los puntos. false de lo contrario
     */
    private boolean isFigure(int xPoint,int yPoint,Nation toNation){
        int[] natPosition = toNation.getPosition();
        int xPosNation=natPosition[0],yPosNation= natPosition[1], wNation = toNation.getWidth(),hNation= toNation.getHeight();
        String shapeNation = toNation.getShape();         
        if(shapeNation.equals("triangle")){
            if( xPoint<=xPosNation+(wNation/2) && xPoint>= xPosNation-(wNation/2) 
               && (yPoint>=yPosNation && yPoint<= yPosNation+hNation)) return true;
        }
        else if(shapeNation.equals("pentagon")){           
            if(xPoint<=xPosNation+wNation && xPoint>= xPosNation-wNation 
                && (yPoint >= yPosNation-hNation && yPoint<=yPosNation+hNation )) return true;
        }
        else if(shapeNation.equals("circle")){
            if((xPoint>=xPosNation && xPoint <= xPosNation+wNation ) && (yPoint>=yPosNation && yPoint<=yPosNation+hNation)) return true;        
        }
        else if(shapeNation.equals("rectangle")){            
            if((xPosNation <= xPoint && xPoint <= xPosNation+wNation) 
            && ( yPosNation<= yPoint &&  yPoint <= yPosNation+hNation)) return true;            
        }
        else{            
            if((xPoint >= xPosNation && xPoint <= (int)(xPosNation+wNation)) &&
            (yPoint >= yPosNation && yPoint <= (int)(yPosNation+hNation))){
                return true;
            }
            else return false;
        }
        return false;
    }
    /**
     * El metodo finish termina el juego, cerrando y elimiando todos los objetos, variables anteriormente creados
     *
     * @param  
     * @return  
     */
    public void  finish()
    {        
        System.exit(0);
    }    
    /**
     * El metodo okRoute revisa si existe un "camino"(ruta o secuencia de rutas) entre un par de naciones anteriormente creadas,
     * para esto utilizamos el metodo busqueda primero en profundidad el cual nos llena un hashmap de 
     * elementos llamado visited, este hashmap no indica todas las naciones a las cuales puedo llegar desde 
     * la nacion A, por tanto si en el hashmap esta visitada la nacion B luego de realizar la dfs podemos 
     * afirmar que existe un camino entre la nacion A y B
     *
     * @param  arreglo estatico de naciones(colores)
     * @return false si  existe un "camino" entre la nacion A y B. true de lo contrario 
     */
    public boolean okRoute(String[] nations )
    {
        //visited.put(nations[0],1);
        depthFirstSearch(nations[0]);
        if(visited.containsKey(nations[1])){ 
            visited.clear();
            return false;
        }
        else{ 
            visited.clear();
            return true;
        }
    }                
    /**
     * El metodo ok revisa si la operacion exactamente anterior se realizo satisfactoriamente
     * , y se guarda  en una variable local para retornar su valor,pero antes ella se predefine en false  
     *
     * @param 
     * @return okR la cual tiene le valor de true si la operacion anterior fue exitosa. false de lo contrario. 
     */
    public boolean ok()  
    { 
        boolean auxBool=okR;
        okR=false;
        return auxBool;
    }        
    /**
     * El metodo okRoutes tomas todas las naciones y hace una busqueda en profundidad sobre 
     * la lista de adyacencia buscando ciclos en el grafo 
     *
     * @param  
     * @return  false si hay dos caminos entre algun par de naciones, true de lo contrario. 
     */
    public boolean okRoutes()
    {
        aCicle=true;
        for(Nation nat: arrayNations){
            dFS(nat.getColor(),aCicle);
        }
        visited.clear();
        return aCicle;
    }
    /**
     * Consultar rutas de una nacion
     *
     * @param  nacion a consultar
     * @return   rutas desde la nacion dada
     */
    public String[] routes(String nation)
    {
        // adjList.entrySet().stream().forEach(e-> System.out.println(e));
        ArrayList<String> a = adjList.get(nation);
        String[] strings = a.stream().toArray(String[]::new);
        return strings;
    }            
    /**
     * Consultar rutas de todas las naciones
     *
     */
    public void routes()
    {
         Map<String,ArrayList<String>> reversedMap = new TreeMap<String,ArrayList<String>>(adjList);
            for (Map.Entry entry: reversedMap.entrySet()){
                //System.out.println(entry.getKey()+ " " + entry.getValue());
            }
    }
    /**
     * el metodo depthFirstSearch es el clasico algoritmo de busqueda primero en profundidad de la teoria de grafos,
     * el cual utiliza la lista de adyacencia para explorar los nodos del grafo; en este caso las naciones y de 
     * forma recursiva(backtracking) garantiza explorar todos los nodos a los cuales se puede acceder de un nodo 
     * inicial A
     *
     * @param  String nacion inicial 
     * @return void el dfs explora primero en profundidad mientras exista la adyacencia. 
     * Cuando no existen vuelve a una adyacencia anterior hasta finalizar todas las adyacencias
     */
    private void depthFirstSearch(String uNation)
    {
        for(String vNation: adjList.get(uNation)){
            if(!visited.containsKey(vNation)){
                visited.put(vNation,1);
                depthFirstSearch( vNation);
            }
        }
    }    
    /**
     * el metodo dfs busca ciclos en el grafo basado en el mismo criterio de depthFirstSearch, pero este lleva una 
     * variable la cual se define en false si se llega a un nodo anteriormente explorado(existe un ciclo en el grafo)
     * se conforma con hallar 1 no cuenta todos los ciclos
     *
     * @param String nacion inicial de exploracion,boolean  acicle booleano que es false cuando hay uno o mas ciclos en el grafo
     * @return void backtracking cumpliendo el mismo criterio de depthFirstSearch
     */
    private void dFS(String uNation,boolean aCicle)
    {
        for(String vNation: adjList.get(uNation)){
            if(visited.containsKey(vNation)){
                visited.put(vNation,1);
                dFS( vNation,aCicle);
            }
            else aCicle=false;
        }
    }    
    /**
     * el metodo naciones genera un arreglo estatico de String con los colores de las naciones que estan en el mundo sin importar
     * si estan visibles o no
     *
     * @param      
     * @return String[] arreglo estatico de naciones  
     */
    public String[] nations()
    {
        String[] theNations = new String[colorNations.size()];
        for(int i=0;i<colorNations.size();i++){
            theNations[i] = colorNations.get(i);
        }
        return theNations;
    }
    /**
     * El metodo suminnout toma un aumenta o disminuye el tamaño de todas las figuras dibujadas en el mundo
     * 
     *
     * @param  "+" o "-" Aumenta o disminuye el tamaño de las figuras respectivamente
     * @return  
     */
    public void zoominnout(String symbol)
    {
        mundo.sign(symbol);
    }
    public void wait(int miliseconds){
        mundo.wait(miliseconds);
    }
}