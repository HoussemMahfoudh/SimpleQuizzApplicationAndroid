package com.example.projetandroid;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Questions  {

    String question;
    List<String> reponses;
    int positionTrue;

    public Questions(String question, List<String> reponses, int positionTrue) {
        this.question = question;
        this.reponses = reponses;
        this.positionTrue = positionTrue;
    }

    protected Questions(Parcel in) {
        question = in.readString();
        in.readList(reponses, String.class.getClassLoader());
        positionTrue = in.readInt();
    }

}
