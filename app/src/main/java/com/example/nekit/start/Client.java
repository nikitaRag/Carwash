package com.example.nekit.start;

/**
 * Created by Nekit on 26.10.2017.
 */

public class Client{
    int client_id;
    String name,secondname,surname;

    Client(int id,String Name,String Secondname,String Surname){
        client_id = id;
        name = Name;
        secondname = Secondname;
        surname = Surname;
    }
    public int getClient_id(){
        return client_id;
    }
    public String getNameSecondname(){
        String str = name + " " + secondname;
        return str;
    }
}
