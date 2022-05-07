import javax.swing.*;
import java.awt.*;

public class NewMacroWindow {
    NewMacroWindow() {
        this.show();
    }

    public void show(){
        int windowWidth = 400;
        int windowHeight = 400;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame f = new JFrame();

//        Container container = new Container();
//        GroupLayout layout = new GroupLayout(container);

        JButton b1 = new JButton("Record");
        JButton b2 = new JButton("Play Recording");
        b1.setBounds(100,140,100,40);
//        b2.setBounds(100,150,100,40);

//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                        .addComponent(b1)
//                        .addComponent(b2)
//        );
//        container.setLayout(layout); // For layout managers
        f.add(b1);
        f.setTitle("New Macro");
        f.setSize(windowWidth,windowHeight);
        Point p = new Point((screenSize.width - windowWidth) / 2,
                (screenSize.height - windowHeight) / 2);
        f.setLocation(p);
        f.setLayout(null);
        f.setVisible(true); // make the frame visible
    }
    // TODO: Program still running in background after closing window. May need to set an Event to terminate the
    //  process on close
}
