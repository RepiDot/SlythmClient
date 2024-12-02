package com.repidot.slythmclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements  TimePickerDialog.OnTimeSetListener{

    public static final String TAG = "MAIN";

    private TextView time_text;

    private static SharedPreferences alarm;
    private static SharedPreferences.Editor alarmE;

    private static CheckBox sun;
    private static CheckBox mon;
    private static CheckBox tue;
    private static CheckBox wed;
    private static CheckBox thu;
    private static CheckBox fri;
    private static CheckBox sat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_text = findViewById(R.id.time_text);
        alarm = getPreferences(Context.MODE_PRIVATE);
        alarmE = alarm.edit();
        sun = findViewById(R.id.checkBoxSunday);
        mon = findViewById(R.id.checkBoxMonday);
        tue = findViewById(R.id.checkBoxTuesday);
        wed = findViewById(R.id.checkBoxWednesday);
        thu = findViewById(R.id.checkBoxThursday);
        fri = findViewById(R.id.checkBoxFriday);
        sat = findViewById(R.id.checkBoxSaturday);

        Button time_btn = findViewById(R.id.time_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = ContextCompat.getSystemService(this, AlarmManager.class);
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // FLAG 추가 필요
                this.startActivity(intent);
            }
        }

        Log.d("Time", String.valueOf(alarm.getInt("H", -1)));
        if(alarm.getInt("H", -1) != -1) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, alarm.getInt("H", -1));
            c.set(Calendar.MINUTE, alarm.getInt("M", -1));
            c.set(Calendar.SECOND, 0);
            updateTimeText(c);

            sun.setChecked(alarm.getBoolean("sun", false));
            mon.setChecked(alarm.getBoolean("mon", false));
            tue.setChecked(alarm.getBoolean("tue", false));
            wed.setChecked(alarm.getBoolean("wed", false));
            thu.setChecked(alarm.getBoolean("thu", false));
            fri.setChecked(alarm.getBoolean("fri", false));
            sat.setChecked(alarm.getBoolean("sat", false));
        }

        //시간 설정
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        //알람 취소
        Button alarm_cancel_btn = findViewById(R.id.alarm_cancel_btn);
        alarm_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
    }


    /**
     * 시간을 정하면 호출되는 메소드
     * @param view 화면
     * @param hourOfDay 시간
     * @param minute 분
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Log.d(TAG, "## onTimeSet ## ");
        Calendar c = Calendar.getInstance();
        boolean[] week = {sun.isChecked(), mon.isChecked(), tue.isChecked(), wed.isChecked(), thu.isChecked(), fri.isChecked(), sat.isChecked()};

        alarmE.putBoolean("sun", sun.isChecked());
        alarmE.putBoolean("mon", mon.isChecked());
        alarmE.putBoolean("tue", tue.isChecked());
        alarmE.putBoolean("wed", wed.isChecked());
        alarmE.putBoolean("thu", thu.isChecked());
        alarmE.putBoolean("fri", fri.isChecked());
        alarmE.putBoolean("sat", sat.isChecked());

        alarmE.putInt("H", hourOfDay);
        alarmE.putInt("M", minute);
        alarmE.apply();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        //화면에 시간지정
        updateTimeText(c);

        //알람설정정
        startAlarm(c, week);
    }

    /**
     * 화면에 사용자가 선택한 시간을 보여주는 메소드
     * @param c 시간
     */
    private void updateTimeText(Calendar c){

        Log.d(TAG, "## updateTimeText ## ");
        String timeText = "알람시간: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        time_text.setText(timeText);
    }

    /**
     * 알람 시작
     * @param c 시간
     */
    @SuppressLint("ScheduleExactAlarm")
    private void startAlarm(Calendar c, boolean[] week){
        Log.d(TAG, "## startAlarm ## ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        // 요일 설정
        intent.putExtra("weekday", week);

        Log.d("Check", Arrays.toString(intent.getBooleanArrayExtra("weekday")));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE);
        long selectTime = c.getTimeInMillis();

        // 설정 시간이 현재 시간 이전일 때, 하루 뒤로 설정
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        Log.d("Check", Arrays.toString(intent.getBooleanArrayExtra("weekday")));

        //RTC_WAKE : 지정된 시간에 기기의 절전 모드를 해제하여 대기 중인 인텐트를 실행
        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        // 매일 반복
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, selectTime, pendingIntent);
    }

    /**
     * 알람 취소
     */
    private void cancelAlarm(){
        Log.d(TAG, "## cancelAlarm ## ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        if(intent.getBooleanArrayExtra("weekday") != null) {
            Log.d("remove", "removeWeekday");
            intent.removeExtra("weekday");
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE);
        alarmE.putInt("H", -1);
        alarmE.apply();

        alarmManager.cancel(pendingIntent);
        time_text.setText("알람 취소");
    }
}






//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.unity3d.player.UnityPlayerGameActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        this.getBaseContext();
//
//        Intent intent = new Intent(MainActivity.this, UnityHandlerActivity.class);
//        startActivity(intent);
//    }
//}