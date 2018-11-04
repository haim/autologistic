package com.estafeta.test.ui.helper.awt;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class RobotFileUpload {

  public void robotInsertText(String text) throws AWTException, InterruptedException {

    StringSelection ss = new StringSelection(text);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

    Robot robot = new Robot();
    robot.delay(350);
    robot.keyPress(KeyEvent.VK_ENTER); // TAB for Unix
    robot.keyRelease(KeyEvent.VK_ENTER); // TAB for Unix
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.delay(350);
    robot.keyRelease(KeyEvent.VK_ENTER);
    // robot.delay(1000);
  }
}
