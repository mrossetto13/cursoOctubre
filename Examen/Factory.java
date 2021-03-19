package Examen;

import Clase13.DocusignFactoryTest;

public class Factory {
    @org.testng.annotations.Factory
    public Object[] factoryMethod(){
        return new Object[]{
                new prueba_mailchimp(),
                new prueba_mailchimp()
        };
    }
}

