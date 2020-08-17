package top.cxh.bluesea;

import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Parent;

/**
 * 启动动画
 * @author cxhqz
 *
 */
public class CustomSplashScreen extends SplashScreen {

	

	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	@Override
	public boolean visible() {
		// TODO Auto-generated method stub
		return super.visible();
	}

	@Override
	public String getImagePath() {
		return "/images/welcome.gif";
	}
	
}
