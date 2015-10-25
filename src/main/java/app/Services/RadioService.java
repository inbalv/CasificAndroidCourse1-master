package app.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class RadioService extends Service {
    public MediaPlayer myRadio;
    public boolean activeRadio;
    public RadioService() {

    }

//define binding to the service-fragment

    private final IBinder binder=new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }
    public class MyBinder extends Binder{
        public RadioService getService(){
            return RadioService.this;
        }
    }

  //playMyRadio() uses new thread which call to runnable, this function connects with media player
    // and tried to active the media player and return activeRadio- true if it is done or false if it catch
    public boolean playMyRadio() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String URLradio = "http://listen.radionomy.com/HippieSoulRadio";
                myRadio = new MediaPlayer();
                try {
                    myRadio.reset();
                    myRadio.setDataSource(URLradio);
                    myRadio.prepareAsync();
                    myRadio.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                            activeRadio= true;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                 activeRadio= false;
                }
                Log.i("app.Services", "We are in the thread");
            }
        };
        Thread serviceThread = new Thread(runnable);
        serviceThread.start();


     return activeRadio;

    }
    //  pauseMyRadio() checks is the MP exists,stop it,and and return false if it stops
    public boolean pauseMyRadio() {
        if (myRadio!=null) {
            if (myRadio.isPlaying()) {
                myRadio.stop();
                activeRadio = false;
            }
        }
      return  activeRadio;
    }
//  stopMyRadio() execute stopSelf for the service and return false to the fragment
    public boolean stopMyRadio(){
        stopSelf();
        boolean activeRadio=false;
        return activeRadio;
    }



}
