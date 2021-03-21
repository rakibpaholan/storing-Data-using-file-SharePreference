package com.example.storingdatausingfilesharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private Button save_data_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_text_id);
        save_data_button = (Button)findViewById(R.id.save_data_id);
        save_data_button.setOnClickListener(this);

        loadFile();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String text = editText.getText().toString();
        if (id == R.id.save_data_id){
            if (text.equals("")){
                Toast.makeText(MainActivity.this,"Please Fill The text file first",Toast.LENGTH_SHORT).show();
            }else {
                saveData(text);
            }
        }
    }

    public void saveData(String text){
        try {
            FileOutputStream fileOutputStream = openFileOutput("dairy.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity.this,"Data is Saved",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFile(){
        try {
            FileInputStream fileInputStream = openFileInput("dairy.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\n");
            }
            editText.setText(stringBuffer.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}