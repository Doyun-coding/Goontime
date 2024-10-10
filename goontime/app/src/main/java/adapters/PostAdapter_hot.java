package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonbarytime.Post;
import com.example.goonbarytime.R;

import java.util.List;

public class PostAdapter_hot extends RecyclerView.Adapter<PostAdapter_hot.PostViewHolder> {

    private List<Post> datas;

    public PostAdapter_hot(List<Post> datas) { this.datas = datas; }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post data = datas.get(position);
        holder.item_title_hot.setText(data.getTitle());
        holder.item_contents_hot.setText(data.getContent());
        holder.item_nickname_hot.setText(data.getNickname());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView item_nickname_hot;
        private TextView item_title_hot;
        private TextView item_contents_hot;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title_hot = itemView.findViewById(R.id.item_title_hot);
            item_contents_hot = itemView.findViewById(R.id.item_contents_hot);
            item_nickname_hot = itemView.findViewById(R.id.item_nickname_hot);

        }
    }
}
