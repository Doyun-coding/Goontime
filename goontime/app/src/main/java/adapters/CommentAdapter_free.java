package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonbarytime.Chat;
import com.example.goonbarytime.Comment;
import com.example.goonbarytime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommentAdapter_free extends RecyclerView.Adapter<CommentAdapter_free.PostViewHolder> {

    ArrayList<Comment> mComment;
    EventListener<QuerySnapshot> context;
    private ArrayList<Chat> mDatas;
    private String myEmail = "";

    public CommentAdapter_free(ArrayList<Chat> mDatas, String myEmail) {
        this.mDatas = mDatas;
        this.myEmail = myEmail;
    }
    public CommentAdapter_free() {

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_free, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Comment comment = mComment.get(position);
        holder.item_comment_nickname_free.setText(comment.getNickname());
        holder.item_comment_contents_free.setText(comment.getContent());
    }

    @Override
    public int getItemCount() { return mComment.size(); }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView item_comment_nickname_free;
        private TextView item_comment_contents_free;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            item_comment_contents_free = itemView.findViewById(R.id.item_comment_contents_free);
            item_comment_nickname_free = itemView.findViewById(R.id.item_comment_nickname_free);

        }
    }
}
