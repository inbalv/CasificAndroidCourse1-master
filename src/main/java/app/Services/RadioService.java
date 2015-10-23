package app.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;

public class RadioService extends Service {
    public RadioService() {
    }



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
    public boolean playMyRadio(){
        boolean actionRadio=true;
        return actionRadio;
    }
    public boolean pauseMyRadio(){
        boolean actionRadio=true;
        return actionRadio;
    }
    public boolean stopMyRadio(){
        stopSelf();
        boolean actionRadio=true;
        return actionRadio;
    }



}
