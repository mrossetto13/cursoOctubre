package Examen;

import org.testng.annotations.Test;

public class DataProvider {
    @org.testng.annotations.DataProvider(name="logIn")
    public Object[][] cuentas(){
        return new Object[][] {
                {"Rossetto1@gmail.com", "holamundo"},
                {"Rossetto2@gmail.com", "holamundo"},
                {"Rossetto3@gmail.com", "holamundo"}
        };
    }

    @Test(dataProvider = "logIn")
    public void mostrarInfoTest(String mail, String password){
        System.out.println("Mail: " + mail + " Password: "+ password);
    }
}
