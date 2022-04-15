import java.util.function.*;
import java.lang.reflect.*;
import java.util.*;

interface STATE{
    public STATE processState(char activeCharacter);
    
    default boolean isFinalState(){
        return false;
    }
}

class STATE_REGISTRY{
    //;
    private static Map<String, STATE_REGISTRY> elements = new LinkedHashMap<String, STATE_REGISTRY>();
    
    Supplier<STATE> constructor;
    
    private STATE_REGISTRY(Supplier<STATE> a){
        constructor = a;
    }
    
    public static STATE makeStateByEnum(STATE_REGISTRY in){
        return in.constructor.get();
    }
    
    public static void register(String name, Supplier<STATE> newConstructor){
        elements.put(name, new STATE_REGISTRY(newConstructor));
        //try{
        //    Field field = STATE_REGISTRY.class.getDeclaredField("$VALUES");
        //    field.setAccessible(true);
        //    STATE_REGISTRY[] temparray = (STATE_REGISTRY[])field.get(null);
        //    STATE_REGISTRY[] newarray = new STATE_REGISTRY[(temparray.length + 1)];
        //    System.arraycopy(temparray, 0, newarray, 0, temparray.length);
        //    int newordinal = temparray.length;
        //    Constructor c = STATE_REGISTRY.class.getDeclaredConstructor(String.class, int.class, Supplier.class);
        //    c.setAccessible(true);
        //    STATE_REGISTRY newStateR = (STATE_REGISTRY)c.newInstance(name, newordinal, newConstructor);
        //    newarray[temparray.length] = newStateR;
        //    field.set(null, newarray);
        //}catch(Exception e){
        //    e.printStackTrace();
        //}
    }
    
    static STATE_REGISTRY Stringto (String seek){
        STATE_REGISTRY ret;
        /*try{
            //ret = valueOf(seek);
        }catch(IllegalArgumentException e){
            try{
                Class.forName(seek);
            }catch(ClassNotFoundException e2){
                e2.printStackTrace();
            }
        }
        ret = valueOf(seek);
        */
        if((ret = elements.get(seek)) == null){
            try{
                Class.forName(seek);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        ret = elements.get(seek);
        return ret;
    }
}

class STATE1 implements STATE{
    public STATE processState(char activeCharacter){
        switch (activeCharacter){
            case '.': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE4"));
            case '+': case '-': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE2"));
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE3"));
            default: 
            return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
        
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE1::new);
    }
}

class STATE2 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '.': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE4"));
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE3"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE2::new);
    }
}

class STATE3 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '.': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE4"));
            case 'e': case 'E': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE6"));
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE3"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    
    @Override
    public boolean isFinalState(){
        return true;
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE3::new);
    }
}

class STATE4 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE5"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE4::new);
    }
}

class STATE5 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case 'e': case 'E': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE6"));
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE5"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    
    @Override
    public boolean isFinalState(){
        return true;
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE5::new);
    }
}

class STATE6 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '+': case '-': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE7"));
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE8"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE6::new);
    }
}

class STATE7 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE8"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE7::new);
    }
}

class STATE8 implements STATE{
    public STATE processState(char activeCharacter){
        switch(activeCharacter){
            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("STATE8"));
            default: return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto("ERROR_STATE"));
        }
    }
    
    @Override
    public boolean isFinalState(){
        return true;
    }
    
    static{
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), STATE8::new);
    }
}

class ERROR_STATE implements STATE{
    public STATE processState(char a){
        return STATE_REGISTRY.makeStateByEnum(STATE_REGISTRY.Stringto(this.getClass().getSimpleName()));
    }
    static{
        //System.out.println(ERROR_STATE.class.getSimpleName() + "a");
        STATE_REGISTRY.register(Thread.currentThread().getStackTrace()[1].getClassName(), ERROR_STATE::new);
    }
}


public class HelloWorld{
    static STATE initialState = new STATE1();
    
    public static void main(String []args){
        String checkFloat1 = "+01.0321e-21";
        for(int i = 0; i < checkFloat1.length(); i++){
            STATE ret = initialState.processState(checkFloat1.toCharArray()[i]);
            initialState = ret;
        }
        
        if(initialState instanceof ERROR_STATE || !initialState.isFinalState()){
            System.out.println("Token is not a valid float");
        }else{
            System.out.println("Congratulations! You found a valid");
        }
    }
}