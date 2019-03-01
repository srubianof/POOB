package aplicacion;
import java.awt.Color;
import java.io.Serializable;

public interface Elemento extends Serializable{
    
  void decida();
  void cambie();
  
  default Color getColor(){
      return Color.red;
  }    
  
  default boolean isVivo(){
      return true;
  }
  
}
