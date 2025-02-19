package hk.edu.polyu.comp.comp2021.cvfs.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CVFSTest {

    @Test
    public void testCVFSConstructor() {
        CVFS cvfs = new CVFS();
        assertNotNull(cvfs);
    }

    @Test
    public void testNewDisk() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);


        assertEquals(500, cvfs.maxSize);
        assertEquals(0, cvfs.curSize);
    }

    @Test
    public void testNewDir() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newDir("testDir");


        assertEquals(1, cvfs.curDir.getFiles().size());
        assertTrue(cvfs.curDir.getFiles().get(0) instanceof CVFS.Directory);
        assertEquals("testDir", ((CVFS.Directory) cvfs.curDir.getFiles().get(0)).getName());
    }

    @Test
    public void testNewDoc() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newDoc("testDoc", "txt", "This is a test document.");


        assertEquals(1, cvfs.curDir.getFiles().size());
        assertTrue(cvfs.curDir.getFiles().get(0) instanceof CVFS.Document);
        CVFS.Document doc = (CVFS.Document) cvfs.curDir.getFiles().get(0);
        assertEquals("testDoc", doc.getName());
        assertEquals("txt", doc.getType());
        assertEquals("This is a test document.", doc.content);
    }

    @Test
    public void testDelete() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newDoc("testDoc", "txt", "This is a test document.");
        cvfs.delete("testDoc");

        assertEquals(0, cvfs.curDir.getFiles().size());
    }

    @Test
    public void testRename() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newDoc("testDoc", "txt", "This is a test document.");
        cvfs.rename("testDoc", "renamedDoc");

        CVFS.Document doc = (CVFS.Document) cvfs.curDir.getFiles().get(0);
        assertEquals("renamedDoc", doc.getName());
    }

    @Test
    public void testNewSimpleCriterion() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newSimpleCri("ab", "name", "contains", "Doc");


        assertEquals(2, cvfs.crit.size());
        CVFS.Criterion criterion = cvfs.crit.get(1);
        assertEquals("ab", criterion.getCriName());
    }

    @Test
    public void testNewNegationCriterion() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newSimpleCri("ab", "name", "contains", "Doc");
        cvfs.newNegation("ac", "ab");


        assertEquals(3, cvfs.crit.size());
        CVFS.Criterion negation = cvfs.crit.get(2);
        assertTrue(negation instanceof CVFS.NegationCriterion);
        assertEquals("ac", negation.getCriName());
    }

    @Test
    public void testNewBinaryCriterion() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newSimpleCri("ab", "name", "contains", "Doc");
        cvfs.newSimpleCri("ac", "type", "equals", "txt");
        cvfs.newBinaryCri("ad", "ab", "&&", "ac");

        assertEquals(4, cvfs.crit.size());
        CVFS.Criterion binary = cvfs.crit.get(3);
        assertTrue(binary instanceof CVFS.BinaryCriterion);
        assertEquals("ad", binary.getCriName());
    }

    @Test
    public void testSearch() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);
        cvfs.newDoc("testDoc", "txt", "This is a test document.");
        cvfs.newSimpleCri("nameContainsDoc", "name", "contains", "Doc");

        cvfs.search("nameContainsDoc");
    }

    @Test
    public void testPrintAllCriteria() {
        CVFS cvfs = new CVFS();
        cvfs.newDisk(500);

        cvfs.newSimpleCri("ab", "name", "contains", "Doc");
        cvfs.newNegation("ac", "ab");
        cvfs.newBinaryCri("ad", "ab", "&&", "ac");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cvfs.printAllCriteria();

        String output = outContent.toString();
        assertTrue(output.contains("ab:   name contains Doc"));
        assertTrue(output.contains("ac:    ~ab"));
        assertTrue(output.contains("ad: (ab && ac)"));


        System.setOut(System.out);
    }

}