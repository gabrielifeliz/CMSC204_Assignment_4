package cmsc204assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This is the user interface to generate 
 * the concordance based on user input
 * @author Gabriel I Feliz
 */
public class ConcordanceGui extends Application {
    
    private TitledPane tp1, tp2;
    private RadioButton rb1, rb2;
    private ToggleGroup rbGroup;
    private TextArea ta;
    private Button bt1, bt2, bt3, bt4, bt5;
    private FileChooser fileChooser;
    private File input, output;
    private HBox hBox1, hBox2, hBox3, hBoxRb;
    private VBox root;
    private ConcordanceDataManager manager;
    
    @Override
    public void start(Stage primaryStage) {
        
        manager = new ConcordanceDataManager();
        
        rbGroup = new ToggleGroup();
        rb1 = new RadioButton("Create Concordance from File");
        rb1.setToggleGroup(rbGroup);
        rb2 = new RadioButton("Create Concordance from Text");
        rb2.setToggleGroup(rbGroup);
        
        hBoxRb = new HBox(rb1, rb2);
        for (Node node : hBoxRb.getChildren())
            HBox.setMargin(node, new Insets(5));
        ta = new TextArea();
        
        tp1 = new TitledPane("Please Select from Following Options", hBoxRb);
        tp1.setCollapsible(false);
        tp1.setPadding(new Insets(20, 10, 5, 10));
        tp2 = new TitledPane("Enter Text", new ScrollPane(ta));
        tp2.setCollapsible(false);
        tp2.setPadding(new Insets(20, 10, 5, 10));
        
        bt1 = new Button("Create Concordance");
        bt1.setOnAction(e -> {
            if (rb1.isSelected()) {
                try {
                    manager.createConcordanceFile(input, output);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Concordance created on output file!");
                    alert.showAndWait();
                } catch (FileNotFoundException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Either the input file or output file "
                            + "could not be found to create concordance");
                    alert.showAndWait();
                }
                
            } else if (rb2.isSelected()) {
                Alert alert = new Alert((!ta.getText().isEmpty()) ? 
                    Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                alert.setHeaderText((!ta.getText().isEmpty()) ? 
                        "Text successfully entered to create concordance!" : 
                        "Text was not entered to create concordance");
                alert.showAndWait();
                if (!ta.getText().isEmpty()) {
                    String concordance = "";
                    for (String element : manager.createConcordanceArray(ta.getText())) {
                        concordance += element + "\n";
                    }
                    ta.setText(concordance);
                }
            } 
        });
        bt2 = new Button("Select Input File");
        bt2.setOnAction(e -> {
            if (rb1.isSelected()) {
                File selectedFile;
                fileChooser = new FileChooser();
                fileChooser.setTitle("Choose an input file to create concordance");
                if ((selectedFile = fileChooser.showOpenDialog(null)) != null) {
                    // Read the file
                    input = selectedFile;
                    Alert alert = new Alert((input != null) ? 
                            Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                    alert.setHeaderText((input != null) ? 
                            "File successfully uploaded." : 
                            "Upload was unsuccessful.\nFile could not be found");
                    alert.showAndWait();
                }
            }
        });
        bt3 = new Button("Select Output File");
        bt3.setOnAction(e -> {
            if (rb1.isSelected()) {
                File selectedFile;
                fileChooser = new FileChooser();
                fileChooser.setTitle("Choose an output file to create concordance");
                if ((selectedFile = fileChooser.showOpenDialog(null)) != null) {
                    // Read the file
                    output = selectedFile;
                    Alert alert = new Alert((output != null) ? 
                            Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                    alert.setHeaderText((output != null) ? 
                            "File successfully uploaded." : 
                            "Upload was unsuccessful.\nFile could not be found");
                    alert.showAndWait();
                }
            }
        });
        bt4 = new Button("Clear");
        bt4.setOnAction(e -> ta.setText(""));
        bt5 = new Button("Exit");
        bt5.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        
        hBox1 = new HBox(tp1);
        hBox2 = new HBox(tp2);
        
        rb1.setOnAction(e -> {
            tp2.setVisible(false);
            bt2.setDisable(false);
            bt3.setDisable(false);
            bt4.setDisable(true);
        });
        rb2.setOnAction(e -> {
            tp2.setVisible(true);
            bt2.setDisable(true);
            bt3.setDisable(true);
            bt4.setDisable(false);
        });
        
        hBox3 = new HBox(bt1, bt2, bt3, bt4, bt5);
        for (Node node : hBox3.getChildren()) 
            HBox.setMargin(node, new Insets(5));
        hBox3.setPadding(new Insets(20, 10, 5, 10));
                
        root = new VBox(hBox1, hBox2, hBox3);
        
        primaryStage.setTitle("Concordance Generator");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
