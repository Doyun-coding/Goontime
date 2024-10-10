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

public class PostAdapter_secret extends RecyclerView.Adapter<PostAdapter_secret.PostViewHolder> {

    private List<Post> datas;

    public PostAdapter_secret(List<Post> datas) { this.datas = datas; }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_secret, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post data = datas.get(position);
        holder.item_title_secret.setText(data.getTitle());
        holder.item_contents_secret.setText(data.getContent());
        holder.item_nickname_secret.setText(data.getNickname());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView item_nickname_secret;
        private TextView item_title_secret;
        private TextView item_contents_secret;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title_secret = itemView.findViewById(R.id.item_title_secret);
            item_contents_secret = itemView.findViewById(R.id.item_contents_secret);
            item_nickname_secret = itemView.findViewById(R.id.item_nickname_secret);

        }
    }
}
