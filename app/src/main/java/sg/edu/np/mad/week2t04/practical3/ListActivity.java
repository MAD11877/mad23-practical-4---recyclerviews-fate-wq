package sg.edu.np.mad.week2t04.practical3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        List<User> userList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Random random = new Random();
        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {
            String name = faker.name().firstName();
            String description = "Description " + (i + 1);
            boolean followed = random.nextBoolean();

            User user = new User(name, description,i, followed);
            userList.add(user);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                User user = userList.get(position);
                int randomNumber = userAdapter.generateRandomNumber();


                // Show AlertDialog with name
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Profile");
                builder.setMessage(user.getName());
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Launch MainActivity
                        // Launch MainActivity and pass the user data
                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("randomNumber", randomNumber);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}







