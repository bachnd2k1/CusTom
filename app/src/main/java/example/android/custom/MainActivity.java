package example.android.custom;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity   {
    Interface anInterface;
    List<Custom>  array;
    List<ItemMenu> arraymenu;
    List<VideoYoutube> listvideo = new ArrayList<>();
    GridView gridview;
    Adapter adapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    SharedPreferences preferences;
    SwipeRefreshLayout swipeRefreshLayout;
    public static String API_Key = "AIzaSyDASv1K0r069KWhyChfVWfza_tSdYvUEk4";
    String ID_PlayList = "PLHdhCo9EQBho_hlMSlt7_l9M8JkPzg2wp";
    String url_getJSON = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLHdhCo9EQBho_hlMSlt7_l9M8JkPzg2wp&key=AIzaSyDASv1K0r069KWhyChfVWfza_tSdYvUEk4&maxResults=50";
    String URL1="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLHdhCo9EQBho_hlMSlt7_l9M8JkPzg2wp&key=AIzaSyDASv1K0r069KWhyChfVWfza_tSdYvUEk4&maxResults=50&pageToken=CDIQAA";
    private Custom custom;

    private GioiThieu gioiThieu=new GioiThieu();
    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        preferences=this.getSharedPreferences("SORT",MODE_PRIVATE);
        gridview = findViewById(R.id.id_gridview);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.id_toolbar);
        listView = findViewById(R.id.id_listview);
        navigationView = findViewById(R.id.id_navigationview);
        swipeRefreshLayout=findViewById(R.id.id_refresh);


      //  myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);




        getData();
        ActionToolBar();
        ActionMenu();
        GetJsonYoutube(url_getJSON);
        GetJsonYoutube(URL1);


       gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                i.putExtra("id", array.get(position).getId());
                i.putExtra("key", array.get(position).getKey());
                i.putExtra("icon", array.get(position).getIcon());
                i.putExtra("name", array.get(position).getName());
                i.putExtra("tittle", array.get(position).getTittle());
                i.putExtra("tags",array.get(position).getTags());
                startActivity(i);
            }
        });


    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String description=array.get(position).getDescription();
                Custom custom=new Custom(description);
                GioiThieu gioiThieu=(GioiThieu)getSupportFragmentManager().findFragmentById(R.id.fragment_gioi_thieu);
                List<Fragment>fragmentList=new ArrayList<>();
                fragmentList.add(gioiThieu);
                Bundle bundle=new Bundle();
                bundle.putSerializable("custom",custom);
                gioiThieu.setArguments(bundle);
                 FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container_view,gioiThieu);
                 fragmentTransaction.commit();

        }
    });


     //   swipeRefreshLayout.setOnRefreshListener(this);
    }


 /*   @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

            }
        },1000);
    }*/


    private void getData() {
        array = new ArrayList<>();
        new ReadJsonAsyncTask().execute("https://raw.githubusercontent.com/ngryman/lol-champions/master/champions.json?fbclid=IwAR0_MlgEOQUQoa5_ci6dCBW5M0rnUFdIPnGN8pGc3vlADvaw5gtdLHlQI0M");
    }



    class ReadJsonAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                String tags="";
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.optString("id");
                    String key=jsonObject.optString("key");
                    String icon = jsonObject.optString("icon");
                    String name = jsonObject.optString("name");
                    String tittle = jsonObject.optString("tittle");
                    JSONArray jsonArray1=jsonObject.getJSONArray("tags");
                    for(int j=0;j<jsonArray1.length();j++)
                    {
                         tags=jsonArray1.optString(j);
                    }
                    String descriptions = jsonObject.optString("description");
                    array.add(new Custom(id, key, icon, name, tittle, tags, descriptions));

                }
                // adapter.notifyDataSetChanged();
                String SortSetting=preferences.getString("Sort","ascending");
                if (SortSetting.equals("ascending"))
                {
                    Collections.sort(array,Custom.AscendingtByName);
                }
                else if (SortSetting.equals("descending"))
                {
                    Collections.sort(array,Custom.DescendingtByName);
                }
                adapter = new Adapter(MainActivity.this,array);
                gridview.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }





    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void ActionMenu() {
        if (arraymenu == null) {
            arraymenu = new ArrayList<ItemMenu>();
            arraymenu.add(new ItemMenu("Trang chủ "));
            arraymenu.add(new ItemMenu("Trang bị"));
            arraymenu.add(new ItemMenu("Ngọc"));
            arraymenu.add(new ItemMenu("Phép Bổ Trợ"));
            arraymenu.add(new ItemMenu("Yêu thích"));
            arraymenu.add(new ItemMenu("Cài đặt"));
        }

     /*  List<String> tmp = new ArrayList<>();
        tmp.add("ahahah");
        tmp.add("hehehe");
        System.out.println("cai gi do day nhi: " + arraymenu.get(0));*/
        ArrayAdapter adapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraymenu);
        listView.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
    }



    private void IntentMenu()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent i=new Intent(MainActivity.this,MainActivity.class);
                        break;

                }
            }
        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_bar);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
             return false;
             }
        });
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.sort)
        {
            ShowDialog1();
            return  true;
        }
        if(id==R.id.filter)
        {
            ShowDialog2();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowDialog2() {
        String [] option1={"All","Assasin","Fighter","Marksman","Mage","Support","Tank"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Filter by");
        builder.setItems(option1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    Adapter adapter=new Adapter(getApplicationContext(),array);
                    gridview.setAdapter(adapter);
                }
                if(which==1)
                {
                    adapter.filter("Assasin");
                }
                if(which==2)
                {
                    adapter.filter("Fighter");
                }
                if(which==3)
                {
                    adapter.filter("Marksman");
                }
                if (which==4)
                {
                    adapter.filter("Mage");
                }
                if(which==5)
                {
                    adapter.filter("Supprt");
                }
                if(which==6)
                {
                    adapter.filter("Tank");
                }

            }
        });
        builder.show();
    }

    private void ShowDialog1() {
        String [] option={"Ascending","Decending"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort by");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0)
                {
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("Sort","ascending");
                    editor.apply();
                    getData();
                }
                if (which==1)
                {
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("Sort","descending");
                    editor.apply();
                    getData();
                }
            }
        });
        builder.show();
    }

    private void GetJsonYoutube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //String Idvideo = " ";
                            JSONArray jsonItems = response.getJSONArray("items");
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                                String idvideo = jsonResourceID.optString("videoId");
                                listvideo.add(new VideoYoutube(idvideo));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }



}