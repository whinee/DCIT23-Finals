package xyz.whinyaan;

import xyz.whinyaan.Section;

// Crisostomo
public class User {
    public void login() throws Exception
    {
        System.out.println( "Login" );

        Section section = new Section();
        section.selectSection();
    }
}
