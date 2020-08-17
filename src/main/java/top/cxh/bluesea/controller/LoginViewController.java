package top.cxh.bluesea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import top.cxh.bluesea.BlueSeaClientApplication;
import top.cxh.bluesea.view.IndexView;
import top.cxh.bluesea.view.LoginView;
import top.cxh.bluesea.view.RegisterView;

@FXMLController
public class LoginViewController implements Initializable {
	
	private Stage stage;
	
	@Autowired
	LoginView loginView;
	
	/**
	 * 账号输入框
	 */
	@FXML
    private TextField textAccount;

	/**
	 * 密码输入框
	 */
    @FXML
    private PasswordField textPassword;
    
    /**
     * 注册按钮
     */
    @FXML
    private Button btnRegister;

    /**
     * 登录按钮
     */
    @FXML
    private Button btnLogin;

    /**
     * 登录按钮事件
     * @param event
     */
    @FXML
    void actionLogin(ActionEvent event) {
    	BlueSeaClientApplication.showView(IndexView.class);
    }

    /**
     * 注册按钮事件
     * @param event
     */
    @FXML
    void actionRegister(ActionEvent event) {
    	BlueSeaClientApplication.showView(RegisterView.class);
    }

    /**
     * 初始化
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
		btnLogin.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnLogin.setCursor(Cursor.HAND);
			}
		});
		
		btnRegister.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnRegister.setCursor(Cursor.HAND);
			}
		});
	}
	
	/**
	 * 启动初始化组件
	 */
	void init() {
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Parent parent = loginView.getView();
            	LoginViewController.this.stage = (Stage) parent.getScene().getWindow();
        		stage.setTitle("BlueSea即时通讯登录");
        		stage.setResizable(false);
            }
        });
	}

}
