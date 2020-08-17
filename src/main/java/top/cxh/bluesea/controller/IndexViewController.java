package top.cxh.bluesea.controller;

import java.awt.MouseInfo;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import top.cxh.bluesea.BlueSeaClientApplication;
import top.cxh.bluesea.view.IndexView;
import top.cxh.bluesea.view.LoginView;

@FXMLController
public class IndexViewController implements Initializable {
	
	private Stage stage;
	
	Scene scene;
	
	@Autowired
	private IndexView indexView;

	/**
	 * 显示我的名字
	 */
	@FXML
	private Label showMyName;

	/**
	 * 显示我的账号
	 */
	@FXML
	private Label showMyAccount;

	/**
	 * 聊天框上好友名字
	 */
	@FXML
	private Label showFriendName;
	
	/**
	 * 搜索输入框
	 */
	@FXML
	private TextField textSearch;
	
	/**
	 * 我的头像
	 */
	@FXML
	private ImageView myImage;

	/**
	 * 消息输入框
	 */
	@FXML
	private TextArea textMsg;
	
	/**
	 * 左侧放置好友
	 */
	@FXML
    private VBox friendVBox;
	
	/**
	 * 左侧放置好友的
	 */
	@FXML
	private ScrollPane friendSCP;
	
	/**
	 * 右侧放置聊天消息
	 */
	@FXML
    private VBox msgVBox;
	
	/**
	 * 右侧放置聊天消息的
	 */
	@FXML
    private ScrollPane msgSCP;
	
	/**
	 * 发送按钮
	 */
	@FXML
	private Button btnSend;
	
	/**
	 * 表情
	 */
	@FXML
    private ImageView emoj;

	/**
	 * 消息面板滚动条是否刷新
	 */
    private boolean updateFlag = false;


    /**
     * 发送按钮事件
     * @param event
     */
	@FXML
	void actionSendMsg(ActionEvent event) {
		sendMsg();
	}
	
	/**
	 * 表情点击事件
	 * @param event
	 */
	@FXML
    void emojClickAction(MouseEvent event) {
		System.out.println("表情");
	}

