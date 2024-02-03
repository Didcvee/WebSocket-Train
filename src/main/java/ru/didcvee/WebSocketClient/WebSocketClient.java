package ru.didcvee.WebSocketClient;

import javafx.application.Platform;
import javafx.scene.Group;
import org.java_websocket.handshake.ServerHandshake;
import ru.didcvee.TicTacToe.TicTacToe;

import java.net.URI;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {
    TicTacToe ticTacToe;

    Group group;



    public WebSocketClient(URI serverUri, TicTacToe ticTacToe) {
        super(serverUri);
        this.ticTacToe = ticTacToe;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened connection");
    }


    @Override
    public void onMessage(String message) {
        Platform.runLater(() -> {
            System.out.println(message);
            String[] strings = message.split(" ");
            ticTacToe.buildMouseEventFromServer(
                    Double.parseDouble(strings[0].substring(0, strings[0].indexOf("."))),
                    Double.parseDouble(strings[1].substring(0, strings[1].indexOf("."))), strings[2]
            );
        });
    }


//    @Override
//    public void onMessage(String message) {
//        System.out.println(message);
//        String[] strings = message.split(" ");
//        System.out.println(strings[0]);
//        System.out.println(strings[1]);
//        ticTacToe.buildMouseEventFromServer(Double.parseDouble(strings[0].substring(0, strings[0].indexOf(".")))
//                ,Double.parseDouble(strings[1].substring(0, strings[1].indexOf("."))), group);
//    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Close connection");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
