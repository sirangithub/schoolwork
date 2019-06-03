package com.company7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocketServer extends JFrame {
    private JTextField tf_send;
    private JTextArea ta_info;
    private PrintWriter writer;
    private BufferedReader reader;
    private ServerSocket server;
    private Socket socket;

    public void getServer(){
        try{
            server =new ServerSocket(1978);
            ta_info.append("服务器套接字已经创建成功\n");//输出信息
            while (true){//如果套接字是连接状态
                ta_info.append("等待客服机的连接......\n");
                socket=server.accept();//实例化Socket对象
                reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer=new PrintWriter(socket.getOutputStream(),true);
                getClientInfo();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getClientInfo(){
        try{
            while(true){
                String line=reader.readLine();
                if(line!=null)
                    ta_info.append("接收到客服机发送的信息："+line+"\n");
                //显示客户端发送的消息
            }
        }catch (Exception e){
            ta_info.append("客服端已退出。\n");
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }
                if(socket!=null){
                    socket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        TestSocketServer frame=new TestSocketServer();
        frame.setVisible(true);
        frame.getServer();
    }
    public TestSocketServer(){
        super();
        setTitle("服务器端程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,379,260);

        final JScrollPane scrollPane=new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        ta_info=new JTextArea();
        scrollPane.setViewportView(ta_info);

        final JPanel panel=new JPanel();
        getContentPane().add(panel,BorderLayout.SOUTH);

        final JLabel label=new JLabel();
        label.setText("服务器发送的信息：");
        panel.add(label);

        tf_send=new JTextField();
        tf_send.setPreferredSize(new Dimension(150,25));
        panel.add(tf_send);
        final JButton button=new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( final ActionEvent e) {
                writer.println(tf_send.getText());
                ta_info.append("服务器发送的信息是："+tf_send.getText());//将文本框中信息写入流
                tf_send.setText("");
            }
        });
        button.setText("发送");
        panel.add(button);
        final JPanel panel_1=new JPanel();
        getContentPane().add(panel_1,BorderLayout.NORTH);
        final  JLabel label_1=new JLabel();
        label_1.setForeground(new Color(0,0,255));
        label_1.setFont(new Font("",Font.BOLD,22));
        label_1.setText("一对一通信——服务器端程序");
        panel_1.add(label_1);
    }
}
