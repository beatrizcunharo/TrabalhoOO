package Arquivo;

import Registros.Login;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 *  
    @author BEATRIZ CUNHA RODRIGUES             201776038
    @author DANIEL RIBEIRO LAVRA                201735042
    @author IVANYLSON HONÓRIO GONÇALVES         201776002
    @author JAKSON DUARTE MARTINS JÚNIOR	201435004
    @author MIKAELA CRISTINY QUITZ              201676014

 */
public class ArquivoLogin {
    public List<Login> logins;
    
    
    public ArquivoLogin(){
        this.logins = new ArrayList<>();
    }
        
    public void cadastra(String user, String senha, String tipo){
        Login l = new Login();      
        l.setUsuario(user);
        l.setSenha(senha);
        l.setTipo(tipo);
        logins.add(l);  
    }
    
    
    public boolean input() throws IOException{
        Type tipoLista = new TypeToken<List<Login>>() {}.getType();
        Gson gson = new Gson();
        String json = gson.toJson(logins, tipoLista);
        
        File file = new File("arquivoLogin.json");
          
            if(file.exists()){
//                FileReader reader = new FileReader(file);
//                BufferedReader buffer = new BufferedReader(reader);
//                StringBuffer sb = new StringBuffer();
//                while(buffer.ready()){
//                    sb.append(buffer.readLine());
//                }
                file.getParentFile().mkdirs();

                Writer fw = new OutputStreamWriter( new FileOutputStream(file) ) ;
//                fw.write( sb.toString() );
                fw.write(json);
                fw.close();
            }else{
                
                FileWriter input = new FileWriter(file, false);
            
                input.write(json);
                input.close();
                return true;
            }
            return false;
        }   
    
    public void output(){
        
        Gson gson = new Gson();
        Type tipo = new TypeToken<List<Login>>() {}.getType();
        File nome = new File("arquivoLogin.json");
        try{
            BufferedReader br = new BufferedReader(new FileReader(nome));
            //FileReader reader = new FileReader(nome);
            String collect = br.lines().collect(Collectors.joining());
            
            System.out.println(collect);
            List<Login> lista = gson.fromJson(collect, tipo);
         
            logins.addAll(lista);
            System.out.println(lista);
        }catch (IOException e){
            e.printStackTrace();
        }       
    }
}
