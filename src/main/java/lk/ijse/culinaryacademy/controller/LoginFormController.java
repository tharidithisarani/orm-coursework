package lk.ijse.culinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.entity.User;
import lk.ijse.culinaryacademy.util.Regex;
import lk.ijse.culinaryacademy.util.TextFeid;
import org.hibernate.Session;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane childNode;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink forgotPasswrod;

    @FXML
    private Button login;

    @FXML
    private PasswordField passwrod;

    @FXML
    private Hyperlink register;

    @FXML
    private AnchorPane rootNodeLogin;

    public static User user = null;

    @FXML
    void btnForgotPasswrodOnAction(ActionEvent event) {

        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/UpdatePassword-form.fxml"));
            this.childNode.getChildren().clear();
            this.childNode.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOnLogin(ActionEvent event) {

        if (isValid()) {
            if (checkCredentials()){
                AnchorPane rootNode = null;
                try {
                    rootNode = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/dashBoard-form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) rootNodeLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
            clearfeilds();
        }



    }

    private void clearfeilds() {
        email.clear();
        passwrod.clear();

    }

    private boolean checkCredentials() {
        Session session = null;

        session =SessionFactoryConfig.getInstance().getSession();
        user = session.get(User.class, email.getText());
        if (user != null) {
            if (user.getPassword().equals(passwrod.getText())) {
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                return false;
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }

        }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/culinaryacademy/view/register-form.fxml"));
            this.childNode.getChildren().clear();
            this.childNode.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValid() {
        if (!Regex.setTextColor(TextFeid.Email, email)) {
            return false;
        }
        if (!Regex.setTextColor(TextFeid.password, passwrod)) {
            return false;


        }
        return true;
    }

    public void txtCourseEmailKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.Email, email)) {}
        
    }

    public void txtCoursepasswrodKeyRealeasedOnAction(KeyEvent keyEvent) {
        if (!Regex.setTextColor(TextFeid.password, passwrod)) {}
    }
}
