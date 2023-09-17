package com.vishnu.doorapp.Door;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vishnu.doorapp.Modul.Userprofile;
import com.vishnu.doorapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.Objects;


public class AddFIDoor extends AppCompatActivity {

    CardView getproductImage;
    ImageView getproductImageinImageview;
    static int PICK_IMAGE_REQUEST=123;
    Uri filePath;
    Button save;
    String name,price,desc;
    EditText productname,productprice,productdesc;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private String ImageAccesToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fidoor);
        productname=findViewById(R.id.getproductname);
        productprice=findViewById(R.id.getproductprice);
        productdesc=findViewById(R.id.getproductdesc);
        getproductImage=findViewById(R.id.getuserimage);
        getproductImageinImageview=findViewById(R.id.getuserimageinimageview);
        save=findViewById(R.id.saveprofile);
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
        mAuth = FirebaseAuth.getInstance();





        getproductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filePath != null) {
                    pd.show();

                    StorageReference childRef = storageRef.child("image.jpg").child(filePath.getEncodedPath());
                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(filePath);
                    Log.e("FilepAth",filePath.toString());

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ////We get Image URl
                            childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ImageAccesToken=uri.toString();
                                    Toast.makeText(getApplicationContext(), "Uri Get Access", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Uri Not Access", Toast.LENGTH_SHORT).show();
                                }
                            });
                            ///Sending Data To Relatime Database
                            SendDataToRelatime(ImageAccesToken);
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SendDataToRelatime(String imageAccesToken) {

        name=productname.getText().toString();
        price=productprice.getText().toString().trim();
        desc=productdesc.getText().toString().trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("https://door-app-2f391-default-rtdb.firebaseio.com/product");
        Userprofile userprofile=new Userprofile(name,imageAccesToken,price,desc);
        myRef.setValue(userprofile);
        Toast.makeText(getApplicationContext(), "Data Add to Relatime Database", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                getproductImageinImageview.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}