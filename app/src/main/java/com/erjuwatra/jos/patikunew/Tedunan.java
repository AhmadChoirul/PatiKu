package com.erjuwatra.jos.patikunew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class Tedunan extends AppCompatActivity {
    GridView gridView;
    TextView txt;
    String google_map = "com.google.android.apps.maps";
    Uri gmmIntentUri;
    Intent mapIntent;
    String map ="-6.6445736,110.9522044";
    String[] names = {"image1", "image2","image3", "image4", "image5", "image6", "image7"};
    String[] images = {"https://i.ibb.co/mXTtxMc/IMG-20201007-084304-tigr.jpg",
            "https://i.ibb.co/GsGTrLt/IMG-20201007-084326-tigr.jpg",
            "https://i.ibb.co/tMFWS8p/IMG-20201007-084408-tigr.jpg",
            "https://i.ibb.co/Mpfp3h4/IMG-20201007-084811-tigr.jpg",
            "https://i.ibb.co/LzhBr4Y/IMG-20201007-084912-tigr.jpg",
            "https://i.ibb.co/k2KJHwc/IMG-20201007-084643-tigr.jpg",
            "https://i.ibb.co/WPBKK3w/IMG-20201007-085425-tigr.jpg",
            "https://i.ibb.co/SXdM2Gn/IMG-20201007-084316-tigr.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tedunan);

        txt = (TextView)findViewById(R.id.tvhehe3);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
                gmmIntentUri = Uri.parse("google.navigation:q=" + map);

                // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                mapIntent.setPackage(google_map);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(Tedunan.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        //and another using third party library which is easy to use let's get started
        //gunakan library yang sssudah ditambahkan di gradle
        ImageSlider imageSlider=findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://i.ibb.co/mXTtxMc/IMG-20201007-084304-tigr.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/GsGTrLt/IMG-20201007-084326-tigr.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/tMFWS8p/IMG-20201007-084408-tigr.jpg"));
        slideModels.add(new SlideModel("https://i.ibb.co/Mpfp3h4/IMG-20201007-084811-tigr.jpg"));
        imageSlider.setImageList(slideModels,true);

        //  Glide.with(this).load("https://i.ibb.co/5Ftc84C/argo-njolong.jpg").into(vp1);
        gridView = findViewById(R.id.gridView);
        CustomAdapter customAdapter = new CustomAdapter(names, images, this);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedName = names[i];
                String selectedImage = images[i];

                startActivity(new Intent(Tedunan.this, ClickedItemPancurActivity.class).putExtra("image", selectedImage ));
            }

        });
    }


    public class CustomAdapter extends BaseAdapter {

        //        private String[] imageNames;
        private String[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, String[] imagesPhoto, Context context) {
//            this.imageNames = imageNames;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);

            }

//            TextView tvName = view.findViewById(R.id.tvName);
            ImageView imageView =view.findViewById(R.id.imageView);
//maksih ws yos

//           tvName.setText(imageNames[i]);
            Glide.with(getApplicationContext()).load(imagesPhoto[i]).into(imageView);
            return view;
        }
    }
}