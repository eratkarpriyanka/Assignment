package youplus.android.com.youplus.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "conversation")
public class ChatData extends Model{

    @Column(name = "name")
    String name;
    @Column(name = "lastMsg")
    String lastMsg;
    @Column(name = "lastRcvTime")
    long time;

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static List<ChatData> getChatList(){
        return new Select().from(ChatData.class).orderBy("lastRcvTime DESC").execute();
    }
}
