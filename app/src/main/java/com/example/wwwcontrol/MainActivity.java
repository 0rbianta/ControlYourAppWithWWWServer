package com.example.wwwcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    private EditText output;
    private String data, requestedText="hello world!", link="https://raw.githubusercontent.com/0rbianta/ControlYourAppWithWWWServer/master/MY_SERVER_DATA.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output=findViewById(R.id.output);

    }

    public void btnGetCommandClick(View v){

        new getCommand().execute();

    }

    public class getCommand extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params){
            try{

                Document site=Jsoup.connect(link).get(); // Server and file that we will pick data.

                data=site.toString(); // Now, we pick data from the WWW Server.

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        output.setText(data); // Set data to output.
                    }
                });

                if (data.contains(requestedText)) {
                    // If the server contains the data we want, it will run this code.
                    System.out.println("Data contains "+requestedText);
                }

            }catch(Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        output.setText("Error. Did you connect internet?"); // Set data to output.
                    }
                });

                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }

}
