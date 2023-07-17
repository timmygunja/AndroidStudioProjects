package com.example.app_5_2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    public void startNotificationListener() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShowNotification();
            }
        }).start();
    }
    @Override
    public void onCreate()
    {
        startNotificationListener();
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void ShowNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(getApplicationContext(), MainActivity2.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(getApplicationContext(),
                1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent2 = new Intent(getApplicationContext(), MainActivity3.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(),
                1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(getBaseContext(),"notification_id")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Уведомление")
                .setContentText("Выберите активность")
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .addAction(R.mipmap.ic_launcher, "Первая активность", pendingIntent1)
                .addAction(R.mipmap.ic_launcher, "Вторая активность", pendingIntent2)
                .build();

        notificationManager.notify(0, notification);
    }

}