package example.android.custom;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends BaseAdapter  {
    GridView gridview;
    private Context context;
    private List<Custom> array;
    private ArrayList<Custom> arraylist;
    public Adapter(Context context, List<Custom> array) {
        this.context = context;
        this.array=array;
        this.arraylist=new ArrayList<Custom>();
        this.arraylist.addAll(array);




        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();

                System.out.println("33: " + exception.getMessage());
            }
        });
    }




    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public interface ClickPass {
        public void GetItem(List<Custom> array);

    }

    public class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//create layoutInflater
            convertView = layoutInflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.id_imgview);
            holder.textView = (TextView) convertView.findViewById(R.id.id_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Custom custom = (example.android.custom.Custom) getItem(position);
        holder.textView.setText(custom.getName());
        Picasso.with(context).load(custom.getIcon()).into(holder.imageView);
        return convertView;
    }


    public void filter(String charText)
    {
        charText=charText.toLowerCase(Locale.getDefault());
        array.clear();
        if (charText.length()==0)
        {
            array.addAll(arraylist);
        }
        else
        {
            for (Custom custom:arraylist)
            {
                if (custom.getId().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    array.add(custom);
                }
            }
        }
       notifyDataSetChanged();
    }





}