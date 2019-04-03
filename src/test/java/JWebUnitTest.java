package uk.uoa.cs.princSwEng.test;


import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTestingEngineKey;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.util.TestingEngineRegistry;


public class JWebUnitTest {

    
    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); 
        setBaseUrl("http://localhost:8080/Echo-Team/");
    }

    @Test
    public void foo() {

    }
    
    @Test
    public void testIndexPage() {
        beginAt("http://localhost:8080/Echo-Team-0.1/"); 
        assertTitleEquals("MetaTranslate");
        
        /*
        assertLinkPresent("manager");
        clickLink("manager");
        assertTitleEquals("Create Survey");
        System.out.println("testIndexPage done!");
        */
        
    }
    /*
    
    @Test
    public void testManagerPage() {
        beginAt("http://localhost:8080/Echo-Team/manager"); 
        assertTitleEquals("Create Survey");
        assertLinkPresent("index");
        clickLink("index");
        assertTitleEquals("Metatranslate");
    }
    */
}