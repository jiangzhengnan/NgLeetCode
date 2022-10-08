package com.ng.code.设计模式.行为型.中介模式;

import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.view.KeyEventDispatcher;
import org.w3c.dom.Text;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/10/06
 * @description :
 * 概念:
 * 中介模式定义了一个单独的（中介）对象，来封装一组对象之间的交互。将这组对象之间的交互委派给与中介对象交互，
 * 来避免对象之间的直接交互。
 * 中介模式的设计思想跟中间层很像，通过引入中介这个中间层，将一组对象之间的交互关系（或者说依赖关系）从多对多（网状关系）
 * 转换为一对多（星状关系）。原来一个对象要跟n个对象交互，现在只需要跟一个中介对象交互，从而最小化对象之间的交互关系，
 * 降低了代码的复杂度，提高了代码的可读性和可维护性。
 * 示例:
 * 假设我们有一个比较复杂的对话框，对话框中有很多控件，比如按钮、文本框、下拉框等。当我们对某个控件进行操作的时候，
 * 其他控件会做出相应的反应，比如，我们在下拉框中选择“注册”，注册相关的控件就会显示在对话框中。
 * 如果我们在下拉框中选择“登陆”，登陆相关的控件就会显示在对话框中。
 */
public class 中介模式 {


    public interface Mediator {
        void handleEvent(Object component, String event);
    }

    static class LandingPageDialog implements Mediator {
        private Button loginButton;
        private Button regButton;
        private Selection selection;
        private EditText usernameInput;
        private EditText passwordInput;
        private EditText repeatedPswdInput;
        private Text hintText;

        @Override
        public void handleEvent(Object component, String event) {
            if (component.equals(loginButton)) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                //校验数据...
                //做业务处理...
            } else if (component.equals(regButton)) {
                //获取usernameInput、passwordInput、repeatedPswdInput数据...
                //校验数据...
                //做业务处理...
            } else if (component.equals(selection)) {
                String selectedItem = selection.toString();
                if (selectedItem.equals("login")) {
                    //usernameInput.show();
                    //passwordInput.show();
                    //repeatedPswdInput.hide();
                    //hintText.hide();
                    //...省略其他代码
                } else if (selectedItem.equals("register")) {
                    //....
                }
            }
        }

    }

    static class UIControl {
        private static final String LOGIN_BTN_ID = "login_btn";
        private static final String REG_BTN_ID = "reg_btn";
        private static final String USERNAME_INPUT_ID = "username_input";
        private static final String PASSWORD_INPUT_ID = "pswd_input";
        private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
        private static final String HINT_TEXT_ID = "hint_text";
        private static final String SELECTION_ID = "selection";

        static Object findViewById(String id) {
            return null;
        }

        public static void main(String[] args) {
            Button loginButton = (Button) findViewById(LOGIN_BTN_ID);
            Button regButton = (Button) findViewById(REG_BTN_ID);
            EditText usernameInput = (EditText) findViewById(USERNAME_INPUT_ID);
            EditText passwordInput = (EditText) findViewById(PASSWORD_INPUT_ID);
            EditText repeatedPswdInput = (EditText) findViewById(REPEATED_PASSWORD_INPUT_ID);
            Text hintText = (Text) findViewById(HINT_TEXT_ID);
            Selection selection = (Selection) findViewById(SELECTION_ID);

            Mediator dialog = new LandingPageDialog();
            //dialog.setLoginButton(loginButton);
            //dialog.setRegButton(regButton);
            //dialog.setUsernameInput(usernameInput);
            //dialog.setPasswordInput(passwordInput);
            //dialog.setRepeatedPswdInput(repeatedPswdInput);
            //dialog.setHintText(hintText);
            //dialog.setSelection(selection);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.handleEvent(loginButton, "click");
                }
            });

            regButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.handleEvent(regButton, "click");
                }
            });

            //....
        }
    }

    public static void main(String[] args) {

    }

}
