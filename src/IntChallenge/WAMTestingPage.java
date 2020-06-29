package IntChallenge;

import org.openqa.selenium.WebDriver;

import java.io.File;

public class WAMTestingPage extends PageObj{


    public WAMTestingPage(WebDriver driver) {

        super(driver);
    }

    public boolean isFileDownloaded(String filename) {

        boolean flag = false;
        String downloadPath = "C:\\Users\\Natalia\\Downloads";
        File folder = new File(downloadPath);
        File[] files = folder.listFiles();
        if (files.length == 0 || files == null) {
            System.out.println("Folder is empty");
            flag = false;
        } else {
            for (File downloadedFile : files) {
                if (downloadedFile.getName().contains(filename)) {
                    System.out.println(filename + " was downloaded");
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

}
