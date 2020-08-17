package top.cxh.bluesea;

import java.net.SocketException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import top.cxh.bluesea.view.LoginView;


@SpringBootApplication
public class BlueSeaClientApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) throws SocketException {
		launch(BlueSeaClientApplication.class, LoginView.class, new CustomSplashScreen(), args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		super.start(stage);
	}

	@Override
	public Collection<Image> loadDefaultIcons() {
		return Arrays.asList(new Image(this.getClass().getClassLoader().getResource("images/bluesea.gif").toExternalForm()));
	}

}
