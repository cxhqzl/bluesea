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
import top.cxh.bluesea.view.LoginView;
import top.cxh.bluesea.view.RegisterView;

@FXMLController
public class RegisterViewController implements Initializable {
	
	private Stage stage;
	
	@Autowired
	RegisterView registerView;
	
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
     * 邮箱输入框
     */
    @FXML
    private TextField textEmail;

    /**
     * 验证码输入框
     */
    @FXML
    private TextField textCode;
    
    /**
     * 返回登录按钮
     */
    @FXML
    private Button btnToLogin;

    /**
     * 注册按钮
     */
    @FXML
    private Button btnRegister;

    /**
     * 检查账号是否可用按钮
     */
    @FXML
    private Button btnCheckAccount;

    /**
     * 获取验证码按钮
     */
    @FXML
    private Button btnGetCode;

    /**
     * 检查账号是否可用按钮事件
     * @param event
     */
    @FXML
    void actionCheckAccount(ActionEvent event) {

    }

    /**
     * 获取验证码按钮事件
     * @param event
     */
    @FXML
    void actionGetCode(ActionEvent event) {

    }

    /**
     * 注册按钮事件
     * @param event
     */
    @FXML
    void actionRegister(ActionEvent event) {

    }

    /**
     * 返回登录按钮事件
     * @param event
     */
    @FXML
    void actionToLogin(ActionEvent event) {
    	stage.setTitle("BlueSea即时通讯登录");
    	BlueSeaClientApplication.showView(LoginView.class);
    }

    /**
     * 初始化
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		init();
		
		btnToLogin.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnToLogin.setCursor(Cursor.HAND);
			}
		});
		btnRegister.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnRegister.setCursor(Cursor.HAND);
			}
		});
		btnCheckAccount.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnCheckAccount.setCursor(Cursor.HAND);
			}
		});
		btnGetCode.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnGetCode.setCursor(Cursor.HAND);
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
            	Parent parent = registerView.getView();
            	RegisterViewController.this.stage = (Stage) parent.getScene().getWindow();
        		stage.setTitle("BlueSea即时通讯注册");
        		stage.setResizable(false);
            }
        });
	}

}
