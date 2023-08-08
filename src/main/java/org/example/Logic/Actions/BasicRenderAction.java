package org.example.Logic.Actions;

import org.example.Entities.StaticEntities.EmptyField;
import org.example.Logic.Map;

public class BasicRenderAction extends Action{
    public static void renderMapWithEmptySpaces(Map map, int squareOfMap){
      for(int i=0;i<squareOfMap;i++){
          map.setMapField(i,new EmptyField());
      }
    }
}
