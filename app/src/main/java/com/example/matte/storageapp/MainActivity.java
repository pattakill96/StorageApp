package com.example.matte.storageapp;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
    public String[] shop1={"","",""};
    public int i=0,p1=0,p2=0;

    Thread fileW = new Thread(new Runnable() {
        @Override
        public void run() {
            Write();
        }
    });
    Thread fileR = new Thread(new Runnable() {
        @Override
        public void run() {
            Read();
        }
    });


    public void Write() {
        FileWriter filewriter = null;
        BufferedWriter out = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            try {
                File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "StorageApp" + File.separator);
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "StorageApp" + File.separator + "shop1.txt");
                file1.mkdirs();
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        filewriter = new FileWriter(file);
                        out = new BufferedWriter(filewriter);
                        for (i = 0; i < shop.length; i++) {
                            out.write(shop[i] + "\n");
                        }
                        out.flush();
                        out.close();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (filewriter != null) try {
                    filewriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read() {
        FileReader reader = null;
        BufferedReader br = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            int i = 0;
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "StorageApp" + File.separator + "shop1.txt");
                if (file.exists()) {
                    reader = new FileReader(file);
                    br = new BufferedReader(reader);
                    String line;
                    while ((line = br.readLine()) != null) {
                        shop1[i] = line;
                        i++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (reader != null) try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fileW.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        fileR.start();
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
