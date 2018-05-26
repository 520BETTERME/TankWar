package test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JsonTest {

    public static void main(String[]args){

        try{
            BufferedReader br = new BufferedReader(
                    new FileReader("sources/recorder/json/enemyTank.json"));
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("sources/recorder/json/enemyTank2.json"));
            String s = null;
            String ws = null;
            while ((s = br.readLine()) != null){
                //System.out.println(s);
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    int enemyReNum = jsonObject.getInt("enemyReNum");
                    System.out.println(enemyReNum);
                    JSONArray enemyTanks = jsonObject.getJSONArray("enemyTanks");
                    for (int i = 0; i < enemyTanks.length(); i++ ){
                        JSONObject enemyTank = enemyTanks.getJSONObject(i);
                        int x = enemyTank.getInt("x");
                        int y = enemyTank.getInt("y");
                        int direction = enemyTank.getInt("direction");
                        System.out.println(x + " " + y + " " + direction);
                    }
                    ws = jsonObject.toString();
                    //System.out.println(ws);
                }catch (JSONException jsone){
                    jsone.printStackTrace();
                }
            }

            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}
