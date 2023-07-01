package sg.edu.np.mad.week2t04.practical3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import sg.edu.np.mad.week2t04.practical3.R;
import sg.edu.np.mad.week2t04.practical3.User;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_SPECIAL = 1;

    private List<User> userList;
    private OnItemClickListener listener;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_SPECIAL) {
            View view = inflater.inflate(R.layout.item_user_7, parent, false);
            return new SpecialViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_user, parent, false);
            return new DefaultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        User user = userList.get(position);

        if (viewHolder instanceof SpecialViewHolder) {
            SpecialViewHolder holder = (SpecialViewHolder) viewHolder;
            // Bind data to views in the special layout
            holder.imageViewProfile.setImageResource(R.drawable.ic_launcher_foreground);
            holder.textViewName.setText(user.getName() + "-" + generateRandomNumber());
            holder.textViewDescription.setText(user.getDescription());
        } else if (viewHolder instanceof DefaultViewHolder) {
            DefaultViewHolder holder = (DefaultViewHolder) viewHolder;
            // Bind data to views in the default layout
            holder.imageViewProfile.setImageResource(R.drawable.ic_launcher_foreground);
            holder.textViewName.setText(user.getName() + "-" + generateRandomNumber());
            holder.textViewDescription.setText(user.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        User user = userList.get(position);
        int idLastDigit = user.getId() % 10;
        if (idLastDigit == 7) {
            return VIEW_TYPE_SPECIAL;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView textViewName;
        TextView textViewDescription;

        public DefaultViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            // Set click listener for the profile image
            imageViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public class SpecialViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView textViewName;
        TextView textViewDescription;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            // Set click listener for the profile image
            imageViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    int generateRandomNumber() {
        // Generate a random integer between 100000000 and 999999999 (inclusive)
        Random random = new Random();
        return random.nextInt(900000000) + 100000000;
    }
}
