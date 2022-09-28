package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinaFX extends Application{
	
	private TextField numeroText;
	private Button comprobarButton;
	private Label infoLabel;
	private VBox root;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		numeroText = new TextField();
		
		comprobarButton = new Button("Comprobar");
		comprobarButton.setOnAction(event -> {
			try {
				onComprobarAction(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		comprobarButton.setDefaultButton(true);
		comprobarButton.setTooltip(new Tooltip("Comprueba si has fallado, si has ganado o si hay un error."));
		
		infoLabel = new Label();
		infoLabel.setText("Introduce un número del 1 al 100");
		
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(infoLabel, numeroText, comprobarButton);
		root.setFillWidth(false);
		root.setSpacing(10);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	int numAleat = (int) ((Math.random() * 100)+1);
	int contador = 0;

	public void onComprobarAction(ActionEvent e) throws Exception {
		
		int numIntroducido;
		
		if(numeroText.getText().isBlank() || !numeroText.getText().matches("[0-9]+") || Integer.parseInt(numeroText.getText()) < 1 || Integer.parseInt(numeroText.getText()) > 100) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setHeaderText("ERROR");
			alertError.setContentText("El número introducido no es válido.");
			alertError.showAndWait();
			throw new Exception("Número inválido");
		}
		
		numIntroducido = Integer.parseInt(numeroText.getText());
					
		if(numIntroducido < numAleat) {
			Alert alertMayor = new Alert(AlertType.WARNING);
			alertMayor.setHeaderText("¡Has fallado!");
			alertMayor.setContentText("El número a acertar es mayor que "+numIntroducido+".\n Vuelve a intentarlo.");
			alertMayor.showAndWait();
			contador++;
		}
		
		if(numIntroducido > numAleat) {
			Alert alertMenor = new Alert(AlertType.WARNING);
			alertMenor.setHeaderText("¡Has fallado!");
			alertMenor.setContentText("El número a acertar es menor que "+numIntroducido+".\n Vuelve a intentarlo.");
			alertMenor.showAndWait();
			contador++;
		}
		
		if(numIntroducido == numAleat) {
			Alert alertAcierto = new Alert(AlertType.INFORMATION);
			alertAcierto.setHeaderText("¡Has ganado!");
			alertAcierto.setContentText("Solo has necesitado "+contador+" intentos.\n Vuelve a jugar y hazlo mejor.");
			alertAcierto.showAndWait();
			numAleat = (int) ((Math.random() * 100)+1);
			contador = 0;
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
