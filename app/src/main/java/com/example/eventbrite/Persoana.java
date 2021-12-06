package com.example.eventbrite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TabelaEventbrite")
public class Persoana implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "nume")
    private String nume;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "birthDate")
    private String dataNastere;

    public Persoana(String nume, String phone, String email, String dataNastere) {
        this.nume = nume;
        this.phone = phone;
        this.email = email;
        this.dataNastere = dataNastere;
    }

    public Persoana() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Persoana(Parcel in) {
        nume = in.readString();
        phone = in.readString();
        email = in.readString();
        dataNastere = in.readString();
    }

    public static final Parcelable.Creator<Persoana> CREATOR = new Parcelable.Creator<Persoana>() {
        @Override
        public Persoana createFromParcel(Parcel in) {
            return new Persoana(in);
        }

        @Override
        public Persoana[] newArray(int size) {
            return new Persoana[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", telefon='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dataNastere='" + dataNastere + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(dataNastere);
    }
}
