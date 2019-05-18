package nuevoproducto;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NuevoProductoController extends BorderPane implements Initializable {
    @FXML
    private BorderPane rootInsertProduct;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private Label tituloLabel;

    @FXML
    private TextField nombreProductoTF;

    @FXML
    private RadioButton aspiradoraRB;

    @FXML
    private RadioButton moduloRB;

    @FXML
    private RadioButton otrosRB;

    @FXML
    private TextField precioTF;

    @FXML
    private TextArea descripcionProducto;

    private NuevoProductoModel model;

    private Stage primaryStage;

    public NuevoProductoController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/NuevoProductoView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new NuevoProductoModel();

        descripcionProducto.setWrapText(true);

        imagenProducto.setImage(new Image(getClass().getResource("/add-image.png").toString()));

        // BINDEOS CON EL MODELO
        model.nombreProductoProperty().bindBidirectional(nombreProductoTF.textProperty());
        model.descripcionProductoProperty().bindBidirectional(descripcionProducto.textProperty());
        Bindings.bindBidirectional(precioTF.textProperty(), model.precioProductoProperty(), new NumberStringConverter());

        model.tipoProductoProperty().bind(Bindings.when(aspiradoraRB.selectedProperty()).then("Aspiradora").otherwise(Bindings.when(moduloRB.selectedProperty()).then("Modulo").otherwise("Otros")));

        //PREPARACION DE LOS RADIOBUTTON PARA ELECCION UNITARIA
        ToggleGroup grupo = new ToggleGroup();
        aspiradoraRB.setToggleGroup(grupo);
        moduloRB.setToggleGroup(grupo);
        otrosRB.setToggleGroup(grupo);
        otrosRB.setSelected(true);


        model.listoParaInsertarProperty().bind(Bindings.when
                (model.nombreProductoProperty().isEmpty()
                        .or(model.direccionImagenProperty().isEmpty()))
                .then(true)
                .otherwise(false));

        // TODO BORRAR!
        model.aModificarProperty().addListener(e -> {/*
            System.out.println("URL: " + model.getDireccionImagen());
            System.out.println("NombreP: " + model.getNombreProducto());
            System.out.println("Descripcion: " + model.getDescripcionProducto());
            System.out.println("Tipo Producto: " + model.getTipoProducto());*/

            System.out.println(model.isaModificar());
        });

        precioTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    precioTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        imagenProducto.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onImagenPulsadaAction(primaryStage);
                event.consume();
            }
        });
    }

    private void onImagenPulsadaAction(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagen JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("Imagen JPEG", "*.JPEG"),
                new FileChooser.ExtensionFilter("Imagen PNG", "*.PNG"),
                new FileChooser.ExtensionFilter("Todos los Documentos", "*.*"));
        File foto = chooser.showOpenDialog(stage);

        model.setDireccionImagen(foto.toURI().toString());
        imagenProducto.setImage(new Image(model.getDireccionImagen()));
    }

    public RadioButton getAspiradoraRB() {
        return aspiradoraRB;
    }

    public RadioButton getModuloRB() {
        return moduloRB;
    }

    public RadioButton getOtrosRB() {
        return otrosRB;
    }

    public NuevoProductoModel getModel() {
        return model;
    }

    public ImageView getImagenProducto() {
        return imagenProducto;
    }

    public String getTipoProducto() {
        return model.getTipoProducto();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof NuevoProductoController))
            return false;
        NuevoProductoController prod = (NuevoProductoController) obj;
        if (prod.getModel().getCodArticulo() == this.getModel().getCodArticulo())
            return true;
        return false;
    }
}
