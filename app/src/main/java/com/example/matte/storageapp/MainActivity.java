package com.example.matte.storageapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends ListActivity {
    public String[] shop ={"ff","ffff","ffffff"};
    public String[] shop1={};
    int i=0;


    public void getString(){
        /* PERMISSIONS ON RUNTIME
        * // Here, thisActivity is the current activity
if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED) {

    // Should we show an explanation?
    if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
            Manifest.permission.READ_CONTACTS)) {

        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

    } else {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(thisActivity,
                new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
    }
}*/
        FileWriter filewriter = null;
        BufferedWriter out = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "shop.txt");
            if(!file.exists()) {
                shop[1]="siamo dentro";
                if(file.createNewFile()){
                    filewriter = new FileWriter(file);
                    out = new BufferedWriter(filewriter);
                    out.write(shop[i]);
                    i++;
                    out.flush();
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(out != null) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(filewriter != null) try {
                filewriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setString() {
        FileReader reader = null;
        BufferedReader br = null;
        int i=0;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
            if(file.exists()) {
                shop[0]="evviva";
                reader = new FileReader(file);
                br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                shop1[i]=line;
                i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getString();
        this.setString();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, shop1);
        getListView().setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
