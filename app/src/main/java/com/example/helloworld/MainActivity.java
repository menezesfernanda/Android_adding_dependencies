package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mylibrary.MyClass;
import com.google.gson.Gson;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    /*to check all the three examples below you can go to your Logcat window after
    running your project and filter for 'System.out' to see the outputs of each example
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /////////////////////////////////////////////////////////////////////////////
        //EXAMPLE 1:                                                               //
        //How to use of Gson dependencies by adding Maven libraries.               //
        //- Go to File>>Project Structure>>Dependencies, click the '+' sign to add //
        //a new one, select the 'Library Dependency'>>type in the search field     //
        //'gson' hit 'Search' and select the result: 'com.google.code.gson'>>OK    //
        // Go back to the Android view to your build.gradle (Module: app) file     //
        // and you should see the dependency:                                      //
        // implementation 'com.google.code.gson:gson:2.8.6' <<!Note:               //
        //                                                     this is the current //
        //                               stable version available that I am using! //
        /////////////////////////////////////////////////////////////////////////////

        Gson gson = new Gson();

        String[] myNumbers = {"one", "two", "three"};

        StringWriter writerJson = new StringWriter();
        gson.toJson(myNumbers, writerJson);

        System.out.print("EXAMPLE 1- output converted to JSON format: ");
        System.out.println(writerJson.toString());


        /////////////////////////////////////////////////////////////////////////////
        //EXAMPLE 2:                                                               //
        //How to use JDom library by adding JAR files with dependencies:           //
        //JDOM jar file downloaded from jdom.org/downloads, then copy/paste file in//
        //the Project/app/libs folder in the Android view, right-click on it and   //
        //chose the "Add as library" option.                                       //
        // Go back to the Android view to your build.gradle (Module: app) file     //
        // and you should see the dependency:                                      //
        //implementation files('libs/jdom-2.0.6.jar') <<!Note:  this is the current//
        //                               available that stable version I am using! //
        /////////////////////////////////////////////////////////////////////////////
        Document doc = new Document(new Element("data"));

        StringWriter writerJdom = new StringWriter();


        try {
            new XMLOutputter().output(doc, writerJdom);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("EXAMPLE 2- output converted to XML format: ");
        System.out.println(writerJdom.toString());

        /////////////////////////////////////////////////////////////////////////////
        //EXAMPLE 3:                                                               //
        //How to create a new Module as a dependency (Java Library):               //
        //- Right-click in your folder app, select New >> Module,                  //
        //- then select Java Library, then give it a name (eg. mylibrary), finish, //
        //- you should see a new folder called mylibrary in your Android view      //
        //- go to your MyClass.java file and add a public method (eg.:             //
        //  public String getString() {                                            //
        //      return "from the module";                                          //
        //  }                                                                      //
        //- Go to File>>Project Structure>>Dependencies, click the '+' sign to add //
        //a new one, select the 'Module Dependency'>>select the 'mylibrary' one.    //
        //That creates a new dependency in your build.gradle file pointing to      //
        //implementation project(path: ':mylibrary')                               //
        /////////////////////////////////////////////////////////////////////////////


        MyClass c = new MyClass();
        System.out.println(c.getString());
    }
}
