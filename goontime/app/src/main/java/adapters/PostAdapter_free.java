package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonbarytime.Comment;
import com.example.goonbarytime.Post;
import com.example.goonbarytime.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter_free extends RecyclerView.Adapter<PostAdapter_free.PostViewHolder> {

    private List<Post> datas;
    private OnNoteListener mOnNoteListener;

    public PostAdapter_free(List<Post> datas, OnNoteListener onNoteListener) {
        this.datas = datas;
        this.mOnNoteListener = onNoteListener;
    }
    public PostAdapter_free(List<Post> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free, parent, false);
        return new PostViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter_free.PostViewHolder holder, int position) {
        Post data = datas.get(position);
        holder.item_title_free.setText(data.getTitle());
        holder.item_contents_free.setText(data.getContent());
        holder.item_nickname_free.setText(data.getNickname());

    }

    @Override
    public int getItemCount() { return datas.size(); }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item_nickname_free;
        private TextView item_title_free;
        private TextView item_contents_free;
        OnNoteListener onNoteListener;

        public PostViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            item_title_free = itemView.findViewById(R.id.item_title_free);
            item_contents_free = itemView.findViewById(R.id.item_contents_free);
            item_nickname_free = itemView.findViewById(R.id.item_nickname_free);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(v, getAdapterPosition());
        }
    }
    public interface OnNoteListener {
        void onNoteClick(View v, int position);
    }
}