	/**
	 * 头像点击事件
	 * @param event
	 */
	@FXML
	void mouseClickMyImage(MouseEvent event) {
		
		DialogPane dp = new DialogPane();
		
		dp.setPrefWidth(200);
		dp.setPrefHeight(230);
		
		BorderPane bp = new BorderPane();
		
		ImageView myInfo = new ImageView(new Image("images/acccount.png"));
		
		myInfo.setFitWidth(200);
		myInfo.setFitHeight(200);
		
		bp.setCenter(myInfo);
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefWidth(200);
		ap.setPrefHeight(50);
		ap.setStyle("-fx-background-color:#ffffff");
		
		ImageView icon1 = new ImageView(new Image("images/addfriend.png"));
		icon1.setFitWidth(20);
		icon1.setFitHeight(20);
		
		ImageView icon2 = new ImageView(new Image("images/exit.png"));
		icon2.setFitWidth(20);
		icon2.setFitHeight(20);
		
		Button button1 = new Button();
        button1.setGraphic(icon1);
        button1.setStyle("-fx-background-color:#ffffff");
        
        Button button2 = new Button();
        button2.setGraphic(icon2);
        button2.setStyle("-fx-background-color:#ffffff");
		
		ap.getChildren().addAll(button1,button2);
		
		AnchorPane.setRightAnchor(button1, 5.0);
		AnchorPane.setTopAnchor(button1, 5.0);
		
		AnchorPane.setRightAnchor(button2, 40.0);
		AnchorPane.setTopAnchor(button2, 5.0);
		
		bp.setBottom(ap);
		
		dp.getChildren().add(bp);
		
		Stage s = new Stage();
		s.getIcons().add(new Image("images/bluesea.gif"));
		s.setResizable(false);
		
		Scene sc = new Scene(dp);
		
		s.setScene(sc);
		
		//设置所有者
		s.initOwner(stage);
		s.initModality(Modality.WINDOW_MODAL);
		s.setX(MouseInfo.getPointerInfo().getLocation().x);
		s.setY(MouseInfo.getPointerInfo().getLocation().y);
		s.show();
		
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				addFriendWindown();
			}
		});
		
		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exitLogin(s);
			}
		});
		
		button1.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				button1.setCursor(Cursor.HAND);
			}
		});
		button2.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				button2.setCursor(Cursor.HAND);
			}
		});
		
	}
	
	/**
	 * 初始化
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		init();
		
		String[] imgs = {"u=1437473548,3272300633&fm=26&gp=0.jpg","timg.jpg","timg111.jpg","test111.jpg"};
		
		for(int i = 1; i <= 20; i ++ ) {
			int count = (int) (Math.random() * 100);
			AnchorPane an = new AnchorPane();
			an.setPrefHeight(60);
			ImageView iv = new ImageView(new Image("touxiang/" + imgs[count % 4]));
			iv.setFitWidth(40);
			iv.setFitHeight(40);
			Label l1 = new Label("名字" + i);
			l1.setFont(new Font(15));
			l1.setMaxWidth(160);
			Label l2 = new Label("今天天气不错今天天气不错今天天气不错今天天气不错今天天气不错");
			l2.setFont(new Font(12));
			l2.setMaxWidth(160);
			l2.setTextFill(Paint.valueOf("#aba6a6"));
			
			an.getChildren().addAll(iv,l1,l2);
			
			AnchorPane.setLeftAnchor(iv, 34.0);
			AnchorPane.setTopAnchor(iv, 10.0);
			
			AnchorPane.setLeftAnchor(l1, 91.0);
			AnchorPane.setTopAnchor(l1, 10.0);
			
			AnchorPane.setLeftAnchor(l2, 91.0);
			AnchorPane.setTopAnchor(l2, 36.0);
			
			friendVBox.getChildren().add(an);
			
			an.setOnMouseMoved(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					an.setCursor(Cursor.HAND);
				}
			});
			an.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					showFriendName.setText(l1.getText());
				}
			});
			
		}
		
		textMsg.setFont(new Font(14));
		
		msgSCP.setVvalue(1.0);
		
		msgSCP.vvalueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(updateFlag) {
					msgSCP.setVvalue(1.0);
					updateFlag = false;
				}
			}
		});
		
		myImage.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				myImage.setCursor(Cursor.HAND);
			}
		});
		btnSend.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnSend.setCursor(Cursor.HAND);
			}
		});
		emoj.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				emoj.setCursor(Cursor.HAND);
			}
		});
		textMsg.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        if(keyEvent.getCode() == KeyCode.ENTER) {
		        	sendMsg();
		        }
		    }
		});
		
		textMsg.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
		        	textMsg.clear();
		        }
				
			}
		});
		
	}
	void init() {
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	Parent parent = indexView.getView();
            	scene =  parent.getScene();
            	IndexViewController.this.stage = (Stage) parent.getScene().getWindow();
        		stage.setTitle("BlueSea");
        		stage.setResizable(false);
            }
        });
	}
	
	/**
	 * 发送消息
	 */
	void sendMsg() {
		System.out.println("发送消息：" + textMsg.getText());
		showSendMsg(textMsg.getText());
		showRecvMsg(textMsg.getText());
		updateFlag = true;
		msgSCP.setVvalue(1);
		this.textMsg.clear();
	}
	/**
	 * 发消息
	 * @param msg
	 */
	void showSendMsg(String msg) {
		AnchorPane an = new AnchorPane();
		an.setPrefWidth(590);
		ImageView iv = new ImageView(new Image("touxiang/u=1437473548,3272300633&fm=26&gp=0.jpg"));
		iv.setFitWidth(40);
		iv.setFitHeight(40);
		
		Label l1 = new Label("我的名字");
		l1.setFont(new Font(13));
		l1.setTextAlignment(TextAlignment.RIGHT);
		l1.setMaxWidth(400);
		
		
		Label l2 = new Label(msg);
		l2.setFont(new Font(18));
		l2.setStyle("-fx-background-color: #ffffff");
		l2.setPadding(new Insets(5, 10, 5, 10));
		l2.setWrapText(true);
		l2.setMaxWidth(400);
		
		an.getChildren().addAll(iv,l1,l2);
		
		AnchorPane.setLeftAnchor(iv, 537.0);
		AnchorPane.setTopAnchor(iv, 25.0);
		
		AnchorPane.setRightAnchor(l1, 56.0);
		AnchorPane.setTopAnchor(l1, 25.0);
		
		AnchorPane.setRightAnchor(l2, 56.0);
		AnchorPane.setTopAnchor(l2, 55.0);
		
		msgVBox.getChildren().add(an);
	}
	/**
	 * 收消息
	 * @param msg
	 */
	void showRecvMsg(String msg) {
		AnchorPane an = new AnchorPane();
		an.setPrefWidth(590);
		ImageView iv = new ImageView(new Image("touxiang/timg.jpg"));
		iv.setFitWidth(40);
		iv.setFitHeight(40);
		
		Label l1 = new Label("好友名字");
		l1.setFont(new Font(13));
		l1.setMaxWidth(400);
		
		Label l2 = new Label(msg);
		l2.setFont(new Font(18));
		l2.setStyle("-fx-background-color: #55a3ec");
		l2.setPadding(new Insets(5, 10, 5, 10));
		l2.setMaxWidth(400);
		l2.setWrapText(true);
		
		an.getChildren().addAll(iv,l1,l2);
		
		AnchorPane.setLeftAnchor(iv, 22.0);
		AnchorPane.setTopAnchor(iv, 23.0);
		
		AnchorPane.setLeftAnchor(l1, 76.0);
		AnchorPane.setTopAnchor(l1, 23.0);
		
		AnchorPane.setLeftAnchor(l2, 76.0);
		AnchorPane.setTopAnchor(l2, 51.0);
		
		msgVBox.getChildren().add(an);
	}
	
	/**
	 * 退出登录
	 * @param s
	 */
	void exitLogin(Stage s) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("提示");
		alert.setHeaderText("确定要退出登录吗?");
		
		Stage alertS = (Stage) alert.getDialogPane().getScene().getWindow();
		alertS.getIcons().add(new Image("images/bluesea.gif"));
		 
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			if(s != null) {
				s.close();
			}
			BlueSeaClientApplication.showView(LoginView.class);
		}
		alert.close();
	}
	
	/**
	 * 添加好友
	 */
	void addFriendWindown() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("添加好友");
		
		dialog.setWidth(400);
		dialog.setHeight(200);
		
		Stage alertS = (Stage) dialog.getDialogPane().getScene().getWindow();
		alertS.getIcons().add(new Image("images/bluesea.gif"));
		 
		// Set the button types.
		ButtonType addFriendBtn = new ButtonType("添加", ButtonData.APPLY);
		dialog.getDialogPane().getButtonTypes().addAll(addFriendBtn, ButtonType.CANCEL);
		
		BorderPane bp = new BorderPane();
		bp.setPrefWidth(400);
		bp.setPrefHeight(200);
		
		AnchorPane ap1 = new AnchorPane();
		ap1.setPrefWidth(400);
		ap1.setPrefHeight(80);
		
		TextField searchKey = new TextField();
		searchKey.setPromptText("请输入账号或邮箱");
		searchKey.setPrefHeight(30);
		
		ap1.getChildren().add(searchKey);
		
		AnchorPane.setTopAnchor(searchKey, 15.0);
		AnchorPane.setLeftAnchor(searchKey, 15.0);
		AnchorPane.setRightAnchor(searchKey, 15.0);
		
		AnchorPane ap2 = new AnchorPane();
		ap2.setPrefWidth(400);
		ap2.setPrefHeight(80);
		
		Node addFriendBtnN = dialog.getDialogPane().lookupButton(addFriendBtn);
		addFriendBtnN.setDisable(true);
		
		searchKey.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        if(keyEvent.getCode() == KeyCode.ENTER) {
		        	int type = 2;
		        	if(searchKey.getText().equals("111")) {
		        		type = 1;
		        	}
		        	if(type == 1) {
		        		ap2.getChildren().clear();
		    			ImageView searchImg = new ImageView(new Image("touxiang/timg.jpg"));
		    			searchImg.setFitWidth(50);
		    			searchImg.setFitHeight(50);
		    			
		    			Label l1 = new Label("名字");
		    			l1.setFont(new Font(18));
		    			Label l2 = new Label("账号:");
		    			l2.setFont(new Font(13));
		    			Label l3 = new Label("cxhquijngjkkl");
		    			l3.setFont(new Font(13));
		    			
		    			ap2.getChildren().addAll(searchImg,l1,l2,l3);
		    			
		    			AnchorPane.setLeftAnchor(searchImg, 30.0);
		    			AnchorPane.setTopAnchor(searchImg, 15.0);
		    			
		    			AnchorPane.setLeftAnchor(l1, 97.0);
		    			AnchorPane.setTopAnchor(l1, 15.0);
		    			
		    			AnchorPane.setLeftAnchor(l2, 97.0);
		    			AnchorPane.setTopAnchor(l2, 46.0);
		    			
		    			AnchorPane.setLeftAnchor(l3, 134.0);
		    			AnchorPane.setTopAnchor(l3, 46.0);
		    			
		    			addFriendBtnN.setDisable(false);
		    		}else {
		    			ap2.getChildren().clear();
		    			ImageView searchImg = new ImageView(new Image("images/nofriend.png"));
		    			searchImg.setFitWidth(50);
		    			searchImg.setFitHeight(50);
		    			Label l1 = new Label("没有该用户！");
		    			l1.setFont(new Font(18));
		    			ap2.getChildren().addAll(searchImg,l1);
		    			AnchorPane.setLeftAnchor(searchImg, 30.0);
		    			AnchorPane.setTopAnchor(searchImg, 15.0);
		    			
		    			AnchorPane.setLeftAnchor(l1, 97.0);
		    			AnchorPane.setTopAnchor(l1, 30.0);
		    		}
		        }
		    }
		});
		
		
		bp.setTop(ap1);
		bp.setCenter(ap2);
		
		 
		dialog.getDialogPane().setContent(bp);
		 
		// Request focus on the username field by default.
		Platform.runLater(() -> searchKey.requestFocus());
		 
		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if(dialogButton == addFriendBtn) {
		        System.out.println("添加");
		    }
		    return "str";
		});
		 
		dialog.showAndWait();
		 
		/*result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.get + ", Password=" + usernamePassword.getValue());
		});*/
	}


}
