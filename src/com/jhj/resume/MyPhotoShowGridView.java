package com.jhj.resume;

import android.os.Bundle;

import android.app.Activity; 
import android.content.Context; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.BaseAdapter; 
import android.widget.GridView; 
import android.widget.ImageView; 
import android.widget.Toast; 
 
public class MyPhotoShowGridView extends Activity { 
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.gridview); 
        GridView gv = (GridView)findViewById(R.id.GridView1); 
        gv.setAdapter(new MyAdapter(this)); 
        gv.setOnItemClickListener(new OnItemClickListener() 
        { 
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            { 
                Toast.makeText(MyPhotoShowGridView.this, "pic" + position, Toast.LENGTH_SHORT).show(); 
            } 
        }); 
    } 
} 
    class MyAdapter extends BaseAdapter{ 
        private Context context; 
        private Integer[] imgs = { 
                R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8,  
                R.drawable.pic0, R.drawable.pic1, R.drawable.pic2,  
                R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,                
                R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, 
        }; 
        MyAdapter(Context context){ 
            this.context = context; 
        } 
        public int getCount() { 
            return imgs.length; 
        } 
 
        public Object getItem(int item) { 
            return item; 
        } 
 
        public long getItemId(int id) { 
            return id; 
        } 
         
        //����View���� 
        public View getView(int position, View convertView, ViewGroup parent) { 
             ImageView imageView; 
                if (convertView == null) { 
                    imageView = new ImageView(context); 
                    imageView.setLayoutParams(new GridView.LayoutParams(100, 100));//����ImageView���󲼾� 
                    imageView.setAdjustViewBounds(true);//���ñ߽���� 
                    imageView.setScaleType(ImageView.ScaleType.CENTER);//���ÿ̶ȵ����� 
                    imageView.setPadding(8, 8, 1, 1);//���ü�� 
                }  
                else { 
                    imageView = (ImageView) convertView; 
                } 
                imageView.setImageResource(imgs[position]);//ΪImageView����ͼƬ��Դ 
                return imageView; 
        } 
} 