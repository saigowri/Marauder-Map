package downloadImages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
public class download {
 
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileLocationSourceDrive = "C:\\Users\\HP\\Pictures\\FaceRecog\\";
 
        String fileLocationDestination = "\\\\ABHIJITH-PC\\Images\\";
        int numberOfFilesToCopy = 2;
        int i = 1;
        while(true) {
        i = i + copyFiles(fileLocationSourceDrive,
                fileLocationDestination,
                numberOfFilesToCopy,i);
        }
    }
 
    public static int copyFiles(String fileLocationSource,
            String fileLocationDestination, int numberOfFilesToCopy, int i) throws InterruptedException {
        File inputLocation = new File(fileLocationSource);
        if (inputLocation.isDirectory()) {
            System.out.println("Listing the files...");
            Thread.sleep(3000);
            File[] attachmentFiles = inputLocation.listFiles();
            System.out.println("Total files in the folder: "
                    + attachmentFiles.length);
            if(attachmentFiles.length > 1)  {
            	int j = 1;
            	Thread.sleep(3000);
            	for (File aFile : attachmentFiles) {
                    if (!aFile.isDirectory()) {
                        String fileName = aFile.getName();
                        if(fileName.contains("crdownload")||fileName.contains("tmp")) {
                        	attachmentFiles[0].delete();
                        	attachmentFiles[1].delete();
                        	return 0;
                        }
                        String sourceFileName = aFile.getAbsolutePath();
                        String destinationFileName = fileLocationDestination
                                + i+"_"+j+".jpg";
                        copyFile(sourceFileName, destinationFileName);
                    }
                    if (numberOfFilesToCopy >= 0) {
                        if (--numberOfFilesToCopy == 0) {
                            break;
                        }
                        j++;
                    }
                }
            	return 1;
            }
            return 0;
        }
        System.out.println("Completed...");
        return 0;
    }
 
    private static void copyFile(String sourceFileName, String destinationFileName) {
        try {
            System.out.println("Reading..." + sourceFileName);
            File sourceFile = new File(sourceFileName);
            File destinationFile = new File(destinationFileName);
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destinationFile);
 
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            sourceFile.delete();
            System.out.println("Dest: " + destinationFileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}