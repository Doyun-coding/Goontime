package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goonbarytime.Post;
import com.example.goonbarytime.R;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> datas;

    public PostAdapter(List<Post> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post data = datas.get(position);
        holder.item_nickname.setText("작성자 : " + data.getNickname());
        holder.item_title.setText(data.getTitle());
        holder.item_contents.setText(data.getContent());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView item_nickname;
        private TextView item_title;
        private TextView item_contents;

       public PostViewHolder(@NonNull View itemView) {
           super(itemView);

           item_nickname = itemView.findViewById(R.id.item_nickname);
           item_title = itemView.findViewById(R.id.item_title);
           item_contents = itemView.findViewById(R.id.item_contents);
       }
   }
}
