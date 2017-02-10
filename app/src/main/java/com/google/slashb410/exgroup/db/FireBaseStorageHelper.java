package com.google.slashb410.exgroup.db;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Created by Tacademy on 2017-02-09.
 */
public class FireBaseStorageHelper {
    private static FireBaseStorageHelper ourInstance = new FireBaseStorageHelper();

    public static FireBaseStorageHelper getInstance() {
        return ourInstance;
    }

    private FireBaseStorageHelper() {

    }


    public void getImage(Context context, String imgName, ImageView imageView){

    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReferenceFromUrl("gs://exgroup-1faeb.appspot.com");
    StorageReference imagesRef = storageRef.child("dumyuser/group"+imgName);

        //imagesRef.getPath();

        imagesRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {

                Picasso.with(context)
                .load(storageMetadata.getDownloadUrl())
                        .into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
          //      U.getInstance().popSimpleDialog(null, );
            }
        });

    }
}
