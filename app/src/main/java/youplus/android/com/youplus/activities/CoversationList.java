package youplus.android.com.youplus.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import youplus.android.com.youplus.R;
import youplus.android.com.youplus.adapter.ConversationListAdapter;
import youplus.android.com.youplus.adapter.DividerItemDecoration;
import youplus.android.com.youplus.model.ChatData;

public class CoversationList extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CoversationList.class.getSimpleName();
    private RecyclerView rvConversationList;
    private ImageView ivAdd;
    private ProgressDialog progressDialog;
    private List<ChatData> chatList;
    private ConversationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coversation_list);

        progressDialog = new ProgressDialog(this);
        setViews();
    }

    private void setViews() {

        rvConversationList = (RecyclerView)findViewById(R.id.rvConversationList);

        // now this below line contains updated booking list
        chatList =  ChatData.getChatList();
        Toast.makeText(this,"size= "+chatList.size(),Toast.LENGTH_LONG).show();
        adapter = new ConversationListAdapter(this,chatList);

        rvConversationList.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        rvConversationList.addItemDecoration(itemDecoration);
        rvConversationList.setLayoutManager(layoutManager);

        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(this);

    }

    public void showProgressDialog(){
        progressDialog.setMessage(getString(R.string.progressProcess));
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog!=null && progressDialog.isShowing())
            progressDialog.hide();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.ivAdd){
            AddAsyncConversation addConversation= new AddAsyncConversation();
            addConversation.execute();
        }
    }

    private class AddAsyncConversation extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ChatData chatData = new ChatData();
            chatData.setName("Contact new");
            chatData.setLastMsg("Did you catch pokeman today ??? ");
            Calendar calendarDateTime= Calendar.getInstance();
            chatData.setTime(calendarDateTime.getTimeInMillis());
            chatList.add(0,chatData);
            chatData.save();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hideProgressDialog();

            adapter.notifyItemInserted(0);
            adapter.notifyDataSetChanged();


            Snackbar snackbar= Snackbar.make(findViewById(R.id.llConversationList), "New Conversation added!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Snackbar snackbar1 = Snackbar.make(findViewById(R.id.llConversationList), "Added conversation removed", Snackbar.LENGTH_SHORT);
                    snackbar1.show();

                    chatList.remove(0);
                    adapter.notifyItemRemoved(0);
                    adapter.notifyDataSetChanged();

                }
            });

            snackbar.show();

        }
    }
}
