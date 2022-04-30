package ec.com.android.comercio.type;

import android.os.Parcel;
import android.os.Parcelable;

public class Tienda implements Parcelable {
    private Long id;
    private String codigo;
    private String nombre;
    private String direccion;
    private String correo;
    private String telefono;

    public Tienda() {
    }

    public Tienda(Long id, String codigo, String nombre, String direccion, String correo, String telefono) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    protected Tienda(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        codigo = in.readString();
        nombre = in.readString();
        direccion = in.readString();
        correo = in.readString();
        telefono = in.readString();
    }

    public static final Creator<Tienda> CREATOR = new Creator<Tienda>() {
        @Override
        public Tienda createFromParcel(Parcel in) {
            return new Tienda(in);
        }

        @Override
        public Tienda[] newArray(int size) {
            return new Tienda[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(codigo);
        parcel.writeString(nombre);
        parcel.writeString(direccion);
        parcel.writeString(correo);
        parcel.writeString(telefono);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
