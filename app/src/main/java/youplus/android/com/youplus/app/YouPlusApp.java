package youplus.android.com.youplus.app;




import com.activeandroid.app.Application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import youplus.android.com.youplus.model.ChatData;

public class YouPlusApp extends Application{

    public static YouPlusApp sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //loadData();
    }

    /**
     * dummy entries for conversation
     */
    private void loadData() {

        ChatData chatdata = new ChatData();
        chatdata.setName("John");
        chatdata.setLastMsg("How are you?");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.US);
        Calendar calendarDateTime= Calendar.getInstance();
        chatdata.setTime(calendarDateTime.getTimeInMillis());
        chatdata.save();

        chatdata = new ChatData();
        chatdata.setName("Tina");
        chatdata.setLastMsg("I want to add you as a friend");
        calendarDateTime.add(Calendar.HOUR,-1);
        calendarDateTime.add(Calendar.MINUTE,-11);
        chatdata.setTime(calendarDateTime.getTimeInMillis());
        chatdata.save();

        chatdata = new ChatData();
        chatdata.setName("Mayuri");
        chatdata.setLastMsg("Lets meet tomorrow");
        calendarDateTime.add(Calendar.HOUR,-3);
        calendarDateTime.add(Calendar.MINUTE,-9);
        chatdata.setTime(calendarDateTime.getTimeInMillis());
        chatdata.save();
    }
}
