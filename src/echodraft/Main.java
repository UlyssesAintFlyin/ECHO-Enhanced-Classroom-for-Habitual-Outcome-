/*
The source code belongs to Manalad, Ivan, 
Bulacan e University Bustos Campus,
Bachelor of Science in Information Technology Major in Web and Mobile Development
*/
package echodraft;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new AppStarter());
    }
}

class AppStarter implements Runnable {
    @Override
    public void run() {
        MainFrame frame = new MainFrame();
        frame.showCard("setup");
    }
}
