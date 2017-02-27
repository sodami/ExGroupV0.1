package com.google.slashb410.exgroup.ui.mypage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 객체 모델이 되는 클래스
 */
public class Student implements Parcelable {
    public String name;

    protected Student(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<Student> getCREATOR() {
        return CREATOR;
    }
}

