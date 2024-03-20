package cn.henu.typechat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import cn.henu.typechat.ApiService;
import cn.henu.typechat.ChatAdapter;
import cn.henu.typechat.ChatRequest;
import cn.henu.typechat.ChatResponse;
import cn.henu.typechat.Message;
import cn.henu.typechat.R;
import cn.henu.typechat.timeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private EditText userInput;
    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<Message> messages;
    private ApiService apiService;
    private Button send;
    private MessageDatabaseHelper messageDatabaseHelper;
    String currentTime = timeUtils.getCurrentTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userInput = findViewById(R.id.editTextUserInput);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);

        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(messages);

        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.57.181.11:5000")  // Replace with your Flask server IP
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create service interface instance
        apiService = retrofit.create(ApiService.class);

        // Initialize MessageDatabaseHelper
        messageDatabaseHelper = new MessageDatabaseHelper(this);

        send = findViewById(R.id.buttonSendMessage);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendMessageClick(v);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Load messages from SQLite database
        loadMessagesFromDatabase();
    }
    private void loadMessagesFromDatabase() {
        // Clear existing messages
        messages.clear();

        // Load messages from SQLite database
        List<String> savedMessages = messageDatabaseHelper.getAllMessages();
        boolean isCurrentUserMessage = true; // Track whether the current message is sent by the user or the server
        for (String message : savedMessages) {
            // Create Message objects from saved messages and add to the list
            messages.add(new Message(message, isCurrentUserMessage, currentTime));
            // Toggle between user and server messages
            isCurrentUserMessage = !isCurrentUserMessage;
        }

        // Refresh RecyclerView
        chatAdapter.notifyDataSetChanged();

        // Scroll to the last message
        recyclerViewChat.scrollToPosition(messages.size() - 1);
    }
    public void onSendMessageClick(View view) {
        String messageContent = userInput.getText().toString();
        Message userMessage = new Message(messageContent, true, currentTime);
        messages.add(userMessage);

        // Save message to SQLite database
        messageDatabaseHelper.addMessage(messageContent);

        // Refresh RecyclerView
        chatAdapter.notifyDataSetChanged();

        // Scroll to the last message
        recyclerViewChat.scrollToPosition(messages.size() - 1);

        // Clear the input box
        userInput.setText("");

        // Create a request object
        ChatRequest request = new ChatRequest(messageContent);

        // Send the request
        Call<ChatResponse> call = apiService.sendMessage(request);
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the server response
                    String serverResponse = response.body().getAnswer();
                    Message serverMessage = new Message(serverResponse, false, currentTime);
                    messages.add(serverMessage);

                    // Save server message to SQLite database
                    messageDatabaseHelper.addMessage(serverResponse);

                    // Refresh RecyclerView
                    chatAdapter.notifyDataSetChanged();

                    // Scroll to the last message
                    recyclerViewChat.scrollToPosition(messages.size() - 1);
                } else {
                    // Handle the failed request
                    String errorMessage = "Server Error";
                    Message errorServerMessage = new Message(errorMessage, false, currentTime);
                    messages.add(errorServerMessage);

                    // Save error message to SQLite database
                    messageDatabaseHelper.addMessage(errorMessage);

                    // Refresh RecyclerView
                    chatAdapter.notifyDataSetChanged();

                    // Scroll to the last message
                    recyclerViewChat.scrollToPosition(messages.size() - 1);
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                // Handle request failure
                String errorMessage = "Request Failed: " + t.getMessage();

                Message errorServerMessage = new Message(errorMessage, false, currentTime);
                messages.add(errorServerMessage);

                // Save error message to SQLite database
                messageDatabaseHelper.addMessage(errorMessage);

                // Refresh RecyclerView
                chatAdapter.notifyDataSetChanged();

                // Scroll to the last message
                recyclerViewChat.scrollToPosition(messages.size() - 1);
            }
        });
    }
}
