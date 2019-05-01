package nuevoproducto;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

public class NuevoProductoModel {
    private IntegerProperty codArticulo;
    private StringProperty direccionImagen;
    private StringProperty nombreProducto;
    private StringProperty tipoProducto;
    private StringProperty descripcionProducto;
    private DoubleProperty precioProducto;
    private BooleanProperty listoParaInsertar;
    private BooleanProperty aModificar;

    public NuevoProductoModel() {
        codArticulo = new SimpleIntegerProperty(this, "codArticulo");
        direccionImagen = new SimpleStringProperty(this, "direccionImagen");
        nombreProducto = new SimpleStringProperty(this, "nombreProducto");
        tipoProducto = new SimpleStringProperty(this, "tipoProducto");
        descripcionProducto = new SimpleStringProperty(this, "descripcionProducto");
        precioProducto = new SimpleDoubleProperty(this, "precioProducto");
        listoParaInsertar = new SimpleBooleanProperty(this, "listoParaInsertar");
        aModificar = new SimpleBooleanProperty(this, "aModificar", false);

    }

    public boolean isaModificar() {
        return aModificar.get();
    }

    public BooleanProperty aModificarProperty() {
        return aModificar;
    }

    public void setaModificar(boolean aModificar) {
        this.aModificar.set(aModificar);
    }

    public boolean isListoParaInsertar() {
        return listoParaInsertar.get();
    }

    public BooleanProperty listoParaInsertarProperty() {
        return listoParaInsertar;
    }

    public void setListoParaInsertar(boolean listoParaInsertar) {
        this.listoParaInsertar.set(listoParaInsertar);
    }

    public int getCodArticulo() {
        return codArticulo.get();
    }

    public IntegerProperty codArticuloProperty() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo.set(codArticulo);
    }

    public String getDescripcionProducto() {
        return descripcionProducto.get();
    }

    public StringProperty descripcionProductoProperty() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto.set(descripcionProducto);
    }

    public String getDireccionImagen() {
        return direccionImagen.get();
    }

    public StringProperty direccionImagenProperty() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen.set(direccionImagen);
    }

    public String getNombreProducto() {
        return nombreProducto.get();
    }

    public StringProperty nombreProductoProperty() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto.set(nombreProducto);
    }

    public String getTipoProducto() {
        return tipoProducto.get();
    }

    public StringProperty tipoProductoProperty() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto.set(tipoProducto);
    }

    public double getPrecioProducto() {
        return precioProducto.get();
    }

    public DoubleProperty precioProductoProperty() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto.set(precioProducto);
    }

    public StringProperty precioAsString() {
        StringProperty string = new SimpleStringProperty(this, "string", precioProductoProperty().getName());
        string.bind(Bindings.concat(precioProducto, "€"));
        return string;
    }
}
