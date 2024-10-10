package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonbarytime.Chat;
import com.example.goonbarytime.R;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ArrayList<Chat> mDatas;
    private String myEmail = "";

    public ChatAdapter(ArrayList<Chat> mDatas, String myEmail) {
        this.mDatas = mDatas;
        this.myEmail = myEmail;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_opp, parent, false);
        if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
        }
        ChatViewHolder vh = new ChatViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = mDatas.get(position);
        holder.chatting.setText(chat.getContents());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView chatting;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chatting = itemView.findViewById(R.id.chatting);

        }
    }

    @Override
    public int getItemViewType(int position) {
      // return super.getItemViewType(position);
        if (mDatas.get(position).getId() != null) {
            if (myEmail.equals(mDatas.get(position).getId())) {
                return 1;
            }
            else {
                return 2;
            }
        }
        return 3;
    }
}
