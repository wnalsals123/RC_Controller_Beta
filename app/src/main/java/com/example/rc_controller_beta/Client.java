package com.example.rc_controller_beta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket sock= null;
    BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
    PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
    public static String line = "ServerMsg";

    public void connection(String IP, int PORT){
        new Thread(()->{
            try {
                sock = new Socket(IP, PORT);
                Option.connect = true;
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void PushMsg(String msg){
        new Thread(()->{
            if(sock == null)
                return;
            out.println(msg);                        //서버로 데이터 전송
            out.flush();
        }).start();
    }

    public void ReadThread(){
        new Thread(()->{
            while(true)
                ReadMsg();
        }).start();
    }

    public void ReadMsg(){
        new Thread(()->{
            try {
                line = in.readLine();                //Client로부터 데이터를 읽어옴
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
