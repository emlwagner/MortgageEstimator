package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EstimatorController {
	
	@FXML
	TextField GrossIncome;
	@FXML
	TextField MonthlyDebt;
	@FXML
	TextField InterestRate;
	@FXML
	TextField DownPayment;
	@FXML
	ComboBox<Integer> Term;
	ObservableList<Integer> TermData = FXCollections.observableArrayList();
	@FXML
	Button Calculate;
	Alert alert = new Alert(AlertType.INFORMATION);
	
	
	public EstimatorController() {
		TermData.add(10);
		TermData.add(15);
		TermData.add(30);
	}
	
	@FXML
	private void initialize() {
		Term.setItems(TermData);
	}
	
	@FXML
	private void HandleCalculate() {
		double grossIncome = Double.parseDouble(GrossIncome.getText());
		double housingOnly = (grossIncome / 12) * 0.18;
		double housingPlus = ((grossIncome / 12) * 0.36) - Double.parseDouble(MonthlyDebt.getText());
		double maxPayment = Math.min(housingOnly, housingPlus);
		double monthlyInterest = (Double.parseDouble(InterestRate.getText()) / 100) / 12;
		double numPayments = Term.getValue() * 12;
		
		double affordable = (maxPayment * (Math.pow(1 + monthlyInterest, numPayments) - 1) / (monthlyInterest * Math.pow(1+ monthlyInterest, numPayments)));
		
		//String stringAffordable = String.format("$%d", Double.toString(affordable));
		
		alert.setTitle("Mortgage");
		alert.setHeaderText("You can afford: ");
		alert.setContentText("$" + Double.toString(affordable));

		alert.showAndWait();
	}
	

}
