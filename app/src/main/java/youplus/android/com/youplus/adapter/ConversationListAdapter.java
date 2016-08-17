package youplus.android.com.youplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import youplus.android.com.youplus.R;
import youplus.android.com.youplus.model.ChatData;

public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ViewHolder>{

    private static final String TAG = ConversationListAdapter.class.getSimpleName();
    private Context context;
    private List<ChatData> conversationList=null;
    private SimpleDateFormat simpleDateFormat;

    public ConversationListAdapter(Context context, List<ChatData> conversationList){

        this.context = context;
        this.conversationList = conversationList;
        simpleDateFormat = new SimpleDateFormat("h:mm a", Locale.US);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View rowChatView = inflater.inflate(R.layout.conversation_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rowChatView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // get model based on position
        ChatData chatData = conversationList.get(position);

      //  Log.e(TAG, "" + chatData.getName());
        // Set item views based on your views and data model
        holder.ivPerson.setImageResource(R.drawable.person);
        long lastTimestamp = chatData.getTime();

        String strDate = simpleDateFormat.format(lastTimestamp);
        holder.tvTime.setText(strDate);
        holder.tvName.setText(chatData.getName());
        holder.tvLastMsg.setText(chatData.getLastMsg());
    }

    @Override
    public int getItemCount() {
        if(conversationList==null)
            return 0;
        return conversationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int id;
        ImageView ivPerson;
        TextView tvName;
        TextView tvLastMsg;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPerson = (ImageView)itemView.findViewById(R.id.ivPersonPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvLastMsg = (TextView) itemView.findViewById(R.id.tvLastMsg);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }
}
