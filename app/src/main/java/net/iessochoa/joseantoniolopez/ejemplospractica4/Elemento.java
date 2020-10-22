package net.iessochoa.joseantoniolopez.ejemplospractica4;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseA on 23/10/2016.
 */

public class Elemento implements Parcelable {
    private int estado;
    private String v1;
    private String v2;
    private String v3;

    public Elemento(int estado,String v1, String v2, String v3) {
        this.estado=estado;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    public Elemento(String v1, String v2, String v3) {
        this.estado=estado;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }
//*********************PARCELABLE************************

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.estado);
        dest.writeString(this.v1);
        dest.writeString(this.v2);
        dest.writeString(this.v3);
    }

    protected Elemento(Parcel in) {
        this.estado = in.readInt();
        this.v1 = in.readString();
        this.v2 = in.readString();
        this.v3 = in.readString();
    }

    public static final Parcelable.Creator<Elemento> CREATOR = new Parcelable.Creator<Elemento>() {
        @Override
        public Elemento createFromParcel(Parcel source) {
            return new Elemento(source);
        }

        @Override
        public Elemento[] newArray(int size) {
            return new Elemento[size];
        }
    };
}
