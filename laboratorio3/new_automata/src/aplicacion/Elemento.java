package aplicacion;
import java.awt.Color;

public interface Elemento{
    
  void decida();
  void cambie();
  
  default Color getColor(){
      return Color.red;
  }    
  
  default boolean isVivo(){
      return true;
  }
  
}
