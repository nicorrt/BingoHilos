module es.iesfranciscodelosrios.Bingo {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.iesfranciscodelosrios.Bingo to javafx.fxml;
    exports es.iesfranciscodelosrios.Bingo;
}
