package utils.testdata;


import com.opencsv.CSVReader;
import com.rapido.api.data.Captain;
import com.rapido.api.utils.ReporterUtil;
import utils.ScenarioContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class
UserData {

    public String getCaptData(String captainType) {
        String captCsvPath = null;
        try {
            captCsvPath = UserData.getPropertyValue("captain.data.file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String captNo = UserData.getUniqueMobileNumber(UserData.getUserDataList(captainType, captCsvPath), "captainNumber");
        ScenarioContext.putData("captainNumber", captNo);
        return captNo;
    }

    public String getCustData(String customerType) {
        String custCsvPath = null;
        try {
            custCsvPath = UserData.getPropertyValue("customer.data.file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String custNo = UserData.getUniqueMobileNumber(UserData.getUserDataList(customerType, custCsvPath), "customerNumber");
        //ScenarioContext.putData("customerNumber", custNo);
        return custNo;
    }

    //This function is to parse the .csv file to list
    public static List<String> getUserDataList(String uType, String path) {
        List<String> list = new ArrayList<>();
        try (CSVReader reader
                     = new CSVReader(new FileReader(path))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (Arrays.toString(nextLine).contains(uType)) {
                    list = Arrays.asList(nextLine);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Captain getCaptainForFEAuto(String mobile) {
        Captain captain = new Captain();
        captain.setMobile(mobile);
        captain.setPassword("rapido12");
        captain.setDeviceId("321543254113233");
        return captain;
    }

    public static String getPropertyValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream inStream = new FileInputStream("testdata.properties");
        properties.load(inStream);
        return properties.getProperty(key);

    }

    public static String getUniqueMobileNumber(List<String> list, String userType) {
        for (String mobileNumber : list) {
            if (list.indexOf(mobileNumber) == 0)
                continue;
            if (createFile(mobileNumber)) {
                ScenarioContext.putData(userType, mobileNumber);
                return mobileNumber;
            } else {
                continue;
            }
        }
        return " ";
    }


    public static boolean createFile(String fileName) {
        try {
            File myObj = new File(fileName + ".txt");
            if (myObj.createNewFile()) {
                ReporterUtil.log("File created: " + myObj.getName());
                return true;
            } else {
                ReporterUtil.log("File already created.");
                return false;
            }
        } catch (IOException e) {
            ReporterUtil.log("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

}

