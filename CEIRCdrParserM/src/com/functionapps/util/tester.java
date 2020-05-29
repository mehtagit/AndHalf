
import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

class tester {

    public static void main(String[] args) {

//        String basePath = "/home/user/Music/testDataFiles/aa";
//
//        File logDir = new File(basePath);
//        File[] logFiles = logDir.listFiles();
//          // System.out.println(logFiles.length);
//        long oldestDate = Long.MAX_VALUE;
//        File oldestFile = null;
//        for (File f : logFiles) {
//            if (f.lastModified() < oldestDate) {
//                oldestDate = f.lastModified();
//                oldestFile = f;
//            }
//        }
//        if (oldestFile != null) {
//             // System.out.println(oldestFile);
//        }n
        String str = "P00MSC02AP420190501235700917387.dat";

      String str1  =  str.substring(0, str.length() - 10)   ;
      String str2  =  str1.substring(str1.length() -14, str1.length() );
      
         // System.out.println("" + str2);
    }

}
