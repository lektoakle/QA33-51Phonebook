package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {


    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"testuser@test.com", "aaA1234#"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader((new FileReader(new File("src/test/resources/testDataUser.csv"))));
        String line = reader.readLine();
        while (line != null) {
            String[] strings = line.split(",");
            list.add(new Object[]{User.builder().email(strings[0]).password(strings[1]).build()});
            line = reader.readLine();
        }

        return list.iterator();
    }
}
